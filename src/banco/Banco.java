package banco;

import java.util.ArrayList;
import java.util.List;

import conta.Conta;

public class Banco {
	
	private static String BANCO_DIGITAL = "NOSSO BANCO";
	private List<Conta> contas;
	
	public Banco() {
		this.nome = BANCO_DIGITAL;
		this.contas = new ArrayList<Conta>();
	}

	private String nome;

	public String getNome() {
		return nome;
	}

	public List<Conta> getContas() {
		return contas;
	}
	
}
