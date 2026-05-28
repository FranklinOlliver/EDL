package HeapComFilaDePrioridade-Array;

public class testeHeapComFilaDePrioridade {
    public static void main(String[] args) {
        // 1. Inicializa a fila de prioridade para números inteiros
        heapComFilaDePrioridade<Integer> fila = new heapComFilaDePrioridade<>();

        System.out.println("=== TESTANDO ESTADO INICIAL ===");
        System.out.println("A fila está vazia? " + fila.isEmpty()); // Esperado: true
        System.out.println("Tamanho inicial: " + fila.size());      // Esperado: 0

        System.out.println("\n=== INSERINDO ELEMENTOS ===");
        // Inserindo elementos de forma totalmente desordenada
        int[] elementos = {45, 12, 85, 3, 27, 12, 9};
        for (int num : elementos) {
            System.out.println("Inserindo: " + num);
            fila.insert(num);
        }

        System.out.println("\n=== TESTANDO MÉTODOS DE CONSULTA ===");
        System.out.println("A fila está vazia? " + fila.isEmpty()); // Esperado: false
        System.out.println("Tamanho atual: " + fila.size());        // Esperado: 7
        System.out.println("Menor elemento (min): " + fila.min());  // Esperado: 3

        System.out.println("\n=== REMOVENDO E EXIBINDO EM ORDEM (removeMin) ===");
        // O loop deve retirar os elementos estritamente do menor para o maior
        while (!fila.isEmpty()) {
            System.out.println("Removido menor: " + fila.removeMin());
        }

        System.out.println("\n=== TESTANDO ESTADO FINAL ===");
        System.out.println("A fila voltou a ficar vazia? " + fila.isEmpty()); // Esperado: true
        System.out.println("Tamanho final: " + fila.size());                  // Esperado: 0
        
        // Testando comportamento de erro em fila vazia
        try {
            fila.min();
        } catch (Exception e) {
            System.out.println("\nSucesso: Exceção capturada ao espiar fila vazia -> " + e.getMessage());
        }
    }
}
