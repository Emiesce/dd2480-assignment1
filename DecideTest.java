import org.junit.Test;
import static org.junit.Assert.*;

public class DecideTest {
    //delta for tests with doubles
    double delta = 0.000001;
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
        Decide decide = new Decide();

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
        Decide decide = new Decide();
    }

}
