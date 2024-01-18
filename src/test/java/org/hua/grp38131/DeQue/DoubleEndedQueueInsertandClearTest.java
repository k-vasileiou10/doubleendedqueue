package org.hua.grp38131.DeQue;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class DoubleEndedQueueInsertandClearTest {
    @Test
    public void Test() {
        int amountOfThingsToEnter=1000, randInt;
        DeQueue<Integer> deQueue=new DoubleEndedQueueArray<Integer>();
        assertTrue(deQueue.isEmpty());
        Random rng=new Random();

        
        for(int i=0; i<amountOfThingsToEnter; i++) {
            randInt=rng.nextInt(2);
            switch(randInt) {
                case 0:
                    deQueue.pushFirst(amountOfThingsToEnter-i);
                break;
                case 1:
                    deQueue.pushLast(i);
                break;
            }
		}

        assertTrue(deQueue.size()==1000);

        deQueue.clear();

        assertTrue(deQueue.size()==0);
    }
}