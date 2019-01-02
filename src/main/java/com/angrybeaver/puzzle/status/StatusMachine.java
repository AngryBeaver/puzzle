package com.angrybeaver.puzzle.status;

import com.angrybeaver.puzzle.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.angrybeaver.puzzle.Direction.*;

public class StatusMachine implements Runnable{
    private static int counter = 0;
    private static long startTime=0;
    private static long endTime=0;
    private List<Card> unusedCards;
    private List<Card> usedCards = new ArrayList<>();
    private int step;
    private Direction lastDirection;
    private Card currentCard;

    public StatusMachine(List<Card> unusedCards, List<Card> usedCards, int step, Direction lastDirection, Card currentCard){
        this.unusedCards = unusedCards;
        this.usedCards = usedCards;
        this.step = step;
        this.lastDirection = lastDirection;
        this.currentCard = currentCard;
    }

    @Override
    public void run(){
        step0();
        step1();
        step2();
        step3();
        step4();
        step5to8();
        step9();
    }

    private void step0(){
        if(step == 0) {
            startTime = System.currentTimeMillis();
            step++;
            currentCard=unusedCards.remove(0);
            usedCards.add(currentCard);
        }
    }
    private void step1(){
        if(step == 1){
            firstDirectionStep(North);
        }
    }
    private void step2(){
        if(step == 2){
            if(lastDirection == North) {
                usedCards.get(0).setPosition(0,North);
                usedCards.get(1).setPosition(1,North);
                spanField(North,2);
                usedCards.get(0).setPosition(1,North);
                usedCards.get(1).setPosition(2,North);
                currentCard = usedCards.get(0);
                spanField(North.getOpposedDirection(),0);
            }else{
                usedCards.get(0).setPosition(2,North);
                usedCards.get(1).setPosition(1,North);
                spanField(North.getOpposedDirection(),0);
            }
        }
    }
    private void step3(){
        if(step == 3){
            currentCard = usedCards.get(0);
            firstDirectionStep(East);
        }
    }
    private void step4(){
        if(step == 4){
            if(lastDirection == East) {
                usedCards.get(0).setPosition(0,East);
                usedCards.get(3).setPosition(1,East);
                spanField(East,2);
                usedCards.get(0).setPosition(1,East);
                usedCards.get(3).setPosition(2,East);
                currentCard = usedCards.get(0);
                spanField(East.getOpposedDirection(),0);
            }else{
                usedCards.get(0).setPosition(2,East);
                usedCards.get(3).setPosition(1,East);
                spanField(East.getOpposedDirection(),0);
            }
        }
    }

    private void step5to8(){
        if(step == 5){
            usedCards.get(1).setPosition(usedCards.get(0).getPosition(East),East);
            usedCards.get(2).setPosition(usedCards.get(0).getPosition(East),East);
            usedCards.get(3).setPosition(usedCards.get(0).getPosition(North),North);
            usedCards.get(4).setPosition(usedCards.get(0).getPosition(North),North);
        }
        if(step == 5 || step == 6 || step == 7 || step == 8){
            checkField();
        }
    }

    private synchronized void step9(){
        if(step == 9){
            counter ++;
            endTime = System.currentTimeMillis();
            usedCards.forEach(card->{
                System.out.println(counter+"#"+(startTime-endTime)+"::::"+card.toString());
            });
        }
    }

    private void firstDirectionStep(Direction direction){
        spanField(direction,0);
        spanField(direction.getOpposedDirection(),0);
    }


    private void spanField(Direction direction,int nextPosition){
        this.lastDirection = direction;
        unusedCards.stream().forEach(card-> {
            for(int x = 0;x<4;x++){
                card.rotate();
                checkForMatch(card,direction,nextPosition);
            }
        });
    }

    private void checkForMatch(Card card, Direction direction, int nextPosition){
        if(currentCard.doesMatchTo(card,direction)){
            card.setPosition(nextPosition,direction);
            StatusMachine statusMachine = clone(card);
            Thread thread = new Thread(statusMachine);
            thread.start();
        }
    }
    private List<Card> cloneCards(List<Card> cards){
        List<Card> clonedCards = new ArrayList<Card>();
        cards.stream().forEach(card->clonedCards.add(new Card(card)));
        return clonedCards;
    }

    private void checkField(){
        for(int north=0;north<3;north++){
            for(int east=0;east<3;east++){
                if(!getCardAtFieldPosition(north,east).isPresent()){
                    checkFieldPosition(north,east);
                    return;
                }
            }
        }
    }
    private StatusMachine clone(Card card){
        int index = unusedCards.indexOf(card);
        List<Card> nextUnusedCards = cloneCards(this.unusedCards);
        List<Card> nextUsedCards =cloneCards(this.usedCards);
        Card card2 = nextUnusedCards.remove(index);
        nextUsedCards.add(card2);
        return new StatusMachine(nextUnusedCards,nextUsedCards,this.step+1,this.lastDirection,card2);
    }

    private void checkFieldPosition(int north, int east){
            unusedCards.stream().forEach(card-> {
                card.setPosition(north,North);
                card.setPosition(east,East);
                for(int x = 0;x<4;x++){
                    card.rotate();
                    if(
                            hasMatchInField(card,north+1, east,North)
                            && hasMatchInField(card,north-1, east,South)
                            && hasMatchInField(card,north, east+1,East)
                            && hasMatchInField(card,north, east-1,West)
                            ){
                        StatusMachine statusMachine = clone(card);
                        Thread thread = new Thread(statusMachine);
                        thread.start();
                    }
                }
            });
    }

    private boolean hasMatchInField(Card card, int north, int east, Direction direction){
        Optional<Card> card2 = getCardAtFieldPosition(north,east);
        if(!getCardAtFieldPosition(north,east).isPresent()){
            return true;
        }
        return card.doesMatchTo(card2.get(),direction);

    }

    private Optional<Card> getCardAtFieldPosition(int north, int east){
        return usedCards.stream().filter(card->card.getPosition(North)== north && card.getPosition(East)== east).findFirst();
    }

}
