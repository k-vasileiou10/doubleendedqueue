package org.hua.grp38131.DeQue;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DoubleEndedQueueInsertandClearTest {
    @Test
    public void Test() {
        int amountOfThingsToEnter=1000;
        DeQueue<Integer> deQueue=new DoubleEndedQueueArray<Integer>();
        assertTrue(deQueue.isEmpty());
        
        for(int i=0; i<amountOfThingsToEnter/2; i++) {
			deQueue.pushLast(i);
			deQueue.pushFirst(amountOfThingsToEnter-i);
		}

        assertTrue(deQueue.size()==1000);

        assertTrue(deQueue.first()==501);
        assertTrue(deQueue.last()==499);

        deQueue.clear();

        assertTrue(deQueue.size()==0);
    }
}