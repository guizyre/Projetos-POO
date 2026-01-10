package academy.guih.poo.javacore.classes.Interface.Dominio;


import java.time.LocalDate;

public class ServicoManutencao extends Servico implements Notificavel {
    private final boolean urgente;
    private final String descricaoDefeito;

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
        this.validar();
        System.out.println(">>> EXECUTANDO MANUTENÇÃO <<<");
        System.out.println("Prioridade: " + (urgente ? "URGENTE" : "Normal"));
        System.out.println("Reparo: " + descricaoDefeito);
        registrarExecucao();
    }

    @Override
    public void exibirResumo() {
        super.exibirResumo();
        System.out.println("   -> Detalhe: Manutenção (" + descricaoDefeito + ")");
    }

    @Override
    public void enviarNotificacao() {
        String prefixo = urgente ? "[SMS URGENTE]" : "[EMAIL]";
        System.out.println(prefixo + " Sr(a) " + nomeCliente + ", a manutenção foi concluída.");
    }
}