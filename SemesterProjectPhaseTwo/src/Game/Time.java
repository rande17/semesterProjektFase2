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
    static int secondsPassed = 0;
    static int startTime =1;
    Timer myTimer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            secondsPassed++;
        }
    };
    
    public Time() {
        
    }
    
    public static int getSecondsPassed() {
        return secondsPassed;
    }
            
    public void start() {
    myTimer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }
    
    public void stopTime() {
        myTimer.cancel();
    }
    
//    public static int timeSpend() {
//          
//        return getSecondsPassed()/startTime;
//    }
    
    public static void main(String[] args) {
        Time time = new Time();
        time.start();
    }
//        static int startTime = (int)Instant.now().getEpochSecond();
//    public Time() {
//        
//    }
//
//      public static int getStartTime() {
//      return startTime;
//  }
//
//  public static int getEndTime() {
//      int endTime = (int)Instant.now().getEpochSecond();
//      return endTime;
//  }
//  
//  public static int timeSpend() {
////      System.out.println(startTime + "\n" + getEndTime());
//      return  getEndTime() - startTime;
//  } 

}
