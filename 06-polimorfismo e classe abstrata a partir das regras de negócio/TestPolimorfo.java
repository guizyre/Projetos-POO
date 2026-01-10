package academy.guih.poo.javacore.classes.testPoo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import academy.guih.poo.javacore.classes.polimorfismo.*;

public class TestPolimorfo {
    public static void main(String[] args) {
        List<Servico> listaServicos = new ArrayList<>();

        // Instalações
        listaServicos.add(new ServicoInstalacao("S1", "Guilherme", LocalDate.of(2025, 11, 5), "Ar-condicionado"));
        listaServicos.add(new ServicoInstalacao("S2", "Hillary", LocalDate.of(2025, 11, 6), "Internet Fibra"));
        listaServicos.add(new ServicoManutencao("S3", "Kayke", LocalDate.of(2025, 11, 7), false, "Troca de filtro"));
        listaServicos.add(new ServicoManutencao("S4", "Jose Guilherme", LocalDate.of(2025, 11, 8), true, "Pane elétrica"));
        listaServicos.add(new ServicoVistoria("S5", "Igor mago", LocalDate.of(2025, 11, 9), "Residencial"));
        listaServicos.add(new ServicoVistoria("S6", "João", LocalDate.of(2025, 11, 10), "Comercial"));
        listaServicos.add(new ServicoLimpeza("S7", "Bruno", LocalDate.of(2025, 11, 11), 85, false));
        listaServicos.add(new ServicoLimpeza("S8", "Felipe", LocalDate.of(2025, 11, 12), 150, true));

        ProcessadorSolicitacoes processador = new ProcessadorSolicitacoes(listaServicos);

        processador.executarTodos();
        System.out.println("\n==== RESUMO FINAL ====\n");
        processador.exibirResumos();
    }
}