/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Game.Score;
import java.util.Comparator;

/**
 *
 * @author Nicolai
 */
public class CompareScore implements Comparator<Score> {

    /**
     * @param score1 takes a Score object that should be compared to an other
     * Score
     * @param score2 takes a Score object that should be compared to an other
     * Score
     * @return returns -1 if score1 is greater than score2, 1 if score2 is
     * higher than score1, otherwise it returns 0
     */
    @Override
    public int compare(Score score1, Score score2) {

        int sc1 = score1.getCurrentScore();
        int sc2 = score2.getCurrentScore();

        //compare the two different object
        //-1 means that the first score is greater than the second, +1 means it smaller and 0 if the score is equal
        if (sc1 > sc2) {
            return -1;
        } else if (sc1 < sc2) {
            return +1;
        } else {
            return 0;
        }

    }

}
