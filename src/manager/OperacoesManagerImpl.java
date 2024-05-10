package manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import banco.Transacao;
import conta.Conta;

public class OperacoesManagerImpl implements OperacoesManager{


    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
	@Override
	public Double sacar(Double valor, Double saldo, Conta conta) {

		if(valor == null || valor == 0D) {
			System.out.println("Informe o valor de Saque.");
			return saldo;
		}
		
		if(valor > saldo) {
			System.out.println("Saldo insuficiente.");
			return saldo;
		}
		
		if(conta == null) {
			System.out.println("Informe a conta para saque");
			return saldo;
			
		}
		
		Transacao transacao = new Transacao(valor, Transacao.SAQUE, conta);
		transacao.setDataTransacao(new Date());
		conta.addExtrato(transacao);
		
		saldo -= valor;
		return saldo;
	
	}

	@Override
	public Double depositar(Double valor, Double saldo, Conta conta) {
		
		if(valor == null) {
			System.out.println("Informe um valor para depósito.");
			return saldo;
		}
		
		if(valor < 0) {
			valor *= -1;
		}else if (valor == 0) {
			System.out.println("Informe um valor diferente de zero para depósito.");
			return saldo;
		}
		
		Transacao transacao = new Transacao(valor, Transacao.DEPOSITO, conta);
		transacao.setDataTransacao(new Date());
		conta.addExtrato(transacao);
		
		saldo += valor;
		return saldo;
	}

	@Override
	public Double transferir(Double valor, Double saldo, Conta contaPrincipal, Conta contaDestino) {

		if(valor > saldo) {
			System.out.println("Saldo insuficiente.");
			return saldo;
		}

		contaDestino.depositar(valor);
		Transacao transacao = new Transacao(valor, Transacao.TRANSFERENCIA, contaPrincipal);
		transacao.setContaDestino(contaDestino);
		transacao.setDataTransacao(new Date());
		contaPrincipal.addExtrato(transacao);
		
		saldo -= valor;
		return saldo;
		
	}

	@Override
	public void imprimirExtrato(Conta conta, List<Transacao> extrato) {
		
		System.out.println("Extrato da Conta: Ag." + conta.getAgencia() + " - Conta" + conta.getNumero() + " ");
		System.out.println("Proprietário: " + conta.getPerfil().getNome() + " ");
		for (Transacao t : extrato) {
			
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
		System.out.println("*---------------------------------------------*");
	}

	@Override
	public void imprimirSaldo(Conta conta) {
		System.out.println("Saldo atual R$ " + conta.getSaldo());
		
	}

}
