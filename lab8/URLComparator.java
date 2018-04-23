
/**
 * This class contains the code to compute the relevance score of a
 * web page, in response to a query.  It also contains a method to
 * compare the scores of two web pages.
 *
 * @author John Donaldson (Spring 2016)
 */

import java.util.Comparator;
import java.util.List;

class URLComparator implements Comparator<WebPageIndex> {

    List<String> query;

    URLComparator(List<String> query) {
	this.query = query;
    }

    public int score(WebPageIndex idx) {

	// Adds getCount for each word in the query then add them all together in the
	// score variable.

	int score = 0;

	for (String word : query) {
	    int currentCount = 0;
	    int negativeCount = 0;
	    // checks for phrase
	    if (word.contains(" ")) {
		if (word.startsWith("-")) { // if negation command available
		    word = word.replace("-", "");
		    negativeCount = idx.getPhraseCount(word);
		} else { // if negation command absent
		    currentCount = idx.getPhraseCount(word);
		}
	    } else { // for words
		if (word.startsWith("-")) { // if negation command available
		    word = word.replace("-", "");
		    negativeCount = idx.getCount(word);
		} else { // if negation command absent
		    currentCount = idx.getCount(word);
		}

	    }
	    score += (currentCount - negativeCount);
	}

	return score;
    }

    public int compare(WebPageIndex idx1, WebPageIndex idx2) {

	int score1 = score(idx1);
	int score2 = score(idx2);

	return score1 - score2;
    }

}
