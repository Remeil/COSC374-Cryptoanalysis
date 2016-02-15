import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/*
 * FileOps.java
 * Author: Johnathan Stiles
 * 
 * This class provides utility methods for reading and writing to a file
 */
public class FileOps {
    
    //Adapted from earlier project at github.com/remeil/FileSearch
    static String readFile(String path) throws IOException 
    {
	byte[] encoded = Files.readAllBytes(Paths.get(path));
	return new String(encoded, StandardCharsets.UTF_8);
    }
    
    static void writeFile(String path, String content) throws IOException
    {
	Files.write(Paths.get(path), content.getBytes(StandardCharsets.UTF_8));
    }
    
}
