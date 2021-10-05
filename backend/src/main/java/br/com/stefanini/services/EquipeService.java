package br.com.stefanini.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import br.com.stefanini.dao.EquipeDao;
import br.com.stefanini.dto.EquipeDto;
import br.com.stefanini.exceptions.ErroNegocialException;
import br.com.stefanini.models.Equipe;

/**
 * @author sillas
 * @version 0.1.0
 * @email sillas.ap16@gmail.com
 * @created 03/10/2021 on 22:00
 */
@RequestScoped
public class EquipeService {
    @Inject
    EquipeDao dao;

    @Transactional(rollbackOn = Exception.class)
    public void inserir(EquipeDto equipeDto) {
        this.validar(equipeDto);
        dao.inserir(convertDtoToModel(equipeDto));
    }

    @Transactional(rollbackOn = Exception.class)
    public void alterar(EquipeDto equipeDto) {
        dao.alterar(convertDtoToModel(equipeDto));
    }

    @Transactional(rollbackOn = Exception.class)
    public void deletar(int id) {
        dao.deletar(id);
    }

    public List<EquipeDto> listar() throws ErroNegocialException {
        // throw new ErroNegocialException(EnumMensagens.ERRO_SQL);
        return dao.listar().stream()
            .map(e -> convertModelToDto(e))
            .collect(Collectors.toList());
    }

    public EquipeDto buscar(int id) throws ErroNegocialException {
        return convertModelToDto(dao.buscar(id));
    }

    private void validar(EquipeDto equipeDto) throws NotFoundException {
        if (equipeDto == null) {
            throw new NotFoundException();
        }
        if (equipeDto.getNome() == null) {
            throw new NotFoundException();
        }
    }

    private Equipe convertDtoToModel(EquipeDto equipeDto) {
        return new Equipe(equipeDto.getId(), equipeDto.getNome());
    }

    private EquipeDto convertModelToDto(Equipe equipe) {
        return new EquipeDto(equipe.getId(), equipe.getNome());
    }
}
