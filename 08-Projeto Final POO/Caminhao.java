package academy.guih.poo.javacore.classes.ProjetoFinal.Dominio;

/**
 * Representa um Caminhão.
 * Possui atributo de carga que influencia diretamente no cálculo de preços (taxa de sobrepeso).
 */
public class Caminhao extends Veiculo implements Manutinivel {
    protected final double carga;

    public Caminhao(String marca, String modelo, int ano, String placa, double carga) {
        super(marca, modelo, ano, placa);
        this.carga = carga;
    }



    @Override
    public void realizarManutencao() {
        System.out.println("=-=-=-= Realizando Manutenção do seu Caminhão =-=-=-=");
        System.out.println("Toda a manutenção foi concluida!.");

    }

    @Override
    public void emitirRelatorio() {
        System.out.println("O relatorio do seu Caminhão: " + modelo + " " + placa + " Foi revisado com sucesso!");

    }

    public double getCarga() {
        return carga;
    }

    /**
     * Verifica se a carga excede o limite operacional padrão (20 toneladas).
     * @return true se a carga for maior que 20.000kg.
     */
    public boolean verificaPesado(){
        return this.carga > 20000;
    }
}
