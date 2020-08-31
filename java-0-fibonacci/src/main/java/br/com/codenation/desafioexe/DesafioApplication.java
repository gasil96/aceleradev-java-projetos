package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

    private static int[] vetAux = new int[31];
    private static int k;

    public static int fibo(int n) {
        k = 1; // inicializa k
        return recursao(n);
    }

    private static int recursao(int n) {
        if (n < 0) {
            return vetAux[0];
        } else {
            if (k < 3) {
                vetAux[n] = k - 1;
                k++;
            } else {
                vetAux[n] = vetAux[n + 1] + vetAux[n + 2];
            }
            return recursao(n - 1);
        }
    }

    public static List<Integer> fibonacci() {
        List<Integer> sequenceFibonacci = new ArrayList<>();

        for (int i = 0; i <= 14; i++) {
            sequenceFibonacci.add(fibo(i));
        }
        return sequenceFibonacci;
    }

    public static Boolean isFibonacci(Integer a) {
        if (fibonacci().contains(a)) {
            return true;
        } else {
            return false;
        }
    }


    //TODO Debbugar
    public static void main(String[] args) {
        System.err.println("Lista Fibonacci Método 1: " + DesafioApplication.fibonacci());
        System.err.println("Verificador de Elemento Método 2: " + isFibonacci(9) );
    }


}