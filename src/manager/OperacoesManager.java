package manager;

import java.util.List;

import banco.Transacao;
import conta.Conta;

public interface OperacoesManager {
	
	Double sacar(Double valor, Double saldo, Conta conta);
	
	Double depositar(Double valor, Double saldo, Conta conta);
	
	Double transferir(Double valor, Double saldo, Conta contaPrincipal, Conta contaDestino);
	
	void imprimirExtrato(Conta conta, List<Transacao> extrato);
	
	void imprimirSaldo(Conta conta);
}
