package org.hua.grp38131.DeQue;

import java.util.Iterator;

public class App {
    public static void main(String[] args) {
        DeQueue<String> SuperMarketList=new DoubleEndedQueueArray<String>();
        System.out.println("Welcome to the Super Market List creator!");

        System.out.println("I am adding bananas.");
        SuperMarketList.pushFirst("Bananas");
        System.out.println("I am adding bread.");
        SuperMarketList.pushFirst("Bread");
        System.out.println("I am adding milk.");
        SuperMarketList.pushFirst("Milk");
        System.out.println("I am adding a GPU at the end.");
        SuperMarketList.pushLast("GPU");
        System.out.println("Maybe I don't need milk anymore.");
        SuperMarketList.popFirst();
        System.out.println("But I do need coffee cups.");
        SuperMarketList.pushFirst("Coffee Cups");
        System.out.println("I don't think I have enough money for a GPU...");
        SuperMarketList.popLast();
        System.out.println("I don't even like bananas!");
        SuperMarketList.popLast();
        System.out.println("I'll get some apples instead.");
        SuperMarketList.pushFirst("Apples");
        System.out.println("Lastly, I'll add some food for my pet python.");
        SuperMarketList.pushLast("Snake Food");

        System.out.println("\nFinal list is:");

        Iterator<String> smlIt=SuperMarketList.iterator();
        Iterator<String> smldIt=SuperMarketList.descendingIterator();

        while(smlIt.hasNext()) {
            System.out.println(smlIt.next());
        }
        System.out.println();

        System.out.println("And in reverse: ");
        while(smldIt.hasNext()) {
            System.out.println(smldIt.next());
        }

        SuperMarketList.clear();
        System.out.println("\nThe list has now been flushed.\n");
    }
}