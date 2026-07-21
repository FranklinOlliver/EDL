package FilaArrayCircular;

public class FilaComArrayCircularImpl implements FilaInterface {
    private int capacidadeDoArray;
    private int indiceDoComecoDaFila;
    private int indiceDoFimDaFila;
    private int tamanhoDaFila;
    private Object[] array;

    // controla reversão lógica (indica se a fila está logicamente invertida)
    private boolean invertida = false;

    public int getCapacidadeDoArray() {
        return capacidadeDoArray;
    }

    private void setCapacidadeDoArray(int novaCapacidadeDoArray) {
        capacidadeDoArray = novaCapacidadeDoArray;
    }

    private void setIndiceDoComecoDaFila(int novoIndiceDoComecoDaFila) {
        indiceDoComecoDaFila = novoIndiceDoComecoDaFila;
    }

    private void setIndiceDoFimDaFila(int novoIndiceDoFimDaFila) {
        indiceDoFimDaFila = novoIndiceDoFimDaFila;
    }

    private void setArray(Object[] novoArray) {
        array = novoArray;
    }

    private void incrementarTamanho() {
        tamanhoDaFila++;
    }

    private void decrementarTamanho() {
        tamanhoDaFila--;
    }

    public boolean estaVazia() {
        return tamanhoDaFila == 0;
    }

    public int tamanho() {
        return tamanhoDaFila;
    }

    public boolean estaCheia() {
        return tamanho() == capacidadeDoArray;
    }

    // inverte a fila em O(1)
    public void reverse() {
        invertida = !invertida;
    }

    // insere um elemento na fila
    public void enfileirar(Object novoObjeto) {
        // se estiver cheia, dobra o tamanho
        if (estaCheia()) {
            duplicarEspacoDaFila();
        }

        if (!invertida) {
            // insere no fim da fila
            array[indiceDoFimDaFila] = novoObjeto;
             // avança de forma circular
            indiceDoFimDaFila = (indiceDoFimDaFila + 1) % capacidadeDoArray;
        } else {
            // insere no começo da fila (modo invertido)
            indiceDoComecoDaFila = (indiceDoComecoDaFila - 1 + capacidadeDoArray) % capacidadeDoArray;
            array[indiceDoComecoDaFila] = novoObjeto;
        }

        incrementarTamanho();
    }

    // remove um elemento da fila
    public Object desenfileirar() {
        if(estaVazia()) {
            throw new EFilaVaziaException("A fila está vazia, portanto, não há o que desenfileirar.");
        }

        Object objetoRemovido;

        if (!invertida) {
            // remove do começo da fila
            objetoRemovido = array[indiceDoComecoDaFila];
            array[indiceDoComecoDaFila] = null;
            indiceDoComecoDaFila = (indiceDoComecoDaFila + 1) % capacidadeDoArray;
        } else {
            // remove do fim no fim da fila (modo invertido)
            indiceDoFimDaFila = (indiceDoFimDaFila - 1 + capacidadeDoArray) % capacidadeDoArray;
            objetoRemovido = array[indiceDoFimDaFila];
            array[indiceDoFimDaFila] = null;
        }

        decrementarTamanho();
        // após remover, verifica se deve reduzir o array
        reduzirEspacoDaFila();
        return objetoRemovido;
    }

    // duplica o tamanho do array
    private void duplicarEspacoDaFila() {
        redimensionarArray(capacidadeDoArray * 2);
    }

    // redução quando <= 1/3 da capacidade
    private void reduzirEspacoDaFila() {
        if (capacidadeDoArray > 1 && tamanhoDaFila <= capacidadeDoArray / 3) {
            redimensionarArray(capacidadeDoArray / 2);
        }
    }

    // centraliza crescimento e redução
    private void redimensionarArray(int novaCapacidade) {
        Object[] novoArray = new Object[novaCapacidade];

        // aopia os elementos respeitando a ordem lógica da fila
        for (int i = 0; i < tamanhoDaFila; i++) {
            int idx;

            if (!invertida) {
                // percorre do começo para frente
                idx = (indiceDoComecoDaFila + i) % capacidadeDoArray;
            } else {
                int inicioLogico = (indiceDoFimDaFila - 1 + capacidadeDoArray) % capacidadeDoArray;
                idx = (inicioLogico - i + capacidadeDoArray) % capacidadeDoArray;
            }

            novoArray[i] = array[idx];
        }

        setArray(novoArray);
        setCapacidadeDoArray(novaCapacidade);
        setIndiceDoComecoDaFila(0);
        setIndiceDoFimDaFila(tamanhoDaFila);

        // após reorganizar, volta ao modo normal
        invertida = false;
    }

    // retorna o primeiro elemento sem remover
    public Object primeiro() {
        if(estaVazia()) {
            throw new EFilaVaziaException("A fila está vazia, portanto, não há primeiro elemento.");
        } 

        if (!invertida) {
            return array[indiceDoComecoDaFila];
        } else {
            int idx = (indiceDoFimDaFila - 1 + capacidadeDoArray) % capacidadeDoArray;
            return array[idx];
        }
    }

    // representação da fila como string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fila: [ ");

        for (int i = 0; i < tamanhoDaFila; i++) {
            int idx;

            if (!invertida) {
                idx = (indiceDoComecoDaFila + i) % capacidadeDoArray;
            } else {
                int inicioLogico = (indiceDoFimDaFila - 1 + capacidadeDoArray) % capacidadeDoArray;
                idx = (inicioLogico - i + capacidadeDoArray) % capacidadeDoArray;
            }

            sb.append(array[idx]);
            if (i < tamanhoDaFila - 1) sb.append(", ");
        }

        sb.append(" ]");
        return sb.toString();
    }

    public FilaComArrayCircularImpl(int capacidadeDoArray) {
        if(capacidadeDoArray < 1) {
            throw new IllegalArgumentException("A capacidade da fila deve ser maior que 0.");
        }

        this.capacidadeDoArray = capacidadeDoArray;
        this.array = new Object[capacidadeDoArray];
        // inicialmente ambos apontam para 0
        this.indiceDoComecoDaFila = 0;
        this.indiceDoFimDaFila = 0;
    }
}
