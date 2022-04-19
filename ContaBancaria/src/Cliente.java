import java.util.ArrayList;
import java.util.List;

public class Cliente {
    float CPF;
    String Nome;
    List<ContaBancaria> Contas = new ArrayList<ContaBancaria>();

    public Cliente(float cpf, String nome){
        this.CPF = cpf;
        this.Nome = nome;
    }
}