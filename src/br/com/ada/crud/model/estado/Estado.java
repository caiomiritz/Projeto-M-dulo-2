package br.com.ada.crud.model.estado;

import java.io.Serializable;
import java.util.UUID;

public class Estado implements Serializable {

    public static final long serialVersionUID = 1l;

    private UUID id;
    private String nome;
    private String sigla;
    private String pais;

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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

}
