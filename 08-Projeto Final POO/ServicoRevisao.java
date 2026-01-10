package academy.guih.poo.javacore.classes.ProjetoFinal.Dominio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Implementação do serviço de Revisão Geral.
 * <p>
 * Funciona como um <b>Menu Interativo</b> (Shopping List):
 * O usuário escolhe múltiplos itens de uma lista pré-definida e o valor
 * vai sendo acumulado na variável {@code totalItens}.
 * </p>
 */
public class ServicoRevisao extends Servico {

    // Tabelas estáticas de preços e nomes (poderiam vir de um Banco de Dados)
    private final String[] tabelaServicos = {
            "Troca de pneus",
            "Troca de filtros",
            "Revisão dos freios",
            "Alinhamento e balanceamento",
            "Verificação da suspensão",
            "Troca de velas",
            "Scan eletrônico",
            "Revisão elétrica",
            "Troca do fluido de freio",
            "Revisão completa"
    };


    // Preço correspondente de cada item
    private final double[] tabelaValores = {
            1200.0, 90.0, 150.0, 80.0, 200.0,
            70.0, 100.0, 130.0, 110.0, 300.0
    };

    // Acumuladores de escolha
    private final StringBuilder itensEscolhidos = new StringBuilder();
    private double totalItens = 0;

    public ServicoRevisao(Veiculo veiculo, String descricao, Double precoBase, double custoMateriais) {
        super(veiculo, descricao, precoBase, custoMateriais);
    }

    /**
     * Gerencia o fluxo da revisão.
     * <p>
     * Fluxo: Validação -> <b>Trava de Segurança</b> -> Menu de Opções -> Seleção -> Recibo.
     * </p>
     */
    @Override
    public boolean executar() {
        try {
            validar();
            // TRAVA DE SEGURANÇA
            // Impede que o menu abra se o veículo for inválido.
            if (veiculo.getMarca() == null || veiculo.getMarca().isBlank() ||
                    veiculo.getModelo() == null || veiculo.getModelo().isBlank() ||
                    veiculo.getAno() > 2025) { // <--- ADICIONE ESTA LINHA

                return false; // PARA TUDO AGORA!
            }

            mostrarTabela();
            selecionarItens();
            gerarRecibo();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao executar revisão: " + e.getMessage());
            return false;
        }
    }

    private void mostrarTabela() {
        System.out.println("========= LISTA DE SERVIÇOS DISPONÍVEIS =========");


        for (int i = 0; i < tabelaServicos.length; i++) {
            System.out.printf("%d - %-30s R$ %.2f%n",
                    i + 1, tabelaServicos[i], tabelaValores[i]);
        }


        System.out.println("Digite os números dos serviços desejados.");
        System.out.println("Digite 0 para finalizar.");
        System.out.println("===================================================");
    }

    /**
     * Loop interativo para seleção de serviços.
     * Trata erros de entrada (ex: digitar letras em vez de números) e acumula o valor total.
     */
    private void selecionarItens() {
        Scanner sc = new Scanner(System.in);
        int opcao;

        while (true) {
            System.out.print("Escolha um item (0 para terminar): ");
            String entrada = sc.nextLine().trim();

            // Entrada vazia → usuário não digitou nada
            if (entrada.isBlank()) {
                System.out.println("Nenhum valor digitado!");
                System.out.println("Deseja tentar novamente? (s/n): ");

                String retry = sc.nextLine().trim().toLowerCase();
                if (retry.equals("s")) {
                    continue; // volta a perguntar
                } else {
                    System.out.println("Encerrando seleção de itens...");
                    break;
                }
            }

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                continue;
            }

            if (opcao == 0) break;

            if (opcao < 1 || opcao > 10) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            String item = tabelaServicos[opcao - 1];
            double valor = tabelaValores[opcao - 1];

            itensEscolhidos.append(" - ").append(item)
                    .append(" | R$ ").append(valor).append("\n");

            totalItens += valor;
            adicionarEtapa(item);

            System.out.println(item + " adicionado!");
        }
    }

    /**
     * Exibe o resumo final chamando {@link #calcularValor()}.
     * Isso garante que a taxa de Caminhão Pesado apareça no resumo se for aplicada.
     */
    @Override
    public String toResumo() {
        return String.format(
                "=== RESUMO DO SERVIÇO ===\n" +
                        "Veículo:   %s\n" +
                        "Descrição: %s\n" +
                        "Preço:     R$ %.2f\n" +
                        "=========================",
                veiculo.getModelo(), descricao, calcularValor()
        );
    }

    @Override
    public void validar() {
        try{
            if(veiculo == null )
                throw new IllegalArgumentException("O veiculo não deve ser nulo!");

            if(veiculo.getModelo() == null || veiculo.getModelo().isBlank())
                throw new IllegalArgumentException("O Modelo deve existir ou não deve ser nulo!");

            if(veiculo.getMarca() == null || veiculo.getMarca().isBlank())
                throw new IllegalArgumentException("A Marca deve não pode ser nula!");

            if (precoBase == null || precoBase <= 0)
                throw new IllegalArgumentException("Preço base inválido!");

            System.out.println("=-=-=-= Validação concluida! =-=-=-=");
        } catch(Exception e){
            System.out.println("Erro ao validar o veiculo!" +  e.getMessage());
        }

    }

    /**
     * Calcula o valor total somando 4 componentes:
     * <ol>
     * <li>Preço Base (Mão de obra fixa);</li>
     * <li>Custo de Materiais;</li>
     * <li>Total dos Itens escolhidos no menu;</li>
     * <li>Taxa Extra se for Caminhão Pesado (> 20 ton).</li>
     * </ol>
     */
    @Override
    public double calcularValor() {
        double total = precoBase + custoMateriais + totalItens;
        if (veiculo instanceof Caminhao) {
            Caminhao caminhao = (Caminhao) veiculo;
            if (caminhao.verificaPesado()) {
                System.out.println(">> Adicional de Carga Pesada (Revisão): + R$ 200,00");
                total += 200.0;
            }
        }
        return total;
    }


    @Override
    public void gerarRecibo() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String idServico = "SRV-" + agora.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));


        System.out.println("============================================");
        System.out.println(" RECIBO DE REVISÃO ");
        System.out.println("============================================");
        System.out.println("ID do Serviço: " + idServico);
        System.out.println("Data: " + agora.format(formato));
        System.out.println("--------------------------------------------");
        System.out.println("Veículo: " + veiculo.getModelo());
        System.out.println("Ano: " + veiculo.getAno());
        System.out.println("Descrição: " + descricao);
        System.out.println("--------------------------------------------");
        System.out.println("Itens Revisados:");
        System.out.println(itensEscolhidos);
        // Exibe se houve taxa extra de forma visual (opcional, mas ajuda)
        if(veiculo instanceof Caminhao && ((Caminhao) veiculo).verificaPesado()){
            System.out.println("(!) Inclui taxa de carga pesada.");
        }
        System.out.println("--------------------------------------------");
        System.out.println("Valor Total: R$ " + calcularValor());
        System.out.println("============================================");
        System.out.println("__________ Ofic. Gon __________");
    }
}
