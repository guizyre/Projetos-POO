package academy.guih.poo.javacore.classes.ProjetoFinal.Dominio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Implementação concreta para o serviço de Troca de Óleo.
 * <p>
 * Possui regra de negócio específica: Caminhões pagam uma taxa extra devido
 * ao maior volume de óleo e complexidade do filtro.
 * </p>
 */
public class ServicoTrocaOleo extends Servico{


    public ServicoTrocaOleo(Veiculo veiculo, String descricao, Double precoBase) {
        super(veiculo, descricao, precoBase);
    }

    /**
     * Orquestra a execução da troca de óleo.
     * <p>
     * Fluxo: Validação -> Trava de Segurança -> Simulação de Tempo -> Geração de Recibo.
     * </p>
     * @return false se os dados do veículo estiverem inválidos.
     */
    public boolean executar(){
        try {

            validar();

            // Impede a execução se a validação detectar dados críticos ausentes.
            if (veiculo.getMarca() == null || veiculo.getMarca().isBlank() ||
                    veiculo.getModelo() == null || veiculo.getModelo().isBlank() ||
                    veiculo.getAno() > 2025) {

                return false; // PARA TUDO AGORA!
            }

            // Simula o trabalho
            System.out.println("... Drenando óleo antigo do carro "+ veiculo.getModelo() + " ...");
            System.out.println("... Trocando filtro de óleo ...");
            System.out.println("... Abastecendo com óleo sintético novo ...");
            try {
                Thread.sleep(800); // <-- tratou a exceção aqui
            } catch (InterruptedException e) {
                System.out.println("Falha ao simular tempo de serviço.");
            }
            System.out.println("Troca de óleo concluída com sucesso!");

            gerarRecibo();
            return true;
        } catch (Exception e) {
            System.out.println("FALHA na Troca de Óleo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retorna o resumo utilizando {@link #calcularValor()} para garantir
     * que taxas aplicadas (como a de caminhão) apareçam no valor final.
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

    /**
     * Valida os campos obrigatórios.
     * Apenas notifica o erro no console; a interrupção do fluxo ocorre em {@link #executar()}.
     */
    @Override
    public void validar() {
        try {
            if (veiculo == null)
                throw new IllegalArgumentException("Veículo não informado!");

            if (veiculo.getModelo() == null || veiculo.getModelo().isBlank())
                throw new IllegalArgumentException("Modelo do veículo vazio!");

            if(veiculo.getAno() > 2025)
                throw new IllegalArgumentException("O Ano não deve ser maior que 2025");

            if (descricao == null || descricao.isBlank())
                throw new IllegalArgumentException("Descrição do serviço vazia!");

            if (precoBase == null || precoBase <= 0)
                throw new IllegalArgumentException("Preço base inválido!");

            System.out.println("=-=-=-= Validação concluida! =-=-=-=");
            // Faça uma validação simples (ex: modelo não pode ser vazio)
            // Se estiver errado, lance uma new Exception()
        } catch (Exception e) {
            System.out.println("Erro ao validar serviço: " + e.getMessage());
            // Print da mensagem de erro
            // Talvez salvar em um log
        }
    }

    /**
     * Calcula o preço final.
     * Regra: Se for Caminhão, aplica taxa extra definida na classe mãe.
     */
    @Override
    public double calcularValor(){
        try {
            validar();

            double preco = precoBase;
//            if(veiculo.getAno() < 2010) return preco * taxaVelho;
            if (veiculo instanceof Caminhao) {
                System.out.println("Aplicando taxa especial para caminhões...");
                preco += preco * taxaCaminhão;
            }

            return preco;

        } catch (Exception e) {
            System.out.println("Erro ao calcular valor: " + e.getMessage());
            return 0;
        }

    }


    @Override
    public void gerarRecibo() {
        // Gerando Data
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Identificação Única
        String idServico = "SRV-" + agora.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

        //  Tipo do Veículo
        String tipoVeiculo = veiculo.getClass().getSimpleName();
        // Resultado: "Carro", "Moto", "Caminhao", etc.

        System.out.println("\n============================================");
        System.out.println("        RECIBO DO SERVIÇO PRESTADO          ");
        System.out.println("============================================");
        System.out.println("ID do Serviço: " + idServico);
        System.out.println("Data: " + agora.format(formato));
        System.out.println("--------------------------------------------");
        System.out.println("Veículo: " + veiculo.getModelo());
        System.out.println("Tipo: " + tipoVeiculo);
        System.out.println("Ano: "  + veiculo.getAno());
        System.out.println("Descrição: " + descricao);
        System.out.println("Valor Total: R$ " + calcularValor());
        System.out.println("============================================");
        System.out.println("______________  Ofic. Gon  _________________");
    }


}
