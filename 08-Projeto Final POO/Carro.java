package academy.guih.poo.javacore.classes.ProjetoFinal.Dominio;

/**
 * Representa um Carro no sistema.
 * Adiciona a distinção para veículos elétricos, o que pode afetar regras de negócio (ex: Troca de Óleo).
 */
public class Carro extends Veiculo implements Manutinivel {
    protected final boolean eletrico;

    /**
     * @param eletrico Define se o carro é movido a eletricidade (true) ou combustão (false).
     */
    public Carro(String marca, String modelo, int ano, String placa, boolean eletrico) {
        super(marca, modelo, ano, placa);
        this.eletrico = eletrico;
    }




    @Override
    public void realizarManutencao() {
        System.out.println("=-=-=-= Carro realizando Manutenção! =-=-=-=");
    }


    @Override
    public void emitirRelatorio() {
        System.out.println("O relatorio do seu Carro: " + modelo + " " + placa + " Foi revisado com sucesso!");

    }

    public boolean getEletrico() {
        return eletrico;
    }
}
