package academy.guih.poo.javacore.classes.dominio;

public class Pedido {
    private Item[] itens;
    private int qtd;

    // Estado do cupom
    private boolean cupomAplicado = false;
    private String cupomCodigo = "";
    private double descontoPercent = 0.0; // 0, 5 ou 10

    public Pedido() { this(10); }

    public Pedido(int capacidade) {
        if (capacidade <= 0) throw new IllegalArgumentException("Capacidade inválida");
        this.itens = new Item[capacidade];
        this.qtd = 0;
    }

    public boolean adicionarItem(Item item) {
        if (item == null) return false;
        if (qtd == itens.length) return false;
        itens[qtd++] = item;
        return true;
    }

    public void listarItens() {
        if (qtd == 0) {
            System.out.println("(sem itens)");
            return;
        }
        for (int i = 0; i < qtd; i++) {
            System.out.println((i + 1) + ". " + itens[i]);
        }
    }

    public double calcularSubtotal() {
        double total = 0.0;
        for (int i = 0; i < qtd; i++) {
            total += itens[i].getPreco();
        }
        return total;
    }

    public boolean aplicarCupom(String codigo) {
        if (cupomAplicado) return false;
        if (qtd == 0) return false;

        switch (codigo.toUpperCase()) {
            case "PROMO10":
                descontoPercent = 10.0;
                break;
            case "PROMO5":
                descontoPercent = 5.0;
                break;
            default:
                return false;
        }

        cupomAplicado = true;
        cupomCodigo = codigo.toUpperCase();
        return true;
    }

    public double calcularTaxa(double taxaPercent) {
        if (taxaPercent < 0 || taxaPercent > 20)
            throw new IllegalArgumentException("Taxa inválida (0 a 20)");

        double subtotal = calcularSubtotal();
        double desconto = subtotal * (descontoPercent / 100.0);
        double baseTaxa = subtotal - desconto;

        return baseTaxa * (taxaPercent / 100.0);
    }

    public double calcularTotal(double taxaPercent) {
        double subtotal = calcularSubtotal();
        double desconto = subtotal * (descontoPercent / 100.0);
        double taxa = calcularTaxa(taxaPercent);
        return subtotal - desconto + taxa;
    }

    public String gerarRelatorio(double taxaPercent) {
        double subtotal = calcularSubtotal();
        double desconto = subtotal * (descontoPercent / 100.0);
        double taxa = calcularTaxa(taxaPercent);
        double total = calcularTotal(taxaPercent);

        StringBuilder sb = new StringBuilder();
        sb.append("--- Recibo ---\n");
        if (qtd == 0) {
            sb.append("(sem itens)\n");
        } else {
            for (int i = 0; i < qtd; i++) {
                sb.append(String.format("%d. %s\n", (i + 1), itens[i].toString()));
            }
        }
        sb.append("Subtotal: R$ ").append(moeda(subtotal)).append("\n");
        if (cupomAplicado) {
            sb.append("Cupom ").append(cupomCodigo)
                    .append(": -R$ ").append(moeda(desconto)).append("\n");
        } else {
            sb.append("Cupom: (nenhum)\n");
        }
        sb.append(String.format("Taxa (%.0f%%): +R$ %s\n", taxaPercent, moeda(taxa)));
        sb.append("TOTAL: R$ ").append(moeda(total)).append("\n");
        return sb.toString();
    }

    public static String moeda(double v) {
        return String.format("%.2f", v);
    }

    // Getters úteis para testes
    public boolean isCupomAplicado() { return cupomAplicado; }
    public String getCupomCodigo() { return cupomCodigo; }
    public double getDescontoPercent() { return descontoPercent; }
}
