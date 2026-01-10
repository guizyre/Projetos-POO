package academy.guih.poo.javacore.classes.herança.dominio;

public class IngressoCamarote extends Ingresso{
    protected String camarote = "Setor A";

    public IngressoCamarote(String codigo, double valorBase, String comprador, String camarote) {
        super(codigo, valorBase, comprador);
        this.camarote = camarote;
    }

    public IngressoCamarote(String codigo, double valorBase) {
        super(codigo, valorBase, "SEM_NOME");
        this.camarote = "Setor A";
    }

    @Override
    public double calcularValorFinal(double taxa) {
        double valorOriginal = valorBase;
        valorBase += 100;
        double resultado = super.calcularValorFinal(taxa);
        valorBase = valorOriginal;
        return resultado;
    }


    public void mudarSetor(String setor){
        if(setor == null || setor.isBlank()) System.out.println("Você ainda continuara no Setor A");
        else {
            camarote = setor;
        }
    }

    @Override
    public void imprimirDados(double taxa){
        super.imprimirDados(taxa);
    }
    //certo
}
