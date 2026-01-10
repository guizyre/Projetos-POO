package academy.guih.poo.javacore.classes.polimorfismo;

import academy.guih.poo.javacore.classes.polimorfismo.Servico;
import java.util.List;

public class ProcessadorSolicitacoes {
    private List<Servico> servicos;

    public ProcessadorSolicitacoes(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public void executarTodos() {
        System.out.println("=== Executand Solicitações ===\n");
        for (Servico s : servicos) {
            try {
                s.executar();
            } catch (Exception e) {
                System.out.println("Erro ao executar " + s.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }

    public void exibirResumos() {
        for (Servico s : servicos) {
            s.exibirResumo();
        }
    }
}