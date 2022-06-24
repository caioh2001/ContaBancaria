import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class ContaBancaria {
    
    // Crie uma classe ContaBancaria. Uma conta bancaria tem um numero, o CPF do titular e um saldo.
    // Crie um programa/teste que leia dados de contas bancarias de um arquivo texto, uma conta 
    // em casa linha, no formato: Numero, CPF, SaldoInicial

    // O sistema será utilizado por clientes que podem possuir mais de uma conta no banco. Assim,
    // podemos dizer que o cliente tem uma lista de contas. A conta, por sua vez, tem uma fila das
    // operações de deposito ou saque que foram realizadas. As tarefas desta etapa, portanto, são:

    // a) Criar as classes cliente e operacao
    // b) Apos carregar os clientes em um vetor enorme o bastante, carregar as contas. Elas devem ser
    // carregadas na lista de cada cliente, corretamente, e em uma lista independente contendo todas as contas.
    // c) Carregar todas as operações para a memoria. Cada operacao deve, entao, ser inserida na fila da sua conta.
    // d) Melhorar o mini aplicativo para permitir consultar as contas de um cliente e para permitir ver o extrato
    // de operacoes de uma conta.

    int NumeroConta;
    String CPFTitular;
    double Saldo;
    Queue<Operacao> Extrato = new LinkedList<Operacao>();

    public ContaBancaria(int nConta, String CPF, double saldo){
        this.NumeroConta = nConta;
        this.CPFTitular = CPF;
        this.Saldo = saldo;
    }

    public void AdicionarSaldo(Operacao op) throws IOException{

        Extrato.add(op);

        Saldo += op.Valor;

        String caminhoArquivo = "Banco_Operacoes2022.txt";
        
        FileWriter fw = new FileWriter( caminhoArquivo, true );

        BufferedWriter bw = new BufferedWriter( fw );

        bw.newLine();
        bw.write(op.NumeroConta + ";" + op.CodOperacao + ";" + op.Valor + ";" + op.Data);

        bw.close();
        fw.close();
    }

    public void SacarSaldo(Operacao op) throws IOException{

        Extrato.add(op);

        Saldo -= op.Valor;

        String caminhoArquivo = "Banco_Operacoes2022.txt";
        
        FileWriter fw = new FileWriter( caminhoArquivo, true );

        BufferedWriter bw = new BufferedWriter( fw );

        bw.newLine();
        bw.write(op.NumeroConta + ";" + op.CodOperacao + ";" + op.Valor + ";" + op.Data);

        bw.close();
        fw.close();
    }

    public void salvarOperacoes(Operacao op) throws IOException{

        if(op.CodOperacao == 0){
            AdicionarSaldo(op);
        }
        else{
            SacarSaldo(op);
        }
    }

    public String toString(){
        return "Conta: " + NumeroConta + ", Saldo: " + Saldo;
    }

    public void mostrarExtrato(){
        System.out.println("Extrato da conta " + NumeroConta);
        for (Operacao operacao : Extrato) {
            System.out.println(operacao);
        }
        System.out.println("Saldo atual: " + Saldo);
    }
}
