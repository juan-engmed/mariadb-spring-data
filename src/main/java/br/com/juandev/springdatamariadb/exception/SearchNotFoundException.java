package br.com.juandev.springdatamariadb.exception;

public class SearchNotFoundException extends RuntimeException{

    public SearchNotFoundException(String resourceName, Long id) {
        super(String.format("%s com identificador: %d não encontrado(a)", resourceName, id));
    }
}
