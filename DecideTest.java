import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DecideTest {
    //delta for tests with doubles
    private double delta = 0.000001;
    private Decide decide;

    @Before
    public void setUp() {
        decide = new Decide();
    }

    @Test 
    public void testDecide(){
        //TODO
    }
    @Test
    public void testFUVCreator(){
        //TODO
    }
    @Test
    public void testPUMCreator(){
        //TODO
    }
    @Test
    public void testCMVCreator(){
        //TODO
    }


    @Test
    public void testTriangleArea() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 1;
        double y2 = 1;

        double x3 = 0;
        double y3 = 1;

        double result = decide.triangleArea(x1, y1, x2, y2, x3, y3);

        assertEquals("the area calculation is not correct", 0.5, result, delta);
    }

    @Test
    public void testDistance() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 3;
        double y2 = 4;

        double x3 = 12;
        double y3 = 5;

        double result1 = decide.distance(x1, y1, x2, y2);
        double result2 = decide.distance(x1, y1, x3, y3);

        assertEquals("the distance is supposed to be 5 but isn't", 5, result1, delta);
        assertEquals("the distance is supposed to be 13 but isn't", 13, result2, delta);
    }

    @Test
    public void testSmallestRadius() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 1;
        double y2 = 0;

        double x3 = -2;
        double y3 = 0;

        double x4 = 0;
        double y4 = -1;

        double x5 = -1;
        double y5 = 0;

        double result1 = decide.smallestRadius(x1, y1, x2, y2, x3, y3);
        double result2 = decide.smallestRadius(x2, y2, x5, y5, x4, y4);

        assertEquals("the radius is supposed to be 1.5 but isn't", 1.5, result1, delta);
        assertEquals("the radius is supposed to be 1 but isn't", 1, result2, delta);
    }



    @Test
    public void testCMV10() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 1;
        double y2 = 1;

        double x3 = 0;
        double y3 = 1;

        decide.NUMPOINTS = 5;
        decide.x[0] = x1;
        decide.y[0] = y1;
        decide.x[2] = x2;
        decide.y[2] = y2;
        decide.x[4] = x3;
        decide.y[4] = y3;

        assertTrue("the parameter area is lesser than the triangle but returns false", decide.CMV10(0.2, 1, 1));
        assertFalse("the parameter area is greater than the triangle but returns true", decide.CMV10(1, 1, 1));
    }

}
