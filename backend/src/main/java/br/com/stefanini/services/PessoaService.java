package br.com.stefanini.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import br.com.stefanini.dao.PessoaDao;
import br.com.stefanini.dto.EquipeDto;
import br.com.stefanini.dto.PessoaDto;
import br.com.stefanini.exceptions.ErroNegocialException;
import br.com.stefanini.models.Equipe;
import br.com.stefanini.models.Pessoa;

/**
 * @author danilo
 * @version 0.1.1
 * @email maratona@stefanini.com
 * @created 21/09/2021 on 07:11
 */
@RequestScoped
public class PessoaService {

    @Inject
    PessoaDao dao;

    @Transactional(rollbackOn = Exception.class)
    public void inserir(PessoaDto pessoaDto) {
        this.validar(pessoaDto);
        this.validaEmailDuplicado(pessoaDto.getEmail());
        dao.inserir(convertDtoToModel(pessoaDto));
    }

    @Transactional(rollbackOn = Exception.class)
    public void alterar(PessoaDto pessoaDto) {
        dao.alterar(convertDtoToModel(pessoaDto));
    }

    @Transactional(rollbackOn = Exception.class)
    public void deletar(int id) {
        dao.deletar(id);
    }

    public List<PessoaDto> listar() throws ErroNegocialException {
        // throw new ErroNegocialException(EnumMensagens.ERRO_SQL);
        return dao.listar().stream()
            .map(p -> convertModelToDto(p))
            .collect(Collectors.toList());
    }

    public PessoaDto buscar(int id) throws ErroNegocialException {
        return convertModelToDto(dao.buscar(id));
    }

    private void validar(PessoaDto pessoaDto) throws NotFoundException {
        if (pessoaDto == null) {
            throw new NotFoundException();
        }
        if (pessoaDto.getNome() == null || pessoaDto.getSobrenome() == null) {
            throw new NotFoundException();
        }
        if (pessoaDto.getEmail() == null) {
            throw new NotFoundException();
        }
        if (pessoaDto.getContato() == null) {
            throw new NotFoundException();
        }

    }

    private void validaEmailDuplicado(String email) {
        System.out.println(email);
    }

    private Pessoa convertDtoToModel(PessoaDto pessoaDto) {
        return new Pessoa(pessoaDto.getId(), pessoaDto.getNome(), pessoaDto.getSobrenome(), pessoaDto.getContato(),
                pessoaDto.getEmail(), pessoaDto.getCargo(),
                pessoaDto.getEquipe() != null ? new Equipe(pessoaDto.getEquipe().getId(), pessoaDto.getEquipe().getNome()) : null);
    }

    private PessoaDto convertModelToDto(Pessoa pessoa) {
        return new PessoaDto(pessoa.getId(), pessoa.getNome(), pessoa.getSobrenome(), pessoa.getContato(),
                pessoa.getEmail(), pessoa.getCargo(),
                pessoa.getEquipe() != null ? new EquipeDto(pessoa.getEquipe().getId(), pessoa.getEquipe().getNome()): null);
    }
}
