/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// testw

import junit.framework.Assert;
import mz.com.osoma.aed.LinkList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author feler
 */
public class LinkedListTestUnit {

    public LinkedListTestUnit() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testeAdiciona() {
        LinkList lista = new LinkList();

        lista.adiciona(1);
        lista.adiciona(2);
        lista.adiciona(3);

        String expected = "[3, 2, 1]";

        Assert.assertEquals(expected, lista.toString());
    }

    @Test
    public void testeAdicionaPorTeste() {

        LinkList lista = new LinkList();

        lista.adiciona(1);
        lista.adiciona(2);
        lista.adiciona(3);
        lista.adiciona(0, 4);

        String expected = "[4, 3, 2, 1]";

        Assert.assertEquals(expected, lista.toString());
    }

    @Test
    public void testePegaPorPosicao() {

        LinkList lista = new LinkList();

        lista.adiciona(1);
        lista.adiciona(2);

        Assert.assertEquals(2, lista.pega(0));
        Assert.assertEquals(1, lista.pega(1));

    }
    
    
    @Test
    public void testeRemovePorPosicao(){
        
        LinkList lista = new LinkList();
        
        lista.adiciona(1);
        lista.adiciona(2);
        lista.adiciona(4);
        
        lista.remove(1);
        Assert.assertEquals(2, lista.tamanho());
        
        lista.adiciona(3);
        
         Assert.assertEquals(3, lista.tamanho());
        
    }
    
    @Test
    public void testeContemElemento(){
        
        LinkList lista = new LinkList();
        
        lista.adiciona(1);
        lista.adiciona(2);
        lista.adiciona(4);
        
        Assert.assertEquals(true, lista.contem(4));
        Assert.assertEquals(false, lista.contem(7));
        
    
    }

}
