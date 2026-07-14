package HeapComNo;

public class TesteHeap {

    public static void main(String[] args) {

        FilaPrioridadeHeap heap = new FilaPrioridadeHeap();

        System.out.println("Inserindo elementos....");
        heap.insert(40);
        heap.insert(20);
        heap.insert(60);
        heap.insert(10);
        heap.insert(50);
        heap.insert(30);
        heap.insert(70);

        System.out.println("Heap:");
        heap.imprimir();

        System.out.println("\nMenor elemento:");
        System.out.println(heap.min());

        System.out.println("\nQuantidade:");
        System.out.println(heap.size());

        System.out.println("\nRemovendo:");

        while (!heap.isEmpty()) {
            System.out.println(heap.removeMin());
            heap.imprimir();
        }
    }
}
