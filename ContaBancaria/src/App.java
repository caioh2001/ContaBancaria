import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class App {

    // Grupo: 
    // Caio Henrique da Silva Bento
    // Diego Henrique da Toledo
    // Felipe Baeta Fonseca
    // Wesley Henrique de Lima

    public static void main(String[] args) throws Exception {

        ContaBancaria[] vetorContas = carregarDados();

        System.out.println("Digite o número que corresponde a opção desejada: ");
        System.out.println("1 - Consultar uma conta pelo ID");
        System.out.println("2 - Inserir uma nova conta");
        System.out.println("3 - Lista de contas ordenadas por ID");

        Scanner scanner = new Scanner(System.in);
        int selecao = scanner.nextInt();

        switch(selecao){
            case 1:{

                vetorContas = carregarDados();

                System.out.println("Digite o número da conta: ");
                int busca = scanner.nextInt();

                for(int i = 0; i < vetorContas.length; i++){
                    if(vetorContas[i].NumeroConta == busca){
                        System.out.println(vetorContas[i].ToString());
                    }
                }
                break;
            }
            case 2:{

                vetorContas = carregarDados();

                System.out.println("Digite um ID para a conta: ");
                int n = scanner.nextInt();

                System.out.println("Digite o CPF do titular da conta: ");
                String c = scanner.next();

                System.out.println("Digite o saldo da conta: ");
                double s = scanner.nextDouble();

                String caminhoArquivo = "dados.txt";

                FileWriter fw = new FileWriter( caminhoArquivo, true );

                BufferedWriter bw = new BufferedWriter( fw );

                bw.newLine();
                bw.write(n + ";" + c + ";" + s);

                bw.close();
                fw.close();

                break;
            }
            case 3:{

                vetorContas = carregarDados();

                ordenacaoContas(vetorContas);

                for(ContaBancaria conta : vetorContas){
                    System.out.println(conta.ToString());
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

    public static ContaBancaria[] carregarDados() throws FileNotFoundException{
        int tamanhoVetor = 0;

        String caminhoArquivo = "dados.txt";

        Scanner scan = new Scanner(new FileReader(caminhoArquivo));

        while (scan.hasNextLine()) {
            scan.nextLine();
            tamanhoVetor++;
        }
        
        ContaBancaria[] vetorContas = new ContaBancaria[tamanhoVetor];

        int posicaoVetor = 0;

        String caminhoArquivo2 = "dados.txt";

        Scanner scan2 = new Scanner(new FileReader(caminhoArquivo2));

        while (scan2.hasNextLine()) {
            String line = scan2.nextLine();
            String[] textoSeparado = line.split(";");
            int n = Integer.parseInt(textoSeparado[0]);
            long c = Long.parseLong(textoSeparado[1]);
            double s = Double.parseDouble(textoSeparado[2]);

            ContaBancaria conta = new ContaBancaria(n,c,s);

            vetorContas[posicaoVetor] = conta;
            posicaoVetor++;
        }

        return vetorContas;
    }

    public static void ordenacaoContas(ContaBancaria[] vetor){
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
