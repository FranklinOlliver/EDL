package HeapComNo;
import java.util.LinkedList;
import java.util.Queue;

public class FilaPrioridadeHeap {

    private No raiz;
    private int tamanho;

    public FilaPrioridadeHeap() {
        raiz = null;
        tamanho = 0;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public int size() {
        return tamanho;
    }

    public Integer min() {
        if (raiz == null)
            return null;

        return raiz.valor;
    }

    // INSERÇÃO
    public void insert(int valor) {

        No novo = new No(valor);

        if (raiz == null) {
            raiz = novo;
            tamanho++;
            return;
        }

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {

            No atual = fila.remove();

            if (atual.esquerda == null) {
                atual.esquerda = novo;
                novo.pai = atual;
                break;
            } else {
                fila.add(atual.esquerda);
            }

            if (atual.direita == null) {
                atual.direita = novo;
                novo.pai = atual;
                break;
            } else {
                fila.add(atual.direita);
            }
        }

        tamanho++;

        subir(novo);
    }

    // SUBIR (Heapify Up)
    private void subir(No no) {

        while (no.pai != null && no.valor < no.pai.valor) {

            int aux = no.valor;
            no.valor = no.pai.valor;
            no.pai.valor = aux;

            no = no.pai;
        }
    }

    // REMOVE MIN
    public Integer removeMin() {

        if (raiz == null)
            return null;

        int menor = raiz.valor;

        if (tamanho == 1) {
            raiz = null;
            tamanho--;
            return menor;
        }

        No ultimo = ultimoNo();

        raiz.valor = ultimo.valor;

        if (ultimo.pai.esquerda == ultimo)
            ultimo.pai.esquerda = null;
        else
            ultimo.pai.direita = null;

        tamanho--;

        descer(raiz);

        return menor;
    }

    // DESCE (Heapify Down)
    private void descer(No no) {

        while (true) {

            No menor = no;

            if (no.esquerda != null &&
                    no.esquerda.valor < menor.valor)
                menor = no.esquerda;

            if (no.direita != null &&
                    no.direita.valor < menor.valor)
                menor = no.direita;

            if (menor == no)
                break;

            int aux = no.valor;
            no.valor = menor.valor;
            menor.valor = aux;

            no = menor;
        }
    }

    // ENCONTRA O ÚLTIMO NÓ
    private No ultimoNo() {

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        No atual = null;

        while (!fila.isEmpty()) {

            atual = fila.remove();

            if (atual.esquerda != null)
                fila.add(atual.esquerda);

            if (atual.direita != null)
                fila.add(atual.direita);
        }

        return atual;
    }

    // IMPRESSÃO POR NÍVEL
    public void imprimir() {

        if (raiz == null) {
            System.out.println("Heap vazio.");
            return;
        }

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {

            No atual = fila.remove();

            System.out.print(atual.valor + " ");

            if (atual.esquerda != null)
                fila.add(atual.esquerda);

            if (atual.direita != null)
                fila.add(atual.direita);
        }

        System.out.println();
    }
}
