import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author ygiam Lynn Giam and Minh Lam
 * 
 * @author ygiam
 *
 */
public class ProcessQueries {

    public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub
	List<WebPageIndex> URLs = new ArrayList<WebPageIndex>();
	File file = new File(args[0]);
	Scanner input = new Scanner(file);
	// this adds the WebPageIndex object into a list
	while (input.hasNext()) {
	    URLs.add(new WebPageIndex(input.nextLine()));
	}

	System.out.print("Enter a query: ");
	Scanner queries = new Scanner(System.in);
	while (queries.hasNext()) {
	    // this adds one line of query into a list
	    List<String> queriesList = new ArrayList<String>();
	    //@@@@@ have to break the line down into many words
	    Scanner s1 = new Scanner(queries.nextLine());
	    while (s1.hasNext()) queriesList.add(s1.next().toLowerCase()); // check if lowercase function works
	    
	    URLComparator comparator = new URLComparator(queriesList);
	    MyPriorityQueue<WebPageIndex> Q = new MyPriorityQueue<WebPageIndex>(comparator);
	    for (WebPageIndex x : URLs) {
		Q.add(x);
	    }
	    // this prints the results based on user specified count
	    if (args.length == 2) {
		int count = Integer.parseInt(args[1]);
		for (int i = 0; i < count; i++) {
		    WebPageIndex currentURL = Q.remove();
		    System.out.println(
			    "(score: " + ((URLComparator) Q.comparator).score(currentURL) + ") " + currentURL.getUrl());
		}

	    } else {
	    	//Added this line so that for loop has fixed iteration number.
	    	int qSize = Q.size();
		for (int i = 0; i < qSize; i++) {
		    WebPageIndex currentURL = Q.remove();
		    System.out.println(
		    		//@@@@@ Made this look more readable.
			    "(score: " + comparator.score(currentURL) + ") " + currentURL.getUrl());
		}
	    }
	    System.out.print("Enter a query: ");
	}

	System.out.print("Thank you for using our Process Queries program!");
    }

}
