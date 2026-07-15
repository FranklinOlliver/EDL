package hash;

public class HashLinearProbing {

    private Object[] hash;
    private int cap_array;  // capacidade do array
    private int n;  // quantidade de elementos no array

    public HashLinearProbing(){

    }

    // construtor que recebe a capacidade do array
    public HashLinearProbing(int tam){
        this.cap_array = tam;
        this.n = 0;
        this.hash = new Object[cap_array];
    }

    public int funcaoHash(Object c){
        return ((int)c % cap_array);
    }

    public boolean isEmpty(){
        return n==0;
    }

    public int size(){
        return n;
    }

    public void insert(Object c){
        int indice = funcaoHash(c);
        boolean x = false;
        boolean add = false;
        // verificar colisão e aumentar a capacidade do array se necessário
        while (hash[indice] != null && hash[indice] != "AV") {
            indice++;
            indice = indice%(cap_array);
            if (n >= (cap_array/2)){
                int new_cap = cap_array * 2;
                Object[] hash_aux = new Object[new_cap];
                for (int i = 0; i < cap_array; i++) {
                    if (hash[i] != null) {
                        int ind_aux = (int) hash[i] % new_cap;
                        while (hash_aux[ind_aux] != null) {
                            ind_aux++;
                            ind_aux = ind_aux%new_cap;
                        }
                        hash_aux[ind_aux] = hash[i];
                    }
                }
                hash = hash_aux;
                cap_array = new_cap;
                if (!add) {
                    insert(c);
                    add = true;
                }
                x = true;
            }
        }
        if (!x) {
            hash[indice] = c;
            n++;
        }
    }

    // remove o elemento do array e retorna o elemento removido ou null se não encontrado
    public Object remove(Object c){
        int indice = funcaoHash(c);
        Object aux;
        while (hash[indice] != null){
            if (hash[indice] == c){
                aux = hash[indice];
                hash[indice] = "AV";
                n--;
                return aux;
            }
            indice++;
            indice = indice%cap_array;
        }
        return null;
    }

    // insere o elemento no array e retorna o elemento inserido ou null se não inserido
    public Object find(Object c){
        int indice = funcaoHash(c);
        while (hash[indice] != null){
            if (hash[indice] == c){
                return hash[indice];
            }
            indice++;
            indice = indice%cap_array;
        }
        return null;
    }

    // impressão do array, mostrando o índice e o valor de cada elemento
    public void print(){
        for (int i=0; i<cap_array; i++){
            if (hash[i] != null && hash[i] != "AV"){
                System.out.print("i"+i+":"+hash[i]+" ");
            }
        }
        System.out.println();
    }
}
