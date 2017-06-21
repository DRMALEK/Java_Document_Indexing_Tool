import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
/**
 *
 */

public static List<String> getFileNames(List<String> fileNames,Path dir){
        try(DirectoryStream<Path> stream=Files.newDirectoryStream(dir)){   //Create new directory stream
        for(Path path:stream){
            if(path.toFile().isDirectory()){
               getFileNames(fileNames,path);       //recursively go throught it if it is a directory
            } else{
                fileNames.add(path.toAbsolutePath().toString());
                System.out.println(path.getFileName());
            }
        }
        }catch(IOException e){
        e.printStackTrace();
        }
        return fileNames;
}

public static List<String> readFile(String fileName){
        //Determining the values of different chareters used as delimeter to split the string
        int static final NEW_LINE_ACSII=10;
        int static final WHITE_SPACE_ACSII=32;
        int static final HORIZONTAL_TAB_ACSII=9;
        int static final COMMA_ACSII=44;


        StringBuilder sb =new StringBuilder(); //for reading char by char puporses
        List<String> listOfWords=new ArrayList<String>();      //list of words

        BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

        int c;

        while((c=reader.read())!=-1){

            //read the characters in the file until encotring one of the delimeters
            while(c != NEW_LINE_ACSII && c!= WHITE_SPACE_ACSII && c!= HORIZONTAL_TAB_ACSII c!= COMMA_ACSII ){
                sb.append(c);
            }
        sb.toString();
        listOfWords.append()

        }
}