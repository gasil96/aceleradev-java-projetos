package br.com.codenation.paymentmethods;

public class CreditCard implements PriceStrategy {

    private static final Double CREDIT_CARD = 0.98;


    @Override
    public Double calculate(Double price) {
        return price * CREDIT_CARD;
    }
}
