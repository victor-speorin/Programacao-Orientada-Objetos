package com.carlosribeiro;

import corejava.Console;

public class Principal
{	public static void main (String[] args)
	{	
		final int TAM = Console.readInt('\n' + "Informe o tamanho da lista de empregados: ");
		ListaDeObjetos<Empregado> listaDeEmpregados = new ListaDeObjetos<Empregado>(TAM);
		String nome;
		double salario;
		String dataAdmissao;
		Empregado umEmpregado;

		boolean continua = true;
		while (continua)
		{	System.out.println('\n' + "==============================================================");
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um empregado");
			System.out.println("2. Alterar um empregado");
			System.out.println("3. Remover um empregado");
			System.out.println("4. Listar relação de empregados");
			System.out.println("5. Listar apenas Número e Nome dos empregados");
			System.out.println("6. Sair");
						
			int opcao = Console.readInt('\n' + "Digite um Número entre 1 e 6:");
					
		  	switch (opcao)
			{	
		  		case 1:
					nome = Console.readLine('\n' + "Digite o nome do empregado: ");
					salario = Console.readDouble("Digite o salario do empregado: ");
					dataAdmissao = Console.readLine("Digite a data de admissão do empregado (DD/MM/AAAA): ");

					try {
						umEmpregado = new Empregado(nome, salario, dataAdmissao);
						listaDeEmpregados.add(umEmpregado);
						System.out.println("Empregado cadastrado com sucesso!");
					}
					catch(DataInvalidaException e) {
						System.out.println(e.getMessage());
					}

					break;

				case 2:	// Alterar
				{	int resposta = Console.readInt('\n' +
						"Digite o número do empregado que você deseja alterar: ");
								
					umEmpregado = listaDeEmpregados.recuperar(resposta);
	
					if(umEmpregado != null)
					{						
						System.out.println('\n' + "O que você deseja alterar?");
						System.out.println('\n' + "1. Nome");
						System.out.println("2. Salario");
						System.out.println("3. Data de Admissão");

						int opcaoAlteracao = Console.readInt('\n' + "Digite 1, 2 ou 3:");			
					
				  		switch (opcaoAlteracao)
						{	case 1:
								String novoNome = Console.readLine("Digite o novo nome: ");
								umEmpregado.setNome(novoNome);
								System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
								break;

							case 2:
								double novoSalario = Console.readDouble("Digite o novo salário: ");
								umEmpregado.setSalario(novoSalario);
								System.out.println('\n' + "Alteração de salário efetuada com sucesso!");
								break;

							case 3:
								String novaDataAdmissao = Console.readLine(
									"Digite a nova data de admissão do empregado (DD/MM/AAAA): ");

								try
								{
									umEmpregado.setDataAdmissao(novaDataAdmissao);
									System.out.println(
										"Altereção de data de admissão efetuada com sucesso!");
								}
								catch(DataInvalidaException e)
								{	System.out.println(e.getMessage());
								}

								break;

							default:
								System.out.println('\n' + "Opção inválida!");
								break;
						}
					}
					else
					{	System.out.println('\n' + "Empregado não encontrado!");
					}						
					break;
				}

				case 3:	// Remover
				{	
					int resposta = Console.readInt('\n' +
						"Digite o número do empregado que você deseja remover: ");
					
					if (listaDeEmpregados.remover(resposta)) 
						System.out.println('\n' + "Empregado removido com sucesso!");
					else
						System.out.println('\n' + "Empregado não encontrado!");
					
					break;
				}

				case 4:	// Listar tudo
					if (listaDeEmpregados.isEmpty())
						System.out.println('\n' + "Não há empregados na lista.");
					else
					{	
						for(Empregado empregado : listaDeEmpregados)
						{
							System.out.println(empregado);
						}
					}
					break;

				case 5:	// Listar Numero e Nome
					if (listaDeEmpregados.isEmpty())
						System.out.println('\n' + "Não há empregados na lista.");
					else
					{	
						for(Empregado empregado : listaDeEmpregados)
						{
							System.out.println("Número = " + empregado.getNumero() +
									           "   Nome = " + empregado.getNome());
						}
					}
					break;

				case 6:	// Sair
					continua = false;
					break;

				default:
					System.out.println('\n' + "Opção inválida!");
			}
		}		
	}
}