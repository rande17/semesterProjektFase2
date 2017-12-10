/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileHandling;

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

    public Save(String _fileName) {
        super(_fileName, false);
    }

    /**
     *
     * @param str
     */
    public void addToSaveGame(String str) {

        saveString += str + "\n";
    }

    /**
     *
     * @throws IOException
     * @throws Throwable
     */
    public void saveGame() throws IOException, Throwable {
        super.write(saveString);
    }

    public String loadGame() throws IOException {
        String loadString = readFile("01.save", Charset.defaultCharset());
        return loadString;
    }

    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
