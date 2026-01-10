package academy.guih.poo.javacore.classes.ProjetoFinal.Dominio;

/**
 * Representa uma Moto.
 * Implementa manutenção específica focada em amortecedores e pneus.
 */
public class Moto extends Veiculo implements Manutinivel {

    public Moto(String marca, String modelo, int ano, String placa) {
        super(marca, modelo, ano, placa);
    }



    @Override
    public void realizarManutencao() {
        System.out.println("=-=-=-= Moto realizando Manutenção! =-=-=-=");

    }

    @Override
    public void emitirRelatorio() {
        System.out.println("O relatorio da sua Moto: " + modelo + " " + placa + " Foi revisado com sucesso!");

    }
}
