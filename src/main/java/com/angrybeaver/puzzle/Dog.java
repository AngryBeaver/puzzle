package com.angrybeaver.puzzle;

public enum Dog {
    WhiteHead(1),
    BlackHead(2),
    OrangeHead(3),
    BrownHead(4),
    WhiteTail(-1),
    BlackTail(-2),
    OrangeTail(-3),
    BrownTail(-4);

    private int number;
    Dog(int number){
        this.number = number;
    }

    public boolean match(Dog dog){
        return dog.number+this.number==0;
    }

}
