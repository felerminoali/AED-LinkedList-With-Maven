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
    private int totalDeElementos;

    public void DoublyLinked() {
        primeira = null;
        ultima = null;
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
        ultima = nova;
        ultima.proxima = nova;

        totalDeElementos++;
    }

    @Override
    public void adiciona(int posicao, int elemento) {

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

    public void removeDoComeco() {

        if (!this.posicaoOcupada(0)) {
            throw new IllegalArgumentException("Posição não existe");
        }

        primeira = primeira.proxima;
        totalDeElementos--;

        if (totalDeElementos == 0) {
            ultima = null;
        }
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
        
        if(posicao == 0){
            removeDoComeco();
        }else if(posicao == totalDeElementos -1){
            removeDoFim();
        }else{
            Link anterior = pegaLink(posicao-1);
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

}
