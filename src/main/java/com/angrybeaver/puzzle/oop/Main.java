package com.angrybeaver.puzzle.oop;

import com.angrybeaver.puzzle.Dog;

import java.util.ArrayList;
import java.util.List;

import static com.angrybeaver.puzzle.Dog.*;
import static com.angrybeaver.puzzle.Dog.BlackTail;
import static com.angrybeaver.puzzle.Dog.BrownTail;

public class Main {

    public static void main(String[] args) {
        List<Card> deck = new ArrayList<>();
        /*Vier Lösungen*/
        deck.add(new Card(0,new Dog[]{WhiteTail, BlackHead, OrangeTail, BrownHead}));
        deck.add(new Card(1,new Dog[]{WhiteTail, OrangeHead, BlackTail, BrownHead}));
        deck.add(new Card(2,new Dog[]{BrownHead, BlackHead, OrangeHead, WhiteHead}));
        deck.add(new Card(3,new Dog[]{WhiteTail, BrownHead, OrangeTail, BlackHead}));
        deck.add(new Card(4,new Dog[]{OrangeTail, WhiteTail, BrownTail, WhiteHead}));
        deck.add(new Card(5,new Dog[]{BrownHead, BlackHead, OrangeHead, WhiteHead}));
        deck.add(new Card(6,new Dog[]{BrownTail, OrangeTail, WhiteHead, BlackHead}));
        deck.add(new Card(7,new Dog[]{BrownHead, BlackHead, OrangeHead, BlackTail}));
        deck.add(new Card(8,new Dog[]{OrangeTail, WhiteTail, BrownTail, BlackTail}));
        /**/

        /* Eine Lösung
        deck.add(new Card(0,new Dog[]{WhiteTail, BlackHead, OrangeTail, BrownHead}));
        deck.add(new Card(1,new Dog[]{WhiteTail, OrangeHead, BlackTail, BrownHead}));
        deck.add(new Card(2,new Dog[]{BrownHead, BlackHead, OrangeHead, WhiteHead}));
        deck.add(new Card(3,new Dog[]{WhiteTail, BrownHead, OrangeTail, BlackHead}));
        deck.add(new Card(4,new Dog[]{OrangeTail, WhiteTail, BrownTail, WhiteHead}));
        deck.add(new Card(5,new Dog[]{BrownHead, OrangeHead, BlackHead, BrownHead}));
        deck.add(new Card(6,new Dog[]{BrownTail, OrangeTail, WhiteHead, BlackHead}));
        deck.add(new Card(7,new Dog[]{BrownHead, BlackHead, OrangeHead, BlackTail}));
        deck.add(new Card(8,new Dog[]{OrangeTail, WhiteTail, BrownTail, BlackTail}));
        /**/
        /* zwei Lösungen
        deck.add(new Card(0,new Dog[]{WhiteHead, BlackTail, OrangeTail, BlackHead}));
        deck.add(new Card(1,new Dog[]{BrownHead, OrangeTail, BlackTail, BlackHead}));
        deck.add(new Card(2,new Dog[]{WhiteHead, BrownTail, OrangeTail, BlackHead}));
        deck.add(new Card(3,new Dog[]{WhiteHead, OrangeTail, WhiteTail, BrownHead}));
        deck.add(new Card(4,new Dog[]{OrangeHead, BrownTail, WhiteTail, BlackHead}));
        deck.add(new Card(5,new Dog[]{BlackHead, WhiteTail, OrangeTail, BrownHead}));
        deck.add(new Card(6,new Dog[]{BlackHead, OrangeTail, BlackTail, BrownHead}));
        deck.add(new Card(7,new Dog[]{OrangeHead, BlackTail, WhiteTail, BrownHead}));
        deck.add(new Card(8,new Dog[]{OrangeHead, BrownTail, WhiteTail, BrownHead}));
        /**/
        new Game(deck).run();
    }
}
