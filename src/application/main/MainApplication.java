package application.main;

import banco.Banco;
import cadastro.Perfil;
import conta.Conta;

public class MainApplication {

	public static void main(String[] args) {
		
		Banco banco = new Banco();
		System.out.println("Bem vindo ao " + banco.getNome());
		System.out.println("*//-----------//*");
		
		Perfil perfil = new Perfil("Jean Vitor", "4599999999");
		Perfil perfilDestino = new Perfil("Joao Carlos", "4599888888");
		
		Conta conta = new Conta(perfil, Conta.CORRENTE, banco);
		Conta contaDestino = new Conta(perfilDestino, Conta.CORRENTE, banco);
		
		conta.depositar(100D);
		conta.sacar(30D);
		conta.transferir(10D, contaDestino);
		conta.imprimirSaldo(conta);
		conta.imprimirExtrato(conta);

	}

}
