package academy.guih.poo.javacore.classes.ProjetoFinal.Dominio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Implementação concreta para o serviço de Lavagem.
 * <p>
 * Diferente dos outros serviços, este possui <b>interatividade</b>:
 * pergunta ao usuário o estado do veículo (muito sujo) durante o cálculo,
 * o que impacta o valor final.
 * </p>
 */
public class ServicoLavagem extends Servico{

    // Armazena o resultado do cálculo para evitar rodar o Scanner múltiplas vezes.
    protected double valorFinal;

    public ServicoLavagem(Veiculo veiculo, String descricao, Double precoBase) {
        super(veiculo, descricao, precoBase);
    }

    /**
     * Gerencia o fluxo da lavagem.
     * <p>
     * Inclui uma <b>Trava de Segurança</b> manual para impedir que o programa
     * faça perguntas ao usuário se os dados do veículo já estiverem inválidos.
     * </p>
     */
    @Override
    public boolean executar() {
        try {
            validar();
            // TRAVA DE SEGURANÇA
            // Verifica manualmente se há dados inválidos críticos.
            // Se houver, retorna false ANTES de chamar calcularValor() (onde está o Scanner).
            if (veiculo.getMarca() == null || veiculo.getMarca().isBlank() ||
                    veiculo.getModelo() == null || veiculo.getModelo().isBlank() ||
                    veiculo.getAno() > 2025) { // <--- ADICIONE ESTA LINHA

                return false;
            }
            System.out.println(toResumo());
            System.out.println("=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            // O Scanner roda aqui dentro
            this.valorFinal = calcularValor();

            System.out.println("Realizando Lavagem do veículo "
                    + veiculo.getModelo() + "...");
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                System.out.println("Falha ao simular tempo de serviço.");
            }
            System.out.println("Lavagem concluída com sucesso!");
            gerarRecibo();

        }catch (Exception e){
            System.out.println("Erro ao executar o ServicoLavagem");
            return false;
        }

        return true;
    }

    /**
     * Exibe o resumo do serviço.
     * <p>
     * <b>Atenção:</b> Utiliza o campo {@code valorFinal} armazenado.
     * Não chamamos {@code calcularValor()} aqui para não reativar o Scanner
     * e perguntar novamente se o carro está sujo.
     * </p>
     */
    @Override
    public String toResumo() {
        double valorParaMostrar = (this.valorFinal > 0) ? this.valorFinal : this.precoBase;
        return String.format(
                "=== RESUMO DO SERVIÇO ===\n" +
                        "Veículo:   %s\n" +
                        "Descrição: %s\n" +
                        "Preço:     R$ %.2f\n" +
                        "=========================",
                veiculo.getModelo(), descricao, valorParaMostrar
        );
    }

    @Override
    public void validar() {
        try {
            if(veiculo == null )
                throw new IllegalArgumentException("O veiculo não deve ser nulo!");
            if(veiculo.getModelo() == null || veiculo.getModelo().isBlank())

                throw new IllegalArgumentException("O Modelo deve existir ou não deve ser nulo!");
            if(veiculo.getMarca() == null || veiculo.getMarca().isBlank())

                throw new IllegalArgumentException("A Marca deve não pode ser nula!");

            if(veiculo.getAno() > 2025)
                throw new IllegalArgumentException("O Ano não deve ser maior que 2025!");


            System.out.println("=-=-=-= Validação concluida! =-=-=-=");

        }catch (Exception e){
            System.out.println("Erro ao validar serviço: " + e.getMessage());
        }

    }

    /**
     * Calcula o valor da lavagem com base na entrada do usuário.
     * <p>
     * Regras aplicadas:
     * <ul>
     * <li>Taxa de sujeira extrema;</li>
     * <li>Taxa acumulativa se for Caminhão E estiver sujo;</li>
     * <li>Taxa fixa se for Caminhão com Carga Pesada (>20 ton).</li>
     * </ul>
     * </p>
     */
    @Override
    public double calcularValor() {
        try {
            validar();

            Scanner sc = new Scanner(System.in);

            System.out.print("O veículo está muito sujo? (s/n): ");
            String resposta = sc.nextLine().trim().toLowerCase();

            // ====== PROTEÇÃO PARA ENTRADA VAZIA ======
            if (resposta.isBlank()) {
                System.out.println("Nenhuma resposta digitada. Considerando como 'não'.");
                resposta = "n";
            }

            boolean muitoSujo = resposta.equals("s");

            double calculo = precoBase;

            if (muitoSujo) {
                System.out.println("Aplicando taxa adicional de sujeira!...");
                calculo += (calculo * taxaMuitosujo); // Multiplica por 1.2 (exemplo)

                // Taxa extra se for caminhão E estiver sujo
                if (veiculo instanceof Caminhao) {
                    System.out.println("Aplicando multiplicador de caminhão sujo...");
                    calculo += (calculo * taxaMuitosujo);
                }
            }

            // --- LÓGICA 2: PESO (Separada da sujeira) ---
            if (veiculo instanceof Caminhao) {
                Caminhao caminhao = (Caminhao) veiculo;

                if (caminhao.verificaPesado()) {
                    System.out.println("⚠ Caminhão com CARGA PESADA (" + caminhao.getCarga() + "kg).");
                    System.out.println("   -> Adicionando taxa de sobrecarga: R$ 200,00");
                    calculo += 200.0;
                }
            }

            // CORREÇÃO CRUCIAL: Retorna a variável local 'calculo', não 'valorFinal'
            return calculo;

        } catch (Exception e) {
            System.out.println("Erro ao calcular valor: " + e.getMessage());
            return 0;
        }
    }


    @Override
    public void gerarRecibo() {
        // ----- Gerando Data -----
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // ----- Identificação Única -----
        String idServico = "SRV-" + agora.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

        // ----- Tipo do Veículo -----
        String tipoVeiculo = veiculo.getClass().getSimpleName();
        // Resultado: "Carro", "Moto", "Caminhao", etc.

        System.out.println("============================================");
        System.out.println("        RECIBO DO SERVIÇO PRESTADO          ");
        System.out.println("============================================");
        System.out.println("ID do Serviço: " + idServico);
        System.out.println("Data: " + agora.format(formato));
        System.out.println("--------------------------------------------");
        System.out.println("Veículo: " + veiculo.getModelo());
        System.out.println("Tipo: " + tipoVeiculo);
        System.out.println("Ano: "  + veiculo.getAno());
        System.out.println("Descrição: " + descricao);
        // Usa o valorFinal que já foi calculado no método executar()
        System.out.println("Valor Total: R$ " + this.valorFinal);
        System.out.println("============================================");
        System.out.println("______________  Ofic. Gon  _________________");
    }

}
