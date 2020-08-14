package com.dataStructures;

import implementations.BasicStack;
import testClasses.Card;

public class Main {

    public static void main(String[] args) {
        BasicStack<Card> cardBasicStack = new BasicStack<>();
        cardBasicStack.push(new Card("Spade","Ace"));
        cardBasicStack.push(new Card("Spade","2"));
        cardBasicStack.push(new Card("Spade","3"));
        cardBasicStack.push(new Card("Spade","4"));
        cardBasicStack.push(new Card("Spade","5"));
        cardBasicStack.push(new Card("Spade","6"));
        cardBasicStack.push(new Card("Spade","7"));
        cardBasicStack.push(new Card("Spade","8"));
        cardBasicStack.push(new Card("Spade","9"));
        cardBasicStack.push(new Card("Spade","10"));
        System.out.println(cardBasicStack.size());
        Card ace = new Card("Spade","Ace");
        Card ace2 = new Card("Heart","Ace");
        System.out.printf("Is there a Ace of Spades in the Deck? %b%n", cardBasicStack.contains(ace));
        System.out.printf("Is there a Ace of Hearths in the Deck? %b%n",cardBasicStack.contains(ace2));
        try {
            Card find = cardBasicStack.access(ace2);
        }
        catch (Exception e)
        {
            System.out.println("Access is working properly");
        }
        while(cardBasicStack.size() != 0)
        {
            System.out.printf("The size of the stack is %d and the top element is %s%n",cardBasicStack.size(),cardBasicStack.pop().toString());
        }
    }
}
