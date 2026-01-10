package academy.guih.poo.javacore.classes.dominio;
import java.util.Random;
import java.util.Scanner;

public class Player2 {
    public String nome;
    public String[] jogada = {"pedra", "papel", "tesoura"};
    public String jogada_atual;
    public int num_vitorias = 0;

    public void sorteio(){
        Random random = new Random();
        this.jogada_atual = jogada[random.nextInt(jogada.length)];
    }

    public String ultimaJogada(){
        return this.jogada_atual;
    }

    public int incrementar_pontos(){
        return ++this.num_vitorias;
    }
}
