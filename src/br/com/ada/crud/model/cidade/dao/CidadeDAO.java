package br.com.ada.crud.model.cidade.dao;

import br.com.ada.crud.model.cidade.Cidade;

import java.util.List;
import java.util.UUID;

public interface CidadeDAO {

    void cadastrar(Cidade cidade);

    List<Cidade> listar();

    Cidade buscar(UUID id);

    void atualizar(UUID id, Cidade cidade);

    Cidade apagar(UUID id);

}
