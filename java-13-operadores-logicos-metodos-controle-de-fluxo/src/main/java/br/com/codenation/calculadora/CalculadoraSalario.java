package br.com.codenation.calculadora;


public class CalculadoraSalario {

    public long calcularSalarioLiquido(double salarioBase) {
        //Use o Math.round apenas no final do método para arredondar o valor final.
        //Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-
        if (salarioBase < 1039.00) {
            return Math.round(0.0);
        } else {
            salarioBase = salarioBase - calcularInss(salarioBase) - calcularIRRF(salarioBase);
            return Math.round(salarioBase);
        }
    }


    //Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
    private double calcularInss(double salarioBase) {
        double descontoInss = 0.0;
        if (salarioBase <= 1500.00) {
            descontoInss = (8.0 / 100 * salarioBase);
        } else if (salarioBase > 1500.00 && salarioBase <= 4000.00) {
            descontoInss = (9.0 / 100 * salarioBase);
        } else {
            descontoInss = (11.0 / 100 * salarioBase);
        }

        return Math.round(descontoInss);
    }

    private double calcularIRRF(double salarioBase) {
        salarioBase = salarioBase - calcularInss(salarioBase);
        double descontoIRRF = 0.0;

        if (salarioBase <= 3000.00) {
            return 0.0;

        } else if (salarioBase > 3000.00 && salarioBase <= 6000.00) {
            descontoIRRF = (7.5 / 100 * salarioBase);
            return descontoIRRF;

        } else if (salarioBase > 6000.00) {
            descontoIRRF = (15.0 / 100 * salarioBase);
            return descontoIRRF;
        } else {
            return descontoIRRF;
        }
    }

    public static void main(String[] args) {
        System.err.println(new CalculadoraSalario().calcularSalarioLiquido(10000));
    }

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/