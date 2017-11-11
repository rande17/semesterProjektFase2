/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileHandling;

import java.io.IOException;

/**
 *
 * @author rickie
 */
public class Save extends WriteToFile {
    
    String saveString = "";
    
    public Save(String _fileName) {
        super(_fileName, false);
    }
    
    public void addToSaveGame(String str){
        
        saveString += str + "\n";
    }
    
    public void saveGame() throws IOException{
        super.write(saveString);
        
    }
    
    
}
