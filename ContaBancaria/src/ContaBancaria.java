public class ContaBancaria {
    
    // Crie uma classe ContaBancaria. Uma conta bancaria tem um numero, o CPF do titular e um saldo.
    // Crie um programa/teste que leia dados de contas bancarias de um arquivo texto, uma conta 
    // em casa linha, no formato: Numero, CPF, SaldoInicial

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
