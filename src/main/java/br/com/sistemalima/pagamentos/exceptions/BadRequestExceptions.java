package br.com.sistemalima.pagamentos.exceptions;

public class BadRequestExceptions extends RuntimeException {

    public BadRequestExceptions(String message) {
        super(message);
    }
}
