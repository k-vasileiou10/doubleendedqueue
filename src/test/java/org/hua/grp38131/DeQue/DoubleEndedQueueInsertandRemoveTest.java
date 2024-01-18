package org.hua.grp38131.DeQue;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class DoubleEndedQueueInsertandRemoveTest {
    @Test
    public void Test() {
        int amountOfThingsToEnter=124;
        DeQueue<Integer> deQueue=new DoubleEndedQueueArray<Integer>();
        assertTrue(deQueue.isEmpty());
        
        for(int i=0; i<amountOfThingsToEnter-1; i++) {
			deQueue.pushLast(i);
		}
        deQueue.pushFirst(amountOfThingsToEnter);

        assertTrue(deQueue.size()==124);

        for(int i=0; i<amountOfThingsToEnter-1; i++) {
			deQueue.popLast();
		}
        deQueue.popFirst();

        assertTrue(deQueue.size()==0);
    }
}