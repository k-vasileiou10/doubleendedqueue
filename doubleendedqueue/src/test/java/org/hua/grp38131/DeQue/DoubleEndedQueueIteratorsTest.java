package org.hua.grp38131.DeQue;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

public class DoubleEndedQueueIteratorsTest {
    @Test
    public void Test() {
        int amountOfThingsToEnter=1000;
        DeQueue<Integer> deQueue=new DoubleEndedQueueArray<Integer>();
        assertTrue(deQueue.isEmpty());
        
        for(int i=0; i<amountOfThingsToEnter; i++) {
			deQueue.pushLast(i);
		}
        assertTrue(deQueue.first()==0);
        assertTrue(deQueue.last()==999);
        
        Set<Integer> sit=new TreeSet<Integer>();
        Iterator<Integer> it=deQueue.iterator();
        Set<Integer> sdit=new TreeSet<Integer>();
        Iterator<Integer> dit=deQueue.descendingIterator();
        
        while(it.hasNext()) {
            sit.add(it.next());
        }
        assertTrue(sit.size()==1000);
        assertTrue(sit.contains(342));

        while(dit.hasNext()) {
            sdit.add(dit.next());
        }
        assertTrue(sdit.size()==1000);
        assertTrue(sdit.contains(669));
    }
}