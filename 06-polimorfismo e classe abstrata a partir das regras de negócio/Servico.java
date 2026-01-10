package academy.guih.poo.javacore.classes.polimorfismo;

import java.time.LocalDate;

public abstract class Servico {
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

    public abstract void executar();

    public abstract void exibirResumo();

    protected void validarCliente() {
        if (nomeCliente.equalsIgnoreCase("SEM_NOME"))
            throw new IllegalStateException("Cliente não informado.");
    }

    protected void registrarExecucao() {
        executado = true;
        System.out.println("Serviço " + numeroSolicitacao + " executado com sucesso!\n");
    }

    public boolean isExecutado() {
        return executado;
    }
}