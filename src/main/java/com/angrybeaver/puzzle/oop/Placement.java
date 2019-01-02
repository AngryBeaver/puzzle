package com.angrybeaver.puzzle.oop;

import com.angrybeaver.puzzle.Direction;
import com.angrybeaver.puzzle.Dog;

public class Placement {
    private Card card;
    private int rotation;

    public Placement(Card card, int rotation){
        this.card = card;
        this.rotation = rotation;
    }

    public Placement(Placement placement){
        this(placement.card,placement.rotation);
    }

    private Dog getDogAt(Direction direction){
        return card.getDogAt(direction.getDirectionAt(rotation));
    }

    public Card getCard(){
        return card;
    }

    public boolean doesAttachTo (Placement placement, Direction direction){
        return getDogAt(direction).match(placement.getDogAt(direction.getOpposedDirection()));
    }

    public String toString(){
        return "{ card:"+this.card.toString()+",rotation:"+rotation+"}";
    }

}
