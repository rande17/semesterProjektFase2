/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acquaintance;

import Game.Command;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author rickie
 */
public interface InterfaceGame {
    public void play() throws FileNotFoundException, IOException, Throwable;
    public void takeItem(Command command);
}
