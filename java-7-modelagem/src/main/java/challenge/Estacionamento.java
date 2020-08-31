package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Estacionamento {

    private List<Carro> carrosEstacionados = new ArrayList<>();

    private final Integer LIMTE_VAGAS = 10;


    public void estacionar(Carro carro) {
        if (carro.getMotorista().getIdade() < 18) {
            throw new EstacionamentoException("Não deve ter motorista menor");
        }

        if (carrosEstacionados.size() < 10) {
            carrosEstacionados.add(carro);
        } else {
            if (carrosEstacionados.stream().filter(c -> c.getMotorista().getIdade() >= 55).collect(Collectors.toList()).size() == 10) {
                throw new EstacionamentoException("Não ha vagas!");
            } else {
                Optional<Carro> carroComMotoristaMenor55 =
                        carrosEstacionados.stream().filter(c -> c.getMotorista().getIdade() <= 55).findFirst();
                carrosEstacionados.remove(carroComMotoristaMenor55.get());
                carrosEstacionados.add(carro);
            }
        }

    }

    public int carrosEstacionados() {
        return carrosEstacionados.size();
    }

    public boolean carroEstacionado(Carro carro) {
        if (carrosEstacionados.contains(carro)) {
            return true;
        } else {
            return false;
        }

    }


    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento();
        Motorista motorista = Motorista.builder().withNome("aaa").withIdade(50).withPontos(5).withHabilitacao("A").build();
        Carro carro = Carro.builder().withPlaca("123456").withCor(Cor.BRANCO).withMotorista(motorista).build();

        System.err.println("Motorista pontos -->" + motorista.getPontos());

        estacionamento.estacionar(carro);
        estacionamento.estacionar(carro);
        estacionamento.estacionar(carro);
        estacionamento.estacionar(carro);
        estacionamento.estacionar(carro);
        estacionamento.estacionar(carro);
        estacionamento.estacionar(carro);
        estacionamento.estacionar(carro);
        estacionamento.estacionar(carro);
        estacionamento.estacionar(carro);
        estacionamento.estacionar(carro);
        estacionamento.estacionar(carro);
        System.err.println("Qunatidade de carros estacionados: " + estacionamento.carrosEstacionados());

    }

}
