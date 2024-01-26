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

        assertEquals(0.5, result, delta);
    }

    @Test
    public void testCMV10LesserNUMPOINTS() {
        decide.NUMPOINTS = 4;

        assertEquals(false, decide.CMV10());
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
        decide.parameters.AREA1 = 1;
        decide.parameters.EPTS = 2;
        decide.parameters.FPTS = 2;

        assertEquals(false, decide.CMV10());
    }

}
