package br.com.stefanini.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.stefanini.models.Equipe;

/**
 * @author sillas
 * @version 0.1.0
 * @email sillas.ap16@gmail.com
 * @created 03/10/2021 on 22:05
 */
@RequestScoped
public class EquipeDao {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public int inserir(Equipe equipe)  {
        String nameQuery = "INSERIR_EQUIPE";
        Query query = em
                .createNamedQuery(nameQuery);

        query.setParameter("nome", equipe.getNome());

        return query.executeUpdate();
    }

    @Transactional
    public int alterar(Equipe equipe)  {
        String nameQuery = "ALTERAR_EQUIPE";
        Query query = em
                .createNamedQuery(nameQuery);

        query.setParameter("id",equipe.getId());
        query.setParameter("nome",equipe.getNome());

        return query.executeUpdate();
    }

    @Transactional
    public int deletar(int id) {
        String nameQuery = "DELETAR_EQUIPE";
        Query query = em
                .createNamedQuery(nameQuery);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    public List<Equipe> listar()  {
        String nameQuery = "LISTAR_EQUIPE";
        TypedQuery<Equipe> query = em
                .createNamedQuery(nameQuery, Equipe.class);
        return query.getResultList();
    }

    public Equipe buscar(int id)  {
        String nameQuery = "BUSCAR_EQUIPE";
        TypedQuery<Equipe> query = em
                .createNamedQuery(nameQuery, Equipe.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}
