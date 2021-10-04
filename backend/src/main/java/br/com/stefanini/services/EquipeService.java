package br.com.stefanini.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import br.com.stefanini.dao.EquipeDao;
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
    public void inserir(Equipe equipeDto) {
        this.validar(equipeDto);
        dao.inserir(equipeDto);
    }

    @Transactional(rollbackOn = Exception.class)
    public void alterar(Equipe equipeDto) {
        dao.alterar(equipeDto);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deletar(int id) {
        dao.deletar(id);
    }

    public List<Equipe> listar() throws ErroNegocialException {
        // throw new ErroNegocialException(EnumMensagens.ERRO_SQL);
       return dao.listar();
    }

    public Equipe buscar(int id) throws ErroNegocialException {
       return dao.buscar(id);
    }

    private void validar(Equipe equipeDto) throws NotFoundException{
        if(equipeDto == null){
            throw new NotFoundException();
        }
        if(equipeDto.getNome() == null){
            throw new NotFoundException();
        }
    }
}
