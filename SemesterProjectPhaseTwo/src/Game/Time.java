/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.time.Instant;

/**
 *
 * @author Martin Sorensen
 */
public class Time {
        static int startTime = (int)Instant.now().getEpochSecond();
    public Time() {
        
    }

      public static int getStartTime() {
      return startTime;
  }

  public static int getEndTime() {
      int endTime = (int)Instant.now().getEpochSecond();
      return endTime;
  }
  
  public static int timeSpend() {
//      System.out.println(startTime + "\n" + getEndTime());
      return  getEndTime() - startTime;
  } 

}
