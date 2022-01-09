package br.com.tiq.exception;

import java.util.HashMap;
import java.util.Map;

public class TQICreditoException extends RuntimeException {

    private Map<String, String> errors;

    public TQICreditoException() {
        this.errors = new HashMap<>();
    }

    public TQICreditoException(String erro, String descricao) {
        this();
        add(erro, descricao);
    }

    public void add(String erro, String descricao) {
        this.errors.put(erro, descricao);
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
