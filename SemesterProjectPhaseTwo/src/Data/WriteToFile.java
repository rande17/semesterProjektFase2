/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.logging.FileHandler;
import java.io.IOException;

/**
 *
 * @author rickie
 */
public class WriteToFile {

    Boolean append = true;
    String userHome = System.getProperty("user.home");
    String defaultFolder = "stranded";
    String fileName;
    String ext = ".save";

    FileHandler fh;

    FileWriter writer;

    /**
     *
     * @param _fileName set the name of the file
     * @param _append defines wether or no the input should be appended to
     * current file or it should clear the file before writing to it
     */
    public WriteToFile(String _fileName, Boolean _append) {
        fileName = _fileName;
        append = _append;

    }

    /**
     *
     * @param _fileName set the name of the file
     * @param _ext extend the file
     */
    public WriteToFile(String _fileName, String _ext) {
        fileName = _fileName;
        ext = "." + _ext;
    }

    /**
     *
     * @param str what to write to the file
     * @throws FileNotFoundException throws an error is the default path does not exists
     * @throws IOException makes an exception if the program doesn't have write
     * access to the folder
     */
    public void write(String str) throws FileNotFoundException, IOException {
        String outputfile = fileName + ext;
        File file = new File(outputfile);
        if (writer == null) {
            writer = new FileWriter(outputfile, append);
        }
        writer.write(str + System.lineSeparator());
        writer.flush();
    }

}
