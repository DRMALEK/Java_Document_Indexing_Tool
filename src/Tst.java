import java.util.ArrayList;

/**
 * Created by Dr.MALEK on 14.05.2017.
 */
public class Tst {
    private int n;              // size
    private Node root;   // root of TST
    private String FileName;  //name of the file that been represented by the tst

    private class Node {
        private ArrayList<Integer> indices;  //indices where the word is in the text file
        private char c;
        private Node left, mid, right;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Inserts the key  into the tst
     * @param index the index of the word in the text file
     * @param key the key is a word
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(String key, int index) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with null key");
        }
        /**
        if (!contains(key)) n++;
        **/
         root = put(root, key, index, 0);
    }

    private Node put(Node x, String key , int index, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
            x.indices = new ArrayList<Integer>();
        }
        if      (c < x.c)               x.left  = put(x.left,  key, index, d);
        else if (c > x.c)               x.right = put(x.right, key, index, d);
        else if (d < key.length() - 1)  x.mid   = put(x.mid,   key, index, d+1);
        else                            x.indices.add(index);
        return x;
    }

    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *     {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * Returns the list of indices of a given key
     * @param key the key
     * @return the list of indices associated with the given key if the key is in the symbol table
     *     and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public ArrayList<Integer>  get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with null argument");
        }
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        Node x = get(root, key, 0);
        if (x == null) return null;
        return x.indices;
    }

    // return sub trie corresponding to given key
    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        char c = key.charAt(d);
        if      (c < x.c)              return get(x.left,  key, d);
        else if (c > x.c)              return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid,   key, d+1);
        else                           return x;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }





}
