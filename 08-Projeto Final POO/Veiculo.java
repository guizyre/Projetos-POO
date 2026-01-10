package academy.guih.poo.javacore.classes.ProjetoFinal.Dominio;

/**
 * Classe <b>Abstrata</b> que serve como base para todos os tipos de veículos (Carro, Moto, Caminhão).
 * <p>
 * Ela concentra os atributos comuns (marca, modelo, placa, ano) e fornece
 * métodos utilitários protegidos para validação de dados.
 * </p>
 * Como é abstrata, não pode ser instanciada diretamente (ninguém tem "um veículo" genérico,
 * a pessoa tem um Carro ou uma Moto).
 */
public abstract class Veiculo {
    // Atributos protected permitem que as classes filhas (Carro, Moto) acessem diretamente se precisarem.
    // Atributos final garantem que, uma vez criado, os dados principais não mudam (imutabilidade).
    protected final String marca;
    protected final String modelo;
    protected final int ano;
    protected final String placa;


    /**
     * Construtor único que obriga o preenchimento dos dados básicos.
     *
     * @param marca  Fabricante do veículo (ex: Honda, Ford).
     * @param modelo Nome do modelo (ex: Civic, Fiesta).
     * @param ano    Ano de fabricação.
     * @param placa  Identificação única do veículo.
     */
    public Veiculo(String marca, String modelo, int ano, String placa) {

        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
    }


    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getPlaca() {
        return placa;
    }

    // --- MÉTODOS UTILITÁRIOS (Helpers) ---

    /**
     * Valida um texto para impedir que valores nulos quebrem o sistema.
     * <p>
     * Se o texto for nulo ou vazio, define um valor padrão ("Não informado").
     * </p>
     * @param valor O texto a ser verificado.
     * @param campo Nome do campo (apenas para referência, se necessário).
     * @return O texto limpo ou o valor padrão.
     */

    protected String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            return "Não informado"; // <--- impede erro
        }
        return valor.trim();
    }
    /**
     * Tenta converter uma String para inteiro de forma segura.
     * <p>
     * Se a conversão falhar (ex: usuário digitou "abc" no ano),
     * captura o erro e retorna 0 para evitar o crash do programa.
     * </p>
     * @param valor A String contendo o ano.
     * @return O ano numérico ou 0 em caso de erro.
     */

    protected int validarAno(String valor) {
        try {
            if (valor == null || valor.trim().isEmpty()) {
                return 0; // <- ano desconhecido, mas válido para evitar NullPointerException
            }
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return 0; // <- fallback seguro
        }
    }


}
