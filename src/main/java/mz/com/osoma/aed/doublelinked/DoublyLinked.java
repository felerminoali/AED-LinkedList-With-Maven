/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.aed.doublelinked;

import mz.com.osoma.aed.Operacoes;

/**
 *
 * @author feler
 */
public class DoublyLinked implements Operacoes {

    private Link primeira;
    private Link ultima;
    private int totalDeElementos = 0;

    public void DoublyLinked() {
        primeira = null;
        ultima = null;
    }
    
    public void DoublyLinked(Link primeira, Link ultima) {
        this.primeira = primeira;
        this.ultima = ultima;
    }

    public boolean isEmpty() {
        return (primeira == null);
    }

    private boolean posicaoOcupada(int posicao) {
        return posicao >= 0 && posicao < this.totalDeElementos;
    }

    private Link pegaLink(int posicao) {

        if (!this.posicaoOcupada(posicao)) {
            throw new IllegalArgumentException("Posição não existe");
        }

        Link atual = primeira;
        for (int i = 0; i < posicao; i++) {
            atual = atual.proxima;
        }
        return atual;
    }

    public void adicionaNoComeco(int elemento) {

        Link nova = new Link(elemento);
        if (this.totalDeElementos == 0) {
            this.primeira = nova;
            this.ultima = nova;
        } else {
            nova.proxima = primeira;
            nova.anterior = nova;
            primeira = nova;
        }

        this.totalDeElementos++;
    }

    @Override
    public void adiciona(int elemento) {
        if (this.totalDeElementos == 0) {
            adicionaNoComeco(elemento);
        } else {
            adicionaDoFim(elemento);
        }

    }

    public void adicionaDoFim(int elemento) {

        Link nova = new Link(elemento);
        nova.anterior = ultima;
        ultima.proxima = nova;
        ultima = nova;
        totalDeElementos++;
    }

    @Override
    public void adiciona(int posicao, int elemento) {

        if (!this.posicaoOcupada(posicao)) {
            throw new IllegalArgumentException("Posição não existe");
        }

        if (posicao == 0) {
            adicionaNoComeco(elemento);
        } else if (posicao == totalDeElementos) {
            adicionaDoFim(elemento);
        } else {
            Link anterior = pegaLink(posicao - 1);
            Link proxima = anterior.proxima;

            Link nova = new Link(elemento);
            nova.proxima = proxima;
            nova.anterior = anterior;
            anterior.proxima = nova;
            proxima.anterior = nova;
            totalDeElementos++;
        }
    }

    @Override
    public int pega(int posicao) {
        return this.pegaLink(posicao).dData;
    }

    public Link removeDoComeco() {

//        if (!this.posicaoOcupada(0)) {
//            throw new IllegalArgumentException("Posição não existe");
//        }
        Link temp = primeira;
        primeira = primeira.proxima;
        totalDeElementos--;

        if (totalDeElementos == 0) {
            ultima = null;
        }

        return temp;
    }

    public void removeDoFim() {

        if (!this.posicaoOcupada(totalDeElementos - 1)) {
            throw new IllegalArgumentException("Posição não existe");
        }

        if (totalDeElementos == 1) {
            removeDoComeco();
        } else {
            Link penultima = ultima.anterior;
            penultima.proxima = null;
            totalDeElementos--;
        }
    }

    @Override
    public void remove(int posicao) {

        if (posicao == 0) {
            removeDoComeco();
        } else if (posicao == totalDeElementos - 1) {
            removeDoFim();
        } else {
            Link anterior = pegaLink(posicao - 1);
            Link proximo = pegaLink(posicao).proxima;

            proximo.anterior = anterior;
            anterior.proxima = proximo;

            totalDeElementos--;

        }

    }

    @Override
    public boolean contem(int elemento) {

        Link actual = primeira;

        while (actual != null) {
            if (actual.dData == elemento) {
                return true;
            }
            actual = actual.proxima;
        }

        return false;
    }

    @Override
    public int tamanho() {
        return this.totalDeElementos;
    }

    public void removerDuplicado() {

        Link actual = primeira;
        while (actual != null) {

            Link actualP = actual;
            Link posterior = actual.proxima;

            while (actualP.dData == posterior.dData) {
                actual = posterior;
                posterior = actual.proxima;
                totalDeElementos--;
                if (posterior == null) {
                    break;
                }

            }
            actualP.proxima = posterior;

            if (posterior != null) {
                posterior.anterior = actualP;
            }
            actual = posterior;
        }
    }

    public void reverso() {
        DoublyLinked rev = new DoublyLinked();
        Link first = this.removeDoComeco();
        while (first != null) {
            rev.adicionaNoComeco(first.dData);
            if (this.isEmpty()) {
                break;
            }
            first = this.removeDoComeco();
        }
        this.totalDeElementos = rev.totalDeElementos;
        this.primeira = rev.primeira;
        this.ultima = rev.ultima;
    }

    @Override
    public String toString() {
        // Verificando se a Lista está vazia
        if (this.totalDeElementos == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        Link atual = primeira;
        // Percorrendo até o penúltimo elemento.
        for (int i = 0; i < this.totalDeElementos - 1; i++) {
            builder.append(atual.dData);
            builder.append(", ");
            atual = atual.proxima;
        }
        // último elemento
        builder.append(atual.dData);
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {

        DoublyLinked list = new DoublyLinked();

        list.adiciona(2);
        list.adiciona(2);

        list.adiciona(3);
        list.adiciona(3);

        list.adiciona(4);
        list.adiciona(4);

        System.out.println(list);

        list.removerDuplicado();
        System.out.println(list);

        list.reverso();
        System.out.println(list);
    }
}
