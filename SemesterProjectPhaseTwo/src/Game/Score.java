/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.io.Serializable;

/**
 *
 * @author rickie
 */
public class Score implements Serializable {

    private int score = 0;
    private String name;

    //no args constructer 
    public Score() {
    }

    /**
     *
     * @param _name sets the name of the score
     * @param _score sets the the score
     */
    public Score(String _name, int _score) {
        score = _score;
        name = _name;
    }

    /**
     *
     * @param addToScore add point to score
     */
    public void addToPoints(int addToScore) {
        score += addToScore;
    }

    public String getName() {
        return name;
    }

    /**
     *
     * @param newName sets new name of score
     */
    public void setName(String newName) {
        name = newName;
    }

    public int getCurrentScore() {
        return score;
    }

}
