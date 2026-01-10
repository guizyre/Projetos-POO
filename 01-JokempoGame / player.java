package academy.guih.poo.javacore.classes.dominio;
import java.util.Random;

public class Player {
    public String nome;
    public int num_vitorias;
    public String[] opcoes = {"pedra", "papel", "tesoura"};
    public String jogada_atual;

    public void sorteio(){
        Random random = new Random();
        int indice = random.nextInt(opcoes.length);
        this.jogada_atual = opcoes[indice];
    }

    public String aJogada_atual(){
        return this.jogada_atual;
    }

    public int incrementar_vitorias(){
        return this.num_vitorias++;
    }
}
