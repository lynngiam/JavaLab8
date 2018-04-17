
/** @author ygiam Lynn Giam & Minh Lam
 * 15 April 2018
 * We have adhered to the Honour Code in this assignment.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class WebPageIndex {
    private RBTreeMap<String, List<Integer>> map = new RBTreeMap<String, List<Integer>>();
    private String baseUrl;
    private int wordCount;
    // TODO: Insert the instance variables here

    // WebPageIndex constructor
    WebPageIndex(String url) throws IOException {
	baseUrl = url;
	wordCount = 1;
	HTMLScanner input = new HTMLScanner(baseUrl);

	while (input.hasNext()) {

	    String currentWord = input.next().toLowerCase();

	    // Finds the current values associated with the current word.
	    List<Integer> currentValues = map.get(currentWord);
	    // If there are no current values, make a new list and put in the first one.
	    if (currentValues == null) {
		List<Integer> posList = new LinkedList<Integer>();
		posList.add(wordCount - 1);
		map.put(currentWord, posList);
		// Otherwise, add the new value to the existing list.
	    } else {
		currentValues.add(wordCount - 1);
	    }

	    wordCount++;
	}
	System.out.println();
    }

    // Returns a count of the words in this web page
    public int getWordCount() {
	// Simply returns the field wordCount.
	return wordCount;
    }

    public String getUrl() {
	// Simply returns the field baseUrl.
	return baseUrl;
    }

    public boolean contains(String s) {
	// Returns true if the word is found in the map.
	if (map.get(s) != null)
	    return true;
	return false;
    }

    public int getCount(String s) {
	List<Integer> wordList = map.get(s);
	if (wordList != null) {
	    return wordList.size();
	}
	return 0;
    }

    public double getFrequency(String s) {
	int count = getCount(s);
	return ((double) count) / wordCount;
    }

    public List<Integer> getLocations(String s) {
	// Return the list stored in the map if the word is found,
	// Else return empty list.
	List<Integer> wordList = map.get(s);

	if (wordList != null)
	    return wordList;
	return new LinkedList<Integer>();
    }

    // return an Iterator over all the words in the index
    public Iterator<String> words() {
	return map.keys();
    }

    public String toString() {
	return map.toString();
    }

    // The main method is an application using a WebPageIndex
    public static void main(String[] args) {
	try {
	    WebPageIndex input = new WebPageIndex(args[0]);
	    Iterator<String> words = input.words();
	    while (words.hasNext()) {
		String word = words.next();
		System.out.printf("%-15s", word);
		System.out.printf("%-10.5f", input.getFrequency(word));
		System.out.printf("%s\n", input.getLocations(word));
	    }
	} catch (IOException e) {
	    System.err.println("No such file or directory.");
	} catch (IndexOutOfBoundsException e) {
	    System.err.println("No input file detected.");
	}
    }

    /*
     * additional features to support multi-word phrases work on these after you
     * have the previous methods working correctly
     */

    // Create scanner to break down words in s and put them into an arraylist.
    private static List<String> phraseToList(String s) {
	Scanner phrase = new Scanner(s);
	List<String> wordsInPhrase = new ArrayList<String>();

	while (phrase.hasNext()) {
	    String word = phrase.next();
	    wordsInPhrase.add(word);
	}
	return wordsInPhrase;
    }

    // Check each word in the list is found in the web page
    private boolean eachWordPresent(List<String> wordList) {
	for (String word : wordList) {
	    if (!contains(word)) {
		return false;
	    }
	}
	return true;
    }

    // Returns a list of the the location lists for each word.
    private List<List<Integer>> getLocationsList(List<String> wordList) {
	List<List<Integer>> indices = new ArrayList<List<Integer>>();

	for (String word : wordList) {
	    List<Integer> positions = map.get(word);
	    indices.add(positions);
	}

	return indices;

    }

    public boolean containsPhrase(String s) {
	List<String> wordsInPhrase = phraseToList(s);

	// Check that all words in the phrase s are in the web page.
	if (!eachWordPresent(wordsInPhrase))
	    return false;

	// Create arraylist indices which contains the positions where each word, in
	// phrase s,
	// occurs in the web page.
	List<List<Integer>> indices = getLocationsList(wordsInPhrase);

	// Go through each positions of the first word in s and trace a path through
	// next words. Return true if their positions increment by 1. Otherwise, false.
	boolean contains = false;
	List<Integer> firstWordPositions = indices.get(0);
	for (Integer pos : firstWordPositions) {
	    if (containsPhraseHelper(pos, indices, 1)) {
		contains = true;
		break;
	    }
	}
	return contains;
    }

    // Private recursive method called by containsPhrase(). If the next word's
    // position is 1 greater than the current,
    // recurse and do it again for the next and the word after next. Stop when the
    // word index exceeds size.
    private boolean containsPhraseHelper(Integer pos, List<List<Integer>> indices, Integer nextListIndex) {
	if (indices.size() <= nextListIndex)
	    return true;

	List<Integer> nextList = indices.get(nextListIndex);
	for (Integer nextPos : nextList) {
	    if (nextPos - pos == 1) {
		return containsPhraseHelper(nextPos, indices, nextListIndex + 1);
	    }
	}

	return false;
    }

    public int getPhraseCount(String s) {
	// First 3 lines similar to containsPhrase().
	List<String> wordsInPhrase = phraseToList(s);
	if (!eachWordPresent(wordsInPhrase))
	    return 0;
	List<List<Integer>> indices = getLocationsList(wordsInPhrase);

	// If a phrase is found, using containsPhraseHelper, increment phraseCount by 1.
	int phraseCount = 0;
	List<Integer> firstWordPositions = indices.get(0);
	for (Integer pos : firstWordPositions) {
	    if (containsPhraseHelper(pos, indices, 1))
		phraseCount++;
	}
	return phraseCount;
    }

    public double getPhraseFrequency(String s) {
	return (double) getPhraseCount(s) / getWordCount();
    }

    public List<Integer> getPhraseLocations(String s) {
	// First 3 lines similar to containsPhrase().
	List<String> wordsInPhrase = phraseToList(s);
	if (!eachWordPresent(wordsInPhrase))
	    return new ArrayList<Integer>();
	List<List<Integer>> indices = getLocationsList(wordsInPhrase);

	// If a phrase is found, using containsPhraseHelper, add the first word's
	// position to phraseLocations.
	List<Integer> phraseLocations = new ArrayList<Integer>();
	List<Integer> firstWordPositions = indices.get(0);
	for (Integer pos : firstWordPositions) {
	    if (containsPhraseHelper(pos, indices, 1))
		phraseLocations.add(pos);
	}
	return phraseLocations;
    }
}