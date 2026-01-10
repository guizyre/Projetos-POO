package academy.guih.poo.javacore.classes.herança.dominio;


public class Ingresso {
    protected String codigo;
    protected double valorBase;
    protected String comprador;

    public Ingresso(String codigo, double valorBase, String comprador) {
        if (codigo == null || codigo.isBlank())
            throw new IllegalArgumentException("O código não pode estar vazio");
        if (valorBase < 10)
            throw new IllegalArgumentException("O valor do ingresso deve ser no mínimo R$ 10.00");
        if (comprador == null || comprador.isBlank())
            throw new IllegalArgumentException("O comprador deve ser válido e não vazio");

        this.codigo = codigo;
        this.valorBase = valorBase;
        this.comprador = comprador;
    }

    public Ingresso(String codigo, double valorBase) {
        this(codigo, valorBase, "SEM_NOME");
    }
    public Ingresso(double valorBase) {
        this("Auto-" + System.currentTimeMillis(), valorBase, "Sem_Nome");
    }

    public double calcularValorFinal(double taxa) {
        if (taxa < 0 || taxa > 0.2)
            throw new IllegalArgumentException("A taxa deve estar entre 0.0 e 0.2 (0% a 20%)");
        return valorBase + (valorBase * taxa);
    }

    public void imprimirDados(double taxa){
        System.out.println("Codigo: " + this.codigo);
        System.out.println("comprador: "  + this.comprador);
        System.out.printf("Valor final: R$ %.2f%n", calcularValorFinal(taxa));
    }

    public String getResumo(double taxa){
        return getClass().getSimpleName() + " - " + this.codigo + " - " + this.comprador;
    }
    //certo
}
