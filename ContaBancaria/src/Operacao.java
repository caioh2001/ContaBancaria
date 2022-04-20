import java.sql.Date;

public class Operacao {
    int NumeroConta;
    int CodOperacao;
    double Valor;
    Date Data;

    public Operacao(int numeroConta, int codOperacao, double valor){
        this.NumeroConta = numeroConta;
        this.CodOperacao = codOperacao;
        this.Valor = valor;
        Data = new Date(System.currentTimeMillis());
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