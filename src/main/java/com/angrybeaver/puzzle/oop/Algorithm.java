package com.angrybeaver.puzzle.oop;

import java.util.ArrayList;
import java.util.List;

public class Algorithm implements Runnable{
    private List<Card> unusedCards ;
    private Solution solution;
    private Game game;
    private boolean hasShiftedX;
    private boolean hasShiftedY;

    public Algorithm(Game game) throws ExpandYException, ExpandXException, NoMatchException {
        solution = new Solution();
        unusedCards = new ArrayList(game.deck);
        Placement placement = getPlacementForCard(unusedCards.get(0),0);
        addPlacement(placement);
        this.game = game;
        hasShiftedX = false;
        hasShiftedY = false;
    }

    public Algorithm(Algorithm algorithm,int shiftX, int shiftY){
        unusedCards = new ArrayList(algorithm.unusedCards);
        this.solution = new Solution(algorithm.solution,shiftX,shiftY);
        hasShiftedX = algorithm.hasShiftedX;
        hasShiftedY = algorithm.hasShiftedY;
        this.game = algorithm.game;
    }


    public void run(){
        if(unusedCards.isEmpty()){
            System.out.println(game.getTimeString()+solution);
        }
        unusedCards.forEach(card -> {
            try{
                forkForCard(card);
            }catch(NoMatchException e){

            }
        });
    }

    private void forkForCard(Card card) throws NoMatchException {
        try{
            Placement placement = getPlacementForCard(card,0);
            Algorithm algorithm = new Algorithm(this,0, 0);
            algorithm.addPlacement(placement);
            //game.addThread(algorithm);
            new Thread(algorithm).start();
        }catch(ExpandXException e){
            expandX();
            forkForCard(card);
        }catch(ExpandYException e) {
            expandY();
            forkForCard(card);
        }
    }

    public void addPlacement(Placement placement) throws ExpandYException, ExpandXException {
        solution.addPlacement(placement);
        unusedCards.remove(placement.getCard());
    }

    private Placement getPlacementForCard(Card card,int rotation) throws ExpandYException, ExpandXException, NoMatchException {
        Placement placement = new Placement(card, rotation);
        if(solution.doesPlacementMatch(placement)){
            return placement;
        }else if(rotation < 3){
            return getPlacementForCard(card,++rotation);
        }else{
            throw new NoMatchException();
        }
    }

    public void expandY(){
        solution.expandY();
        if(!hasShiftedY) {
            Thread thread = new Thread(new Algorithm(this, 0, 1));
            thread.start();
            hasShiftedY = true;
        }
    }

    public void expandX(){
        solution.expandX();
        if(!hasShiftedX) {
            Thread thread = new Thread(new Algorithm(this, 1, 0));
            thread.start();
            hasShiftedX = true;
        }
    }
}
