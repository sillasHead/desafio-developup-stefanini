package br.com.stefanini.models;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * @author sillas
 * @version 0.1.0
 * @email sillas.ap16@gmail.com
 * @created 03/10/2021 on 21:59
 */
@Entity
@Table(name="equipe", schema="H2DB")
@NamedNativeQueries({
        @NamedNativeQuery(name="INSERIR_EQUIPE", query = " INSERT into H2DB.equipe (nome) " +
                "VALUES (:nome) "),
        @NamedNativeQuery(name="LISTAR_EQUIPE", query = "select id, nome from H2DB.equipe ", resultClass = Equipe.class),
        @NamedNativeQuery(name="ALTERAR_EQUIPE", query = "update H2DB.equipe " + 
                "set nome = :nome where id = :id"),
        @NamedNativeQuery(name="DELETAR_EQUIPE", query = "delete from H2DB.equipe where id = :id"),
        @NamedNativeQuery(name="BUSCAR_EQUIPE", query = "select id, nome from H2DB.equipe where id = :id", resultClass = Equipe.class)
})
public class Equipe implements Serializable{
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "nome")
    private String nome;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
