package academy.guih.poo.javacore.classes.herança.dominio;

public class IngressoComum extends Ingresso{
    private boolean comprovouEstudante = false;

    public IngressoComum(String codigo, double valorBase, String comprador, boolean comprovouEstudante) {
        super(codigo, valorBase, comprador);
        this.comprovouEstudante = comprovouEstudante;
    }

    public IngressoComum(String codigo, double valorBase, boolean comprovouEstudante) {
        super(codigo, valorBase, "SEM_NOME");
        this.comprovouEstudante = comprovouEstudante;
    }

    public void aplicarDescontoEstudante(boolean comprovou){
        if (comprovou == true) comprovouEstudante = true;
    }

    @Override
    public double calcularValorFinal(double taxa) {
        if (comprovouEstudante) {
            valorBase *= 0.5;
        }
        return super.calcularValorFinal(taxa);
    }
//certo
}
