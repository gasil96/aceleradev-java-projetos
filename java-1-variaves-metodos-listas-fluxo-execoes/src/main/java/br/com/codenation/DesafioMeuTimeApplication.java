package br.com.codenation;

import br.com.codenation.entity.Jogador;
import br.com.codenation.entity.Time;
import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
//
//    private List<Time> listaTime;
//
//    public DesafioMeuTimeApplication() {
//        listaTime = new ArrayList<>();
//    }

    /**
     * Estância a Lista de Times e Jogadores em Memória;
     */
    List<Time> listaTime = new ArrayList<Time>();
    List<Jogador> listaJogadores = new ArrayList<Jogador>();

    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
                            String corUniformeSecundario) {
        if (!buscarTimes().contains(id)) {
            listaTime.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
        } else {
            throw new IdentificadorUtilizadoException();
        }
    }

    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
                               BigDecimal salario) {
        if (buscarTimes().contains(idTime)) {
            if (buscarJogadoresDoTime(idTime).contains(id)) {
                throw new IdentificadorUtilizadoException();
            } else {
                listaJogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
            }
        } else {
            throw new TimeNaoEncontradoException();
        }

    }

    public void definirCapitao(Long idJogador) {
        Optional<Jogador> jogador = listaJogadores.stream().filter(j -> j.getId() == idJogador).findAny();
        if (jogador.isPresent()) {
            List<Jogador> jogadores = listaJogadores.stream().filter(j -> j.getIdTime() == jogador.get().getIdTime())
                    .collect(Collectors.toList());
            for (Jogador j : jogadores) {
                j.setCapitao(false);
            }
            jogador.get().setCapitao(true);
        } else {
            throw new JogadorNaoEncontradoException();
        }
    }

    public Long buscarCapitaoDoTime(Long idTime) {
        if (!buscarTimes().contains(idTime)) {
            throw new TimeNaoEncontradoException();
        }
        List<Jogador> jogadoresTime = listaJogadores.stream().filter(jt -> jt.getIdTime() == idTime)
                .collect(Collectors.toList());
        if (!jogadoresTime.stream().filter(jt -> jt.getCapitao() == true).collect(Collectors.toList()).isEmpty()) {
            return jogadoresTime.stream().findAny().get().getId();
        } else {
            throw new CapitaoNaoInformadoException();
        }
    }

    public String buscarNomeJogador(Long idJogador) {
        Optional<Jogador> jogadorLocalizado = listaJogadores.stream()
                .filter(filtro -> filtro.getId() == idJogador).findAny();
        if (!jogadorLocalizado.isPresent()) {
            throw new JogadorNaoEncontradoException();
        } else {
            return jogadorLocalizado.get().getNome();
        }
    }

    public String buscarNomeTime(Long idTime) {
        Optional<Time> timeLocalizado = listaTime.stream()
                .filter(filtro -> filtro.getId() == idTime).findAny();
        if (!timeLocalizado.isPresent()) {
            throw new TimeNaoEncontradoException();
        } else {
            return timeLocalizado.get().getNome();
        }
    }

    public List<Long> buscarJogadoresDoTime(Long idTime) {
        if (!buscarTimes().contains(idTime)) {
            throw new TimeNaoEncontradoException();
        }
        List<Long> idsJogadores = new ArrayList<Long>();
        listaJogadores.stream().filter(lj -> lj.getIdTime() == idTime).collect(Collectors.toList())
                .forEach(j -> idsJogadores.add(j.getId()));
        return idsJogadores;
    }

    public Long buscarMelhorJogadorDoTime(Long idTime) {
        if (!buscarTimes().contains(idTime)) {
            throw new TimeNaoEncontradoException();
        }

        Integer melhorNivel = listaJogadores.stream().filter(j -> j.getIdTime() == idTime).collect(Collectors.toList())
                .stream().map(n -> n.getNivelHabilidade()).max(Integer::compareTo).orElse(null);

        List<Jogador> niveis = listaJogadores.stream().filter(j -> j.getNivelHabilidade() == melhorNivel && j
                .getIdTime() == idTime)
                .collect(Collectors.toList());
        if (niveis.size() > 1) {
            Collections.sort(niveis, Comparator.comparingInt(Jogador::getNivelHabilidade));
        }

        return niveis.stream().findAny().get().getId();

    }

    public Long buscarJogadorMaisVelho(Long idTime) {
        if (!buscarTimes().contains(idTime)) {
            throw new TimeNaoEncontradoException();
        }

        LocalDate dataMaisAntiga = listaJogadores.stream().filter(j -> j.getIdTime() == idTime)
                .map(dt -> dt.getDataNascimento()).min(LocalDate::compareTo).orElse(null);

        System.err.println(dataMaisAntiga);

        List<Jogador> jogadorMaiorIdade = listaJogadores.stream().filter(j -> j
                .getDataNascimento().equals(dataMaisAntiga)).collect(Collectors.toList());

        if(jogadorMaiorIdade.size() > 1){
            Collections.sort(jogadorMaiorIdade, Comparator.comparingLong(Jogador::getId));
        }
        return jogadorMaiorIdade.stream().findAny().get().getId();
    }

    public List<Long> buscarTimes() {
        List<Long> idsTimes = new ArrayList<Long>();
        listaTime.forEach(t -> idsTimes.add(t.getId()));
        return idsTimes;
    }

    public Long buscarJogadorMaiorSalario(Long idTime) {
        if (!buscarTimes().contains(idTime)) {
            throw new TimeNaoEncontradoException();
        }
        BigDecimal maiorSalarioEncontrado = listaJogadores.stream()
                .filter(jogador -> jogador.getIdTime() == idTime)
                .collect(Collectors.toList()).stream()
                .map(s -> s.getSalario()).max(BigDecimal::compareTo)
                .orElse(new BigDecimal(0));
        List<Jogador> jogadoresSalarios = listaJogadores.stream().filter(j -> j.getSalario().equals(maiorSalarioEncontrado) && j.getIdTime() == idTime)
                .collect(Collectors.toList());
        if (jogadoresSalarios.size() > 1) {
            Collections.sort(jogadoresSalarios, Comparator.comparingLong(Jogador::getId));
        }
        return jogadoresSalarios.stream().findAny().get().getId();
    }

    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        Optional<Jogador> jogador = listaJogadores.stream().filter(j -> j.getId() == idJogador).findAny();
        if (jogador.isPresent()) {
            return jogador.get().getSalario();
        } else {
            throw new JogadorNaoEncontradoException();
        }
    }

    //TODO Falta Criterio de desempate
    public List<Long> buscarTopJogadores(Integer top) {
        List<Jogador> topJogadoresTotal = listaJogadores;
        Collections.sort(topJogadoresTotal, Comparator.comparingInt(Jogador::getNivelHabilidade).reversed());
        System.err.println(topJogadoresTotal.toString());

        List<Jogador> topJ = topJogadoresTotal.stream().limit(top).collect(Collectors.toList());

        System.err.println(topJ.toString());
        List<Long> idTopJogadores = new ArrayList<Long>();
        topJ.forEach(t -> idTopJogadores.add(t.getId()));
        System.err.println(idTopJogadores);
        return idTopJogadores;
    }

    //TODO - Testando em tempo de execução
    public static void main(String[] args) {
        DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

        teste.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        teste.incluirTime(2l, "Teste2", LocalDate.now(), "branco", "branco");

        teste.incluirJogador(1l, 1l, "Jogador", LocalDate.now().minus(21, ChronoUnit.YEARS), 1, BigDecimal.TEN);
        teste.incluirJogador(9l, 1l, "Jogador2", LocalDate.now().minus(10, ChronoUnit.YEARS), 3, new BigDecimal(340));
        teste.incluirJogador(2l, 1l, "Jogador2", LocalDate.now().minus(10, ChronoUnit.YEARS), 3, new BigDecimal(340));
        teste.incluirJogador(22l, 1l, "Jogador2", LocalDate.now().minus(10, ChronoUnit.YEARS), 4, new BigDecimal(3409));
        teste.incluirJogador(3l, 1l, "Jogador3", LocalDate.now().minus(10, ChronoUnit.YEARS), 1, new BigDecimal(1));

        teste.buscarJogadorMaiorSalario(1l);

        teste.buscarSalarioDoJogador(22l);

        teste.buscarJogadoresDoTime(1l);

        teste.definirCapitao(3l);
        teste.buscarCapitaoDoTime(1l);

        teste.buscarMelhorJogadorDoTime(1l);

        teste.buscarTopJogadores(2);

        teste.buscarJogadorMaisVelho(1l);

        System.err.println(LocalDate.now().getYear());


    }

}
