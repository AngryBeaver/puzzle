package com.angrybeaver.puzzle.oop;

import com.angrybeaver.puzzle.Direction;
import com.angrybeaver.puzzle.Dog;

public class Card {
    private int id;
    private Dog[] dogs;

    public Card(int id, Dog[] dogs){
        this.id = id;
        this.dogs = dogs;
    }

    public Card(Card card){
        this.id = card.id;
        this.dogs = card.dogs.clone();
    }

    public Dog getDogAt(Direction direction){
        return dogs[direction.getPosition()];
    }

    public String toString(){
        return "{id:"+id+",dogs:"+dogs[0]+"+"+dogs[1]+"+"+dogs[2]+"+"+dogs[3]+"}";
    }

}
