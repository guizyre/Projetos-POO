package academy.guih.poo.javacore.classes.dominio;
import academy.guih.poo.javacore.classes.dominio.Player;

public class JokenpoGame {
    public Player player1 = new Player();
    public Player player2 = new Player();
    public Player player3 = new Player();

    public JokenpoGame(String jogada1, String jogada2, String jogada3){
        player1.jogada_atual = jogada1;
        player2.jogada_atual = jogada2;
        player3.jogada_atual = jogada3;
        verificarResultado();
    }
    public boolean fimDeJogo(){
        if (player1.num_vitorias >= 3){
            System.out.println( player1.nome + " vencedor!");
            return true;
        }
        else if (player2.num_vitorias >= 3){
            System.out.println(player2.nome + " vencedor!");
            return true;
        }
        else if (player3.num_vitorias >= 3){
            System.out.println(player3.nome + " vencedor!");
            return true;
        }
        return false;

    }


    public void verificarResultado(){
        if(player1.jogada_atual.equals(player2.jogada_atual) &&
        player2.jogada_atual.equals(player3.jogada_atual)) {
            System.out.println("Empate! todas as jogadas foram iguais...");
        }
        else if (!player1.jogada_atual.equals(player2.jogada_atual) &&
                !player2.jogada_atual.equals(player3.jogada_atual) &&
                !player1.jogada_atual.equals(player3.jogada_atual)) {
            System.out.println("Empate triplo! Todas as jogadas são diferentes.");
        }
        else if((player1.jogada_atual.equals(player2.jogada_atual) &&
                !player1.jogada_atual.equals(player3.jogada_atual))){
            System.out.println(player1.nome + " e " + player2.nome + " empataram entre sí " + player3.nome + " Ganhou a rodada!" );
            player3.incrementar_vitorias();
            fimDeJogo();
        }
        else if(player1.jogada_atual.equals(player3.jogada_atual) &&
                !player1.jogada_atual.equals(player2.jogada_atual)){
            System.out.println(player1.nome + " e " + player3.nome + " empataram entre sí " + player2.nome + " Ganhou a rodada!" );
            player2.incrementar_vitorias();
            fimDeJogo();

        }
        else{
            System.out.println(player2.nome + " e " + player3.nome + " empataram entre sí " + player1.nome + " Ganhou a rodada!" );
            player1.incrementar_vitorias();
            fimDeJogo();
        }
    }



}
