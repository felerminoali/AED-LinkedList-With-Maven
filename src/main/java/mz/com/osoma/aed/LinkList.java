/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.aed;

/**
 *
 * @author feler
 */
public class LinkList implements Operacoes {

    private Link primeiro;
    private int totalDeElementos;

    public void LinkList() {
        primeiro = null;
    }

    public boolean isEmpty() {
        return (primeiro == null);
    }

    private boolean posicaoOcupada(int posicao) {
        return posicao >= 0 && posicao < this.totalDeElementos;
    }

    private Link pegaLink(int posicao) {

        if (!this.posicaoOcupada(posicao)) {
            throw new IllegalArgumentException("Posição não existe");
        }

        Link atual = primeiro;
        for (int i = 0; i < posicao; i++) {
            atual = atual.proxima;
        }
        return atual;
    }

    
    
    
    @Override
    public void adiciona(int elemento) {
        Link nova = new Link(elemento);
        nova.proxima = primeiro;
        primeiro = nova;
        this.totalDeElementos++;
    }

    @Override
    public void adiciona(int posicao, int elemento) {

        if (posicao == 0) {
            this.adiciona(elemento);
        } else {
            Link anterior = pegaLink(posicao - 1);

            Link nova = new Link(elemento);
            nova.proxima = anterior.proxima;
            anterior.proxima = nova;
            totalDeElementos++;
        }
    }

    @Override
    public int pega(int posicao) {
        return this.pegaLink(posicao).dData;
    }

    public void remove() {

        if (!this.posicaoOcupada(0)) {
            throw new IllegalArgumentException("Posição não existe");
        }

        primeiro = primeiro.proxima;
        totalDeElementos--;

    }

    @Override
    public void remove(int posicao) {

        if (posicao == 0) {
            remove();
        } else {
            Link anterior = pegaLink(posicao - 1);
            Link actual = pegaLink(posicao);

            anterior.proxima = actual.proxima;

            totalDeElementos--;
        }
    }

    @Override
    public boolean contem(int elemento) {

        Link actual = primeiro;

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
        Link atual = primeiro;
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
