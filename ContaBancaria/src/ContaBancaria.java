public class ContaBancaria {
    
    // Crie uma classe ContaBancaria. Uma conta bancaria tem um numero, o CPF do titular e um saldo.
    // Crie um programa/teste que leia dados de contas bancarias de um arquivo texto, uma conta 
    // em casa linha, no formato: Numero, CPF, SaldoInicial

    // O sistema será utilizado por clientes que podem possuir mais de uma conta no banco. Assim,
    // podemos dizer que o cliente tem uma lista de contas. A conta, por sua vez, tem uma fila das
    // operações de deposito ou saque que foram realizadas. As tarefas desta etapa, portanto, são:

    // a) Cria as classes cliente e operacao
    // b) Apos carregar os clientes em um vetor enorme o bastante, carregar as contas. Elas devem ser
    // carregadas na lista de cada cliente, corretamente, e em uma lista independente contendo todas as contas.
    // c) Carregar todas as operações para a memoria. Cada operacao deve, entao, ser inserida na fila da sua conta.
    // d) Melhorar o mini aplicativo para permitir consultar as contas de um cliente e para permitir ver o extrato
    // de operacoes de uma conta.

    int numeroConta;
    long CPFTitular;
    double Saldo;

    public void adicionarSaldo(double valor){
        Saldo = Saldo + valor;
    }

    public void sacarSaldo(double valor){
        Saldo = Saldo - valor;
    }

    public ContaBancaria(int nConta, long CPF, double saldo){
        this.numeroConta = nConta;
        this.CPFTitular = CPF;
        this.Saldo = saldo;
    }

    public String ToString(){
        return "A conta " + numeroConta + " tem o CPF: " + CPFTitular + " como titular e possui " + Saldo + " de saldo.";
    }

}
