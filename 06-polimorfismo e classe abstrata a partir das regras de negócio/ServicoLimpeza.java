package academy.guih.poo.javacore.classes.polimorfismo;

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
        validarCliente();
        if (area <= 0) {
            throw new IllegalStateException("Área inválida para limpeza!");
        }

        double custo = calcularCusto();
        System.out.println("[" + numeroSolicitacao + "] Executando limpeza " + (limpezaPesada ? "pesada" : "leve") +
                " de " + area + "m² para " + nomeCliente + " | Custo: R$" + custo);
        registrarExecucao();
    }

    private double calcularCusto() {
        double valorBase = area * 5.0;
        if (limpezaPesada) valorBase *= 1.5;
        return valorBase;
    }

    @Override
    public void exibirResumo() {
        System.out.println("Limpeza | Cliente: " + nomeCliente + " | Área: " + area +
                "m² | Tipo: " + (limpezaPesada ? "Pesada" : "Leve") + " | Executado: " + executado);
    }
}