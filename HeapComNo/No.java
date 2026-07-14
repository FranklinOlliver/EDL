package HeapComNo;

public class No {
    int valor;
    No esquerda;
    No direita;
    No pai;

    public No(int valor) {
        this.valor = valor;
        esquerda = null;
        direita = null;
        pai = null;
    }
}
