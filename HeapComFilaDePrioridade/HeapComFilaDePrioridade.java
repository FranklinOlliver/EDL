package HeapComFilaDePrioridade;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class heapComFilaDePrioridade<T extends Comparable<T>> {
    private ArrayList<T> heap;

    // Construtor: Inicializa o array dinâmico
    public heapComFilaDePrioridade() {
        this.heap = new ArrayList<>();
    }

    // Retorna a quantidade de elementos na fila
    public int size() {
        return heap.size();
    }

    // Verifica se a fila está vazia
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Retorna o menor elemento sem removê-lo
    public T min() {
        if (isEmpty()) {
            throw new NoSuchElementException("A fila de prioridade está vazia.");
        }
        return heap.get(0);
    }

    // Insere um novo elemento na fila
    public void insert(T item) {
        heap.add(item); // Adiciona no final do array
        heapifyUp(heap.size() - 1); // Restaura a propriedade do heap subindo o nó
    }

    // Remove e retorna o menor elemento da fila
    public T removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("A fila de prioridade está vazia.");
        }

        T raiz = heap.get(0);
        T ultimoItem = heap.remove(heap.size() - 1); // Remove o último elemento

        if (!heap.isEmpty()) {
            heap.set(0, ultimoItem); // Move o último elemento para a raiz
            heapifyDown(0); // Restaura a propriedade do heap descendo o nó
        }

        return raiz;
    }

    // Auxiliar: Flutua o elemento para cima para manter a ordem do Min-Heap
    private void heapifyUp(int index) {
        int pai = (index - 1) / 2;

        // Se o nó atual for menor que o pai, eles trocam de lugar
        if (index > 0 && heap.get(index).compareTo(heap.get(pai)) < 0) {
            swap(index, pai);
            heapifyUp(pai); // Continua subindo recursivamente
        }
    }

    // Auxiliar: Empurra o elemento para baixo para manter a ordem do Min-Heap
    private void heapifyDown(int index) {
        int menor = index;
        int esquerdo = 2 * index + 1;
        int direito = 2 * index + 2;
        int tamanho = heap.size();

        // Verifica se o filho esquerdo é menor que o nó atual
        if (esquerdo < tamanho && heap.get(esquerdo).compareTo(heap.get(menor)) < 0) {
            menor = esquerdo;
        }

        // Verifica se o filho direito é menor que o menor encontrado até agora
        if (direito < tamanho && heap.get(direito).compareTo(heap.get(menor)) < 0) {
            menor = direito;
        }

        // Se o menor não for o próprio nó, realiza a troca e continua descendo
        if (menor != index) {
            swap(index, menor);
            heapifyDown(menor); // Continua descendo recursivamente
        }
    }

    // Auxiliar: Realiza a troca de dois elementos no array
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
