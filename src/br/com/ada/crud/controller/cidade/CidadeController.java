package br.com.ada.crud.controller.cidade;

import br.com.ada.crud.model.cidade.Cidade;

import java.util.List;
import java.util.UUID;

public interface CidadeController {

    void cadastrar(Cidade cidade);

    Cidade buscar(UUID id);

    List<Cidade> listar();

    void atualizar(UUID id, Cidade cidade);

    Cidade apagar(UUID id);

}
