import java.util.ArrayList;

/**
 * Created by Dr.MALEK on 16.05.2017.
 */
public class Search {
    /**
     * @param searchKey
     * @param searchType
     * @param listOfTsts
     */
    public static void search(String searchKey, String searchType, ArrayList<Tst> listOfTsts) {
        if (searchType.compareTo("-W") == 0) {
            searchPhrase(searchKey, listOfTsts);
        } else {
            searchWord(searchKey, listOfTsts);
        }

    }
    private static void searchWord(String searchKey, ArrayList<Tst> listOfTsts){
        ArrayList<String> listOfWords = splitSearchKey(searchKey.trim());  //split the input search key
        boolean found =false;
        for (Tst tst : listOfTsts){
            for (String word : listOfWords){
                ArrayList<Integer> listOfIndcies;
                if ((listOfIndcies = tst.get(word.toLowerCase())) == null){
                    //
                }
                else{
                    for(int index : listOfIndcies ){
                        System.out.printf("%s;%s;%d\n", tst.getFileName(), word, index);
                        found = true;
                    }
                }
            }
        }
        if(found == false){
            System.out.printf("No result found:%s\n",searchKey);
        }

    }

    private static void searchPhrase(String searchKey, ArrayList<Tst> listOfTsts) {
        ArrayList<String> listOfWords = splitSearchKey(searchKey);  //split the input search key
        boolean found =false;            
        boolean FoundAtAll = false;          //to determine whether if there is any result has been found or not

        for (Tst tst : listOfTsts) {
            ArrayList<ArrayList<Integer>> listOfIndiciesOfFoundWords = new ArrayList<>();
            for (String word : listOfWords) {

                ArrayList<Integer> listOfIndcies;

                if ((listOfIndcies = tst.get(word.toLowerCase())) == null) {
                    listOfIndiciesOfFoundWords = new ArrayList<>(); // empty the array list of the indices of the words
                    break;
                } else {
                    //add the last of indices of the found word
                    listOfIndiciesOfFoundWords.add(listOfIndcies);
                }
            }
            if (listOfIndiciesOfFoundWords.size() != 0) {
                found = searchPhraseHelper(listOfIndiciesOfFoundWords, searchKey, tst);      //start search for the phrase over the tst
                listOfIndiciesOfFoundWords =  new ArrayList<>();              //reset
            }
            if(found ==true){
            	FoundAtAll = true;
            }
          }
        if (!FoundAtAll){
            System.out.printf("No result found:%s\n",searchKey);
        }
    }

    private static boolean searchPhraseHelper(ArrayList<ArrayList<Integer>> listOfIndicesOfFoundWords, String searchKey, Tst tst) {
        boolean found = false;      //to determine whether a the next word with index + 1 from a current word is found or not
        boolean FoundAtAll = false;      //to determine whether if there is any result has been found or not
        if(listOfIndicesOfFoundWords.size()==1){
            for(int index : listOfIndicesOfFoundWords.get(0)){
                int firstElementIndex = index;
                System.out.printf("%s;%s;%d\n", tst.getFileName(), searchKey, firstElementIndex);
            }
            FoundAtAll = true;         //one search key has been found it correspondent in the tst
            return FoundAtAll;
        }

        for (int i = 0; i < listOfIndicesOfFoundWords.get(0).size(); i++) {          //take the first list of indices inside the big list of indices
            int firstElementIndex = listOfIndicesOfFoundWords.get(0).get(i);
            for (int j = 1; j < listOfIndicesOfFoundWords.size(); j++) {

              for(int nextElementIndex : listOfIndicesOfFoundWords.get(j)) {

                  if (nextElementIndex - firstElementIndex != j) {
                      found = false;
                  }
                  else{
                      found = true;
                      break;          //we found it, so we go to another list
                  }
              }
            }
            if (found == true){
                System.out.printf("%s;%s;%d\n", tst.getFileName(), searchKey, firstElementIndex);
                FoundAtAll = true;         //one search key has been found it correspondent in the tst
            }
        }
        return FoundAtAll;
    }

    private static ArrayList<String> splitSearchKey(String searchKey) {
        final int NEW_LINE_ACSII = 10;
        final int WHITE_SPACE_ACSII = 32;
        final int HORIZONTAL_TAB_ACSII = 9;
        final int COMMA_ACSII = 44;
        final int CARRIAGE_RETURN = 13; // incase if there is multible new lines in the end of the file

        int c;
        int index = 0;        //character index
        ArrayList<String> listOfWords = new ArrayList<String>();
        StringBuilder sb = new StringBuilder(); //for reading char by char purposes

        while (index < searchKey.length()) {
            c = searchKey.charAt(index++);
            if (c != NEW_LINE_ACSII && c != WHITE_SPACE_ACSII && c != HORIZONTAL_TAB_ACSII && c != COMMA_ACSII && c != CARRIAGE_RETURN) {
                sb.append((char) c);
            } else {
                listOfWords.add(sb.toString());
                sb = new StringBuilder();
            }

        }
        if (sb.length() != 0) {
            listOfWords.add(sb.toString());      //just to add the last word in case that the file does not end with newline char
        }
        return listOfWords;
    }
}