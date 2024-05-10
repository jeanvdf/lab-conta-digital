package conta;

import java.util.ArrayList;
import java.util.List;

import banco.Banco;
import banco.Transacao;
import cadastro.Perfil;
import manager.OperacoesManagerImpl;

public class Conta {

	private static OperacoesManagerImpl manager = new OperacoesManagerImpl();
	private Integer AGENCIA_PADRAO = 001;
	private static Integer NUMERO_CONTA_SEQUENCIAL = 001;

	public Conta(String tipo, Banco banco) {
		this.agencia = AGENCIA_PADRAO ;
		this.numero = NUMERO_CONTA_SEQUENCIAL++;
		this.tipo = tipo;
		this.extrato = new ArrayList<Transacao>();
		this.saldo = 0D;
	}
	
	public Conta(Perfil perfil, String tipo, Banco banco) {
		this.agencia = AGENCIA_PADRAO;
		this.numero = NUMERO_CONTA_SEQUENCIAL++;
		this.tipo = tipo;
		this.perfil = perfil;
		this.extrato = new ArrayList<Transacao>();
		this.saldo = 0D;
	}
	
	protected Integer agencia;
	protected Integer numero;
	protected Double saldo;
	protected Perfil perfil;
	private List<Transacao> extrato;
	
	public static String CORRENTE  = "CORRENTE";
	public static String POUPANCA  = "POUPANCA";
	protected String tipo;
	
	public Double getSaldo() {
		return saldo;
	}
	public Integer getAgencia() {
		return agencia;
	}
	public Integer getNumero() {
		return numero;
	}
	public List<Transacao> getExtrato() {
		return extrato;
	}
	public Perfil getPerfil() {
		return perfil;
	}

	public void sacar(Double valor) {
		
		this.saldo = manager.sacar(valor, this.saldo, this);
		
		System.out.println("Saque de R$" + valor + " realizado.");
	
	}
	
	public void depositar(Double valor) {
		
		this.saldo = manager.depositar(valor, this.saldo, this);
		
		System.out.println("Depósito de R$" + valor + " realizado na conta " + this.getAgencia() + "/" +this.getNumero());
		
	}
	
	public void transferir(Double valor, Conta contaDestino) {

		this.saldo = manager.transferir(valor, this.saldo, this, contaDestino);
		
		System.out.println("Transferência realizada.");
		
	}
	
	public void imprimirExtrato(Conta conta) {
		
		manager.imprimirExtrato(conta, conta.getExtrato());
	}
	
	public void imprimirSaldo(Conta conta) {
		System.out.println("Saldo atual R$ " + conta.getSaldo());
	}
	
	public void addExtrato(Transacao transacao) {
		this.extrato.add(transacao);
	}
	
}
