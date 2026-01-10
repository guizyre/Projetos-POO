package academy.guih.poo.javacore.classes.Interface.Dominio;

import java.util.List;

public class ProcessadorSolicitacoes {
    private List<IServico> servicos;

    public ProcessadorSolicitacoes(List<IServico> servicos) {
        this.servicos = servicos;
    }

    public void executarTodos() {
        System.out.println("------ INICIANDO PROCESSAMENTO DE SOLICITAÇÕES ------\n");
        for (IServico s : servicos) {
            try {
                s.executar();
            } catch (Exception e) {
                System.out.println("ERRO CRÍTICO: " + e.getMessage());
            }
        }
    }

    public void imprimirRelatorioGeral() {
        System.out.println("------ RELATÓRIO GERAL ------");
        for (IServico s : servicos) {
            s.exibirResumo();
        }
        System.out.println("-----------------------------");
    }
}
