package com.angrybeaver.puzzle;

import java.util.Arrays;

public enum Direction {
    North(0),East(1),South(2),West(3);
    private int position;

    Direction(int position){
        this.position = position;
    }
    public int getPosition(){
        return position;
    }
    public Direction getOpposedDirection(){
        return getDirectionAt(2);
    }

    public Direction getDirectionAt(int rotation){
        return Arrays.stream(values()).filter(
                direction->direction.getPosition()==(this.position+rotation)%4)
                .findFirst().get();
    }

}
