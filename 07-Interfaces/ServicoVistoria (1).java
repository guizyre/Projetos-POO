package academy.guih.poo.javacore.classes.Interface.Dominio;

import java.time.LocalDate;

public class ServicoVistoria extends Servico {
    private final String tipoImovel;
    private String relatorioGerado;

    public ServicoVistoria(String numeroSolicitacao, String nomeCliente, LocalDate data, String tipoImovel) {
        super(numeroSolicitacao, nomeCliente, data);
        this.tipoImovel = tipoImovel;
    }

    @Override
    public void executar() {
        this.validar();
        System.out.println(">>> EXECUTANDO VISTORIA <<<");
        System.out.println("Vistoriando imóvel do tipo: " + tipoImovel);
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
