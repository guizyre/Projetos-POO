package academy.guih.poo.javacore.classes.abstrata;// Aqui depende de onde está localizada sua classe

import java.util.Random;

public class BaseForca {

    //Atributos
    private String palavraSorteada;
    private char[] palavraProgresso;
    private int errosCometidos;
    private char[] letrasTentadas; //
    private int quantidadeLetrasTentadas;
    private boolean fim;

    // Inteiro final para erros máximos
    public static final int MAX_ERROS = 6;

    // palavras que podem ser sorteadas
    private static final String[] Palavras = {
            "TECLADO", "MONITOR", "PROCESSADOR", "ALGORITMO", "LINGUAGEM",
            "COMPILADOR", "DEBUGAR", "FRAMEWORK", "BIBLIOTECA", "DATABASE",
            "SERVIDOR", "CLIENTE", "INTERFACE", "VARIAVEL", "FUNCAO"

    };

    //função em que a palavra e sorteada
    public void sorteio(){
        Random input = new Random();
        int indice = input.nextInt(Palavras.length);
        this.palavraSorteada =  Palavras[indice];

    }

    //Prepara o jogo para uma nova partida
    public void iniciarPartida() {

        // chamo a palavra sorteio
        sorteio();

        // Reseta o estado do jogo
        this.errosCometidos = 0;
        this.fim = false;
        this.letrasTentadas = new char[26];
        this.quantidadeLetrasTentadas = 0;

        // Cria o progresso com underlines
        this.palavraProgresso = new char[this.palavraSorteada.length()];
        for (int i = 0; i < palavraProgresso.length; i++) {
            this.palavraProgresso[i] = '_';
        }
    }

    private void verificarFimDeJogo() {
        // Verifica derrota
        if (errosCometidos >= MAX_ERROS) {
            fim = true;
            return; // Jogo acabou, não precisa checar vitória
        }

        // Verifica vitória se não há mais underlines)
        boolean temUnderline = false;
        for (char c : palavraProgresso) {
            if (c == '_') {
                temUnderline = true;
                break;
            }
        }
        if (!temUnderline) {
            fim = true;
        }
    }

    //adiciona as letras na lista
    private void adicionarLetraNaLista(char letra) {
        this.letrasTentadas[this.quantidadeLetrasTentadas] = letra;
        this.quantidadeLetrasTentadas++;
    }

    //Processa a jogada do usuário.
    public void tentarLetra(char letra) {

        // se o jogo já terminou não faz nada
        if (fim) return;

        // aqui eu jogo a letra para maiúscula
        letra = Character.toUpperCase(letra);

        // Verifica se a letra é repetida Se for não faz mais nada
        if (letraJaTentada(letra)) return;

        // aqui adiciono a letra no array de já tentadas
        adicionarLetraNaLista(letra);

        // Verifica se a letra existe na palavra e atualiza o progresso
        boolean acertou = false;
        for (int i = 0; i < palavraSorteada.length(); i++) {
            if (palavraSorteada.charAt(i) == letra) {
                palavraProgresso[i] = letra;
                acertou = true;
            }
        }

        // Se não acertou adiciona um erro
        if (!acertou) {
            errosCometidos++;
        }

        // Após cada jogada verifica se o jogo terminou
        verificarFimDeJogo();
    }


    // função private
    private boolean letraJaTentada(char letra) {
        for (int i = 0; i < quantidadeLetrasTentadas; i++) {
            if (letrasTentadas[i] == letra) {
                return true;
            }
        }
        return false;
    }

    public String LetrasTentadas() {
        String usadas = "";

        for (int i = 0; i < quantidadeLetrasTentadas; i++) {
            usadas += letrasTentadas[i] + " ";
        }

        return usadas.trim();
    }


    //faz a conversão do array para uma String formatada
    public String getPalavraProgresso() {
        String resultado = "";
        for(char c : this.palavraProgresso) {
            resultado += c + " ";
        }
        return resultado;
    }

    //metodos gets para dar acesso controlado de "leitura' aos atributos private
    public int ErrosCometidos() {
        return this.errosCometidos;
    }

    public boolean isFim() {
        return this.fim;
    }

    public String PalavraSorteada() {
        return this.palavraSorteada;
    }

    public boolean venceu() {
        return this.fim && this.errosCometidos < MAX_ERROS;
    }
}