package com.carlosribeiro;

import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Empregado 
{
	private static int contador = 0;
	private int numero;
	private String nome;
	private double salario;
	private LocalDate dataAdmissao;

	// Formatador para números
	private static final NumberFormat NF;

	// Formatador para imprimir e efetuar o parse de objetos date-time
	private static final DateTimeFormatter DTF;
	
	static                               // NF será utilizado para formatar o salário
	{
		Locale locale = Locale.of("pt", "BR");
		NF = NumberFormat.getNumberInstance(locale);
		DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		NF.setMaximumFractionDigits (2);	   // O default é 3.
		NF.setMinimumFractionDigits (2);
	}

	public Empregado (String nome, double salario, String dataAdmissao)
			throws DataInvalidaException {
		setDataAdmissao(dataAdmissao);

		this.numero = ++contador;	// Caso a data de admissão seja
									// inválida o contador não será
									// incrementado.
		this.nome = nome;
		this.salario = salario;

		/* 	O método parseInt() gera a exceção NumberFormatException
			e o método substring() gera a exceção StringIndexOutOfBoundsException.
			E o método LocalDate.of gera a exceção DateTimeException.
			Estas exceções são do tipo unchecked, logo o compilador não obriga o
			programador a declarar que este método gera estas exceções.

			(throws NumberFormatException, StringIndexOutOfBoundsException, DateTimeException)

		  	Toda exceção que deriva das classes Error e RuntimeException são consideradas
		  	unchecked exceptions. O programador deve se preocupar com as exceções do tipo
		  	checked, manipulando-as ou anunciando que elas podem ser propagadas.
				
		   	Exceções do tipo unchecked são:
		   	- Um acesso fora dos limites de um array
		   	- Um acesso a um ponteiro null, etc.
				
		   	Hierarquia das classes que representam exceções:
		
	      		Throwable
					Error                  -  unchecked exceptions
					Exception
					    RuntimeException   -  unchecked exceptions
                           ...
						IOException        -  checked exceptions
						SQLException
						...
		*/
	}

	public String toString()
	{	return "Número = " + numero +
			   "  Nome = " + nome +
			   "  Salário = " + getSalarioMasc() +
			   "  Data de Admissão = " + getDataAdmissaoMasc();
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

	public String getSalarioMasc() {
		return NF.format(salario);
	}
		
	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public String getDataAdmissaoMasc() {
		return DTF.format(dataAdmissao);
	}

	public void setNome(String n)
	{	nome = n;
	}
		
	public void setSalario(double s)
	{	salario = s;
	}
		
	public void setDataAdmissao (String novaDataAdmissao)
		throws DataInvalidaException
	{
		try {
			int dia = Integer.parseInt(novaDataAdmissao.substring(0, 2));
			int mes = Integer.parseInt(novaDataAdmissao.substring(3, 5));
			int ano = Integer.parseInt(novaDataAdmissao.substring(6, 10));

			dataAdmissao = LocalDate.of(ano, mes, dia);
		}
		catch(StringIndexOutOfBoundsException |
		      NumberFormatException |
			  DateTimeException e) {
			throw new DataInvalidaException("Data inválida.");
		}
    }

	public boolean equals(Object num) {
		return numero == (Integer) num;
	}
}