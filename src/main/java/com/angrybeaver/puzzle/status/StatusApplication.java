package com.angrybeaver.puzzle.status;

import com.angrybeaver.puzzle.Dog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

import static com.angrybeaver.puzzle.Direction.North;
import static com.angrybeaver.puzzle.Dog.*;

@SpringBootApplication
public class StatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatusApplication.class, args);
		List<Card> unusedCards = new ArrayList<>();
		List<Card> usedCards = new ArrayList<>();


		unusedCards.add(new Card(0,new Dog[]{WhiteTail, BlackHead, OrangeTail, BrownHead}));
		unusedCards.add(new Card(1,new Dog[]{WhiteTail, OrangeHead, BlackTail, BrownHead}));
		unusedCards.add(new Card(2,new Dog[]{BrownHead, BlackHead, OrangeHead, WhiteHead}));
		unusedCards.add(new Card(3,new Dog[]{WhiteTail, BrownHead, OrangeTail, BlackHead}));
		unusedCards.add(new Card(4,new Dog[]{OrangeTail, WhiteTail, BrownTail, WhiteHead}));
		unusedCards.add(new Card(5,new Dog[]{BrownHead, BlackHead, OrangeHead, WhiteHead}));
		unusedCards.add(new Card(6,new Dog[]{BrownTail, OrangeTail, WhiteHead, BlackHead}));
		unusedCards.add(new Card(7,new Dog[]{BrownHead, BlackHead, OrangeHead, BlackTail}));
		unusedCards.add(new Card(8,new Dog[]{OrangeTail, WhiteTail, BrownTail, BlackTail}));

		/*
		unusedCards.add(0,new Card(new Dog[]{WhiteHead, BlackTail, OrangeTail, BlackHead}));
		unusedCards.add(1,new Card(new Dog[]{BrownHead, OrangeTail, BlackTail, BlackHead}));
		unusedCards.add(2,new Card(new Dog[]{WhiteHead, BrownTail, OrangeTail, BlackHead}));
		unusedCards.add(3,new Card(new Dog[]{WhiteHead, OrangeTail, WhiteTail, BrownHead}));
		unusedCards.add(4,new Card(new Dog[]{OrangeHead, BrownTail, WhiteTail, BlackHead}));
		unusedCards.add(5,new Card(new Dog[]{BlackHead, WhiteTail, OrangeTail, BrownHead}));
		unusedCards.add(6,new Card(new Dog[]{BlackHead, OrangeTail, BlackTail, BrownHead}));
		unusedCards.add(7,new Card(new Dog[]{OrangeHead, BlackTail, WhiteTail, BrownHead}));
		unusedCards.add(8,new Card(new Dog[]{OrangeHead, BrownTail, WhiteTail, BrownHead}));
		/**/


		new StatusMachine(unusedCards ,usedCards,0,North,null).run();
	}

}

