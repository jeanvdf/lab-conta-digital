package conta;

public interface IConta {
	
	void sacar(Double valor);
	
	void depositar(Double valor);
	
	void transferir(Double valor, Conta contaDestino);
	
	void imprimirExtrato(Conta conta);
	
	void imprimirSaldo(Conta conta);
}
