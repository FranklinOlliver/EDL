package TreeSort;
import java.util.ArrayList;

public class TreeSort {
    private No raiz;

    // Inserção
    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }
    private No inserirRec(No atual, int valor) {
        if (atual == null)
            return new No(valor);
        if (valor < atual.valor)
            atual.esquerda = inserirRec(atual.esquerda, valor);
        else
            atual.direita = inserirRec(atual.direita, valor);
        return atual;
    }

    // Percurso em ordem
    public void emOrdem(No no, ArrayList<Integer> lista) {
        if (no != null) {
            emOrdem(no.esquerda, lista);
            lista.add(no.valor);
            emOrdem(no.direita, lista);
        }
    }

    // Ordenação
    public ArrayList<Integer> ordenar() {
        ArrayList<Integer> lista = new ArrayList<>();
        emOrdem(raiz, lista);
        return lista;
    }
}
