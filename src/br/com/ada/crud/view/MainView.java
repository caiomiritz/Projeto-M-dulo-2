package br.com.ada.crud.view;

import br.com.ada.crud.controller.cidade.CidadeController;
import br.com.ada.crud.controller.cidade.CidadeControllerFactory;
import br.com.ada.crud.controller.estado.EstadoController;
import br.com.ada.crud.controller.estado.EstadoControllerFactory;
import br.com.ada.crud.controller.pais.PaisController;
import br.com.ada.crud.controller.pais.PaisControllerFactory;

import java.util.Scanner;

public class MainView {

    private static CidadeController cidadeController = CidadeControllerFactory.getInstance().criar();
    private static EstadoController estadoController = EstadoControllerFactory.getInstance().criar();
    private static PaisController paisController = PaisControllerFactory.getInstance().criar();

    private static CidadeView cidadeview = new CidadeView(cidadeController, new Scanner(System.in));
    private static EstadoView estadoView = new EstadoView(estadoController, new Scanner(System.in));
    private static PaisView paisView = new PaisView(paisController, new Scanner(System.in));

    public static Integer exibicao(){

        Scanner scanner = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("               MENU DE OPÇÕES                  ");
        System.out.println("-----------------------------------------------");
        System.out.println("1- Cidade");
        System.out.println("2- Estado");
        System.out.println("3- Pais");
        System.out.println("0- Sair");
        System.out.println("-----------------------------------------------");
        System.out.print("Por favor, digite o número do elemento ao qual queira utilizar: ");
        Integer opcao = scanner.nextInt();
        scanner.nextLine();

        return opcao;
    }

    public static void option(){

        Integer opcao = exibicao();

        switch (opcao){
            case 1:
                cidadeview.exibirOpcoes();
                break;
            case 0:
                System.exit(0);
                break;
            case 2:
                estadoView.exibirOpcoes();
                break;
            case 3:
                paisView.exibirOpcoes();
                break;
            default:
                System.out.println("Opção invalida!");
        }
    }





}
