import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DecideTest {
    //delta for tests with doubles
    private double delta = 0.000001;
    private Decide decide;
    public enum Connectors {
        NOTUSED, ORR, ANDD
    }
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
        boolean [] CMVtest = {true, true, true, true, true, true, true, true, true, true, true, true, false ,false , false};
        Connectors [][] LCMtest = new Connectors[15][15];
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                LCMtest[i][j] = Connectors.NOTUSED;
            }
        }
        LCMtest[0][0] = Connectors.ORR;
        LCMtest[0][14] = Connectors.ANDD;
        boolean[][] expectedPUM= new boolean[15][15];
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                expectedPUM[i][j] = false;
            }
        }
        expectedPUM[0][14] = false;
        expectedPUM[0][0] = true;
        decide.PUMCreator();
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                assertEquals(expectedPUM[i][j], decide.PUM[i][j]);
            }
        }
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
    public void testCMV0() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 1;
        double y2 = 1;

        double x3 = 10;
        double y3 = 1;

        double x4 = 10;
        double y4 = 2;

        decide.NUMPOINTS = 4;
        decide.x[0] = x1;
        decide.y[0] = y1;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;
        decide.x[3] = x4;
        decide.y[3] = y4;

        assertThrows(AssertionError.class, () -> decide.CMV0(-1));
        assertTrue("the length is 5, point 3 & 4 are further away but it returns false", decide.CMV0(5));
        assertFalse("the length is 10, no two consecutive points are that far away but cmv0(10) returns true", decide.CMV0(10));
    }


    @Test
    public void testCMV1() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 1;
        double y2 = 0;

        double x3 = 0;
        double y3 = 1;

        double x4 = 0;
        double y4 = -1;

        decide.NUMPOINTS = 4;
        decide.x[0] = x1;
        decide.y[0] = y1;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;
        decide.x[3] = x4;
        decide.y[3] = y4;

        assertThrows(AssertionError.class, () -> decide.CMV1(-1)); //tests bad parameters
        assertTrue("the radius param. is 0.5, point 2,3,4 can only be contain on/in a circle of radius minimum 1", decide.CMV1(0.5));
        assertFalse("the radius param. is 10, points 2,3,4 can be contained in such a circle", decide.CMV1(10));
        assertFalse("the radius param. is 1, points 2,3,4 can be contained ON such a circle", decide.CMV1(1));
    }


    @Test
    public void testCMV2() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 0;
        double y2 = 0;

        double x3 = 1;
        double y3 = 0;

        double x4 = 1;
        double y4 = 1;

        decide.NUMPOINTS = 3;
        decide.x[0] = x1;
        decide.y[0] = y1;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;
        decide.x[3] = x4;
        decide.y[3] = y4;

        assertThrows(AssertionError.class, () -> decide.CMV2(-1)); //tests bad parameters
        assertThrows(AssertionError.class, () -> decide.CMV2(decide.PI)); //tests bad parameters
        assertThrows(AssertionError.class, () -> decide.CMV2(decide.PI + 1)); //tests bad parameters
        assertFalse("since two points coincide, it should return false", decide.CMV2(3));
        decide.NUMPOINTS = 4;
        assertTrue("the angle formed by p2, p3, p4 is pi/2 so it should return true with epsilon = 1", decide.CMV2(1));
        assertFalse("the angle formed by p2, p3, p4 is pi/2 so it should return false with epsilon = 2", decide.CMV2(2));
    }

    @Test
    public void testCMV3() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 0;
        double y2 = 0;

        double x3 = 10;
        double y3 = 0;

        double x4 = 0;
        double y4 = 10;

        decide.NUMPOINTS = 3;
        decide.x[0] = x1;
        decide.y[0] = y1;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;
        decide.x[3] = x4;
        decide.y[3] = y4;

        assertThrows(AssertionError.class, () -> decide.CMV3(-1)); //tests bad parameters
        assertFalse("since two points coincide, it should return false, the area is 0", decide.CMV3(1));
        decide.NUMPOINTS = 4;
        assertTrue("the area formed by p2, p3, p4 is 50 so it should return true with area1 = 1", decide.CMV3(1));
        assertFalse("the area formed by p2, p3, p4 is 50 so it should return false with area1 = 50 ", decide.CMV3(50));
    }

    @Test
    public void testCMV4() {
        double x1 = 0;
        double y1 = 0;

        double x2 = -1;
        double y2 = 0;

        double x3 = 0;
        double y3 = -1;

        double x4 = 1;
        double y4 = -1;

        decide.NUMPOINTS = 6;
        decide.x[0] = x1;
        decide.y[0] = y1;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;
        decide.x[3] = x4;
        decide.y[3] = y4;
        decide.x[4] = x4;
        decide.y[4] = y4;
        decide.x[5] = x4;
        decide.y[5] = y4;

        assertThrows(AssertionError.class, () -> decide.CMV4(1, 1)); //tests bad parameters
        assertThrows(AssertionError.class, () -> decide.CMV4(decide.NUMPOINTS + 1, 1)); //tests bad parameters
        assertThrows(AssertionError.class, () -> decide.CMV4(2, 0)); //tests bad parameters
        assertThrows(AssertionError.class, () -> decide.CMV4(2, 4)); //tests bad parameters
        assertTrue("since the first three points are in 3 different quadrant, it should return true with quads " +
                "= 2 and qpts = 3", decide.CMV4(3, 2));
        assertFalse("since no three points can be in 4 different quadrant, CMV4 should return false with quads " +
                "= 3 and qpts = 3", decide.CMV4(3, 3));
        assertTrue("the 4 points are in 4 different quadrant so CMV4 should return true with quads = 3 " +
                "and qpts = 4", decide.CMV4(4, 3));
    }


    @Test
    public void testCMV5() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 1;
        double y2 = 0;

        double x3 = 1;
        double y3 = 0;

        double x4 = 0;
        double y4 = 0;

        decide.NUMPOINTS = 2;
        decide.x[0] = x1;
        decide.y[0] = y1;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;
        decide.x[3] = x4;
        decide.y[3] = y4;

        assertFalse("since x2 > x1, should be false", decide.CMV5());
        decide.NUMPOINTS = 3;
        assertFalse("since x2 > x1 and x3 == x2, should be false", decide.CMV5());
        decide.NUMPOINTS = 4;
        assertTrue("since x4 < x3, should be true", decide.CMV5());
    }


    @Test
    public void testCMV6() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 1;
        double y2 = 0;

        double x3 = 0;
        double y3 = 0;

        decide.NUMPOINTS = 3;
        decide.x[0] = x1;
        decide.y[0] = y1;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;

        assertThrows(AssertionError.class, () -> decide.CMV6(-1, 3)); //tests bad parameters
        assertThrows(AssertionError.class, () -> decide.CMV6(0, 2)); //tests bad parameters
        assertThrows(AssertionError.class, () -> decide.CMV6(0, decide.NUMPOINTS + 1)); //tests bad parameters

        assertFalse("x2 is at 1 from point, so it should return false with dist = 1", decide.CMV6(1, 3));
        assertFalse("x2 is at 1 from point, so it should return false with dist = 2", decide.CMV6(2, 3));
        assertTrue("x2 is at 1 from point, so it should return true with dist = 0.5", decide.CMV6(0.5, 3));

        x2 = 100;
        y2 = 10;
        x3 = 10;
        y3 = 0;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;

        assertFalse("x2 is at 10 from line, so it should return false with dist = 10", decide.CMV6(10, 3));
        assertFalse("x2 is at 10 from line, so it should return false with dist = 11", decide.CMV6(11, 3));
        assertTrue("x2 is at 10 from line, so it should return true with dist = 9", decide.CMV6(9, 3));
    }

    @Test
    public void testCMV7() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 1;
        double y2 = 0;

        double x3 = 2;
        double y3 = 0;

        double x4 = 3;
        double y4 = 0;

        decide.NUMPOINTS = 4;
        decide.x[0] = x1;
        decide.y[0] = y1;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;
        decide.x[3] = x4;
        decide.y[3] = y4;

        assertTrue("Distance is 3 which is more than 1, but CMV7 returns false", decide.CMV7(2, 1));
        assertFalse("kpts is larger than NUMPOINTS - 2, but CMV7 return true", decide.CMV7(3, 1));
        assertFalse("Distance is 3 which is less than 4, but CMV7 returns true", decide.CMV7(2, 4));
    }



    @Test
    public void testCMV10() {
        //These coordinates form a triangle with an area of 0.5
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

        assertThrows(AssertionError.class, () -> decide.CMV10(0, 0, 1)); //tests bad parameters
        assertThrows(AssertionError.class, () -> decide.CMV10(0, 1, 0)); //tests bad parameters

        //0.2 < 0.5, expected return is true
        assertTrue("the parameter area is lesser than the triangle but returns false", decide.CMV10(0.2, 1, 1));

        //1 !< 0.5, expected return is false
        assertFalse("the parameter area is greater than the triangle but returns true", decide.CMV10(1, 1, 1));
    }

    @Test
    public void testCMV8() {
        assertThrows(AssertionError.class, () -> decide.CMV8(1, 0, 0)); //tests bad parameters

        decide.NUMPOINTS = 5;
        //These points should be contained within a circle with radius 1
        decide.x[0] = 0;
        decide.y[0] = 0;
        decide.x[2] = 0;
        decide.y[2] = 1;
        decide.x[4] = 0;
        decide.y[4] = -1;

        assertTrue("parameter radius is lesser than smallest circle and should return true but is not", decide.CMV8(0.9, 1, 1));
        assertFalse("parameter radius is greater than smallest circle and should return false but is not", decide.CMV8(1.1, 1, 1));
    }
  
    @Test
    public void testCMV14() {
        decide.NUMPOINTS = 5;
        //forms a triangle with area 0.5
        decide.x[0] = 0;
        decide.y[0] = 0;
        decide.x[2] = 1;
        decide.y[2] = 1;
        decide.x[4] = 0;
        decide.y[4] = 1;

        assertTrue("there are points with triangle areas between 0.4 and 0.6 but it returns false", decide.CMV14(0.4, 0.6, 1, 1));
        assertFalse("there are no points with triangle area below 0.4 but it returns true", decide.CMV14(0.4, 0.4, 1, 1));
    }
    
    @Test
    public void testCMV12() {
        //These points have a distance of 5 between each other
        decide.NUMPOINTS = 3;
        decide.x[0] = 0;
        decide.y[0] = 0;
        decide.x[2] = 0;
        decide.y[2] = 5;

        assertThrows(AssertionError.class, () -> decide.CMV12(-1, 0, 1)); //tests bad parameters

        assertTrue("Should return true since there is a distance greater than length1 and lesser than length2", decide.CMV12(4, 6, 1));
        assertFalse("There exists no distance lesser than 4 but still returns true", decide.CMV12(4, 4, 1));
    }

}
