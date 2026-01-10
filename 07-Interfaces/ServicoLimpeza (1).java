package academy.guih.poo.javacore.classes.Interface.Dominio;

import java.time.LocalDate;

public class ServicoLimpeza extends Servico {
    private double area; // em metros quadrados
    private boolean limpezaPesada;

    public ServicoLimpeza(String numeroSolicitacao, String nomeCliente, LocalDate data, double area, boolean limpezaPesada) {
        super(numeroSolicitacao, nomeCliente, data);
        this.area = area;
        this.limpezaPesada = limpezaPesada;
    }

    @Override
    public void executar() {
        this.validar();

        if (area <= 0) {
            throw new IllegalStateException("Área inválida para limpeza!");
        }

        double custo = calcularCusto();

        System.out.println(">>> EXECUTANDO LIMPEZA <<<");
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Tipo: " + (limpezaPesada ? "Pesada" : "Leve"));
        System.out.println("Área: " + area + "m² | Custo Total: R$ " + custo);

        registrarExecucao();
    }

    private double calcularCusto() {
        double valorBase = area * 5.0;
        if (limpezaPesada) {
            valorBase *= 1.5;
        }
        return valorBase;
    }

    @Override
    public void exibirResumo() {
        super.exibirResumo();
        System.out.println("   -> Detalhe: Limpeza de " + area + "m² (R$ " + calcularCusto() + ")");
    }
}
