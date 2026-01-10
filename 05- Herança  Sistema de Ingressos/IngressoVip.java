package academy.guih.poo.javacore.classes.herança.dominio;

public class IngressoVip extends Ingresso {
    private boolean acessoVip = true;

    public IngressoVip(String codigo, double valorBase, String comprador, boolean acessoVip) {
        super(codigo, valorBase, comprador);
        this.acessoVip = acessoVip;
    }

    public IngressoVip(String codigo, double valorBase) {
        super(codigo, valorBase, "SEM_NOME");
        this.acessoVip = true;
    }

    @Override
    public double calcularValorFinal(double taxa){
        double valorPai = super.calcularValorFinal(taxa);
        if(acessoVip == true) valorPai *= 2;
        return valorPai;
    }

    public boolean temAcessoVip(){
        if(acessoVip == true){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void imprimirDados(double taxa){
        super.imprimirDados(taxa);

        temAcessoVip();
    }
    //certo
}
