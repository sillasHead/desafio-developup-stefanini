package br.com.stefanini.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author sillas
 * @version 0.1.0
 * @email sillas@gmail.com
 * @created 04/10/2021 on 22:20
 */
public class EquipeDto {

    private int id;

    @Size(max = 255, message = "Máximo de 255 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String nome;

    public EquipeDto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public EquipeDto() {}

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
