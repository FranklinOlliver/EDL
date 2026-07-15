package arvorePesquisa;

import java.util.ArrayList;

public class ArvorePesquisa {

    protected static No root;
    protected int size;

    // árvore sem elementos
    public ArvorePesquisa() {
    }

    // árvore já contendo a raiz
    public ArvorePesquisa(Object key) {
        root = new No(key);
        size = 1;
    }

    // raiz da árvore
    public No root()
    {
        return root;
    }

    // verificar se o nó possui pelo menos um filho
    public boolean isInternal(No v)
    {
        return (v.getFilhoEsquerdo() != null || v.getFilhoDireito() != null);
    }

    // verificar se é folha
    public boolean isExternal(No v)
    {
        return (v.getFilhoEsquerdo() == null && v.getFilhoDireito() == null);
    }

    // verificar se o nó é a raiz
    public boolean isRoot(No v)
    {
        return v == root;
    }

    // quantidade de nós
    public int size(){
        return size;
    }

    // há ou não elementos
    public boolean isEmpty()
    {
        // return false;
        return size == 0;
    }

    // retorna o pai do nó
    public No parent(No v)
    {
        return v.getPai();
    }

    // calcular a profundidade do nó
    public int depth(No v){
        if (isRoot(v))
            return 0;
        else
            return 1 + depth(v.getPai());
    }

    // calcular a altura da subárvore
    public int height(No v){
        if(isExternal(v)){
            return 0;
        }
        else {
            int h1 = 0;
            if (hasLeft(v)){
                h1 = 1 + height(v.getFilhoEsquerdo());
            }
            int h2 = 0;
            if (hasRight(v)) {
                h2 = 1 + height(v.getFilhoDireito());
            }
            return h1 > h2 ? h1 : h2;   // maior caminho até uma folha
        }
    }

    // substituir o valor armazenado
    public Object replace(No v, Object o) {
        Object aux = v.getElemento();
        v.setElemento(o);
        return aux;
    }

    // método de busca
    public No find(Object k, No v){
        if (isExternal(v)){
            return v;
        }
        if ( (int) k < (int) v.getElemento()){
            if (hasLeft(v)){
                return find(k, v.getFilhoEsquerdo());
            }
            return v;
        }
        else if ( k == v.getElemento()){
            return v;
        }
        else if ( (int) k > (int) v.getElemento()){
            if (hasRight(v)){
                return find(k , v.getFilhoDireito());
            }
            return v;
        }
        return v;
    }

    // inserir um novo elemento
    public No insert(Object k){
        No aux = find(k, root);
        No v = new No();
        v.setElemento(k);
        if ( (int) k <= (int) aux.getElemento()){
            v.setPai(aux);
            aux.setFilhoEsquerdo(v);
        }
        else {
            v.setPai(aux);
            aux.setFilhoDireito(v);
        }
        size++;
        return v;
    }

    // remover um elemento
    public void remove(Object k){
        No aux = find(k, root);
        if(k != aux.getElemento()) {
            throw new InvalidNoException("O Node com a chave "+ k + " nao existe!");
        }
        else {
            if (isExternal(aux)) {
                if (aux.getPai().getFilhoEsquerdo() == aux) {
                    aux.getPai().setFilhoEsquerdo(null);
                } else {
                    aux.getPai().setFilhoDireito(null);
                }
            } else if (aux.getFilhoEsquerdo() == null) { //verifica se o aux tem filho direito
                if (aux.getPai().getFilhoEsquerdo() == aux) { // verifica se é filho esquerdo
                    aux.getPai().setFilhoEsquerdo(aux.getFilhoDireito());
                    aux.getFilhoDireito().setPai(aux.getPai());
                } else if (aux.getPai().getFilhoDireito() == aux) {
                    aux.getPai().setFilhoDireito(aux.getFilhoDireito());
                    aux.getFilhoDireito().setPai(aux.getPai());
                }
            } else if (aux.getFilhoDireito() == null) {
                if (aux.getPai().getFilhoEsquerdo() == aux) {
                    aux.getPai().setFilhoEsquerdo(aux.getFilhoEsquerdo());
                    aux.getFilhoEsquerdo().setPai(aux.getPai());
                } else if (aux.getPai().getFilhoDireito() == aux) {
                    aux.getPai().setFilhoDireito(aux.getFilhoEsquerdo());
                    aux.getFilhoEsquerdo().setPai(aux.getPai());
                }

            } else { // v possui os 2 filhos.
                No min;
                min = aux;
                min = min.getFilhoDireito();
                while (min.getFilhoEsquerdo() != null) { //encontrar o menor a direita
                    min = min.getFilhoEsquerdo();
                }
                size++;
                remove(min.getElemento());
                aux.setElemento(min.getElemento());
            }
            size--;
        }
    }

    // travessia pre orden
    public void preOrder(No v) {
        if (v != null){
            System.out.println(v.getElemento());
            preOrder(v.getFilhoEsquerdo());
            preOrder(v.getFilhoDireito());
        }

    }

    // travessia pos orden
    public void posOrder(No v){
        if (v != null){
            posOrder(v.getFilhoEsquerdo());
            posOrder(v.getFilhoDireito());
            System.out.println(v.getElemento());
        }
    }

    // travessia in orden
    public void inOrder(No v){
        if (v != null){
            inOrder(v.getFilhoEsquerdo());
            System.out.println(v.getElemento());
            inOrder(v.getFilhoDireito());
        }
    }

    // verificar se possui filho esquerdo
    protected static boolean hasLeft(No v){
        return v.getFilhoEsquerdo() != null;
    }
    // verificar se possui filho direito
    protected static boolean hasRight(No v){
        return v.getFilhoDireito() != null;
    }

    // Exibir a árvore
    public void printArvore() {
        ArrayList<No> lista = new ArrayList<>();
        organizador(root, lista);
        System.out.println("A R V O R E:");
        for(int j=0; j <= height(root); j++) {
            for(int i = 0; i<size();i++) {
                if(depth(lista.get(i)) == j) {
                    System.out.print("(" + (lista.get(i)).getElemento() + ")");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }

    private void organizador(No no,  ArrayList<No> lista) {
        if(no.getFilhoEsquerdo() != null) {
            organizador(no.getFilhoEsquerdo(),lista);
        }
        lista.add(no);
        if(no.getFilhoDireito() != null) {
            organizador(no.getFilhoDireito(),lista);
        }
    }
}

