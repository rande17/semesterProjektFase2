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
    

    public Score() {
    }

    public Score(String name, int score) {
        this.score = score;
        this.name = name;
    }

    public void addToPoints(int addToScore) {
        score += addToScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
         name = newName;
    }
    

    public int getCurrentScore() {
        return score;
    }

}
