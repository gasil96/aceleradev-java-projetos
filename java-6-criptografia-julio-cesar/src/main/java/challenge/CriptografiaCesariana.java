package challenge;

public class CriptografiaCesariana implements Criptografia {

    int chave = 3;

    @Override
    public String criptografar(String entrada) {
        entrada =  new Validation().validarEntrada(entrada);
        String entradaCriptografado = new String();
        for (int i = 0; i < entrada.length(); i++) {
            if (((int) entrada.charAt(i)) < 97 || ((int) entrada.charAt(i)) > 122) {
                entradaCriptografado += entrada.charAt(i);
            } else {
                if (((int) entrada.charAt(i) + chave) > 122)
                    entradaCriptografado += (char) (entrada.charAt(i) + chave - 25);
                else if (((int) entrada.charAt(i) + chave) < 97)
                    entradaCriptografado += (char) (entrada.charAt(i) + chave + 26);
                else {
                    entradaCriptografado += (char) (entrada.charAt(i) + chave);
                }
            }
        }
        return entradaCriptografado;
    }

    @Override
    public String descriptografar(String entrada) {
        entrada = new Validation().validarEntrada(entrada);
        String textDescriptografrado = new String();
        for (int i = 0; i < entrada.length(); i++) {
            if (((int) entrada.charAt(i)) < 97 || ((int) entrada.charAt(i)) > 122) {
                textDescriptografrado += entrada.charAt(i);
            } else {
                if (((int) entrada.charAt(i) - chave) > 122)
                    textDescriptografrado += (char) (entrada.charAt(i) - chave - 25);
                else if (((int) entrada.charAt(i) - chave) < 97)
                    textDescriptografrado += (char) (entrada.charAt(i) - chave + 26);
                else {
                    textDescriptografrado += (char) (entrada.charAt(i) - chave);
                }
            }
        }
        return textDescriptografrado.toLowerCase();
    }

}
