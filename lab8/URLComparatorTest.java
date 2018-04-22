import static org.junit.Assert.*;

import java.io.IOException;
import java.util.*;

import java.util.List;

import org.junit.Test;

public class URLComparatorTest {

	@Test
	public void testScore() throws IOException{
		List<String> queryTest = new ArrayList<String>();
		queryTest.add("oberlin");
		queryTest.add("cs");
		URLComparator test = new URLComparator(queryTest);
		assertEquals("Number of matches: ", 10, test.score(new WebPageIndex("urls-profs")));
	}

	@Test
	public void testCompare() throws IOException{
		List<String> queryTest = new ArrayList<String>();
		queryTest.add("oberlin");
		queryTest.add("cs");
		URLComparator test = new URLComparator(queryTest);
		WebPageIndex profs = new WebPageIndex("urls-profs");
		WebPageIndex cs = new WebPageIndex("urls-cs");
		WebPageIndex oberlin = new WebPageIndex("urls-oberlin");
		assertTrue("urls-profs have more matches", test.compare(profs, oberlin) < 0);
		assertTrue("urls-oberlin have more matches", test.compare(cs, oberlin) > 0);
		assertTrue("urls-profs have unequal matches", test.compare(profs, profs) == 0);
	}

}
