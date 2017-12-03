/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileHandling;

import acquaintance.InterfaceData;

/**
 *
 * @author Martin Sorensen
 */
public class DataFacade implements InterfaceData{

    @Override
    public String printHighscore() {
      return HighscoreManager.getHighscoreList();
    }
    
}
