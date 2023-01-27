package br.com.ada.crud.model.cidade;

import java.io.Serializable;
import java.util.UUID;

public class Cidade implements Serializable {

    public static final long serialVersionUID = 1l;

    private UUID id;
    private String nome;
    private String estado;

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
