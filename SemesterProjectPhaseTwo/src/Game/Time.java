/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Thomas
 * @author Nicolai
 * @author Rickie
 * @author Frederik
 * @author Julie
 * @author Martin
 */
public class Time {

    //data field
    static int secondsPassed = 0;
    static int startTime = 1;

    Timer myTimer = new Timer();

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            secondsPassed++;
        }
    };

    /**
     * no-args constructor for time
     */
    public Time() {

    }

    /**
     * getter method used to get amount of secondspassed
     *
     * @return int secondspassed
     */
    public static int getSecondsPassed() {
        return secondsPassed;
    }

    public void start() {
        myTimer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }

    /**
     * method used for stopping the timer
     */
    public void stopTime() {
        myTimer.cancel();
    }

}
