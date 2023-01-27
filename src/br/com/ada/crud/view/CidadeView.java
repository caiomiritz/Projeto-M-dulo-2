package br.com.ada.crud.view;

import br.com.ada.crud.Main;
import br.com.ada.crud.controller.cidade.CidadeController;
import br.com.ada.crud.controller.cidade.exception.CidadeNaoEncontrada;
import br.com.ada.crud.model.cidade.Cidade;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CidadeView extends MainView {

    private CidadeController controller;
    private Scanner scanner;

    public CidadeView(CidadeController controller, Scanner scanner)
    {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void cadastrar() {

        Cidade cidade = new Cidade();

        System.out.print("Informe o nome da cidade: ");
        String nome = scanner.nextLine();
        cidade.setNome(nome);

        System.out.print("Informe a sigla do estado: ");
        String estado = scanner.nextLine();
        cidade.setEstado(estado);

        controller.cadastrar(cidade);
    }

    public void listar(UUID id) {

        Cidade cidade = controller.buscar(id);
        exibirCidade(cidade);
    }

    public void atualizar() {

        listar();

        System.out.println(" ");
        System.out.print("Informe o número da cidade que deseja atualizar: ");
        Integer numero = scanner.nextInt();
        scanner.nextLine();

        System.out.println(" ");
        System.out.println("-----------------CIDADE SOLICITADA-----------------");
        Cidade cidade = controller.listar().get(numero - 1);
        atualizar(cidade);
    }

    public void atualizar(Cidade cidade) {

        exibirCidade(cidade);

        System.out.println(" ");
        System.out.print("Informe o novo nome da cidade: ");
        String nome = scanner.nextLine();
        cidade.setNome(nome);

        System.out.print("Informe a nova sigla do estado: ");
        String estado = scanner.nextLine();
        cidade.setEstado(estado);

        try {
            controller.atualizar(cidade.getId(), cidade);
        }
        catch (CidadeNaoEncontrada ex) {
            System.out.println("Cidade informada não existe na base. Tente novamente!");
        }
    }

    public void apagar() {

        listar();
        System.out.println(" ");
        System.out.print("Informe o número da cidade que deseja apagar: ");
        Integer numero = scanner.nextInt();
        scanner.nextLine();

        Cidade cidade = controller.listar().get(numero - 1);
        apagar(cidade.getId());
    }

    public void apagar(UUID id) {

        try {

            Cidade cidade = controller.apagar(id);
            System.out.println(" ");
            System.out.println("----------------CIDADE APAGADA----------------");
            exibirCidade(cidade);
        }
        catch (CidadeNaoEncontrada ex) {
            System.out.println("A cidade não foi agapada pois não foi localizada. Tente novamente!");
        }
    }

    public void listar() {

        List<Cidade> cidades = controller.listar();
        System.out.println(" ");
        System.out.println("| Número | Nome da Cidade | Sigla do Estado | ID |");
        System.out.println(" ");

        for (int i = 0; i < cidades.size(); i++) {

            System.out.print("| " + (i + 1));
            exibirCidade(cidades.get(i));
        }
    }

    public void exibirCidade(Cidade cidade) {

        System.out.print(" | ");
        System.out.print(cidade.getNome());
        System.out.print(" | ");
        System.out.print(cidade.getEstado());
        System.out.print(" | ");
        System.out.print(cidade.getId());
        System.out.println(" |");
    }

    public void exibirOpcoes() {

        System.out.println(" ");
        System.out.println("                MENU DA CIDADE          ");
        System.out.println("-----------------------------------------------");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Apagar");
        System.out.println("5 - Voltar ao Menu");
        System.out.println("0 - Sair");
        System.out.println("-----------------------------------------------");
        System.out.print("Informe a opção desejada: ");
        Integer opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                cadastrar();
                break;
            case 2:
                listar();
                break;
            case 3:
                atualizar();
                break;
            case 4:
                apagar();
                break;
            case 5:
                super.option();
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Opção invalida!");
        }

        exibirOpcoes();
    }
}
