package academy.guih.poo.javacore.classes.Interface.test;
import academy.guih.poo.javacore.classes.Interface.Dominio.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class testInterface {
    public static void main(String[] args) {
        LocalDate hoje = LocalDate.now();

        ServicoLimpeza s1 = new ServicoLimpeza("LIMP-001", "Tech Corp", hoje, 100, false);

        ServicoInstalacao s2 = new ServicoInstalacao("INST-055", "João Silva", hoje, "Ar Condicionado");
        ServicoManutencao s3 = new ServicoManutencao("MANUT-999", "Maria Souza", hoje, true, "Vazamento");

        ServicoVistoria s4 = new ServicoVistoria("VIST-100", "Pedro Imóveis", hoje, "Comercial");



        List<IServico> listaServicos = new ArrayList<>();
        listaServicos.add(s1);
        listaServicos.add(s2);
        listaServicos.add(s3);
        listaServicos.add(s4);

        ProcessadorSolicitacoes processador = new ProcessadorSolicitacoes(listaServicos);

        System.out.println("--- 1. EXECUTANDO SERVIÇOS (Ação Principal + Resumo) ---");
        processador.executarTodos();
        processador.imprimirRelatorioGeral();

        System.out.println("\n------------------------------------------------------\n");


        List<Notificavel> listaNotificaveis = new ArrayList<>();

        listaNotificaveis.add(s2); // Instalação
        listaNotificaveis.add(s3); // Manutenção

        ProcessadorNotificacoes notificador = new ProcessadorNotificacoes();

        System.out.println("--- 2. ENVIANDO NOTIFICAÇÕES (Apenas Notificáveis) ---");
        notificador.enviarTodas(listaNotificaveis);
    }
}
