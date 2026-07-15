package TADLista;

public class TestListaDuplamenteEncadeada {
    public static void main(String[] args) throws ListaVaziaException {
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
        System.out.println("Tamanho: "+lista.size());
        lista.insertFirst(1);
        lista.insertFirst(2);
        lista.insertLast(4);
        lista.insertBefore(2,10);
        lista.insertAfter(0,5);
        lista.print();
    }
}
