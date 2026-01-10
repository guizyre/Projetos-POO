package academy.guih.poo.javacore.classes.ProjetoFinal.Dominio;

/**
 * Interface responsável por definir o <b>comportamento financeiro</b> de uma classe.
 * <p>
 * Qualquer objeto que seja "Pagável" deve ser capaz de calcular seu próprio preço
 * (aplicando taxas, descontos ou regras de negócio) e fornecer um comprovante
 * dessa transação.
 * </p>
 */
public interface Pagavel {

    /**
     * Realiza o cálculo final do valor a ser cobrado.
     * <p>
     * Este método deve considerar todas as variáveis que afetam o preço, como:
     * <ul>
     * <li>Taxas para veículos pesados (Caminhões);</li>
     * <li>Adicionais de complexidade ou sujeira;</li>
     * <li>Custo de materiais utilizados.</li>
     */
    double calcularValor();

    /**
     * Gera e exibe o comprovante detalhado do serviço ou transação.
     * <p>
     * O recibo deve conter informações vitais como: ID único, Data/Hora,
     * descrição do item e o valor final calculado.
     * </p>
     */
    void gerarRecibo();

}
