package academy.guih.poo.javacore.classes.ProjetoFinal.Dominio;

import java.util.Scanner;

public class Oficina {

    /**
     * Método auxiliar para criar o delay de 0.8s.
     */
    private void esperar(int valor) {
        try {
            Thread.sleep(valor);
        } catch (InterruptedException e) {
            System.out.println("Erro no timer");
        }
    }

    /**
     * O MAESTRO CHAMA APENAS ESTE MÉTODO.
     * Este método coordena: Perguntar dados -> Validar -> Processar (Imprimir bonito).
     */
    public void realizarAtendimento(Scanner sc) {
        // 1. Coleta dados do veículo
        Veiculo veiculo = capturarDadosVeiculo(sc);
        if (veiculo == null) return; // Se cancelou ou erro, para aqui.

        // 2. Coleta dados do serviço
        Servico servico = capturarDadosServico(sc, veiculo);
        if (servico == null) return; // Se cancelou ou erro, para aqui.

        processarOficina(veiculo, servico);
    }

    private void processarOficina(Veiculo veiculo, Servico servico) {

        if (veiculo == null || servico == null) {
            System.out.println("Dados inválidos. Nada informado. Encerrando atendimento.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("      OFICINA GON - INÍCIO DE ATENDIMENTO");
        System.out.println("========================================");
        esperar(800);
        System.out.println("CLIENTE / VEÍCULO:");
        System.out.println("Marca/Modelo: " + veiculo.getMarca() + " " + veiculo.getModelo());
        System.out.println("Placa: " + veiculo.getPlaca() + " | Ano: " + veiculo.getAno());
        System.out.println("----------------------------------------\n");
        esperar(600);

        System.out.println(">>> INICIANDO EXECUÇÃO DO SERVIÇO: " + servico.descricao);
        esperar(800);

        // 2) EXECUTAR SOMENTE UMA VEZ
        boolean sucesso = servico.executar();
        if (!sucesso) {
            System.out.println("❌ O serviço NÃO pôde ser concluído.");
            return;
        }
        System.out.println("✔ Execução concluída.");
        esperar(600);

        // 3) SE O VEÍCULO POSSUI MANUTENÇÃO ESPECÍFICA
        if (veiculo instanceof Manutinivel manutinivel) {
            System.out.println("✔ Executando manutenção específica do veículo...");
            esperar(800);
            manutinivel.realizarManutencao();
            esperar(800);

            System.out.println("✔ Emitindo relatório final da manutenção...");
            manutinivel.emitirRelatorio();
            esperar(500);
        }

        // 4) RESUMO FINAL
        System.out.println("\n===== RESUMO FINAL DO SERVIÇO =====");
        System.out.println(servico.toResumo());
        esperar(600);

        if (!servico.getEtapas().isEmpty()) {
            System.out.println("Etapas realizadas:");
            System.out.println("Com cada serviço valendo 20 Reais adicionais :)");
            for (String etapa : servico.getEtapas()) {
                System.out.println(" • " + etapa);
                try { Thread.sleep(300); } catch (Exception e) {}
            }
        }

        System.out.println();
        esperar(50);
        System.out.println("                   Obrigado por escolher a");
        esperar(50);
        System.out.println("                         Ofic. Gon!");
        esperar(50);
        System.out.println("===================================================");
    }


    private Veiculo capturarDadosVeiculo(Scanner sc) {
        System.out.println("\n--- 📋 CADASTRO DE VEÍCULO ---");
        esperar(800);
        System.out.print("Qual tipo? [carro, moto ou caminhao]: ");
        String tipo = sc.nextLine().trim().toLowerCase();

        esperar(50);
        System.out.print("Marca: ");   String marca = sc.nextLine().trim();
        esperar(50);
        System.out.print("Modelo: ");  String modelo = sc.nextLine().trim();
        esperar(50);
        System.out.print("Placa: ");   String placa = sc.nextLine().trim();
        esperar(50);
        System.out.print("Ano: ");
        int ano = 0;
        try {
            ano = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠ Ano inválido, definindo como 2025.");
            ano = 2025;
        }

        switch (tipo) {
            case "carro":
                esperar(500);
                System.out.print("É elétrico? (s/n): ");
                boolean eletrico = sc.nextLine().trim().equalsIgnoreCase("s");
                return new Carro(marca, modelo, ano, placa, eletrico);
            case "moto":
                return new Moto(marca, modelo, ano, placa);
            case "caminhao":
                esperar(500);
                System.out.println("Qual é a sua carga atual [Kg]? ");
                double carga = 0;
                try {
                    carga = Double.parseDouble(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("⚠ Valor inválido! Considerando vazio (0kg).");
                    carga = 0;
                }
                return new Caminhao(marca, modelo, ano, placa, carga);
            default:
                esperar(500);
                System.out.println("❌ Tipo de veículo desconhecido!");
                return null;
        }
    }

    private Servico capturarDadosServico(Scanner sc, Veiculo veiculo) {
        System.out.println("\n--- 🛠 ESCOLHA O SERVIÇO ---");
        esperar(100);
        System.out.println("1 - Troca de Óleo");
        esperar(100);
        System.out.println("2 - Lavagem");
        esperar(100);

        System.out.println("3 - Revisão");
        esperar(100);

        System.out.print("Opção: ");

        int opcao = 0;
        try {
            opcao = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return null;
        }

        esperar(800);
        switch (opcao) {
            case 1:
                if (veiculo instanceof Carro && ((Carro) veiculo).getEletrico()) {
                    System.out.println("❌ REGRA DE NEGÓCIO: Carro elétrico não troca óleo!");
                    return null;
                }
                return new ServicoTrocaOleo(veiculo, "Troca de Óleo Premium", 200.0);
            case 2:
                return new ServicoLavagem(veiculo, "Lavagem Completa", 50.0);
            case 3:
                return new ServicoRevisao(veiculo, "Revisão Geral", 100.0, 20.0);
            default:
                System.out.println("❌ Opção inválida.");
                return null;
        }
    }
}