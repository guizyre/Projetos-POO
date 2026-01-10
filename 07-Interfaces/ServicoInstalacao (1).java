package academy.guih.poo.javacore.classes.Interface.Dominio;

import java.time.LocalDate;

public class ServicoInstalacao extends Servico implements Notificavel {
    private final String equipamento;

    public ServicoInstalacao(String numeroSolicitacao, String nomeCliente, LocalDate data, String equipamento) {
        super(numeroSolicitacao, nomeCliente, data);
        this.equipamento = equipamento;
    }

    @Override
    public void executar() {
        this.validar();
        System.out.println(">>> EXECUTANDO INSTALAÇÃO <<<");
        System.out.println("Instalando: " + equipamento + " no endereço do cliente " + nomeCliente);
        registrarExecucao();
    }

    @Override
    public void exibirResumo() {
        super.exibirResumo();
        System.out.println("   -> Detalhe: Instalação de " + equipamento);
    }

    @Override
    public void enviarNotificacao() {
        System.out.println("[NOTIFICAÇÃO] Olá " + nomeCliente + ", seu equipamento " + equipamento + " foi instalado e testado.");
    }
}
