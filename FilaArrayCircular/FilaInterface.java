package FilaArrayCircular;

/*
    Segundo os slides do professor, "o TAD fila armazena objetos arbitrários".
    Isto é: objetos de quaquer natureza.

    Inserções e remoções seguem o esquema FIFO (First In, First Out).
    Ou seja:
                +======================================+
                | O primeiro objeto que entrou na fila |
                | deve ser o primeiro a sair.          |
                +======================================+
    ---

    Acima de cada método anotado nesta interface, será encontrada a descrição
    como posta no slide, usando citação direta.
 */

public interface FilaInterface {

    /* [void enqueue]
    * "enqueue(object): insere um elemento no fim da fila"
    */
    public void enfileirar(Object objeto); // pode levantar EFilaCheiaException

    /* [Object dequeue]
    * "object dequeue(): remove e retorna o elemento do início da fila"
    */
    public Object desenfileirar();

    /* [int size]
     * "integer size(): retorna o número de elementos armazenados"
     */
    public int tamanho();

    /* [boolean isEmpty]
     * "boolean isEmpty(): indica se há elementos na fila"
     */
    public boolean estaVazia();

    public Object primeiro();

    /* "object first(): retorna o elemento do início sem removê-lo" */
}
