package br.com.ada.crud.view;

import br.com.ada.crud.Main;
import br.com.ada.crud.controller.estado.exception.EstadoNaoEncontrado;
import br.com.ada.crud.controller.pais.PaisController;
import br.com.ada.crud.controller.pais.exception.PaisNaoEncontrado;
import br.com.ada.crud.model.pais.Pais;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PaisView extends MainView {

    private PaisController controller;
    private Scanner scanner;

    public PaisView(PaisController controller, Scanner scanner)
    {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void cadastrar() {

        Pais pais = new Pais();

        System.out.print("Informe o nome do país: ");
        String nome = scanner.nextLine();
        pais.setNome(nome);

        System.out.print("Informe a sigla do pais: ");
        String siglaP = scanner.nextLine();
        pais.setSigla(siglaP);

        controller.cadastrar(pais);
    }

    public void listar(UUID id) {

        Pais pais = controller.buscar(id);
        exibirPais(pais);
    }

    public void atualizar() {

        listar();

        System.out.println(" ");
        System.out.print("Informe o número do país que deseja atualizar: ");
        Integer numero = scanner.nextInt();
        scanner.nextLine();

        System.out.println(" ");
        System.out.println("-----------------PAÍS SOLICITADO-----------------");
        Pais pais = controller.listar().get(numero - 1);
        atualizar(pais);
    }

    public void atualizar(Pais pais) {

        exibirPais(pais);

        System.out.println(" ");
        System.out.print("Informe o novo nome do país: ");
        String nome = scanner.nextLine();
        pais.setNome(nome);

        System.out.print("Informe a nova sigla do país: ");
        String siglaP = scanner.nextLine();
        pais.setSigla(siglaP);

        try {
            controller.atualizar(pais.getId(), pais);
        }
        catch (PaisNaoEncontrado ex) {
            System.out.println("País informado não existe na base. Tente novamente!");
        }
    }

    public void apagar() {

        listar();
        System.out.println(" ");
        System.out.print("Informe o número do país que deseja apagar: ");
        Integer numero = scanner.nextInt();
        scanner.nextLine();

        Pais pais = controller.listar().get(numero - 1);
        apagar(pais.getId());
    }

    public void apagar(UUID id) {

        try {

            Pais pais = controller.apagar(id);
            System.out.println(" ");
            System.out.println("----------------PAÍS APAGADO----------------");
            exibirPais(pais);
        }
        catch (EstadoNaoEncontrado ex) {
            System.out.println("O país não foi agapado pois não foi localizado. Tente novamente!");
        }
    }

    public void listar() {

        List<Pais> paises = controller.listar();
        System.out.println(" ");
        System.out.println("| Número | Nome do País | Sigla | ID |");
        System.out.println(" ");

        for (int i = 0; i < paises.size(); i++) {

            System.out.print("| " + (i + 1));
            exibirPais(paises.get(i));
        }
    }

    public void exibirPais(Pais pais) {

        System.out.print(" | ");
        System.out.print(pais.getNome());
        System.out.print(" | ");
        System.out.print(pais.getSigla());
        System.out.print(" | ");
        System.out.print(pais.getId());
        System.out.println(" |");
    }

    public void exibirOpcoes() {

        System.out.println(" ");
        System.out.println("                MENU DO PAÍS          ");
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
