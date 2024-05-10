package conta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import banco.Banco;
import banco.Transacao;
import cadastro.Perfil;

public class Conta implements IConta{

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Integer AGENCIA_PADRAO = 1;
	private Integer NUMERO_CONTA_SEQUENCIAL = 1;

	public Conta(String tipo, Banco banco) {
		this.agencia = AGENCIA_PADRAO ;
		this.agencia = NUMERO_CONTA_SEQUENCIAL++;
		this.tipo = tipo;
		this.extrato = new ArrayList<Transacao>();
		this.saldo = 0D;
	}
	
	public Conta(Perfil perfil, String tipo, Banco banco) {
		this.agencia = AGENCIA_PADRAO ;
		this.agencia = NUMERO_CONTA_SEQUENCIAL++;
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

	@Override
	public void sacar(Double valor) {
		if(valor > saldo) {
			System.out.println("Saldo insuficiente.");
			return;
		}

		Transacao transacao = new Transacao(valor, Transacao.SAQUE, this);
		transacao.setDataTransacao(new Date());
		this.extrato.add(transacao);
		this.saldo -= valor;
		System.out.println("Saque realizado.");
	
	}
	
	@Override
	public void depositar(Double valor) {
		if(valor == null) {
			System.out.println("Informe um valor para depósito.");
			return;
		}
		
		if(valor < 0) {
			valor *= -1;
		}else if (valor == 0) {
			System.out.println("Informe um valor maior que zero para depósito.");
			return;
		}
		
		Transacao transacao = new Transacao(valor, Transacao.DEPOSITO, this);
		transacao.setDataTransacao(new Date());
		this.extrato.add(transacao);
		
		this.saldo += valor;
		System.out.println("Depósito realizado.");
		
	}
	
	@Override
	public void transferir(Double valor, Conta contaDestino) {
		
		if(valor > saldo) {
			System.out.println("Saldo insuficiente.");
			return;
		}

		this.saldo -= valor;
		contaDestino.depositar(valor);
		Transacao transacao = new Transacao(valor, Transacao.DEPOSITO, this);
		transacao.setContaDestino(contaDestino);
		transacao.setDataTransacao(new Date());
		
		this.extrato.add(transacao);
		
		System.out.println("Transferência realizada.");
		
	}
	
	@Override
	public void imprimirExtrato(Conta conta) {
		
		for (Transacao t : this.extrato) {
			
			if(Transacao.DEPOSITO.equals(t.getOperacao())) {
				System.out.println("Deposito de R$" + t.getValor() + " na data " + sdf.format(t.getDataTransacao()));
				
			}else if(Transacao.SAQUE.equals(t.getOperacao())) {
				System.out.println("Saque de R$" + t.getValor() + " na data " + sdf.format(t.getDataTransacao()));
				
			}else {
				System.out.println("Transferencia de " +t.getContaPrincipal().getPerfil().getNome()  
									+ " para " + t.getContaDestino().getPerfil().getNome() 
									+ " no valor de R$" + t.getValor() 
									+ " na data " + sdf.format(t.getDataTransacao()));
				
			}
		}
		
	}
	
	public void imprimirSaldo(Conta conta) {
		System.out.println("Saldo atual R$ " + conta.getSaldo());
	}
	
}
