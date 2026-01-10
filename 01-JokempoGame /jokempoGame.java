package academy.guih.poo.javacore.classes.dominio;
import academy.guih.poo.javacore.classes.dominio.Player2;
import java.util.Scanner;


public class JokempoGame2 {
    public Player2 player1 = new Player2();
    public Player2 player2 = new Player2();
    public Player2 player3 = new Player2();
    public boolean jogador3Real = false;

    public void Nomes() {
        player1.nome = "Bot_dorbeman";
        player2.nome = "Bot_pit";
        player3.nome = "Usuario";

    }

    public void Jogador_real(){
        Scanner input = new Scanner(System.in);
        System.out.println("Você quer que o jogador 3 seja real? [S/N] ");
        String resposta = input.nextLine();
        if(resposta.equalsIgnoreCase("S")){
            jogador3Real = true;
            System.out.println("Digite o nome do jogador 3: ");
            player3.nome = input.nextLine();

        } else{
            System.out.println("Jogador 3 será um bot");
            player3.nome = "Usuario";
        }
    }



    public JokempoGame2(String jogada1, String jogada2, String jogada3) {
        player1.jogada_atual = jogada1;
        player2.jogada_atual = jogada2;
        player3.jogada_atual = jogada3;
//        verificarResultado();
    }

    public boolean endGame() {
        if (player1.num_vitorias >= 3){
            System.out.println(player1.nome + " Ganhou!");
            return true;
        }
        else if (player2.num_vitorias >= 3){
            System.out.println(player2.nome + " Ganhou!");
            return true;
        }
        else if (player3.num_vitorias >= 3){
            System.out.println(player3.nome + " Ganhou!");
            return true;
        }

        return false;
    }

    public void verificarResultado() {
        if(player1.jogada_atual.equals(player2.jogada_atual) &&
                player2.jogada_atual.equals(player3.jogada_atual)){
            System.out.println("Houve um empate entre os três jogadores!");
        }
        else if(!player1.jogada_atual.equals(player2.jogada_atual)
                && !player2.jogada_atual.equals(player3.jogada_atual)
                && !player1.jogada_atual.equals(player3.jogada_atual)){
            System.out.println("Os três jogaram diferente entre eles mesmo");
            System.out.println("Empate técnico!");
        }
        else {
            // Sub-caso 3.1: Jogador 1 e 2 empatam
            if (player1.jogada_atual.equals(player2.jogada_atual)) {
                // Verificamos se a jogada da dupla (player1) vence a do player3
                if ((player1.jogada_atual.equals("pedra") && player3.jogada_atual.equals("tesoura")) ||
                        (player1.jogada_atual.equals("tesoura") && player3.jogada_atual.equals("papel")) ||
                        (player1.jogada_atual.equals("papel") && player3.jogada_atual.equals("pedra"))) {

                    System.out.println(player1.nome + " e " + player2.nome + " ganharam a rodada!");
                    player1.incrementar_pontos();
                    endGame();
                    player2.incrementar_pontos();
                    endGame();
                }

                else { // Se não, o player3 ganhou da dupla
                    System.out.println(player3.nome + " ganhou a rodada!");
                    player3.incrementar_pontos();
                    endGame();
                }
            }
            // Sub-caso 3.2: Jogador 1 e 3 empatam
            else if (player1.jogada_atual.equals(player3.jogada_atual)) {
                // Verificamos se a jogada da dupla (player1) vence a do player2
                if ((player1.jogada_atual.equals("pedra") && player2.jogada_atual.equals("tesoura")) ||
                        (player1.jogada_atual.equals("tesoura") && player2.jogada_atual.equals("papel")) ||
                        (player1.jogada_atual.equals("papel") && player2.jogada_atual.equals("pedra"))) {

                    System.out.println(player1.nome + " e " + player3.nome + " ganharam a rodada!");
                    player1.incrementar_pontos();
                    endGame();
                    player3.incrementar_pontos();
                    endGame();
                } else { // Se não, o player2 ganhou da dupla
                    System.out.println(player2.nome + " ganhou a rodada!");
                    player2.incrementar_pontos();
                    endGame();
                }
            }
            // Sub-caso 3.3: Jogador 2 e 3 empatam
            else if (player2.jogada_atual.equals(player3.jogada_atual)) {
                // Verificamos se a jogada da dupla (player2) vence a do player1
                if ((player2.jogada_atual.equals("pedra") && player1.jogada_atual.equals("tesoura")) ||
                        (player2.jogada_atual.equals("tesoura") && player1.jogada_atual.equals("papel")) ||
                        (player2.jogada_atual.equals("papel") && player1.jogada_atual.equals("pedra"))) {

                    System.out.println(player2.nome + " e " + player3.nome + " ganharam a rodada!");
                    player2.incrementar_pontos();
                    endGame();
                    player3.incrementar_pontos();
                    endGame();
                } else { // Se não, o player1 ganhou da dupla
                    System.out.println(player1.nome + " ganhou a rodada!");
                    player1.incrementar_pontos();
                    endGame();

                }
            }
        }

    }


}
