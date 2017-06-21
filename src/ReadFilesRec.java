import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReadFilesRec {

    public static List<Path> getFileNames(List<Path> fileNames, Path dir) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {   //Create new directory stream
            for (Path path : stream) {
                if (path.toFile().isDirectory()) {
                    getFileNames(fileNames, path);       //recursively go throught it if it is a directory
                } else {
                    fileNames.add(path);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNames;
    }

    public static ArrayList<String> readFile(String fileName) throws IOException {
        //Determining the values of different chareters used as delimeter to split the string
        final int NEW_LINE_ACSII = 10;
        final int WHITE_SPACE_ACSII = 32;
        final int HORIZONTAL_TAB_ACSII = 9;
        final int COMMA_ACSII = 44;
        final int CARRIAGE_RETURN = 13; // incase if there is multible new lines in the end of the file


        ArrayList<String> listOfWords = new ArrayList<String>();      //list of words

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));
        StringBuilder sb = new StringBuilder(); //for reading char by char puporses
        int c;

        while ((c = reader.read()) != -1) {
            //read the characters in the file until encountering one of the delimiters

            if (c != NEW_LINE_ACSII && c != WHITE_SPACE_ACSII && c != HORIZONTAL_TAB_ACSII && c != COMMA_ACSII && c != CARRIAGE_RETURN) {
                
            	sb.append((char) c);
            } else {
                if (sb.length() > 0) {          // avoid the case where the string is null like in the case of "comma+whitespace"
                	String sbStr = removeUTF8BOM(sb.toString());
                	listOfWords.add(sbStr.toLowerCase());      //add the stringBuilder after convert it to lower and to string object
                    sb = new StringBuilder();

                }
            }

        }
        if (sb.length() != 0) {
            listOfWords.add(sb.toString().toLowerCase());      //just to add the last word in case that the file does not end with newline char
        }
        return listOfWords;
    }
    public static final String UTF8_BOM = "\uFEFF";

    private static String removeUTF8BOM(String s) {
        if (s.startsWith(UTF8_BOM)) {
            s = s.substring(1);
        }
        return s;
    }
}