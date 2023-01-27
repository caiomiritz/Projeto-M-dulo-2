package br.com.ada.crud.model.pais;

import java.io.Serializable;
import java.util.UUID;

public class Pais implements Serializable {

    public static final long serialVersionUID = 1l;

    private UUID id;
    private String nome;
    private String sigla;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
