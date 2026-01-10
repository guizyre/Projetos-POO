package academy.guih.poo.javacore.classes.ProjetoFinal.Test;

import academy.guih.poo.javacore.classes.ProjetoFinal.Dominio.*;

import java.util.Scanner;

public class MainProjeto {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Oficina oficinaGon = new Oficina();

        System.out.println("===============================");
        System.out.println("   BEM-VINDO À OFICINA GON   ");
        System.out.println("===============================");

        while (true) {
            // A main apenas manda a oficina realizar o atendimento.
            oficinaGon.realizarAtendimento(input);

            System.out.println("\n--------------------------------");
            System.out.print("Deseja atender outro cliente? (s/n): ");
            String continuar = input.nextLine().trim().toLowerCase();

            if(!continuar.equals("s")) {
                System.out.println("Esperamos que possamos nos ver novamente! :)");
                break;
            }
        }

        // boas praticas. Né Igor?? kk
        input.close();
    }
}
