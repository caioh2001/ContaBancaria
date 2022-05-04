import java.util.ArrayList;
import java.util.List;

public class Cliente {
    long CPF;
    String Nome;
    List<ContaBancaria> Contas = new ArrayList<ContaBancaria>();

    public Cliente(Long cpf, String nome){
        this.CPF = cpf;
        this.Nome = nome;
    }

    public String toString(){

        return "CPF: " + CPF + " ,Nome: " + Nome;
    }

    public void CarregarContas(List<ContaBancaria> lista){

        for (ContaBancaria conta : lista) {
            if(conta.CPFTitular == this.CPF){
                Contas.add(conta);
            }
        }
    }
}