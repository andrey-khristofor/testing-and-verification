import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ParserTest {

    private Parser p;
    private static int count = 0;

    @org.junit.jupiter.api.BeforeAll
    static void beforeAll(){
        System.out.println("Tests are starting");
    }

    @org.junit.jupiter.api.AfterEach
    void afterEach() {
        count++;
        System.out.println("Test " + count + " complete");
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        p = new Parser();
    }

    @Test
    public void testSortAndOut()
    {
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

    @Test
    public void testReplaceAndParse()
    {
        String s = "ich  to   hohg  n  n  l";
        String[] CorrectParsed = {"ich", "to", "hohg", "n", "n", "l"};
        String[] ExpectedParsed = p.ReplaceAndParse(s);

        assertThat(ExpectedParsed, is(equalTo(CorrectParsed)));
    }

    @Test
    public void testChangeLongWords()
    {
        String[] currPars = {"ffggddddddddddddddddddddddddddddddddddddd", "f"};
        String[] CorrectChanged = {"ffggdddddddddddddddddddddddddd", "f"};

        String[] ExpectedChanged = p.ChangeLongWords(currPars);

        assertThat(ExpectedChanged, hasItemInArray("f"));
        assertThat(ExpectedChanged, equalTo(CorrectChanged));
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testPartitionCount(String str, double partition) {
        assertThat(p.PartitionCount(str), is(equalTo(partition)));
    }


    public static Collection data() {
        return Arrays.asList(new Object[][] {{"abab", 0.5},{"gogoo", 0.6},{"fooo", 0.75} });
    }
}