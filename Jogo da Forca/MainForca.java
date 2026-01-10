package academy.guih.poo.javacore.classes.dominio;
import academy.guih.poo.javacore.classes.abstrata.BaseForca;

import java.util.Scanner;

public class MainForca {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BaseForca jogo = new BaseForca();

        jogo.iniciarPartida();

        System.out.println("=== FORCA ===  (erros: " + jogo.ErrosCometidos() + "/" + BaseForca.MAX_ERROS + ")");
        System.out.println("Palavra: " + jogo.getPalavraProgresso());
        System.out.println("Usadas : —");

        while (!jogo.isFim()) {
            System.out.print("\nPalpite: ");
            char letra = input.next().charAt(0);


            int errosAntes = jogo.ErrosCometidos();

            jogo.tentarLetra(letra);

            if (jogo.ErrosCometidos() > errosAntes) {
                System.out.println("Errou!");
            } else {
                System.out.println("Acertou!");
            }

            System.out.println("Palavra: " + jogo.getPalavraProgresso());
            System.out.println("Usadas : " + jogo.LetrasTentadas());
            System.out.println("(erros: " + jogo.ErrosCometidos() + "/" + BaseForca.MAX_ERROS + ")");
        }

        // Resultado final
        if (jogo.venceu()) {
            System.out.println("\n Parabéns! Você venceu!");
        } else {
            System.out.println("\n Você perdeu! A palavra era: " + jogo.PalavraSorteada());
        }

        input.close();
    }
}