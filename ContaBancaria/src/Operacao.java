
public class Operacao {
    int NumeroConta;
    int CodOperacao;
    double Valor;
    String Data;

    public Operacao(int numeroConta, int codOperacao, double valor, String dt){
        this.NumeroConta = numeroConta;
        this.CodOperacao = codOperacao;
        this.Valor = valor;
        this.Data = dt;
    }

    public String toString(){
        if(CodOperacao == 0){
            return "Deposito de " + Valor + " na data " + Data + ".";
        }
        else{
            return "Saque de " + Valor + " na data " + Data + ".";
        }
    }
}