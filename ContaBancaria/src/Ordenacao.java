import java.util.Random;


    // GANHEI 2 PONTOS EXTRAS


public class Ordenacao {
    public static void main(String[] args) {

        int[] vetorzada = geraVetor(1000, 2.5);

        ordenacaoBolha(vetorzada);

        for(int i = 0; i < vetorzada.length; i++){
            System.out.println(vetorzada[i]);
        }
    }

    static Random sorteio = new Random(7);

    public static int[] geraVetor(int tamanho, double bagunca){
        int[] vetor = new int[tamanho];

        for(int i = 0; i < vetor.length; i++){
            vetor[i] = (i+1);
        }

        int vezes = (int)(bagunca*tamanho);
        for(int i = 0; i<vezes; i++){
            int pos1 = sorteio.nextInt(tamanho);
            int pos2 = sorteio.nextInt(tamanho);
            int aux = vetor[pos1];

            vetor[pos1] = vetor[pos2];
            vetor[pos2] = aux;
        }

        return vetor;
    }

    public static void ordenacaoBolha(int[] vetor){
        for(int posReferencia = vetor.length; posReferencia > 0; posReferencia--){
            int trocas = 0;
            for(int posComp = 0; posComp < (posReferencia - 1); posComp++){
                if(vetor[posComp] > vetor[posComp+1]){
                    int aux = vetor[posComp];

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
