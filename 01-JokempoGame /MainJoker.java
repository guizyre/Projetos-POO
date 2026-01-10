package academy.guih.poo.javacore.classes.test;
import academy.guih.poo.javacore.classes.dominio.JokempoGame2;
import academy.guih.poo.javacore.classes.dominio.Player2;
import java.util.Scanner;


public class MainJoker2 {
    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        int contador = 1;

        JokempoGame2 jogo = new JokempoGame2("pedra", "papel", "tesoura");
        jogo.Nomes();

        System.out.println("Os Jogadores Serão:");
        System.out.println(jogo.player1.nome);
        System.out.println(jogo.player2.nome);
        jogo.Jogador_real();

            while(!jogo.endGame()){
                    jogo.player1.sorteio();
                    jogo.player2.sorteio();

                    if(jogo.jogador3Real){
                        input = new Scanner(System.in);
                        System.out.println(jogo.player3.nome + ", digite sua jogada (pedra, papel ou tesoura): ");
                        jogo.player3.jogada_atual = input.nextLine().toLowerCase();
                    }else{
                        jogo.player3.sorteio();
                    }


                    System.out.println("\nRodada " + contador + ":");
                    System.out.println();
                    System.out.println(jogo.player1.nome + " jogou " + jogo.player1.jogada_atual);
                    sleep(500);
                    System.out.println(jogo.player2.nome + " jogou " + jogo.player2.jogada_atual);
                    sleep(500);
                    System.out.println(jogo.player3.nome + " jogou " + jogo.player3.jogada_atual);
                System.out.println();
                System.out.println("________________________________________________________");

                    jogo.verificarResultado();

                    System.out.println("Placar atual:");
                    System.out.println(jogo.player1.nome + ": " + jogo.player1.num_vitorias);
                    System.out.println(jogo.player2.nome + ": " + jogo.player2.num_vitorias);
                    System.out.println(jogo.player3.nome + ": " + jogo.player3.num_vitorias);
                    System.out.println("_______________________________________________________________________");
                    contador++;


            }
    }

}
