package com.carlosribeiro;

import java.util.*;

public class ListaDeObjetos<T> {
    private int corrente;
    private final ArrayList<T> lista;

    public ListaDeObjetos(int tam) {
        corrente = -1;
        lista = new ArrayList<>(tam);
    }

    public void adicionar(T obj) {
        lista.add(obj);
    }

    public int tamanho() {
        return lista.size();
    }

    public boolean remover(int numero) {
        int posicao = localizar(numero);
        if (posicao != -1) {
            lista.remove(posicao);
            return true;
        }
        return false;
    }

    public T recuperar(int numero) {
        int posicao = localizar(numero);
        if (posicao != -1) {
            return lista.get(posicao);
        }
        return null;
    }
    private int localizar(int numero) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(numero)) {
                return i;
            }
        }
        return -1;
    }

    public boolean vazia() {
        return lista.isEmpty();
    }


    public T recuperarPrimeiro() {
        if (vazia()) {
            return null;
        }
        corrente = 0;
        return lista.get(corrente);
    }

    public T recuperarProximo() {
        if (corrente == -1) {
            throw new ArrayIndexOutOfBoundsException("Fora dos limites da lista.");
        }
        else {
            corrente++;
            if (corrente < lista.size()) {
                return lista.get(corrente);
            }
            else {
                corrente = -1;
                return null;
            }
        }
    }
}

