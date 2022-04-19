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
}