package TreeSort;

import java.util.ArrayList;

public class testeTreeSort {
    public static void main(String[] args) {
        int[] vetor = {50, 30, 70, 20, 40, 60, 80};

        TreeSort ts = new TreeSort();

        for (int valor : vetor)
            ts.inserir(valor);

        ArrayList<Integer> ordenado = ts.ordenar();
        
        System.out.println(ordenado);
    }
}
