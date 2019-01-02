package com.angrybeaver.puzzle.status;

import com.angrybeaver.puzzle.Direction;
import com.angrybeaver.puzzle.Dog;

import static com.angrybeaver.puzzle.Direction.East;
import static com.angrybeaver.puzzle.Direction.North;

public class Card {
    private int id;
    private Dog[] dogs;
    private int positionNorth;
    private int positionEast;

    public Card(int id, Dog[] dogs){
        this.id = id;
        this.dogs = dogs;
    }

    public Card(Card card){
        this.id = card.id;
        this.dogs = card.dogs.clone();
        this.positionNorth = card.positionNorth;
        this.positionEast = card.positionEast;
    }

    public void rotate(){
        Dog[] temp = new Dog[dogs.length];
        for(int x = 0; x <= dogs.length-1; x++){
            temp[(x+1) % dogs.length ] = dogs[x];
        }
        dogs = temp;
    }

    public void setPosition(int position,Direction direction){
        if(direction.equals(North) || direction.equals(North.getOpposedDirection())){
            positionNorth = position;
        }
        if(direction == East || direction.equals(East.getOpposedDirection())){
            positionEast = position;
        }
    }

    public int getPosition(Direction direction){
        if(direction.equals(North) || direction.equals(North.getOpposedDirection())){
           return positionNorth;
        }
        return positionEast;
    }

    public Dog getDogAt(Direction direction){
        return dogs[direction.getPosition()];
    }

    public boolean doesMatchTo(Card card, Direction direction){
        return card.getDogAt(direction.getOpposedDirection()).match(getDogAt(direction));
    }

    public String toString(){
        return "{id:"+id+",dogs:"+dogs[0]+"+"+dogs[1]+"+"+dogs[2]+"+"+dogs[3]+",north:"+positionNorth+",east:"+positionEast+"}";
    }

}
