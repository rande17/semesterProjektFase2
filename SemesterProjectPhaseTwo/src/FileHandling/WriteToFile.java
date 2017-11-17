/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileHandling;

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
    File file;

    FileWriter writer;

    public WriteToFile(String _fileName, Boolean _append) {
        fileName = _fileName;
        append = _append;
        
    }

    public WriteToFile(String _fileName, String _ext) {
        fileName = _fileName;
        ext = "." + _ext;
    }

    public void write(String str) throws FileNotFoundException, IOException {
        if (writer == null) {
            writer = new FileWriter(userHome + File.separator + defaultFolder + File.separator + fileName + ext, append);
        }
        writer.write(str + System.lineSeparator());
        writer.flush();
    }

}
