package br.com.codenation.paymentmethods;

public class DebitCard implements PriceStrategy {

    private static final Double DEBIT_CARD = 0.95;

    @Override
    public Double calculate(Double price) {
        return price * DEBIT_CARD;
    }
}
