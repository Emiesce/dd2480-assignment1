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
    }
    private static boolean[] FUVCreator(){
        //TODO
    }

    private static boolean[][] PUMCreator(){
        //TODO
    }

    private static boolean[] CMVCreator(){
        //TODO
        
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

    private static boolean CMV2(double epsilon){
        double x1 = x[0]; 
        double y1 = y[0]; 

        double x_vertex = x[1]; 
        double y_vertex = y[1]; 

        for(int i = 2; i < NUMPOINTS; ++i){
            double x2 = x[i]; 
            double y2 = y[i]; 

            if((x1 == x_vertex && y1 == y_vertex) || (x2 == x_vertex && y2 == y_vertex)){
                continue; //go on to next iteration since one point coincide with the vertex
            }

            double a_x = x_vertex - x1; 
            double a_y = y_vertex - y1; 
            double b_x = x_vertex - x2; 
            double b_y = y_vertex - y2;

            double a_dot_b = a_x * b_x + a_y * b_y; 
            double a_norm = distance(a_x, a_y, 0, 0); 
            double b_norm = distance(b_x, b_y, 0, 0); 

            double angle = acos(a_dot_b / (a_norm * b_norm)); 
            angle = (angle + 2 * Math.PI) % (2 * Math.PI); // Ensure the angle is in the range [0, 2π)

            if(doubleCompare(angle, PI - epsilon) == CompType.LT || 
                doubleCompare(angle, PI + epsilon) == CompType.GT){
                    return true; 
                }

            //prepare data for next iteration 
            x1 = x_vertex; 
            y1 = y_vertex; 
            x_vertex = x2; 
            y_vertex = y2; 
        }

        //no three consecutive points such that angle < (PI − EPSILON) or angle > (PI + EPSILON)
        return false;         
    }

    private static boolean CMV3(double area1){
        double x1 = x[0]; 
        double y1 = y[0]; 
        double x2 = x[1]; 
        double y2 = y[1]; 
        for(int i= 2; i < NUMPOINTS; ++i){
            double x3 = x[i]; 
            double y3 = y[i]; 

            double area = (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0 ; //area of the triangle
            CompType result = doubleCompare(area, area1); 
            if(result == CompType.GT) return true; //area > area1

            //prepare data for next iteration 
            x1 = x2;
            y1 = y2;
            x2 = x3;
            y2 = y3; 
        } 
        
        //no set of three consecutive points have their area > area1
        return false;   
        
    }

    private static boolean CMV4(int qpts, int quads){
        //number of points in each quadrant
        int quad1 = 0; 
        int quad2 = 0; 
        int quad3 = 0; 
        int quad4 = 0; 

        //initialization of number of points in each quadrant
        for(int i = 0; i < qpts -1; ++i){
            if(x[i] >= 0 && y[i] >= 0){
                quad1 += 1; 
            }
            else if(x[i] < 0 && y[i] >= 0){
                quad2 += 1; 
            }
            else if(x[i] < 0 && y[i] <= 0){
                quad3 += 1; 
            }
            else if(x[i] < 0 && y[i] > 0){
                quad4 += 1; 
            }
        }

        for(int i = qpts - 1; i < NUMPOINTS; ++i){
            //change the number of points in each quadrant by taking into account the
            //new point of the list 
            if (x[i] >= 0 && y[i] >= 0){
                quad1 += 1; 
            }
            else if(x[i] < 0 && y[i] >= 0){
                quad2 += 1; 
            }
            else if(x[i] < 0 && y[i] <= 0){
                quad3 += 1; 
            }
            else if(x[i] < 0 && y[i] > 0){
                quad4 += 1; 
            }

            int unused_quads = 0; 
            if(quad1 == 0) unused_quads +=1; 
            if(quad2 == 0) unused_quads +=1; 
            if(quad3 == 0) unused_quads +=1; 
            if(quad4 == 0) unused_quads +=1; 

            if(unused_quads < (3 - quads)) return true; 

            //change the number of points in each quadrant by removing the first point 
            // of the list in our counts
            if(x[i - (qpts -1)] >= 0 && y[i] >= 0){
                quad1 -= 1; 
            }
            else if(x[i - (qpts -1)] < 0 && y[i] >= 0){
                quad2 -= 1; 
            }
            else if(x[i - (qpts -1)] < 0 && y[i] <= 0){
                quad3 -= 1; 
            }
            else if(x[i - (qpts -1)] < 0 && y[i] > 0){
                quad4 -= 1; 
            }
        }

        return false; 
        
    }

    private static boolean CMV5(){
        double x1 = x[0]; 
        
        for(int i = 1; i < NUMPOINTS; ++i){
            double x2 = x[i]; 
            if(doubleCompare(x1, x2) == CompType.GT) return true; 
            x1 = x2; 
        }
        return false; 
        
    }

    private static boolean CMV6(double dist, int n_pts){
        if(NUMPOINTS < 3) return false; 

        for(int i = 0; i <= NUMPOINTS - n_pts; ++i){
            double x1 = x[i]; 
            double y1 = y[i];
            double x2 = x[i + n_pts - 1]; 
            double y2 = y[i + n_pts - 1]; 
            
            //same point
            if(x1 == x2 && y1 == y2){ 
                 
                for(int j = i + 1; j < i + n_pts - 1; ++j){
                    double distance = distance(x1, y1, x[j], y[j]); 
                    if(doubleCompare(distance, dist) == CompType.GT) return true; 
                }
            }else{
                double denominator = distance(x1, y1, x2, y2); 

                for(int j = i + 1; j < i + n_pts - 1; ++j){
                    double nominator = Math.abs((x2 - x1) * (y1 - y[i]) - (x1 - x[i]) * (y2 - y1));
                    double distance = nominator / denominator; 
                    if(doubleCompare(distance, dist) == CompType.GT) return true; 
                }

            }
        }
        return false;      
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

    private static double distance(double x1, double y1, double x2, double y2){
        return pow((pow(x1-x2, 2) + pow(y1-y2, 2)), 0.5); 
    }
    


    
}

