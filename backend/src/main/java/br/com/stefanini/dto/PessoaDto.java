package br.com.stefanini.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.smallrye.common.constraint.NotNull;

/**
 * @author sillas
 * @version 0.1.0
 * @email sillas@gmail.com
 * @created 04/10/2021 on 22:25
 */
public class PessoaDto {
    private int id;

    @Size(max = 255, message = "Máximo de 255 caracteres")
    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @Size(max = 255, message = "Máximo de 255 caracteres")
    @NotBlank(message = "Sobrenome obrigatório")
    private String sobrenome;

    @Size(max = 200, message = "Máximo de 200 caracteres")
    @NotBlank(message = "Contato obrigatório")
    private String contato;

    @Size(max = 255, message = "Máximo de 255 caracteres")
    @NotBlank(message = "Email obrigatório")
    @Email
    private String email;

    private int cargo;

    @NotNull
    private EquipeDto equipe;

    public PessoaDto(int id,
            @Size(max = 255, message = "Máximo de 255 caracteres") @NotBlank(message = "Nome obrigatório") String nome,
            @Size(max = 255, message = "Máximo de 255 caracteres") @NotBlank(message = "Sobrenome obrigatório") String sobrenome,
            @Size(max = 200, message = "Máximo de 200 caracteres") @NotBlank(message = "Contato obrigatório") String contato,
            @Size(max = 255, message = "Máximo de 255 caracteres") @NotBlank(message = "Email obrigatório") @Email String email,
            int cargo, EquipeDto equipe) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.contato = contato;
        this.email = email;
        this.cargo = cargo;
        this.equipe = equipe;
    }

    public PessoaDto() {
    }

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public EquipeDto getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeDto equipe) {
        this.equipe = equipe;
    }
}
