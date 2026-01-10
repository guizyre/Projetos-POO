package academy.guih.poo.javacore.classes.Interface.Dominio;

import java.time.LocalDate;

public abstract class Servico implements IServico{
    protected String numeroSolicitacao;
    protected String nomeCliente;
    protected LocalDate data;
    protected boolean executado;

    public Servico(String numeroSolicitacao, String nomeCliente, LocalDate data) {
        if (numeroSolicitacao == null || numeroSolicitacao.isBlank())
            throw new IllegalArgumentException("Número de solicitação inválido.");
        if (nomeCliente == null || nomeCliente.isBlank())
            throw new IllegalArgumentException("Nome do cliente inválido.");
        if (data == null)
            throw new IllegalArgumentException("Data inválida.");

        this.numeroSolicitacao = numeroSolicitacao;
        this.nomeCliente = nomeCliente;
        this.data = data;
        this.executado = false;
    }

    public void validar() {
        if (nomeCliente.equalsIgnoreCase("SEM_NOME")) {
            throw new IllegalStateException("Cliente não informado ou inválido.");
        }
        System.out.println("[LOG] Validação comum concluída para: " + numeroSolicitacao);
    }

    @Override
    public abstract void executar();

    @Override
    public void exibirResumo() {
        System.out.println("Resumo Base | Num: " + numeroSolicitacao + " | Cliente: " + nomeCliente);
    }

    protected void registrarExecucao() {
        this.executado = true;
        System.out.println("[LOG] Status atualizado para EXECUTADO.\n");
    }
}
