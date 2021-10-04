package br.com.stefanini.dao;

import br.com.stefanini.models.Pessoa;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author danilo
 * @version 0.1.1
 * @email maratona@stefanini.com
 * @created 21/09/2021 on 07:13
 */
@RequestScoped
public class PessoaDao {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public int inserir(Pessoa pessoa)  {
        String nameQuery = "INSERIR_PESSOA";
        Query query = em
                .createNamedQuery(nameQuery);

        query.setParameter("nome", pessoa.getNome());
        query.setParameter("sobrenome",pessoa.getSobrenome());
        query.setParameter("contato",pessoa.getContato());
        query.setParameter("email", pessoa.getEmail());
        query.setParameter("cargo", pessoa.getCargo());
        if (pessoa.getEquipe() != null) { //TODO: ajustar
            query.setParameter("equipe_id", pessoa.getEquipe().getId());
        }

        return query.executeUpdate();
    }

    @Transactional
    public int alterar(Pessoa pessoa)  {
        String nameQuery = "ALTERAR_PESSOA";
        Query query = em
                .createNamedQuery(nameQuery);

        query.setParameter("id",pessoa.getId());
        query.setParameter("nome",pessoa.getNome());
        query.setParameter("sobrenome",pessoa.getSobrenome());
        query.setParameter("contato",pessoa.getContato());
        query.setParameter("email", pessoa.getEmail());
        query.setParameter("cargo", pessoa.getCargo());
        if (pessoa.getEquipe() != null) { //TODO: ajustar
            query.setParameter("equipe_id", pessoa.getEquipe().getId());
        }

        return query.executeUpdate();
    }

    @Transactional
    public int deletar(int id) {
        String nameQuery = "DELETAR_PESSOA";
        Query query = em
                .createNamedQuery(nameQuery);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    public List<Pessoa> listar()  {
        String nameQuery = "LISTAR_PESSOA";
        TypedQuery<Pessoa> query = em
                .createNamedQuery(nameQuery, Pessoa.class);
        return query.getResultList();
    }

    public Pessoa buscar(int id)  {
        String nameQuery = "BUSCAR_PESSOA";
        TypedQuery<Pessoa> query = em
                .createNamedQuery(nameQuery, Pessoa.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}
