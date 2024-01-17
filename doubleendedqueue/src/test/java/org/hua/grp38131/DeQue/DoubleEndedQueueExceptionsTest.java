package org.hua.grp38131.DeQue;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class DoubleEndedQueueExceptionsTest {
    @Test
    public void Test() {
        DeQueue<Integer> deQueue=new DoubleEndedQueueArray<Integer>();
        assertTrue(deQueue.isEmpty());
        
        assertThrows(NoSuchElementException.class, ()->deQueue.first());                
        assertThrows(NoSuchElementException.class, ()->deQueue.last());
        assertThrows(NoSuchElementException.class, ()->deQueue.popFirst());
        assertThrows(NoSuchElementException.class, ()->deQueue.popLast());
        assertThrows(IllegalArgumentException.class, ()->deQueue.pushFirst(null));
        assertThrows(IllegalArgumentException.class, ()->deQueue.pushLast(null));
        
        int amountOfThingsToEnter=10;
        for(int i=0; i<amountOfThingsToEnter; i++) {
			deQueue.pushLast(i);
		}

        Iterator<Integer> it=deQueue.iterator();
        
        assertThrows(ConcurrentModificationException.class, ()-> {
            while(it.hasNext()) {
                deQueue.popFirst();
            }
        });
    }
}