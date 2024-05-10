package banco;

import java.util.Date;

import conta.Conta;

public class Transacao {
	
	public Transacao(Double valor, String operacao, Conta contaPrincipal) {
		this.contaPrincipal = contaPrincipal;
		this.operacao = operacao;
		this.valor = valor;
	}
	
	public static String DEPOSITO  = "DEPOSITO";
	public static String SAQUE  = "SAQUE";
	public static String TRANSFERENCIA  = "TRANSFERENCIA";
	private String operacao;
	
	private Conta contaPrincipal;
	private Double valor;
	private Conta contaDestino;
	private Date dataTransacao;
	
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	public Conta getContaPrincipal() {
		return contaPrincipal;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Conta getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}
	public Date getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	

}
