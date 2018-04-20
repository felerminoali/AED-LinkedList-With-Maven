/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.aed.doublelinked;

/**
 *
 * @author feler
 */
public class Link {
    
    
    public int dData;
    public Link proxima;
    public Link anterior;

    public Link(int dData) {
        this.dData = dData;
    }
    
    public Link(int dData, Link proxima) {
        this.dData = dData;
        this.proxima = proxima;
    }
    
    @Override
    public String toString(){
        return "{" + dData + "} ";
    }
    
}
