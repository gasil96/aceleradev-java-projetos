package br.com.codenation.paymentmethods;

public class Transfer implements PriceStrategy{

    private static final Double TRANSFER = 0.92;

    @Override
    public Double calculate(Double price) {
        return price * TRANSFER;
    }
}
