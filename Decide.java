

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

    Parameters parameters;
    double[] x = new double[100];
    double[] y = new double[100];

    int NUMPOINTS;
    Connectors[][] LCM = new Connectors[15][15];
    boolean[] FUV = new boolean[15];
    boolean[][] PUM = new boolean[15][15];
    boolean[] CMV = new boolean[15];
    boolean launch;
    

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
    }
    private static boolean[] FUVCreator(){
        //TODO
    }

    private static boolean[][] PUMCreator(){
        //TODO
        for(int i = 0; i < 15; ++i){
            for(int j = 0; j < 15; ++j){
                if(LCM[i][j]==Connectors.NOTUSED){
                    PUM[i][j] = true;
                }
                else if(LCM[i][j]==Connectors.ANDD){
                    PUM[i][j] = CMV[i] && CMV[j];
                }
                else if(LCM[i][j]==Connectors.ORR){
                    PUM[i][j] = CMV[i] || CMV[j];
                }
            }
        }
        return new boolean[0];
    }

    private static boolean[] CMVCreator(){
        //TODO
        
    }
    private static boolean CMV0(){
        //TODO
        
    }
    private static boolean CMV1(){
        //TODO
        
    }

    private static boolean CMV2(){
        //TODO
        
    }

    private static boolean CMV3(){
        //TODO
        
    }

    private static boolean CMV4(){
        //TODO
        
    }

    private static boolean CMV5(){
        //TODO
        
    }

    private static boolean CMV6(){
        //TODO
        
    }

    private static boolean CMV7(){
        //TODO
        
    }

    private static boolean CMV8(){
        //TODO
        
    }

    private static boolean CMV9(){
        //TODO
        
    }

    private static boolean CMV10(){
        //TODO
        
    }

    private static boolean CMV11(){
        //TODO
        
    }

    private static boolean CMV12(){
        //TODO
        
    }

    private static boolean CMV13(){
        //TODO
        
    }

    private static boolean CMV14(){
        //TODO
        
    }
    


    
}

