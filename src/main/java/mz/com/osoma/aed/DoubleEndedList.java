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
public class DoubleEndedList implements Operacoes{
    
    private Link primeira;
    private Link ultima;
    private int totalDeElementos;
    
    public void LinkList() {
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
    
    public void adicionaNoComeco(int elemento){
        
        Link nova = new Link(elemento);
        nova.proxima = primeira;
        primeira = nova;
        
        if(totalDeElementos == 0){
            // caso especial da lista vazia
            this.ultima = this.primeira;
        }
        
        this.totalDeElementos++;
    
    }

    @Override
    public void adiciona(int elemento) {
        
        if(totalDeElementos == 0){
            this.adicionaNoComeco(elemento);
        }else{
            
            // Adiciona no fim
            Link nova = new Link(elemento);
            
            this.ultima.proxima = nova;
            this.ultima = nova;
            this.totalDeElementos++;
        
        }
        
    }

    @Override
    public void adiciona(int posicao, int elemento) {
        
        if(posicao == 0){
            this.adicionaNoComeco(elemento);
        }else if(posicao == this.totalDeElementos){
            this.adiciona(elemento);
        }else{
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

    public void removeDoComeco() {

        if (!this.posicaoOcupada(0)) {
            throw new IllegalArgumentException("Posição não existe");
        }

        primeira = primeira.proxima;
        totalDeElementos--;
        
        if(this.totalDeElementos == 0){
            this.ultima = null;
        }

    }
    
    
    public void removeDoFim(){
    
        if (!this.posicaoOcupada(this.totalDeElementos-1)) {
            throw new IllegalArgumentException("Posição não existe");
        }
        
        if(this.totalDeElementos == 1){
            this.removeDoComeco();
        }else{
            Link penultima = this.pegaLink(totalDeElementos-2);
            penultima.proxima = null;
            this.ultima = penultima;
            this.totalDeElementos--;
        }
    
    }
    
    @Override
    public void remove(int posicao) {
        
        if (!this.posicaoOcupada(0)) {
            throw new IllegalArgumentException("Posição não existe");
        }
        
        if(posicao ==0){
            this.removeDoComeco();
        } else if(posicao == this.totalDeElementos-1){
            this.removeDoFim();
        }else{
            Link anterior = pegaLink(posicao - 1);
            Link actual = pegaLink(posicao);

            anterior.proxima = actual.proxima;

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
