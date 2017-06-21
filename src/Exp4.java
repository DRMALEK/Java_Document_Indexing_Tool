import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
/**
 * 
 * @author M.MALEK BABA
 *
 */


public class Exp4 {

    public static void main(String[] args) throws IOException {
        Locale.setDefault(new Locale("tr","TR"));
        Path pathes  = Paths.get(args[0]);
        ArrayList<Path> listOfFilesPaths = new ArrayList<Path>();
        ReadFilesRec.getFileNames(listOfFilesPaths,pathes);   //recursively and store the files's pathes in list of file array

        ArrayList<Tst> listOfTsts = new ArrayList<Tst>(); //list of ternary search tree for every inputfile

        for(Path path : listOfFilesPaths){
        	List<String> fileWords = ReadFilesRec.readFile(path.toAbsolutePath().toString());     // read the file and extracting its words into array
            Tst tst = new Tst();
            tst.setFileName(path.getFileName().toString());

            for (int i =0; i < fileWords.size(); i++){
                String word = fileWords.get(i);
                tst.put(word,i+1);        //putting the word in the corresponding tst according to the each input file

            }
            System.out.printf("%s indexed\n",path.getFileName().toString());
            listOfTsts.add(tst);
        }
        //taking the input from the console
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        while (true) {
            String input = br.readLine();
            if(input.compareTo("q")==0){             //quite the program when the user input q
                break;
            }
            String[] inputArray = input.split("[\\s]", 3); //splitting the input into three parts according to the white space
            Search.search(inputArray[2], inputArray[1], listOfTsts);
        }
        br.close();
        
    }
}
