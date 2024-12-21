package com.carlosribeiro;

import java.util.ArrayList;

public class ListaDeObjetos<T> extends ArrayList<T> {
    private int corrente;
    public ListaDeObjetos(int tam) {
        super(tam);
        corrente = -1;
    }

    public boolean remover(int numero) {
        int posicao = localizar(numero);
        if (posicao != -1) {
            this.remove(posicao);
            return true;
        }
        return false;
    }

    public T recuperar(int numero) {
        int posicao = localizar(numero);
        if (posicao != -1) {
            return this.get(posicao);
        }
        return null;
    }
    private int localizar(int numero) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).equals(numero)) {
                return i;
            }
        }
        return -1;
    }
    public T recuperarPrimeiro() {
        if (isEmpty()) {
            return null;
        }
        corrente = 0;
        return this.get(corrente);
    }

    public T recuperarProximo() {
        if (corrente == -1) {
            throw new ArrayIndexOutOfBoundsException("Fora dos limites da lista.");
        }
        else {
            corrente++;
            if (corrente < this.size()) {
                return this.get(corrente);
            }
            else {
                corrente = -1;
                return null;
            }
        }
    }
}

