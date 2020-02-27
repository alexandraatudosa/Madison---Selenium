import org.junit.*;

import static org.junit.Assert.*;

public class AnnotationsTest {
    @BeforeClass
    public static void beforeClass() {
        System.out.println("in before class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("in after class");
    }

    @Before
    public void before() {
        System.out.println("in before");
    }

    @After
    public void after() {
        System.out.println("in after");
    }

    @Test
    public void test() {
        System.out.println("in test");
    }

    @Test
    public void testAssertions() {
        String str1 = new String("abc");
        String str2 = new String("abc");
        String str3 = null;
        String str4 = "abc";
        String str5 = "abc";

        int val1 = 5;
        int val2 = 6;

        String[] expectedArray = {"one", "two", "three"};
        String[] resultArray = {"one", "two", "three"};

        assertEquals(str1, str2);
        assertTrue(val1 < val2);
        assertFalse(val1 > val2);
        assertNotNull(str1);
        assertNull(str3);
        assertSame(str4, str5);
        assertNotSame(str1, str3);
        assertArrayEquals(expectedArray, resultArray);
    }

    @Ignore
    public void ignoreTest() {
        System.out.println("ignoreee meee!");
    }

}
