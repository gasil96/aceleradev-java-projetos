package challenge;

public class Validation {

    public String validarEntrada(String entrada) {
        switch (String.valueOf(entrada)) {
            case "":
                throw  new IllegalArgumentException("Entrada não deve ser vazia");
            case "null":
                throw  new NullPointerException("Entrada não deve ser nula");
            default: return entrada.toLowerCase();
        }


    }

}
