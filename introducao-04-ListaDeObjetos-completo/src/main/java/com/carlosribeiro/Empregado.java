package com.carlosribeiro;

public class Empregado {
    private static int contador = 0;
    private int numero;
    private String nome;
    private double salario;

    public Empregado(String nome, double salario) {
        this.numero = ++contador;
        this.nome = nome;
        this.salario = salario;
    }

    public String toString() {
        return "Número = " + numero + "  Nome = " + nome + "  Salário = " + salario;
    }
    public int getNumero() {
        return numero;
    }
    public String getNome() {
        return nome;
    }
    public double getSalario() {
        return salario;
    }
    public void setNome(String n) {
        nome = n;
    }
    public void setSalario(double s) {
        salario = s;
    }

//    @Override
//    public boolean equals(Object num) {
//        return numero == (Integer)num;
//    }
    @Override
    public boolean equals(Object num) {
        return Integer.valueOf(numero).equals(num);
    }
}

