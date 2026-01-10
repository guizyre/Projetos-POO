package academy.guih.poo.javacore.classes.polimorfismo;

import java.time.LocalDate;

public class ServicoInstalacao extends Servico {
    private String equipamento;

    public ServicoInstalacao(String numeroSolicitacao, String nomeCliente, LocalDate data, String equipamento) {
        super(numeroSolicitacao, nomeCliente, data);
        this.equipamento = equipamento;
    }

    @Override
    public void executar() {
        validarCliente();
        System.out.println("[" + numeroSolicitacao + "] Instalando " + equipamento + " para " + nomeCliente);
        registrarExecucao();
    }

    @Override
    public void exibirResumo() {
        System.out.println("Instalação | Cliente: " + nomeCliente + " | Equipamento: " + equipamento + " | Executado: " + executado);
    }
}