/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.aed.ficha;

/**
 *
 * @author felermino
 */
class Link {

    int val;
    Link next;

    Link(int x) {
        val = x;
    }
    
    @Override
    public String toString(){
        return this.val+"";
    }
}

class LinkedList{
    
    Link first;
    
    public LinkedList(){
        first = null;
    }
    
    public LinkedList(Link first){
        this.first = first;
    }
    
    public void add(int element){
        Link newLink = new Link(element);
        newLink.next = first;
        first = newLink;
    } 
 
    public Link remove(){
        Link temp = first;
        first = first.next;
        return temp;
    }
    
    @Override
    public String toString(){
        Link actual = first;
        String out = "";
        while(actual != null){
            out+=actual.val+" ";
            actual = actual.next;
        }
        return out;
    }
}

public class Solution {
    
    public Link mergeKLists(Link[] lists) {
        
        Link list = lists[0];
        Link first = list;
        int i = 0;
        while (i < lists.length-1) {
            Link prev = null;
            while(list!=null){
                prev = list;
                list = list.next;
            }
            i++;
            list = prev;
            list.next = lists[i];
        }
        
        return first;
    }
    
    public static void main(String[] args) {
        
        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();
        LinkedList list3 = new LinkedList();
        
        
        list1.add(2);
        list1.add(1);
        
        list2.add(4);
        list2.add(3);
        
        list3.add(6);
        list3.add(5);
        
        Link first = new Solution().mergeKLists(new Link[]{list1.first, list2.first, list3.first});
        
        LinkedList merged = new LinkedList(first);
        
        System.out.println(merged);
    }
}
