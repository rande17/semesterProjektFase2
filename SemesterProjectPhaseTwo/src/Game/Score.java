/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.io.Serializable;

/**
 * @author Thomas
 * @author Nicolai
 * @author Rickie
 * @author Frederik
 * @author Julie
 * @author Martin
 */
public class Score implements Serializable {

    private int score = 0;
    private String name;

    //no args constructer 

    /**
     * no-args constructor for Score
     */
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

    /**
     * getter method used to get the name of score
     *
     * @return name of the score
     */
    public String getName() {
        return name;
    }

    /**
     * setter method used to set a name for score
     *
     * @param _name is the name of score
     */
    public void setName(String _name) {
        name = _name;
    }

    /**
     * getter method used to get the currentscore
     *
     * @return score which is the currentscore
     */
    public int getCurrentScore() {
        return score;
    }

}
