import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    // Grupo: 
    // Caio Henrique da Silva Bento
    // Diego Henrique da Toledo
    // Felipe Baeta Fonseca
    // Wesley Henrique de Lima

    public static void main(String[] args) throws Exception {

        ContaBancaria[] vetorContas = CarregarContas();

        OrdenacaoContas(vetorContas);

        List<ContaBancaria> listaContas = new ArrayList<>();

        for (ContaBancaria conta : vetorContas) {
            listaContas.add(conta);
        }

        Cliente[] vetorClientes = CarregarClientes();
        CarregarContasClientes(vetorClientes, listaContas);

        Operacao[] vetorOperacoes = CarregarOperacoes();
        CarregarOperacoesContas(listaContas, vetorOperacoes);

        System.out.println("Digite o número que corresponde a opção desejada: ");
        System.out.println("1 - Consultar uma conta pelo ID");
        System.out.println("2 - Inserir uma nova conta");
        System.out.println("3 - Lista de contas ordenadas por ID");
        System.out.println("4 - Consultar contas de um cliente");
        System.out.println("5 - Consultar o extrato de uma conta");

        Scanner scanner = new Scanner(System.in);
        int selecao = scanner.nextInt();

        switch(selecao){
            case 1:{
                // buscar conta

                System.out.println("Digite o número da conta: ");
                int busca = scanner.nextInt();
 
                for (ContaBancaria conta : listaContas) {
                    if(conta.NumeroConta == busca){
                        System.out.println(conta);
                    }
                }

                break;
            }
            case 2:{
                // inserir conta

                System.out.println("Digite um ID para a conta: ");
                int n = scanner.nextInt();

                System.out.println("Digite o CPF do titular da conta: ");
                String c = scanner.next();

                System.out.println("Digite o saldo da conta: ");
                double s = scanner.nextDouble();

                String caminhoArquivo = "contas.txt";

                FileWriter fw = new FileWriter( caminhoArquivo, true );

                BufferedWriter bw = new BufferedWriter( fw );

                bw.newLine();
                bw.write(n + ";" + c + ";" + s);

                bw.close();
                fw.close();

                break;
            }
            case 3:{
                for (ContaBancaria conta : listaContas) {
                    System.out.println(conta);
                }
            }
            case 4:{
                System.out.println("Digite o CPF do cliente: ");
                long busca = scanner.nextLong();

                for(int i = 0; i < vetorClientes.length; i++){
                    if(vetorClientes[i].CPF == busca){

                        System.out.println("Cliente : " + vetorClientes[i].Nome);

                        for (ContaBancaria conta : vetorClientes[i].Contas) {
                            System.out.println(conta);
                        }
                    }
                }
                break;
            }
            case 5:{
                // Trazer o extrato da conta

                System.out.println("Digite o número da conta: ");
                int busca = scanner.nextInt();

                for (ContaBancaria conta : listaContas) {
                    if(conta.NumeroConta == busca)
                    {
                        conta.ExtratoConta();
                    }
                }

                break;
            }
            default:{
                System.out.println("Selecione uma opção válida.");
            }
        }

        scanner.close();

        // A partir da classe "ContaBancaria" ja criada, nesta etapa, o grupo de trabalho deve:
        // a) carregar um conjunto maior que 100 contas de arquivo para a memoria principal.
        // b) criar um mini aplicativo que permita consultar ou inserir novas contas.
        // c) salvar as mudanças em arquivo.
        // d) quando solicitado, exibir as contas ordenadas por seu numero.
    }

    public static ContaBancaria[] CarregarContas() throws FileNotFoundException{

        int tamanhoVetor = 0;

        String caminhoArquivo = "contas.txt";

        Scanner scan = new Scanner(new FileReader(caminhoArquivo));

        while (scan.hasNextLine()) {
            scan.nextLine();
            tamanhoVetor++;
        }
        
        ContaBancaria[] vetorContas = new ContaBancaria[tamanhoVetor];

        int posicaoVetor = 0;

        String caminhoArquivo2 = "contas.txt";

        Scanner scan2 = new Scanner(new FileReader(caminhoArquivo2));

        while (scan2.hasNextLine()) {
            String line = scan2.nextLine();
            String[] textoSeparado = line.split(";");
            int n = Integer.parseInt(textoSeparado[0]);
            Long c = Long.parseLong(textoSeparado[1]);
            double s = Double.parseDouble(textoSeparado[2]);

            ContaBancaria conta = new ContaBancaria(n,c,s);

            vetorContas[posicaoVetor] = conta;
            posicaoVetor++;
        }

        return vetorContas;
    }

    public static Cliente[] CarregarClientes() throws FileNotFoundException{

        int tamanhoVetor = 0;

        String caminhoArquivo = "clientes.txt";

        Scanner scan = new Scanner(new FileReader(caminhoArquivo));

        while (scan.hasNextLine()) {
            scan.nextLine();
            tamanhoVetor++;
        }
        
        Cliente[] vetorClientes = new Cliente[tamanhoVetor];

        int posicaoVetor = 0;

        String caminhoArquivo2 = "clientes.txt";

        Scanner scan2 = new Scanner(new FileReader(caminhoArquivo2));

        while (scan2.hasNextLine()) {
            String line = scan2.nextLine();
            String[] textoSeparado = line.split(";");
            long cpfCliente = Long.parseLong(textoSeparado[0]);
            String nomeCliente = textoSeparado[1];

            Cliente cliente = new Cliente(cpfCliente,nomeCliente);

            vetorClientes[posicaoVetor] = cliente;
            posicaoVetor++;
        }

        return vetorClientes;
    }

    public static void CarregarContasClientes(Cliente[] vetClientes, List<ContaBancaria> listContas){

        for(int i = 0; i < vetClientes.length; i++){
            vetClientes[i].CarregarContas(listContas);
        }
    }

    public static Operacao[] CarregarOperacoes() throws FileNotFoundException{
        int tamanhoVetor = 0;

        String caminhoArquivo = "operacoes.txt";

        Scanner scan = new Scanner(new FileReader(caminhoArquivo));

        while (scan.hasNextLine()) {
            scan.nextLine();
            tamanhoVetor++;
        }
        
        Operacao[] vetorOp = new Operacao[tamanhoVetor];

        int posicaoVetor = 0;

        String caminhoArquivo2 = "operacoes.txt";

        Scanner scan2 = new Scanner(new FileReader(caminhoArquivo2));

        while (scan2.hasNextLine()) {
            String line = scan2.nextLine();
            String[] textoSeparado = line.split(";");
            int numConta = Integer.parseInt(textoSeparado[0]);
            int codOp = Integer.parseInt(textoSeparado[1]);
            double valor = Double.parseDouble(textoSeparado[2]);

            Operacao op = new Operacao(numConta,codOp,valor);

            vetorOp[posicaoVetor] = op;
            posicaoVetor++;
        }

        return vetorOp;
    }

    public static void CarregarOperacoesContas(List<ContaBancaria> listContas, Operacao[] vetOp){

        for (ContaBancaria conta : listContas) {
            conta.CarregarOperacoes(vetOp);
        }
    }

    public static void OrdenacaoContas(ContaBancaria[] vetor){
        for(int posReferencia = vetor.length; posReferencia > 0; posReferencia--){
            int trocas = 0;
            for(int posComp = 0; posComp < (posReferencia - 1); posComp++){
                if(vetor[posComp].NumeroConta > vetor[posComp+1].NumeroConta){
                    ContaBancaria aux = vetor[posComp];

                    vetor[posComp] = vetor[posComp+1];
                    vetor[posComp+1] = aux;
                    trocas++;
                }
            }
            if(trocas == 0){
                return;
            }
        }
    }
}
