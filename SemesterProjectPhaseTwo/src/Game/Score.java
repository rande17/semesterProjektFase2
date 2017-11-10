/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**
 *
 * @author rickie
 */
public  class Score {
    
    //initialize Score as 0 so you start with 0 points
    private int currentScore = 0;
    
    public Score(){
        
    }
    /**
     * @param addToScore adds this amount to the currentScore variable
     */
    public void addToPoints(int addToScore){
        currentScore += addToScore;
    }
    
    /**
     * @return gets the value of currentScore variable
     */
    public int getCurrentScore(){
        return currentScore;
    }
    
}
