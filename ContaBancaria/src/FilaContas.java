public class FilaContas {
    int inicio;
    int fim;
    int tamanho;
    int qtdeElementos;
    Operacao f[];

    public FilaContas(int tam){
        this.inicio = fim = -1;
        this.tamanho = tam;
        this.f = new Operacao[tamanho];
        this.qtdeElementos = 0;
    }

    public boolean estaVazia(){
        if (qtdeElementos == 0){
            return true;
        }
        return false;
    }

    public boolean estaCheia(){
        if (qtdeElementos == tamanho - 1){
            return true;
        }
        return false;
    }

    public void adicionar(Operacao conta){
        if (! estaCheia()){
            if (inicio == -1){
                inicio = 0;
            }
            fim++;
            f[fim] = conta;
            qtdeElementos++;
        }
    }

    public void remover(){
        if (! estaVazia() ){
            inicio++;
            qtdeElementos--;
        }
    }

    public void mostrar(){
        for (int i = 0; i < f.length; i++) {
            System.out.println(f[i]);
        }
    }
}
