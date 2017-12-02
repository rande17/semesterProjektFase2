/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Martin Sorensen
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

    // no args constructer
    public Time() {

    }
    //get secondspassed
    public static int getSecondsPassed() {
        return secondsPassed;
    }
   
    public void start() {
        myTimer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }

    public void stopTime() {
        myTimer.cancel();
    }

}
