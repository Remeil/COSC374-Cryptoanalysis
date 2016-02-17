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
    //Given a valid path to a file, return a string containing the contents of that file.
    static String readFile(String path) throws IOException 
    {
	byte[] encoded = Files.readAllBytes(Paths.get(path));
	return new String(encoded, StandardCharsets.UTF_8);
    }
    
    //Given a valid path to a file, overwrite its contents with content.
    static void writeFile(String path, String content) throws IOException
    {
	Files.write(Paths.get(path), content.getBytes(StandardCharsets.UTF_8));
    }
    
}
