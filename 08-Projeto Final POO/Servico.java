package academy.guih.poo.javacore.classes.ProjetoFinal.Dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata base para todos os serviços da oficina.
 * <p>
 * Centraliza os dados comuns (Veículo, Preço, Descrição) e implementa os contratos
 * Financeiro ({@link Pagavel}) e Operacional ({@link IExecutor}).
 * </p>
 */
public abstract class Servico implements Pagavel, IExecutor {
    protected Veiculo veiculo;
    protected String descricao;
    protected Double precoBase;

    // Variáveis de controle financeiro
    private double valorFinal;
    protected double custoMateriais;

    // Taxas percentuais aplicáveis (0.2 = 20%)
    protected double taxaMuitosujo = 0.2;
    final double taxaVelho = 0.1;
    final double taxaCaminhão = 0.2;

    // Histórico de passos executados no serviço
    private List<String> etapas = new ArrayList<>();

    /**
     * Construtor principal. Valida dados obrigatórios imediatamente.
     * @throws IllegalArgumentException se Veículo, Descrição ou Preço forem inválidos.
     */
    public Servico(Veiculo veiculo, String descricao, Double precoBase) {
        if (veiculo == null) {
            throw new IllegalArgumentException("Não é possível criar serviço sem Veículo.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição do serviço é obrigatória.");
        }
        if (precoBase == null || precoBase <= 0) {
            throw new IllegalArgumentException("O preço base deve ser maior que zero.");
        }

        this.veiculo = veiculo;
        this.descricao = descricao;
        this.precoBase = precoBase;
    }

    /**
     * Construtor sobrecarregado para serviços que já iniciam com custo de material.
     */
    public Servico(Veiculo veiculo, String descricao, Double precoBase, double custoMateriais) {
        this.veiculo = veiculo;
        this.descricao = descricao;
        this.precoBase = precoBase;
        this.custoMateriais = custoMateriais;
    }


    // Métodos Abstratos (Obrigam as filhas a implementar a lógica específica)
    public abstract boolean executar();

    public abstract String toResumo();

    public abstract void validar();

    @Override
    public abstract double calcularValor();

    @Override
    public abstract void gerarRecibo();



    /**
     * Registra uma etapa realizada no histórico do serviço.
     * Útil para exibir no relatório final o que foi feito passo a passo.
     */
    public void adicionarEtapa(String etapa) {
        etapas.add(etapa);
    }

    public List<String> getEtapas() {
        return etapas;
    }


    @Override
    public String toString() {
        return "Servico{" +
                "veiculo=" + veiculo.getModelo() +
                ", descricao='" + descricao + '\'' +
                ", precoBase=" + precoBase +
                ", valorFinal=" + valorFinal +
                ", custoMateriais=" + custoMateriais +
                ", taxaMuitosujo=" + taxaMuitosujo +
                ", taxaVelho=" + taxaVelho +
                ", taxaCaminhão=" + taxaCaminhão +
                ", etapas=" + etapas +
                '}';
    }
}
