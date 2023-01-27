package br.com.ada.crud.view;

import br.com.ada.crud.Main;
import br.com.ada.crud.controller.estado.EstadoController;
import br.com.ada.crud.controller.estado.exception.EstadoNaoEncontrado;
import br.com.ada.crud.model.estado.Estado;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class EstadoView extends MainView {

    private EstadoController controller;
    private Scanner scanner;

    public EstadoView(EstadoController controller, Scanner scanner)
    {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void cadastrar() {

        Estado estado = new Estado();

        System.out.print("Informe o nome do estado: ");
        String nome = scanner.nextLine();
        estado.setNome(nome);

        System.out.print("Informe a sigla do estado: ");
        String siglaE = scanner.nextLine();
        estado.setSigla(siglaE);

        System.out.print("Informe a sigla do pais: ");
        String siglaP = scanner.nextLine();
        estado.setPais(siglaP);

        controller.cadastrar(estado);
    }

    public void listar(UUID id) {

        Estado estado = controller.buscar(id);
        exibirEstado(estado);
    }

    public void atualizar() {

        listar();

        System.out.println(" ");
        System.out.print("Informe o número do estado que deseja atualizar: ");
        Integer numero = scanner.nextInt();
        scanner.nextLine();

        System.out.println(" ");
        System.out.println("-----------------ESTADO SOLICITADO-----------------");
        Estado estado = controller.listar().get(numero - 1);
        atualizar(estado);
    }

    public void atualizar(Estado estado) {

        exibirEstado(estado);

        System.out.println(" ");
        System.out.print("Informe o novo nome do estado: ");
        String nome = scanner.nextLine();
        estado.setNome(nome);

        System.out.print("Informe a nova sigla do estado: ");
        String siglaE = scanner.nextLine();
        estado.setSigla(siglaE);

        System.out.print("Informe a nova sigla do pais: ");
        String siglaP = scanner.nextLine();
        estado.setPais(siglaP);

        try {
            controller.atualizar(estado.getId(), estado);
        }
        catch (EstadoNaoEncontrado ex) {
            System.out.println("Estado informado não existe na base. Tente novamente!");
        }
    }

    public void apagar() {

        listar();
        System.out.println(" ");
        System.out.print("Informe o número do estado que deseja apagar: ");
        Integer numero = scanner.nextInt();
        scanner.nextLine();

        Estado estado = controller.listar().get(numero - 1);
        apagar(estado.getId());
    }

    public void apagar(UUID id) {

        try {

            Estado estado = controller.apagar(id);
            System.out.println(" ");
            System.out.println("----------------ESTADO APAGADO----------------");
            exibirEstado(estado);
        }
        catch (EstadoNaoEncontrado ex) {
            System.out.println("O estado não foi agapado pois não foi localizado. Tente novamente!");
        }
    }

    public void listar() {

        List<Estado> estados = controller.listar();
        System.out.println(" ");
        System.out.println("| Número | Nome do Estado | Sigla  | Sigla do País | ID |");
        System.out.println(" ");

        for (int i = 0; i < estados.size(); i++) {

            System.out.print("| " + (i + 1));
            exibirEstado(estados.get(i));
        }
    }

    public void exibirEstado(Estado estado) {

        System.out.print(" | ");
        System.out.print(estado.getNome());
        System.out.print(" | ");
        System.out.print(estado.getSigla());
        System.out.print(" | ");
        System.out.print(estado.getPais());
        System.out.print(" | ");
        System.out.print(estado.getId());
        System.out.println(" |");
    }

    public void exibirOpcoes() {

        System.out.println(" ");
        System.out.println("                MENU DO ESTADO          ");
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
