import org.testng.annotations.*;

import java.util.*;

import static org.testng.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ParserTest {

    private Parser p;
    private static int count = 1;

    @BeforeClass(groups={"GROUP1", "GROUP2"})
    public void beforeClass() {
        p = new Parser();
        System.out.println("Tests are starting");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Test " + count + " is starting");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Test " + count + " complete");
        count++;
    }



    @Test(groups={"GROUP1"})
    public void testSortAndOut() {
        Map<Double, Set<String>> map = new HashMap<Double, Set<String>>();
        Set<String> set1 = new HashSet<String>();
        set1.add("dodo");
        set1.add("fogo");
        Set<String> set2 = new HashSet<String>();
        set2.add("doghi");
        set2.add("vofhu");

        map.put(0.5, set1);
        map.put(0.4, set2);

        assertEquals("0.4 doghi\n0.4 vofhu\n0.5 fogo\n0.5 dodo\n", p.SortAndOut(map));
    }

    @Test(groups={"GROUP1"})
    public void testReplaceAndParse() {
        String s = "ich  to   hohg  n  n  l";
        String[] CorrectParsed = {"ich", "to", "hohg", "n", "n", "l"};
        String[] ExpectedParsed = p.ReplaceAndParse(s);

        assertThat(ExpectedParsed, is(equalTo(CorrectParsed)));
    }

    @Test(groups={"GROUP2"})
    public void testChangeLongWords() {
        String[] currPars = {"ffggddddddddddddddddddddddddddddddddddddd", "f"};
        String[] CorrectChanged = {"ffggdddddddddddddddddddddddddd", "f"};

        String[] ExpectedChanged = p.ChangeLongWords(currPars);

        assertThat(ExpectedChanged, hasItemInArray("f"));
        assertThat(ExpectedChanged, equalTo(CorrectChanged));
    }

    @Test(dataProvider = "dp", groups={"GROUP2"})
    public void testPartitionCount(String str, double partition) {
        assertThat(p.PartitionCount(str), is(equalTo(partition)));
    }


    @DataProvider
    public static Object[][] dp() {
        return new Object[][] {new Object[]{"abab", 0.5},
                new Object[]{"gogoo", 0.6},
                new Object[]{"fooo", 0.75}};
    }
}