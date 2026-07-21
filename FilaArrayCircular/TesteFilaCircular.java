package FilaArrayCircular;

public class TesteFilaCircular {

    public static void main(String[] args) {

        FilaComArrayCircularImpl fila = new FilaComArrayCircularImpl(3);

        System.out.println("=== TESTE 1: Inserção inicial ===");
        fila.enfileirar("A");
        fila.enfileirar("B");
        fila.enfileirar("C");
        System.out.println(fila); // [A, B, C]

        System.out.println("\n=== TESTE 2: Reverse simples ===");
        fila.reverse();
        System.out.println(fila); // [C, B, A]

        System.out.println("Removendo após reverse:");
        System.out.println(fila.desenfileirar()); // C
        System.out.println(fila); // [B, A]

        System.out.println("\n=== TESTE 3: Enqueue após reverse ===");
        fila.enfileirar("D");
        System.out.println(fila); // [D, B, A]

        System.out.println("\n=== TESTE 4: Reverse novamente ===");
        fila.reverse();
        System.out.println(fila); // [A, B, D]

        System.out.println("Removendo:");
        System.out.println(fila.desenfileirar()); // A
        System.out.println(fila); // [B, D]

        System.out.println("\n=== TESTE 5: Forçar expansão ===");
        fila.enfileirar("E");
        fila.enfileirar("F"); // deve expandir
        System.out.println(fila);

        System.out.println("\n=== TESTE 6: Reverse após expansão ===");
        fila.reverse();
        System.out.println(fila);

        System.out.println("\n=== TESTE 7: Remoções após reverse ===");
        while (!fila.estaVazia()) {
            System.out.println("Removido: " + fila.desenfileirar());
        }

        System.out.println("\n=== TESTE 8: Exceção ===");
        try {
            fila.desenfileirar();
        } catch (EFilaVaziaException e) {
            System.out.println("OK! Exceção capturada: " + e.getMessage());
        }

        System.out.println("\n=== TESTE 9: Reverse em fila vazia ===");
        fila.reverse();
        System.out.println("Fila após reverse vazia: " + fila);

        System.out.println("\n=== TESTE 10: Inserção após reverse vazia ===");
        fila.enfileirar("X");
        fila.enfileirar("Y");
        System.out.println(fila);

        System.out.println("Removendo:");
        System.out.println(fila.desenfileirar());
        System.out.println(fila);
    }
}
