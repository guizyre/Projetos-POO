package academy.guih.poo.javacore.classes.ProjetoFinal.Dominio;

/**
 * Interface que define o <b>contrato de execução</b> para qualquer serviço da oficina.
 * <p>
 * Ela garante que todo serviço siga um padrão rigoroso de funcionamento:
 * 1. Validação prévia;
 * 2. Execução lógica;
 * 3. Apresentação de resumo.
 * </p>
 */

public interface IExecutor {

    /**
     * Verifica se todas as pré-condições para o serviço estão ok.
     * <p>
     * Deve checar se o veículo existe, se o ano é válido, se o preço base está correto, etc.
     * Se algo estiver errado, deve lançar uma exceção ou avisar o sistema.
     */
    void validar();

    /**
     * O método principal que realiza a ação do serviço.
     * <p>
     * Aqui ocorre o cálculo do preço, a simulação do tempo (Thread.sleep) e a lógica de negócio.
     * </p>
     * @return <b>true</b> se o serviço foi concluído com sucesso;
     * <b>false</b> se houve algum erro durante o processo.
     */
    boolean executar();

    /**
     * Retorna uma String formatada com os dados finais do serviço.
     * Útil para mostrar o "Resumo Final" na tela após a execução.
     */
    String toResumo();
}
