/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author rickie
 */
public class Save extends WriteToFile {

    String saveString = "";

    /**
     *
     * @param _fileName takes a string that's used for setting a filename for
     * the file to write to
     */
    public Save(String _fileName) {
        super(_fileName, false);
    }

    /**
     *
     * @param str adds the input string to string that is supposed to be written
     * to
     */
    public void addToSaveGame(String str) {

        saveString += str + "\n";
    }

    /**
     *
     * @throws IOException makes an exception if the program doesn't have write
     * access to the folder
     */
    public void saveGame() throws IOException {
        super.write(saveString);
    }

    /**
     *
     * @return  the save file
     * @throws IOException makes an exception if the program doesn't have read
     * access to the file or the file doesn't exists
     */
    public String loadGame() throws IOException {
        String loadString = readFile(fileName + ".save", Charset.defaultCharset());
        return loadString;
    }

    /**
     *
     * @param path is the default input path
     * @param encoding is tne default char encoding
     * @return returns a string with the content of the file
     * @throws IOException makes an exception if the program doesn't have read
     * access to the file or the file doesn't exists
     */
    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
