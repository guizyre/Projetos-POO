package academy.guih.poo.javacore.classes.polimorfismo;

import java.time.LocalDate;

public class ServicoVistoria extends Servico {
    private String tipoImovel;
    private String relatorioGerado;

    public ServicoVistoria(String numeroSolicitacao, String nomeCliente, LocalDate data, String tipoImovel) {
        super(numeroSolicitacao, nomeCliente, data);
        this.tipoImovel = tipoImovel;
    }

    @Override
    public void executar() {
        validarCliente();
        relatorioGerado = gerarRelatorio();
        System.out.println("[" + numeroSolicitacao + "] Realizando vistoria em imóvel " + tipoImovel + " de " + nomeCliente);
        System.out.println("Relatório: " + relatorioGerado);
        registrarExecucao();
    }

    private String gerarRelatorio() {
        return "Imóvel tipo " + tipoImovel + " vistoriado em " + data + " — sem irregularidades encontradas.";
    }

    @Override
    public void exibirResumo() {
        System.out.println("Vistoria | Cliente: " + nomeCliente + " | Tipo: " + tipoImovel + " | Relatório: " + relatorioGerado +
                " | Executado: " + executado);
    }
}