package org.hua.grp38131.DeQue;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class DoubleEndedQueueInsertandRemoveTest {
    @Test
    public void Test() {
        int amountOfThingsToEnter=128;
        DeQueue<Integer> deQueue=new DoubleEndedQueueArray<Integer>();
        assertTrue(deQueue.isEmpty());
        
        for(int i=0; i<amountOfThingsToEnter/2; i++) {
			deQueue.pushLast(i);
			deQueue.pushFirst(amountOfThingsToEnter-i);
		}

        assertTrue(deQueue.size()==128);

        for(int i=0; i<amountOfThingsToEnter/2; i++) {
			deQueue.popLast();
            deQueue.popFirst();
		}

        assertTrue(deQueue.size()==0);
    }
}