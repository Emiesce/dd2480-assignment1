import static java.lang.Math.*;

public class Decide{

    public static final double PI = 3.1415926535;

    private class Parameters{
        double LENGTH1;    // Length in LICs 0, 7, 12
        double RADIUS1;    // Radius in LICs 1, 8, 13
        double EPSILON;    // Deviation from PI in LICs 2, 9
        double AREA1;      // Area in LICs 3, 10, 14
        int QPTS;          // No. of consecutive points in LIC 4
        int QUADS;         // No. of quadrants in LIC 4
        double DIST;       // Distance in LIC 6
        int NPTS;          // No. of consecutive points in LIC 6
        int KPTS;          // No. of int. points in LICs 7, 12
        int APTS;          // No. of int. points in LICs 8, 13
        int BPTS;          // No. of int. points in LICs 8, 13
        int CPTS;          // No. of int. points in LIC 9
        int DPTS;          // No. of int. points in LIC 9
        int EPTS;          // No. of int. points in LICs 10, 14
        int FPTS;          // No. of int. points in LICs 10, 14
        int GPTS;          // No. of int. points in LIC 11
        double LENGTH2;    // Maximum length in LIC 12
        double RADIUS2;    // Maximum radius in LIC 13
        double AREA2;      // Maximum area in LIC 14

    }

    // Enum for CONNECTORS
    public enum Connectors { NOTUSED, ORR, ANDD }

    static Parameters parameters;
    static double[] x = new double[100];
    static double[] y = new double[100];

    static int NUMPOINTS;
    static Connectors[][] LCM = new Connectors[15][15];
    static boolean[] FUV = new boolean[15];
    static boolean[][] PUM = new boolean[15][15];
    static boolean[] CMV = new boolean[15];
    static boolean launch;
    

    //compare floating point numbers
    public static CompType doubleCompare(double A, double B) {
        if (Math.abs(A - B) < 0.000001) {
            return CompType.EQ;
        } else if (A < B) {
            return CompType.LT;
        } else {
            return CompType.GT;
        }
    }

    public enum CompType{LT, EQ, GT} 
    



    public static void main(String[] args){
        //TODO
    }
    private static boolean decide(){
        //TODO

        return false;
    }
    private static boolean[] FUVCreator(){
        //TODO

        return new boolean[0];
    }

    private static boolean[][] PUMCreator(){
        //TODO

        return new boolean[0][0];
    }

    private static boolean[] CMVCreator(){
        //TODO

        return new boolean[0];
    }
    private static boolean CMV0(double length1){
        double x1 = x[0]; 
        double y1 = y[0]; 
        for(int i = 1; i < NUMPOINTS; ++i) {
            double x2 = x[i];
            double y2 = y[i];
            double square_dist = pow((x1-x2),2) + pow((y1-y2),2);
            CompType result = doubleCompare(square_dist, pow(length1, 2));
            if(result == CompType.GT) return true;
            x1 = x2;
            y1 = y2;
        }
        return false;
    }
    
    private static boolean CMV1(double radius1){
        double x1 = x[0]; 
        double y1 = y[0]; 
        double x2 = x[1]; 
        double y2 = y[1]; 
        double a = distance(x1,y1,x2,y2); 
        for(int i= 2; i < NUMPOINTS; ++i){
            double x3 = x[i]; 
            double y3 = y[i]; 
            double b = distance(x2,y2,x3,y3); 
            double c = distance(x3,y3,x1,y1); 

            double s = (a + b + c) / 2.0 ; //semiperimeter of the triangle
            double circumradius = (a * b * c) / 
                        (4 * pow(s * (a + b -s) * (a + c -s) * (b + c -s), 0.5)); //the circumradius
            CompType result = doubleCompare(circumradius, radius1); 
            if(result == CompType.GT) return true; //circumradius > radius1

            //prepare data for next iteration 
            x1 = x2;
            y1 = y2;
            x2 = x3;
            y2 = y3;
            a=b; 
        } 
        //no set of three consecutive points have their circumradius > radius
        return false;      
    }

    private static boolean CMV2(){
        //TODO

        return false;
    }

    private static boolean CMV3(){
        //TODO

        return false;
    }

    private static boolean CMV4(){
        //TODO

        return false;
    }

    private static boolean CMV5(){
        //TODO

        return false;
    }

    private static boolean CMV6(){
        //TODO

        return false;
    }

    private static boolean CMV7(){
        //TODO

        return false;
    }

    private static boolean CMV8(){
        //TODO

        return false;
    }

    private static boolean CMV9(){
        //TODO

        return false;
    }

    public boolean CMV10(){
        if(NUMPOINTS < 5) return false;

        double x1;
        double y1;

        double x2;
        double y2;

        double x3;
        double y3;

        for(int i = 0; i < NUMPOINTS - (parameters.EPTS + parameters.FPTS); i++) {
            x1 = x[i];
            y1 = y[i];

            x2 = x[i + parameters.EPTS];
            y2 = y[i + parameters.EPTS];

            x3 = x[i + parameters.EPTS + parameters.FPTS];
            y3 = y[i + parameters.EPTS + parameters.FPTS];

            if(triangleArea(x1, y1, x2, y2, x3, y3) > parameters.AREA1) return true;
        }

        return false;
    }

    private static boolean CMV11(){
        //TODO

        return false;
    }

    private static boolean CMV12(){
        //TODO

        return false;
    }

    private static boolean CMV13(){
        //TODO

        return false;
    }

    private static boolean CMV14(){
        //TODO

        return false;
    }

    private static double distance(double x1, double y1, double x2, double y2){
        return pow((pow(x1-x2, 2) + pow(y1-y2, 2)), 0.5); 
    }

    /**
     * helper function to calculate area of 3 points using shoelace formula
     * @return area of triangle
     */
    public double triangleArea(double x1, double y1,
                               double x2, double y2,
                               double x3, double y3) {
        return 0.5 * Math.abs((x1*y2 + x2*y3 + x3*y1) - (x2*y1 + x3*y2 + x1*y3));
    }

    
}

