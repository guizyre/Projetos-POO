import academy.guih.poo.javacore.classes.herança.dominio.*;

public class TesteIngressos{
    public static void main(String[] args) {

        IngressoComum comumSemDesc = new IngressoComum("C1", 200.0, "Guilherme", false);
        System.out.println("\nIngresso Comum (sem desconto)");
        comumSemDesc.imprimirDados(0.1);

        IngressoComum comumEstudante = new IngressoComum("C2", 200.0, "Lucas", true);
        System.out.println("\n Ingresso Comum (estudante com 50% desconto)");
        comumEstudante.imprimirDados(0.1);

        IngressoVip vip = new IngressoVip("V1", 200.0, "Maria", true);
        System.out.println("\nIngresso VIP");
        vip.imprimirDados(0.1);

        IngressoCamarote camarote = new IngressoCamarote("CAM1", 200.0, "João", "Setor B");
        System.out.println("\nIngresso Camarote");
        camarote.imprimirDados(0.1);


        try {
            Ingresso invalido = new Ingresso("", -50, "");
        } catch (Exception e) {
            System.out.println("\nTeste de validação disparado corretamente valor: " + e.getMessage());
        }

        try {
            IngressoComum taxaInvalida = new IngressoComum("C3", 100.0, "Ana", false);
            taxaInvalida.imprimirDados(0.3);
        } catch (Exception e) {
            System.out.println("\nTeste de validação de taxa disparado corretamente taxa: " + e.getMessage());
        }

        System.out.println("\nTeste mudar setor vazio:");
        camarote.mudarSetor("");
    }
//certo
}
