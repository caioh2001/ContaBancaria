import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class App {

    // Grupo: 
    // Caio Henrique da Silva Bento

    public static void main(String[] args) throws Exception {
        
        ContaBancaria[] vetorContas = CarregarContas();

        Cliente[] vetorClientes = CarregarClientes();

        Operacao[] vetorOperacoes = CarregarOperacoes();

        Dictionary<Integer,ContaBancaria> Contas = new Hashtable<Integer,ContaBancaria>();

        Dictionary<String,Cliente> Clientes = new Hashtable<String,Cliente>();

        for (ContaBancaria contaBancaria : vetorContas) {

            Contas.put(contaBancaria.NumeroConta, contaBancaria);

            for(Cliente cliente : vetorClientes){
                if(contaBancaria.CPFTitular.equals(cliente.CPF)){
                    cliente.AdicionarConta(contaBancaria);
                    break;
                }
            }

            for (Operacao operacao : vetorOperacoes) {
                if(operacao.NumeroConta == contaBancaria.NumeroConta){
                    contaBancaria.salvarOperacoes(operacao);
                }
            }
        }

        for(Cliente cliente : vetorClientes){
            Clientes.put(cliente.CPF, cliente);
        }

        System.out.println("Digite o número que corresponde a opção desejada: ");
        System.out.println("1 - Consultar uma conta pelo ID");
        System.out.println("2 - Inserir uma nova conta");
        System.out.println("3 - Lista de contas ordenadas por ID");
        System.out.println("4 - Consultar contas de um cliente");
        System.out.println("5 - Consultar o extrato de uma conta");
        System.out.println("6 - Acessar área do cliente");
        System.out.println("7 - Acessar área do administrador");

        Scanner scanner = new Scanner(System.in);
        int selecao = scanner.nextInt();

        switch(selecao){
            case 1:{
                // Buscar conta
                System.out.println("Digite um ID da conta: ");
                System.out.println(Contas.get(scanner.nextInt()));

                break;
            }
            case 2:{
                // Inserir conta
                System.out.println("Digite um ID para a conta: ");
                int n = scanner.nextInt();

                System.out.println("Digite o CPF do titular da conta: ");
                String c = scanner.next();

                System.out.println("Digite o saldo da conta: ");
                double s = scanner.nextDouble();

                String caminhoArquivo = "Banco_Contas2022.txt";

                FileWriter fw = new FileWriter( caminhoArquivo, true );

                BufferedWriter bw = new BufferedWriter( fw );

                bw.newLine();
                bw.write(n + ";" + c + ";" + s);

                bw.close();
                fw.close();

                break;
            }
            case 3:{
                // Retornar todas as contas em ordem por ID
                for (ContaBancaria contaBancaria : vetorContas) {
                    System.out.println(contaBancaria);
                }

                break;
            }
            case 4:{
                // Pesquisar contas por CPF
                System.out.println("Digite o CPF do cliente: ");
                String CPF = scanner.next();

                Clientes.get(CPF).MostrarContas();

                break;
            }
            case 5:{
                // Pesquisar extrato de conta por ID
                System.out.println("Digite o número da conta: ");
                int nConta = scanner.nextInt();

                Contas.get(nConta).mostrarExtrato();

                break;
            }
            case 6:{
                System.out.println("Digite seu CPF: ");
                String CpfCliente = scanner.next();
                
                System.out.println("Selecione uma das opções: ");
                System.out.println("1 - Verificar extrato de uma conta que você possui.");
                System.out.println("2 - Incluir uma nova conta");
                System.out.println("3 - Fazer operações de saque e depósito em suas contas");
                System.out.println("4 - Extrato de sua posição financeira");
                
                int select = scanner.nextInt();

                switch(select){
                    case 1:{
                        System.out.println("Você possui as seguintes contas:");
                        for (ContaBancaria conta : Clientes.get(CpfCliente).Contas) {
                            System.out.println(conta.NumeroConta);
                        }
                        System.out.println("Digite o número da conta que você deseja ver o extrato:");
                        int procurarConta = scanner.nextInt();
                        for (ContaBancaria conta : Clientes.get(CpfCliente).Contas) {
                            if(conta.NumeroConta == procurarConta){
                                conta.mostrarExtrato();
                            }
                        }
                        break;
                    }
                    case 2:{
                        System.out.println("Digite um ID para a conta: ");
                        int n = scanner.nextInt();
        
                        System.out.println("Digite o saldo da conta: ");
                        double s = scanner.nextDouble();

                        ContaBancaria contaCliente = new ContaBancaria(n, CpfCliente, s);

                        Clientes.get(CpfCliente).AdicionarConta(contaCliente);
        
                        String caminhoArquivo = "Banco_Contas2022.txt";
        
                        FileWriter fw = new FileWriter( caminhoArquivo, true );
        
                        BufferedWriter bw = new BufferedWriter( fw );
        
                        bw.newLine();
                        bw.write(n + ";" + CpfCliente + ";" + s);
        
                        bw.close();
                        fw.close();
        
                        break;
                    }
                    case 3:{
                        System.out.println("Você possui as seguintes contas:");
                        for (ContaBancaria conta : Clientes.get(CpfCliente).Contas) {
                            System.out.println(conta.NumeroConta);
                        }
                        System.out.println("Digite o número da conta que você deseja fazer a operação:");
                        int procurarConta = scanner.nextInt();
                        for (ContaBancaria conta : Clientes.get(CpfCliente).Contas) {
                            if(conta.NumeroConta == procurarConta){
                                System.out.println("Selecione uma ação: ");
                                System.out.println("0 - Depositar");
                                System.out.println("1 - Sacar");
                                int selecionado = scanner.nextInt();
                                if(selecionado == 0){
                                    System.out.println("Digite o valor do deposito:");
                                    double valorDep = scanner.nextDouble();
                                    System.out.println("Digite a data do deposito (dd/mm/aaaa): ");
                                    String dataDep = scanner.next();
                                    Operacao op = new Operacao(conta.NumeroConta, 0, valorDep, dataDep);
                                    conta.AdicionarSaldo(op);
                                    System.out.println("Deposito feito com sucesso!");
                                }
                                else{
                                    System.out.println("Digite o valor do saque:");
                                    double valorSaq = scanner.nextDouble();
                                    System.out.println("Digite a data do saque (dd/mm/aaaa): ");
                                    String dataSaq = scanner.next();
                                    Operacao op = new Operacao(conta.NumeroConta, 1, valorSaq, dataSaq);
                                    conta.SacarSaldo(op);
                                    System.out.println("Saque feito com sucesso!");
                                }
                            }
                        }
                        break;
                    }
                    case 4:{
                        double saldoTotal = 0.0;
                        for (ContaBancaria conta : Clientes.get(CpfCliente).Contas) {
                            System.out.println(conta);
                            saldoTotal += conta.Saldo;
                        }
                        System.out.println("SALDO TOTAL: " + saldoTotal);
                        break;
                    }
                    default:{
                        System.out.println("Selecione uma opção válida.");
                    }
                }

                break;
            }
            case 7:{
                System.out.println("Selecione uma opção: ");
                System.out.println("1 - Retornar a conta com o maior saldo no momento");
                System.out.println("2 - Retornar o saldo médio dos clientes");
                System.out.println("3 - Retornar os dez clientes com maior saldo total");
                int select1 = scanner.nextInt();

                switch(select1){
                    case 1:{
                        ContaBancaria maiorSaldo = vetorContas[0];
                        for (ContaBancaria cont : vetorContas) {
                            if(cont.Saldo > maiorSaldo.Saldo){
                                maiorSaldo = cont;
                            }
                        }
                        System.out.println("A conta: " + maiorSaldo.NumeroConta + " possui o maior saldo.");
                        break;
                    }
                    case 2:{
                        double saldoMedio = 0.0;
                        for(int i = 0; i < vetorClientes.length; i++){
                            saldoMedio += vetorClientes[i].SaldoTotal();
                        }
                        double resposta = saldoMedio / vetorClientes.length;

                        System.out.println("A média de saldo dos clientes é : " + resposta);
                        break;

                    }
                    case 3:{
                        ordenacaoQuickSortComSaldo(vetorClientes,0, vetorClientes.length - 1);
                        for (int i = vetorClientes.length - 1; i > vetorClientes.length - 11; i--) {
                            System.out.println(vetorClientes[i].Nome + "/ Saldo: " + vetorClientes[i].SaldoTotal());
                        }
                        break;
                    }
                    default:{
                        System.out.println("Selecione uma opção válida.");
                    }
                }
    
                break;
            }
            default:{
                System.out.println("Selecione uma opção válida.");
            }
            
        }

        scanner.close();

    }

    public static  ContaBancaria[] CarregarContas() throws FileNotFoundException{
        // Carrega as contas do arquivo contas.txt ordenadas por ID para o vetorContas

        List<ContaBancaria> listaContas = new ArrayList<ContaBancaria>();

        String caminhoArquivo = "Banco_Contas2022.txt";

        Scanner scan = new Scanner(new FileReader(caminhoArquivo));

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] textoSeparado = line.split(";");
            int numero = Integer.parseInt(textoSeparado[0]);
            String cpf = textoSeparado[1];
            double saldo = Double.parseDouble(textoSeparado[2]);

            ContaBancaria conta = new ContaBancaria(numero,cpf,saldo);

            listaContas.add(conta);
        }

        ContaBancaria[] vetorContas = new ContaBancaria[listaContas.size()];
        int aux = 0;

        for (ContaBancaria contaBancaria : listaContas) {
            vetorContas[aux] = contaBancaria;
            aux++;
        }

        ordenacaoQuickSort(vetorContas, 0, vetorContas.length - 1);

        return vetorContas;
    }

    public static Cliente[] CarregarClientes() throws FileNotFoundException{
        // Carrega os clientes do arquivo clientes.txt para o vetorClientes

        List<Cliente> listaClientes = new ArrayList<Cliente>();

        String caminhoArquivo = "Banco_Clientes2022.txt";

        Scanner scan = new Scanner(new FileReader(caminhoArquivo));

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] textoSeparado = line.split(";");
            String CPF = textoSeparado[0];
            String nome = textoSeparado[1];

            Cliente cliente = new Cliente(CPF,nome);

            listaClientes.add(cliente);
        }

        Cliente[] vetorClientes = new Cliente[listaClientes.size()];
        int aux = 0;

        for (Cliente cliente : listaClientes) {
            vetorClientes[aux] = cliente;
            aux++;
        }
        return vetorClientes;

    }

    public static Operacao[] CarregarOperacoes() throws FileNotFoundException{
        // Carrega as operacoes do arquivo operacoes.txt para o vetorOperacoes

        List<Operacao> listaOperacoes = new ArrayList<Operacao>();

        String caminhoArquivo = "Banco_Operacoes2022.txt";

        Scanner scan = new Scanner(new FileReader(caminhoArquivo));

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] textoSeparado = line.split(";");
            int nConta = Integer.parseInt(textoSeparado[0]);
            int codOp = Integer.parseInt(textoSeparado[1]);
            Double valor = Double.parseDouble(textoSeparado[2]);
            String data = textoSeparado[3];

            Operacao op = new Operacao(nConta,codOp,valor,data);

            listaOperacoes.add(op);
        }

        Operacao[] vetorOperacoes = new Operacao[listaOperacoes.size()];
        int aux = 0;

        for (Operacao operacao : listaOperacoes) {
            vetorOperacoes[aux] = operacao;
            aux++;
        }

        return vetorOperacoes;
    }

    private static void ordenacaoQuickSort(ContaBancaria[]  vetor, int inicio, int fim) {

        if (inicio < fim) {

            int posicaoPivo = separar(vetor, inicio, fim);
            ordenacaoQuickSort(vetor, inicio, posicaoPivo - 1);
            ordenacaoQuickSort(vetor, posicaoPivo + 1, fim);
        }
    }

    private static int separar(ContaBancaria[] vetor, int inicio, int fim) {

        ContaBancaria pivo = vetor[inicio];
        int i = inicio + 1;
        int f = fim;

        while (i <= f) {
            if (vetor[i].NumeroConta <= pivo.NumeroConta)
                i++;
            else if (pivo.NumeroConta < vetor[f].NumeroConta)
                f--;
            else {
                ContaBancaria troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
            }
        }

        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }

    private static void ordenacaoQuickSortComSaldo(Cliente[]  vetor, int inicio, int fim) {

        if (inicio < fim) {

            int posicaoPivo = separarComSaldo(vetor, inicio, fim);
            ordenacaoQuickSortComSaldo(vetor, inicio, posicaoPivo - 1);
            ordenacaoQuickSortComSaldo(vetor, posicaoPivo + 1, fim);
        }
    }

    private static int separarComSaldo(Cliente[] vetor, int inicio, int fim) {

        Cliente pivo = vetor[inicio];
        int i = inicio + 1;
        int f = fim;

        while (i <= f) {
            if (vetor[i].SaldoTotal() <= pivo.SaldoTotal())
                i++;
            else if (pivo.SaldoTotal() < vetor[f].SaldoTotal())
                f--;
            else {
                Cliente troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
            }
        }

        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }
}
