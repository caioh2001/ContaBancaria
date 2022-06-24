import java.util.ArrayList;
import java.util.List;

public class Cliente {
    String CPF;
    String Nome;
    List<ContaBancaria> Contas = new ArrayList<ContaBancaria>();

    public Cliente(String cpf, String nome){
        this.CPF = cpf;
        this.Nome = nome;
    }

    public String toString(){

        return "CPF: " + CPF + " ,Nome: " + Nome;
    }

    public void AdicionarConta(ContaBancaria conta){
        Contas.add(conta);
    }

    public void MostrarContas(){
        System.out.println("Contas do cliente " + Nome);
        for (ContaBancaria contaBancaria : Contas) {
            System.out.println(contaBancaria);
        }
    }

    public double SaldoTotal(){
        double total = 0;
        for (ContaBancaria contaBancaria : Contas) {
            total += contaBancaria.Saldo;
        }

        return total;
    }
}