package com.carlosribeiro;

public class Empregado
{
	private static int contador = 0;
	private final int numero;
	private String nome;
	private double salario;
	private static double salarioMaximo = 1000000;
		
	public Empregado (String nome, double salario)
			throws SalarioSuperiorAoLimiteException {
		this.setSalario(salario);
		this.numero = ++contador;
		this.nome = nome;
	}

	public String toString()
	{	return "Número = " + numero +
			   "  Nome = " + nome +
			   "  Salário = " + salario;
	}

	public int getNumero()
	{	return numero;
	}
	public String getNome()
	{	return nome;
	}
	public double getSalario()
	{	return salario;
	}

	public void setNome(String nome)
	{	this.nome = nome;
	}
	public void setSalario(double salario)
			throws SalarioSuperiorAoLimiteException {
		if (salario > salarioMaximo) {
			throw new SalarioSuperiorAoLimiteException(
					"Salário de " + salario + " superior ao " +
					"limite de " + salarioMaximo);
		}
		this.salario = salario;
	}

	public boolean equals(Object num) {
		return numero == (Integer) num;
	}
}









