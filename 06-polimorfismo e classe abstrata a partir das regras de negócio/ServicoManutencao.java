package academy.guih.poo.javacore.classes.polimorfismo;

import java.time.LocalDate;

public class ServicoManutencao extends Servico {
    private boolean urgente;
    private String descricaoDefeito;

    public ServicoManutencao(String numeroSolicitacao, String nomeCliente, LocalDate data,
                             boolean urgente, String descricaoDefeito) {
        super(numeroSolicitacao, nomeCliente, data);
        if (descricaoDefeito == null || descricaoDefeito.isBlank())
            throw new IllegalArgumentException("Descrição do defeito obrigatória.");
        this.urgente = urgente;
        this.descricaoDefeito = descricaoDefeito;
    }

    @Override
    public void executar() {
        validarCliente();
        System.out.println("[" + numeroSolicitacao + "] Manutenção " + (urgente ? "URGENTE" : "padrão") +
                " para " + nomeCliente + " | Defeito: " + descricaoDefeito);
        registrarExecucao();
    }

    @Override
    public void exibirResumo() {
        System.out.println("Manutenção | Cliente: " + nomeCliente + " | Urgente: " + (urgente ? "Sim" : "Não") +
                " | Defeito: " + descricaoDefeito + " | Executado: " + executado);
    }
}