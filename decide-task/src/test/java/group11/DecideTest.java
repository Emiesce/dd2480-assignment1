package group11; 
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
    public void testMAINFalse() {
        decide.NUMPOINTS = 52;

        //Testing all LIC (assuming that there are no overlaps)
        //LIC0 : points are farther than 5 units
        decide.parameters.LENGTH1 = 5;
        decide.x[0] = 0;
        decide.y[0] = 10;
        decide.x[1] = 0;
        decide.y[1] = 20;

        //LIC1 points is not contained within radius of 0.5
        decide.parameters.RADIUS1 = 0.5;
        decide.x[2] = 10;
        decide.y[2] = 10;
        decide.x[3] = 10;
        decide.y[3] = 11;
        decide.x[4] = 10;
        decide.y[4] = 9;

        //LIC2 points form an angle of 90 degrees, should fulfill the criteria of being lesser than pi-1 roughly 120 deg
        decide.parameters.EPSILON = 1;
        decide.x[5] = 20;
        decide.y[5] = 11;
        decide.x[6] = 20;
        decide.y[6] = 10;
        decide.x[7] = 21;
        decide.y[7] = 10;

        //LIC3 points form a triangle with 0.5 as area
        decide.parameters.AREA1 = 0.4;
        decide.x[8] = 30;
        decide.y[8] = 11;
        decide.x[9] = 30;
        decide.y[9] = 10;
        decide.x[10] = 31;
        decide.y[10] = 10;

        //LIC4 2 points contained in quadrant 1
        decide.parameters.QPTS = 2;
        decide.parameters.QUADS = 1;
        decide.x[11] = 40;
        decide.y[11] = 1;
        decide.x[12] = 40;
        decide.y[12] = -1;

        //LIC5 difference in X-axis between two points is negative
        decide.x[13] = 50;
        decide.y[13] = 10;
        decide.x[14] = 49;
        decide.y[14] = 10;

        //LIC6 2nd point is at a distance 1 from line created by the first and last point
        decide.parameters.NPTS = 3;
        decide.parameters.DIST = 0.9;
        decide.x[15] = 60;
        decide.y[15] = 10;
        decide.x[16] = 61;
        decide.y[16] = 11;
        decide.x[17] = 61;
        decide.y[17] = 10;

        //LIC7 points are 10 units away from each other
        //LENGTH1 = 5
        decide.parameters.KPTS = 1;
        decide.x[18] = 70;
        decide.y[18] = 10;
        decide.x[19] = 0;
        decide.y[19] = 0;
        decide.x[20] = 70;
        decide.y[20] = 20;

        //LIC8 points are not contained within circle of radius 0.5
        //decide.parameters.RADIUS1 = 0.5;
        decide.parameters.APTS = 1;
        decide.parameters.BPTS = 1;
        decide.x[21] = 80;
        decide.y[21] = 9;
        decide.x[22] = 0;
        decide.y[22] = 0;
        decide.x[23] = 80;
        decide.y[23] = 11;
        decide.x[24] = 0;
        decide.y[24] = 0;
        decide.x[25] = 80;
        decide.y[25] = 10;

        //LIC9 same as LIC2 but with space between the points and shifted in the x axis as the pattern follows
        decide.parameters.CPTS = 1;
        decide.parameters.DPTS = 1;
        decide.x[26] = 90;
        decide.y[26] = 11;
        decide.x[27] = 0;
        decide.y[27] = 0;
        decide.x[28] = 90;
        decide.y[28] = 10;
        decide.x[29] = 0;
        decide.y[29] = 0;
        decide.x[30] = 91;
        decide.y[30] = 10;

        //LIC10 same as LIC3 but with space inbetween
        //Area should be greater than 0.4
        decide.parameters.EPTS = 1;
        decide.parameters.FPTS = 1;
        decide.x[31] = 100;
        decide.y[31] = 11;
        decide.x[32] = 0;
        decide.y[32] = 0;
        decide.x[33] = 100;
        decide.y[33] = 10;
        decide.x[34] = 0;
        decide.y[34] = 0;
        decide.x[35] = 101;
        decide.y[35] = 10;

        //LIC11 same as LIC5 but with space between
        decide.parameters.GPTS = 1;
        decide.x[36] = 110;
        decide.y[36] = 10;
        decide.x[37] = 0;
        decide.y[37] = 0;
        decide.x[38] = 109;
        decide.y[38] = 10;

        //LIC12 points are at a distance 6 which is between 5 and 7
        decide.parameters.KPTS = 1;
        //LENGTH1 = 5
        decide.parameters.LENGTH2 = 7;
        decide.x[39] = 120;
        decide.y[39] = 10;
        decide.x[40] = 0;
        decide.y[40] = 0;
        decide.x[41] = 120;
        decide.y[41] = 16;

        //LIC13 points should be contained in a circle between 0.5 and 2
        //A_PTS = 1
        //B_PTS = 1
        //RADIUS1 = 0.5
        decide.parameters.RADIUS2 = 2;
        decide.x[42] = 130;
        decide.y[42] = 11;
        decide.x[43] = 0;
        decide.y[43] = 0;
        decide.x[44] = 130;
        decide.y[44] = 10;
        decide.x[45] = 0;
        decide.y[45] = 0;
        decide.x[46] = 130;
        decide.y[46] = 9;

        //LIC14 triangle should be an area between 0.4 and 2
        //E_PTS = 1
        //F_PTS = 1
        //AREA1 = 0.4
        decide.parameters.AREA2 = 2;
        decide.x[47] = 140;
        decide.y[47] = 11;
        decide.x[48] = 0;
        decide.y[48] = 0;
        decide.x[49] = 140;
        decide.y[49] = 10;
        decide.x[50] = 0;
        decide.y[50] = 0;
        decide.x[51] = 141;
        decide.y[51] = 10;

        //all cmv:s has to be true
        for(int i = 0; i < 15; i++) {
            decide.PUV[i] = true;
            for(int j = 0; j < 15; j++) {
                decide.LCM[i][j] = Decide.Connectors.ANDD;
            }
        }



        //assuming one CMV fails we should not be launching
        decide.CMV[0] = false;
        decide.PUMCreator();
        decide.FUVCreator();
        boolean result = decide.decide();
        assertFalse("should be false but is true", result);

        
    }

    @Test
    public void testMAINTrue() {
        decide.NUMPOINTS = 52;

        //Testing all LIC (assuming that there are no overlaps)
        //LIC0 : points are farther than 5 units
        decide.parameters.LENGTH1 = 5;
        decide.x[0] = 0;
        decide.y[0] = 10;
        decide.x[1] = 0;
        decide.y[1] = 20;

        //LIC1 points is not contained within radius of 0.5
        decide.parameters.RADIUS1 = 0.5;
        decide.x[2] = 10;
        decide.y[2] = 10;
        decide.x[3] = 10;
        decide.y[3] = 11;
        decide.x[4] = 10;
        decide.y[4] = 9;

        //LIC2 points form an angle of 90 degrees, should fulfill the criteria of being lesser than pi-1 roughly 120 deg
        decide.parameters.EPSILON = 1;
        decide.x[5] = 20;
        decide.y[5] = 11;
        decide.x[6] = 20;
        decide.y[6] = 10;
        decide.x[7] = 21;
        decide.y[7] = 10;

        //LIC3 points form a triangle with 0.5 as area
        decide.parameters.AREA1 = 0.4;
        decide.x[8] = 30;
        decide.y[8] = 11;
        decide.x[9] = 30;
        decide.y[9] = 10;
        decide.x[10] = 31;
        decide.y[10] = 10;

        //LIC4 2 points contained in quadrant 1
        decide.parameters.QPTS = 2;
        decide.parameters.QUADS = 1;
        decide.x[11] = 40;
        decide.y[11] = 1;
        decide.x[12] = 40;
        decide.y[12] = -1;

        //LIC5 difference in X-axis between two points is negative
        decide.x[13] = 50;
        decide.y[13] = 10;
        decide.x[14] = 49;
        decide.y[14] = 10;

        //LIC6 2nd point is at a distance 1 from line created by the first and last point
        decide.parameters.NPTS = 3;
        decide.parameters.DIST = 0.9;
        decide.x[15] = 60;
        decide.y[15] = 10;
        decide.x[16] = 61;
        decide.y[16] = 11;
        decide.x[17] = 61;
        decide.y[17] = 10;

        //LIC7 points are 10 units away from each other
        //LENGTH1 = 5
        decide.parameters.KPTS = 1;
        decide.x[18] = 70;
        decide.y[18] = 10;
        decide.x[19] = 0;
        decide.y[19] = 0;
        decide.x[20] = 70;
        decide.y[20] = 20;

        //LIC8 points are not contained within circle of radius 0.5
        //decide.parameters.RADIUS1 = 0.5;
        decide.parameters.APTS = 1;
        decide.parameters.BPTS = 1;
        decide.x[21] = 80;
        decide.y[21] = 9;
        decide.x[22] = 0;
        decide.y[22] = 0;
        decide.x[23] = 80;
        decide.y[23] = 11;
        decide.x[24] = 0;
        decide.y[24] = 0;
        decide.x[25] = 80;
        decide.y[25] = 10;

        //LIC9 same as LIC2 but with space between the points and shifted in the x axis as the pattern follows
        decide.parameters.CPTS = 1;
        decide.parameters.DPTS = 1;
        decide.x[26] = 90;
        decide.y[26] = 11;
        decide.x[27] = 0;
        decide.y[27] = 0;
        decide.x[28] = 90;
        decide.y[28] = 10;
        decide.x[29] = 0;
        decide.y[29] = 0;
        decide.x[30] = 91;
        decide.y[30] = 10;

        //LIC10 same as LIC3 but with space inbetween
        //Area should be greater than 0.4
        decide.parameters.EPTS = 1;
        decide.parameters.FPTS = 1;
        decide.x[31] = 100;
        decide.y[31] = 11;
        decide.x[32] = 0;
        decide.y[32] = 0;
        decide.x[33] = 100;
        decide.y[33] = 10;
        decide.x[34] = 0;
        decide.y[34] = 0;
        decide.x[35] = 101;
        decide.y[35] = 10;

        //LIC11 same as LIC5 but with space between
        decide.parameters.GPTS = 1;
        decide.x[36] = 110;
        decide.y[36] = 10;
        decide.x[37] = 0;
        decide.y[37] = 0;
        decide.x[38] = 109;
        decide.y[38] = 10;

        //LIC12 points are at a distance 6 which is between 5 and 7
        decide.parameters.KPTS = 1;
        //LENGTH1 = 5
        decide.parameters.LENGTH2 = 7;
        decide.x[39] = 120;
        decide.y[39] = 10;
        decide.x[40] = 0;
        decide.y[40] = 0;
        decide.x[41] = 120;
        decide.y[41] = 16;

        //LIC13 points should be contained in a circle between 0.5 and 2
        //A_PTS = 1
        //B_PTS = 1
        //RADIUS1 = 0.5
        decide.parameters.RADIUS2 = 2;
        decide.x[42] = 130;
        decide.y[42] = 11;
        decide.x[43] = 0;
        decide.y[43] = 0;
        decide.x[44] = 130;
        decide.y[44] = 10;
        decide.x[45] = 0;
        decide.y[45] = 0;
        decide.x[46] = 130;
        decide.y[46] = 9;

        //LIC14 triangle should be an area between 0.4 and 2
        //E_PTS = 1
        //F_PTS = 1
        //AREA1 = 0.4
        decide.parameters.AREA2 = 2;
        decide.x[47] = 140;
        decide.y[47] = 11;
        decide.x[48] = 0;
        decide.y[48] = 0;
        decide.x[49] = 140;
        decide.y[49] = 10;
        decide.x[50] = 0;
        decide.y[50] = 0;
        decide.x[51] = 141;
        decide.y[51] = 10;

        //all cmv:s has to be true
        for(int i = 0; i < 15; i++) {
            decide.PUV[i] = true;
            for(int j = 0; j < 15; j++) {
                decide.LCM[i][j] = Decide.Connectors.ANDD;
            }
        }

        decide.CMVCreator();
        decide.PUMCreator();
        decide.FUVCreator();
        boolean result = decide.decide();

        assertTrue("should be true but is false", result);

        //assuming one CMV fails we should not be launching
        decide.CMV[0] = false;
        

        //should launch because this change makes it ignore CMV0
        for(int i = 0; i < 15; i++) {
            decide.LCM[0][i] = Decide.Connectors.NOTUSED;
            decide.LCM[i][0] = Decide.Connectors.NOTUSED;
        }
        decide.PUMCreator();
        decide.FUVCreator();
        result = decide.decide();
        assertTrue("should launch but is not", result);
    }


    @Test
    public void testDecideFalse1(){
        boolean [] FUVtest1 = {true, true, true, true, true, true, true, true, true, true, true, true, false ,true , true};
        decide.FUV = FUVtest1;
        assertFalse("FUVtest1 should return false/no launch", decide.decide());
    }

    @Test
    public void testDecideFalse2(){
        boolean [] FUVtest3 = {false, true, true, true, true, true, true, true, true, true, true, true, true ,true , true};
        decide.FUV = FUVtest3;
        assertFalse("FUVtest3 should return false/no launch", decide.decide());
    }

    @Test
    public void testDecideTrue(){
        boolean [] FUVtest2 = {true, true, true, true, true, true, true, true, true, true, true, true, true ,true , true};
        decide.FUV = FUVtest2;
        assertTrue("FUVtest2 should return true/launch", decide.decide());
    }

    

    @Test
    public void testFUVCreatorFalse() {
        // 1. FUV[0] is false because PUV[0] is true, but PUM[0,1] and PUM[0,3] are false.
        // 2. FUV[1] is true because PUV[1] is false.
        // 3. FUV[2] is true because PUV[2] is true and PUM[2,i] is true for all i6 = 2, 0 ≤ i ≤ 14

        boolean[][] PUMtest= new boolean[15][15];
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                PUMtest[i][j] = true;
            }
        }

        boolean[] PUVtest= new boolean[15];
        for(int i = 0; i < 15; i++){
            PUVtest[i] = true;
        }

        PUVtest[1] = false;
        PUMtest[0][1] = false;
        PUMtest[0][3] = false;

        decide.PUV = PUVtest;
        decide.PUM = PUMtest;
        decide.FUVCreator();
        assertFalse("Since PUM[0][1] and PUM[0][3] are false, this should return false", decide.FUV[0]);
    }
    @Test
    public void testFUVCreatorTrue() {
        // 1. FUV[0] is false because PUV[0] is true, but PUM[0,1] and PUM[0,3] are false.
        // 2. FUV[1] is true because PUV[1] is false.
        // 3. FUV[2] is true because PUV[2] is true and PUM[2,i] is true for all i6 = 2, 0 ≤ i ≤ 14

        boolean[][] PUMtest= new boolean[15][15];
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                PUMtest[i][j] = true;
            }
        }

        boolean[] PUVtest= new boolean[15];
        for(int i = 0; i < 15; i++){
            PUVtest[i] = true;
        }

        PUVtest[1] = false;
        PUMtest[0][1] = false;
        PUMtest[0][3] = false;

        decide.PUV = PUVtest;
        decide.PUM = PUMtest;
        decide.FUVCreator();
        assertTrue("Since PUV[1] is false, this should return true", decide.FUV[1]);
        assertTrue("Since PUV[2] is true, but all PUM[2][i] are true, this should return true", decide.FUV[2]);
    }


    @Test
    public void testPUMCreator(){
        //TODO
        boolean [] CMVtest = {true, true, true, true, true, true, true, true, true, true, true, true, false ,false , false};
        Decide.Connectors [][] LCMtest = new Decide.Connectors[15][15];
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                LCMtest[i][j] = Decide.Connectors.NOTUSED;
            }
        }
        LCMtest[0][0] = Decide.Connectors.ORR;
        LCMtest[14][0] = Decide.Connectors.ANDD;

        LCMtest[0][14] = Decide.Connectors.ANDD;

        boolean[][] expectedPUM= new boolean[15][15];
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                expectedPUM[i][j] = true;
            }
        }
        expectedPUM[0][14] = false;
        expectedPUM[0][0] = true;
        expectedPUM[14][0] = false;
        decide.CMV = CMVtest;
        decide.LCM = LCMtest;
        decide.PUMCreator();
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                assertEquals(expectedPUM[i][j], decide.PUM[i][j]);
            }
        }
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
    public void testCMV0False() {
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
        assertFalse("the length is 10, no two consecutive points are that far away but cmv0(10) returns true", decide.CMV0(10));
    }

    @Test

    public void testCMV0True(){
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
        
    }


    @Test
    public void testCMV1False() {
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
        assertFalse("the radius param. is 10, points 2,3,4 can be contained in such a circle", decide.CMV1(10));
        assertFalse("the radius param. is 1, points 2,3,4 can be contained ON such a circle", decide.CMV1(1));
    }

    @Test
    public void testCMV1True(){
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
    }


    @Test
    public void testCMV2True() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 0;
        double y2 = 0;

        double x3 = 1;
        double y3 = 0;

        double x4 = 1;
        double y4 = 1;

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
        decide.NUMPOINTS = 4;
        assertTrue("the angle formed by p2, p3, p4 is pi/2 so it should return true with epsilon = 1", decide.CMV2(1));
    }

    @Test
    public void testCMV2False() {
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
        assertFalse("the angle formed by p2, p3, p4 is pi/2 so it should return false with epsilon = 2", decide.CMV2(2));
    }

    @Test
    public void testCMV3False() {
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
        assertFalse("the area formed by p2, p3, p4 is 50 so it should return false with area1 = 50 ", decide.CMV3(50));
    }

    @Test
    public void testCMV3True() {
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
        decide.NUMPOINTS = 4;
        assertTrue("the area formed by p2, p3, p4 is 50 so it should return true with area1 = 1", decide.CMV3(1));

    }
    @Test
    public void testCMV4True() {
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
        assertTrue("the 4 points are in 4 different quadrant so CMV4 should return true with quads = 3 " +
                "and qpts = 4", decide.CMV4(4, 3));
    }
    @Test
    public void testCMV4False() {
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


        assertFalse("since no three points can be in 4 different quadrant, CMV4 should return false with quads " +
                "= 3 and qpts = 3", decide.CMV4(3, 3));
   
    }


    @Test
    public void testCMV5False() {
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
    }
    @Test
    public void testCMV5True() {
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

      
        decide.NUMPOINTS = 4;
        assertTrue("since x4 < x3, should be true", decide.CMV5());
    }


    @Test
    public void testCMV6False() {
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
    }
    @Test
    public void testCMV6True() {
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

        assertTrue("x2 is at 1 from point, so it should return true with dist = 0.5", decide.CMV6(0.5, 3));

        x2 = 100;
        y2 = 10;
        x3 = 10;
        y3 = 0;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;

        
        assertTrue("x2 is at 10 from line, so it should return true with dist = 9", decide.CMV6(9, 3));
    }

    @Test
    public void testCMV7False() {
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

        assertThrows(AssertionError.class, () -> decide.CMV7(0, 1)); //kpts under 1 not allowed
        assertThrows(AssertionError.class, () -> decide.CMV7(10, 1)); //kpts > NUMPOINTS - 2 not allowed
        assertFalse("Distance is 3 which is less than 4, but CMV7 returns true", decide.CMV7(2, 4));
    }

    @Test
    public void testCMV9False() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 0;
        double y2 = 0;

        double x3 = 1;
        double y3 = 1;

        double x4 = 0;
        double y4 = 0;

        double x5 = 0;
        double y5 = 1;

        decide.NUMPOINTS = 5;
        decide.x[0] = x1;
        decide.y[0] = y1;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;
        decide.x[3] = x4;
        decide.y[3] = y4;
        decide.x[4] = x5;
        decide.y[4] = y5;

        assertThrows(AssertionError.class, () -> decide.CMV9(0, 1, 1)); //cpts under 0 not allowed
        assertThrows(AssertionError.class, () -> decide.CMV9(1, 0, 1)); //dpts under 0 not allowed
        assertThrows(AssertionError.class, () -> decide.CMV9(1, decide.NUMPOINTS, 1)); //cpts + dpts > NUMPOINTS - 3 not allowed 
        assertThrows(AssertionError.class, () -> decide.CMV9(decide.NUMPOINTS, 1, 1)); //cpts + dpts > NUMPOINTS - 3 not allowed 

        assertFalse("angle between points (0,0), (1,1), (0,1) is not less than PI-3 or more than PI+3", decide.CMV9(1, 1, 3));
    }
    @Test
    public void testCMV9True() {
        double x1 = 0;
        double y1 = 0;

        double x2 = 0;
        double y2 = 0;

        double x3 = 1;
        double y3 = 1;

        double x4 = 0;
        double y4 = 0;

        double x5 = 0;
        double y5 = 1;

        decide.NUMPOINTS = 5;
        decide.x[0] = x1;
        decide.y[0] = y1;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;
        decide.x[3] = x4;
        decide.y[3] = y4;
        decide.x[4] = x5;
        decide.y[4] = y5;

        assertThrows(AssertionError.class, () -> decide.CMV9(0, 1, 1)); //cpts under 0 not allowed
        assertThrows(AssertionError.class, () -> decide.CMV9(1, 0, 1)); //dpts under 0 not allowed
        assertThrows(AssertionError.class, () -> decide.CMV9(1, decide.NUMPOINTS, 1)); //cpts + dpts > NUMPOINTS - 3 not allowed 
        assertThrows(AssertionError.class, () -> decide.CMV9(decide.NUMPOINTS, 1, 1)); //cpts + dpts > NUMPOINTS - 3 not allowed 

        assertTrue("angle between points (0,0), (1,1), (0,1) is less than PI-0.1", decide.CMV9(1, 1, 0.1));
    }
    

    @Test
    public void testAngleBetweenPoints() {
        assertEquals(Math.PI / 2, decide.angleBetweenPoints(1, 1, 2, 1, 2, 2), 0.01); // 90 degrees 
        assertEquals(Math.PI, decide.angleBetweenPoints(1, 1, 2, 2, 3, 3), 0.01); // 180 degrees 
    }


    @Test
    public void testCMV10False() {
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


        //1 !< 0.5, expected return is false
        assertFalse("the parameter area is greater than the triangle but returns true", decide.CMV10(1, 1, 1));
    }

    @Test
    public void testCMV10True() {
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

    }

    @Test
    public void testCMV8False() {
        assertThrows(AssertionError.class, () -> decide.CMV8(1, 0, 0)); //tests bad parameters

        decide.NUMPOINTS = 5;
        //These points should be contained within a circle with radius 1
        decide.x[0] = 0;
        decide.y[0] = 0;
        decide.x[2] = 0;
        decide.y[2] = 1;
        decide.x[4] = 0;
        decide.y[4] = -1;

        assertFalse("parameter radius is greater than smallest circle and should return false but is not", decide.CMV8(1.1, 1, 1));
    }

    @Test
    public void testCMV8True() {
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
    }

    @Test
    public void testCMV13False() {
        double x1 = 0;
        double y1 = 0;
    
        double x2 = 3;
        double y2 = 3;
    
        double x3 = 0;
        double y3 = 1;
    
        double x4 = 3;
        double y4 = 3;

        double x5 = 1;
        double y5 = 0;
    
        decide.NUMPOINTS = 5;
        decide.x[0] = x1;
        decide.y[0] = y1;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;
        decide.x[3] = x4;
        decide.y[3] = y4;
        decide.x[4] = x5;
        decide.y[4] = y5;
    
        assertThrows(AssertionError.class, () -> decide.CMV13(1, 2, 0.5, -1.5)); //tests bad parameters radius2 < 0
        assertFalse("Returned true even though the points (0,0), (0,1), (1,0) can be contained in circle of a radius1", decide.CMV13(1, 2, 5, 0.1));
    }

    @Test
    public void testCMV13True() {
        double x1 = 0;
        double y1 = 0;
    
        double x2 = 3;
        double y2 = 3;
    
        double x3 = 0;
        double y3 = 1;
    
        double x4 = 3;
        double y4 = 3;

        double x5 = 1;
        double y5 = 0;
    
        decide.NUMPOINTS = 5;
        decide.x[0] = x1;
        decide.y[0] = y1;
        decide.x[1] = x2;
        decide.y[1] = y2;
        decide.x[2] = x3;
        decide.y[2] = y3;
        decide.x[3] = x4;
        decide.y[3] = y4;
        decide.x[4] = x5;
        decide.y[4] = y5;
    
        assertThrows(AssertionError.class, () -> decide.CMV13(1, 2, 0.5, -1.5)); //tests bad parameters radius2 < 0
        assertTrue("Returned false even though the points (0,0), (0,1), (1,0) cannot be contained in a circle of radius1 and can be contained in one with radius2", decide.CMV13(1, 1, 0.1, 5));
    }
  
    @Test
    public void testCMV14False() {
        decide.NUMPOINTS = 5;
        //forms a triangle with area 0.5
        decide.x[0] = 0;
        decide.y[0] = 0;
        decide.x[2] = 1;
        decide.y[2] = 1;
        decide.x[4] = 0;
        decide.y[4] = 1;

        assertThrows(AssertionError.class, () -> decide.CMV14(1, -1, 1, 1)); //tests bad parameters

        assertFalse("there are no points with triangle area below 0.4 but it returns true", decide.CMV14(0.4, 0.4, 1, 1));
    }
    @Test
    public void testCMV14True() {
        decide.NUMPOINTS = 5;
        //forms a triangle with area 0.5
        decide.x[0] = 0;
        decide.y[0] = 0;
        decide.x[2] = 1;
        decide.y[2] = 1;
        decide.x[4] = 0;
        decide.y[4] = 1;

        assertThrows(AssertionError.class, () -> decide.CMV14(1, -1, 1, 1)); //tests bad parameters

        assertTrue("there are points with triangle areas between 0.4 and 0.6 but it returns false", decide.CMV14(0.4, 0.6, 1, 1));
    }
    
    @Test
    public void testCMV12False() {
        //These points have a distance of 5 between each other
        decide.NUMPOINTS = 3;
        decide.x[0] = 0;
        decide.y[0] = 0;
        decide.x[2] = 0;
        decide.y[2] = 5;

        assertThrows(AssertionError.class, () -> decide.CMV12(-1, 0, 1)); //tests bad parameters

        assertFalse("There exists no distance lesser than 4 but still returns true", decide.CMV12(4, 4, 1));
    }
    @Test
    public void testCMV12True() {
        //These points have a distance of 5 between each other
        decide.NUMPOINTS = 3;
        decide.x[0] = 0;
        decide.y[0] = 0;
        decide.x[2] = 0;
        decide.y[2] = 5;

        assertThrows(AssertionError.class, () -> decide.CMV12(-1, 0, 1)); //tests bad parameters

        assertTrue("Should return true since there is a distance greater than length1 and lesser than length2", decide.CMV12(4, 6, 1));
    }

}
