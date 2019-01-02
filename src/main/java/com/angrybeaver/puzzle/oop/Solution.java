package com.angrybeaver.puzzle.oop;

import java.awt.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.angrybeaver.puzzle.Direction.*;

public class Solution {
    private Map<Integer,Map<Integer,Placement>> placements = new HashMap();
    private int sizeY = 1;
    private int sizeX = 1;

    Solution(){
        placements.put(0,new HashMap());
    }

    Solution(Solution solution){
        this(solution,0,0);
    }

    Solution(Solution solution,int shiftX,int shiftY){
        this.placements = new HashMap();
        solution.placements.forEach((y,map)->
            map.forEach((x,placement)->
                    setPlacementAt(placement,new Point(x+shiftX,y+shiftY)))
        );
        this.sizeY = solution.sizeY;
        this.sizeX = solution.sizeX;
    }

    public void addPlacement(Placement placement) throws ExpandYException, ExpandXException {
        Point point = getNextPoint();
        setPlacementAt(placement,point);
    }

    public void expandY(){
        placements.put(sizeY,new HashMap());
        this.sizeY ++;
    }

    public void expandX(){
        this.sizeX ++;
    }

    public Point getNextPoint() throws ExpandYException,ExpandXException{
        for(int y=0;y<sizeY;y++){
            for(int x=0;x<sizeX;x++){
                if(! getPlacement(x,y).isPresent()){
                    return new Point(x,y);
                }
            }
        }
        if (sizeY < sizeX) {
            throw new ExpandYException();
        }
        throw new ExpandXException();
    }

    public boolean doesPlacementMatch(Placement placement) throws ExpandYException, ExpandXException {
        Point point = getNextPoint();
        AtomicBoolean result = new AtomicBoolean(true);
        getPlacement(point.x,point.y+1)
                .ifPresent(match->
                        result.set(match.doesAttachTo(placement, North) && result.get()));
        getPlacement(point.x,point.y-1)
                .ifPresent(match->
                        result.set(match.doesAttachTo(placement, South) && result.get()));
        getPlacement(point.x+1,point.y)
                .ifPresent(match->
                        result.set(match.doesAttachTo(placement, East) && result.get()));
        getPlacement(point.x-1,point.y)
                .ifPresent(match->
                        result.set(match.doesAttachTo(placement, West) && result.get()));

        return result.get();
    }

    public String toString(){
        String result = "{placements:[";
        for(int y=0;y<sizeY;y++){
            for(int x=0;x<sizeX;x++){
                if(getPlacement(x,y).isPresent()){
                    result += "\n\t{x:"+x+",y:"+y+",placement:"+getPlacement(x,y)+"},";
                }
            }
        }
        return result+"\n]}";
    }

    private Optional<Placement> getPlacement(int x, int y){
        return Optional.ofNullable(placements.get(y))
                .map(map->map.get(x));
    }

    private void setPlacementAt(Placement placement,Point point){
        if(placements.get(point.y) == null){
            placements.put(point.y,new HashMap());
        }
        placements.get(point.y).put(point.x, placement);
    }
}
