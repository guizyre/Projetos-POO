package academy.guih.poo.javacore.classes.ProjetoFinal.Dominio;

/**
 * Interface que define o contrato de manutenção para os veículos.
 * <p>
 * Qualquer classe que implementar esta interface (como Carro, Moto, Caminhão)
 * é <b>obrigada</b> a definir como sua manutenção específica é realizada
 * e como o relatório é emitido.
 * </p>
 * Isso garante o Polimorfismo: podemos chamar esses métodos sem saber
 * exatamente qual tipo de veículo estamos tratando.
 */


public interface Manutinivel {

    /**
     * Executa a lógica de manutenção específica do veículo.
     * Ex: Verificar carga (Caminhão), Checar parte elétrica (Carro), etc.
     */
    void realizarManutencao();

    /**
     * Exibe no console um resumo ou status após a manutenção ser concluída.
     */
    void emitirRelatorio();
}
