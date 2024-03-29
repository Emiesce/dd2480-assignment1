package group11;
import static java.lang.Math.*;

public class Decide {

    public final double PI = 3.1415926535;

    public class Parameters{
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
    public enum Connectors {
        NOTUSED, ORR, ANDD
    }

    Parameters parameters = new Parameters();
    double[] x = new double[100];
    double[] y = new double[100];

    int NUMPOINTS;
    Connectors[][] LCM = new Connectors[15][15];
    boolean[] FUV = new boolean[15];
    boolean[][] PUM = new boolean[15][15];
    boolean[] CMV = new boolean[15];
    boolean[] PUV = new boolean[15];
    boolean launch;
    

    //compare floating point numbers
    public CompType doubleCompare(double A, double B) {
        if (Math.abs(A - B) < 0.000001) {
            return CompType.EQ;
        } else if (A < B) {
            return CompType.LT;
        } else {
            return CompType.GT;
        }
    }

    public enum CompType {
        LT, EQ, GT
    }

    public static void main(String[] args){
        Decide decide = new Decide();
        decide.CMVCreator();
        decide.PUMCreator();
        decide.FUVCreator();
        boolean result = decide.decide();
        if (result){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    /**
     * Decide checks the array elements of FUV to determine the launch.
     * @return boolean - true if launch, false if not
     */
    public boolean decide(){
        // If any element in FUV is false, return false/no launch
        for (int i = 0; i < 15; ++i) {
            if(!FUV[i]){
                return false;
            }
        }

        return true;
    }
    
    /**
     * Assigns boolean values to array elements in FUV according to conditions determined by array elements from PUV and PUM.
     * @return boolean[] - array of boolean values for FUV elements
     */
    public boolean[] FUVCreator(){
        for(int i = 0; i < 15; ++i) {
            if (!PUV[i]) { // If PUV[i] is false, FUV[i] is True
                FUV[i] = true;
            }
            else {
                boolean temp = true;
                for (int j = 0; j < 15; ++j) {
                    if(!PUM[i][j]) { // If PUM[i][j] is false, FUV[i] is false
                        temp = false;
                        break;
                    }
                }
                FUV[i] = temp;
            }
        }
        return new boolean[0];
    }

    /**
     * Assigns boolean values to array elements in PUM according to conditions determined by array elements from LCM and CMV.
     * @return boolean[][] - array of boolean values for PUM element
     */
    public void PUMCreator(){
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
        
    }

    /**
     * Assigns boolean values to array elements in CMV according to conditions determined by the parameters.
     * @return boolean[] - array of boolean values for CMV elements
     */
    public void CMVCreator(){
        // Set CMV[i] True if LIC[i] is true, else false
        for (int i = 0; i < 15; ++i) {
            switch (i) {
                case 0:
                    CMV[i] = CMV0(parameters.LENGTH1);
                    break;
                case 1:
                    CMV[i] = CMV1(parameters.RADIUS1);
                    break;
                case 2:
                    CMV[i] = CMV2(parameters.EPSILON);
                    break;
                case 3:
                    CMV[i] = CMV3(parameters.AREA1);
                    break;
                case 4:
                    CMV[i] = CMV4(parameters.QPTS, parameters.QUADS);
                    break;
                case 5:
                    CMV[i] = CMV5();
                    break;
                case 6:
                    CMV[i] = CMV6(parameters.DIST, parameters.NPTS);
                    break;
                case 7:
                    CMV[i] = CMV7(parameters.KPTS, parameters.LENGTH1);
                    break;
                case 8:
                    CMV[i] = CMV8(parameters.RADIUS1,parameters.APTS, parameters.BPTS);
                    break;
                case 9:
                    CMV[i] = CMV9(parameters.CPTS, parameters.DPTS, parameters.EPSILON);
                    break;
                case 10:
                    CMV[i] = CMV10(parameters.AREA1,parameters.EPTS, parameters.FPTS);
                    break;
                case 11:
                    CMV[i] = CMV11(parameters.GPTS);
                    break;
                case 12:
                    CMV[i] = CMV12(parameters.LENGTH1, parameters.LENGTH2,parameters.KPTS);
                    break;
                case 13:
                    CMV[i] = CMV13(parameters.APTS, parameters.BPTS, parameters.RADIUS1, parameters.RADIUS2);
                    break;
                case 14:
                    CMV[i] = CMV14(parameters.AREA1, parameters.AREA2,parameters.EPTS, parameters.FPTS);
                    break;
            }
        }
    }

    /**
     * CMV0 checks if the distance between consecutive points is greater than the parameter LENGTH1.
     * @param length1
     * @return boolean - true if the distance is greater than LENGTH1, false if not
     */
    public boolean CMV0(double length1){
        assert length1 >= 0;
        double x1 = x[0]; 
        double y1 = y[0]; 

        for(int i = 1; i < NUMPOINTS; ++i) {
            double x2 = x[i];
            double y2 = y[i];
            double square_dist = pow((x1 - x2), 2) + pow((y1 - y2), 2);
            CompType result = doubleCompare(square_dist, pow(length1, 2));
            if (result == CompType.GT)
                return true;
            x1 = x2;
            y1 = y2;
        }
        return false;
    }
    
    /**
     * CMV1 checks if the circumradius of any set of three consecutive points is greater than the parameter RADIUS1.
     * @param radius1
     * @return boolean - true if the circumradius is greater than RADIUS1, false if not
     */
    public boolean CMV1(double radius1){
        assert radius1 >= 0;
        double x1 = x[0]; 
        double y1 = y[0]; 
        double x2 = x[1]; 
        double y2 = y[1]; 

        for(int i= 2; i < NUMPOINTS; ++i){
            double x3 = x[i]; 
            double y3 = y[i]; 
            double radius = smallestRadius(x1, y1, x2, y2, x3, y3); //the circumradius
            CompType result = doubleCompare(radius, radius1); 
            if(result == CompType.GT) return true; //circumradius > radius1

            //prepare data for next iteration 
            x1 = x2;
            y1 = y2;
            x2 = x3;
            y2 = y3;
        } 
        //no set of three consecutive points have their circumradius > radius
        return false;      
    }

    /**
     * Determine if there exists at least one set of three consecutive data points which form an angle such that: 
     * angle < (PI − EPSILON) or angle > (PI + EPSILON). The second of the three consecutive points is always the vertex 
     * of the angle. If either the first point or the last point (or both) coincides with the vertex, the angle is undefined
     * @param epsilon
     * @return boolean - true if the angle is less than (PI - EPSILON) or greater than (PI + EPSILON), false if not
     */
    public boolean CMV2(double epsilon){
        assert (epsilon >= 0 && epsilon < PI);
        double x1 = x[0]; 
        double y1 = y[0]; 

        double x_vertex = x[1];
        double y_vertex = y[1];

        for(int i = 2; i < NUMPOINTS; ++i){
            double x2 = x[i]; 
            double y2 = y[i];
            if((x1 == x_vertex && y1 == y_vertex) || (x2 == x_vertex && y2 == y_vertex)){
                x1 = x_vertex;
                y1 = y_vertex;
                x_vertex = x2;
                y_vertex = y2;
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

            if (doubleCompare(angle, PI - epsilon) == CompType.LT ||
                    doubleCompare(angle, PI + epsilon) == CompType.GT) {
                return true;
            }

            // prepare data for next iteration
            x1 = x_vertex;
            y1 = y_vertex;
            x_vertex = x2;
            y_vertex = y2;
        }
        //no three consecutive points such that angle < (PI − EPSILON) or angle > (PI + EPSILON)
        return false;         
    }

    /**
     * Determine if there exists at least one set of three consecutive data points 
     * that are the vertices of a triangle with an area greater than AREA1.
     * @param area1
     * @return boolean - true if the area of the triangle is greater than AREA1, false if not
     */
    public boolean CMV3(double area1){
        assert area1 >= 0;
        double x1 = x[0]; 
        double y1 = y[0]; 
        double x2 = x[1]; 
        double y2 = y[1]; 

        for(int i= 2; i < NUMPOINTS; ++i){
            double x3 = x[i]; 
            double y3 = y[i]; 

            double area = triangleArea(x1, y1, x2, y2, x3, y3); //area of the triangle
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

    /**
     * Determine if there exists at least one set of QPTS consecutive data points that lie in more than QUADS quadrants.
     * @param qpts
     * @param quads
     * @return boolean - true if consecutive data points lie in more than QUADS quadrants, false if not
     */
    public boolean CMV4(int qpts, int quads){
        assert (2 <= qpts && qpts <= NUMPOINTS);
        assert (1 <= quads && quads <= 3);
        //number of points in each quadrant
        int quad1 = 0; 
        int quad2 = 0; 
        int quad3 = 0; 
        int quad4 = 0; 

        //initialization of number of points in each quadrant
        for(int i = 0; i < qpts -1; ++i){
            if(x[i] >= 0 && y[i] >= 0){
                quad1 += 1; 
            }else if(x[i] < 0 && y[i] >= 0){
                quad2 += 1; 
            }else if(x[i] <= 0 && y[i] < 0){
                quad3 += 1; 
            }else if(x[i] > 0 && y[i] < 0){
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
            else if(x[i] <= 0 && y[i] < 0){
                quad3 += 1; 
            }
            else if(x[i] > 0 && y[i] < 0){
                quad4 += 1; 
            }
            int unused_quads = 0; 
            if(quad1 == 0) unused_quads +=1; 
            if(quad2 == 0) unused_quads +=1; 
            if(quad3 == 0) unused_quads +=1; 
            if(quad4 == 0) unused_quads +=1;

            if(unused_quads < (4 - quads)) return true;

            // change the number of points in each quadrant by removing the first point
            // of the list in our counts
            if(x[i - (qpts -1)] >= 0 && y[i - (qpts -1)] >= 0){
                quad1 -= 1; 
            } else if(x[i - (qpts -1)] < 0 && y[i - (qpts -1)] >= 0){
                quad2 -= 1; 
            } else if(x[i - (qpts -1)] <= 0 && y[i - (qpts -1)] < 0){
                quad3 -= 1; 
            } else if(x[i - (qpts -1)] > 0 && y[i - (qpts -1)] < 0){
                quad4 -= 1; 
            }
        }
        return false;     
    }

    /**
     * Determine if there exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[j],Y[j]), such that X[j] - X[i] < 0. (where i = j-1).
     * @param radius1
     * @return boolean - true if condition is met, false if not
     */
    public boolean CMV5(){
        double x1 = x[0]; 
        
        for(int i = 1; i < NUMPOINTS; ++i){
            double x2 = x[i]; 
            if(doubleCompare(x1, x2) == CompType.GT) return true; 
            x1 = x2; 
        }
        return false;     
    }

    /**
     * Determine if there exists at least one set of NPTS consecutive data points such that at least one of the points 
     * lies a distance greater than DIST from the line joining the first and last of these NPTS points. 
     * Condition is not met when NUMPOINTS < 3.
     * @param dist
     * @param npts
     * @return boolean - true if the distance between the first and last point is greater than DIST, false if not
     */
    public boolean CMV6(double dist, int npts){
        assert (3 <= npts && npts <= NUMPOINTS);
        assert (0 <= dist);
        if(NUMPOINTS < 3) return false; 

        for(int i = 0; i <= NUMPOINTS - npts; ++i){
            double x1 = x[i]; 
            double y1 = y[i];
            double x2 = x[i + npts - 1];
            double y2 = y[i + npts - 1];
            
            //same point
            if(x1 == x2 && y1 == y2){ 
                 
                for(int j = i + 1; j < i + npts - 1; ++j){
                    double distance = distance(x1, y1, x[j], y[j]);
                    if(doubleCompare(distance, dist) == CompType.GT) return true; 
                }
            } else {
                double denominator = distance(x1, y1, x2, y2);

                for(int j = i + 1; j < i + npts - 1; ++j){
                    double nominator = Math.abs((x2 - x1) * (y1 - y[j]) - (x1 - x[j]) * (y2 - y1));
                    double distance = nominator / denominator;
                    if(doubleCompare(distance, dist) == CompType.GT) return true; 
                }
            }
        }
        return false;
    }

    /**
     * Determine if there exists at least one set of KPTS consecutive data points such that the distance between the first 
     * and last of these KPTS points is greater than LENGTH1. 
     * Condition is not met when NUMPOINTS < 3.
     * @param kpts
     * @param length1
     * @return boolean - true if the distance between the first and last point is greater than LENGTH1, false if not
     */
    public boolean CMV7(int kpts, double length1) {
        assert(kpts >= 1);
        assert(kpts <= NUMPOINTS - 2);
        
        if (NUMPOINTS < 3) {
            return false;
        }

        for (int i = 0; i <= NUMPOINTS - kpts - 2; i++) {
            double x1 = x[i];
            double y1 = y[i];
            double x2 = x[i + kpts + 1];
            double y2 = y[i + kpts + 1];

            double distance = distance(x1, y1, x2, y2);

            if (doubleCompare(distance, length1) == CompType.GT) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if there exists at least one set of three data points separated by exactly APTS and BPTS consecutive 
     * intervening points, respectively, that cannot be contained within or on a circle of radius RADIUS1.
     * @param radius1
     * @param apts
     * @param bpts
     * @return boolean - true if the condition is met, false if not
     */
    public boolean CMV8(double radius1, int apts, int bpts){
        assert(1 <= apts && 1 <= bpts);
        assert(apts + bpts <= NUMPOINTS-3);
        if(NUMPOINTS < 5) return false;

        double x1;
        double y1;
        double x2;
        double y2;
        double x3;
        double y3;

        for(int i = 0; i < NUMPOINTS - (apts + bpts + 2); i++) {
             x1 = x[i];
             y1 = y[i];
             x2 = x[i + apts + 1];
             y2 = y[i + apts + 1];
             x3 = x[i + apts + bpts + 2];
             y3 = y[i + apts + bpts + 2];

             double smallestRadius = smallestRadius(x1, y1, x2, y2, x3, y3);

             if(doubleCompare(smallestRadius, radius1) != CompType.LT) return true;
         }

        return false;
    }

    /**
     * Determine if there exists at least one set of three data points separated by exactly `CPTS` and `DPTS` consecutive 
     * intervening points, respectively, that form an angle such that: angle < (`PI − EPSILON`) or angle > (`PI + EPSILON`). 
     * The second point of the set of three points is always the vertex of the angle. If either the first point or the 
     * last point (or both) coincide with the vertex, the angle is undefined.
     * @param cpts
     * @param dpts
     * @param epsilon
     * @return
     */
    public boolean CMV9(int cpts, int dpts, double epsilon) {
        assert(cpts + dpts <= NUMPOINTS - 3);
        assert(cpts >= 1);
        assert(dpts >= 1);
        
        if (NUMPOINTS < 5) {
            return false;
        }
    
        for (int i = 0; i < NUMPOINTS - (cpts + dpts + 2); i++) {
            double x1 = x[i];
            double y1 = y[i];
    
            double x2 = x[i + cpts + 1];
            double y2 = y[i + cpts + 1];
    
            double x3 = x[i + cpts + dpts + 2];
            double y3 = y[i + cpts + dpts + 2];
    
            if (doubleCompare(angleBetweenPoints(x1, y1, x2, y2, x3, y3), PI - epsilon) == CompType.LT ||
                doubleCompare(angleBetweenPoints(x1, y1, x2, y2, x3, y3), PI + epsilon) == CompType.GT) {
                return true;
            }
        }
    
        return false;
    }

    /**
     * Determine if there exists at least one set of three data points separated by exactly `EPTS` and `FPTS` 
     * consecutive intervening points, respectively, that are the vertices of a triangle with an area greater than `AREA1`. 
     * Condition is not met when `NUMPOINTS < 5`.
     * @param area1
     * @param epts
     * @param fpts
     * @return true if the area of the triangle is greater than area1, false otherwise
     */
    public boolean CMV10(double area1, int epts, int fpts){
        assert (1 <= epts && 1 <= fpts);
        assert (epts + fpts <= NUMPOINTS-3);
        if(NUMPOINTS < 5) return false;

        double x1;
        double y1;

        double x2;
        double y2;

        double x3;
        double y3;

        for(int i = 0; i < NUMPOINTS - (epts + fpts + 2); i++) {
            x1 = x[i];
            y1 = y[i];

            x2 = x[i + epts + 1];
            y2 = y[i + epts + 1];

            x3 = x[i + epts + fpts + 2];
            y3 = y[i + epts + fpts + 2];


            if(doubleCompare(triangleArea(x1, y1, x2, y2, x3, y3), area1) == CompType.GT) return true;
        }

        return false;
    }

    /**
     * Function to determine if there exists at least one set of two data points, (`X[i]`,`Y[i]`) and (`X[j]`,`Y[j]`), 
     * separated by exactly `GPTS` consecutive intervening points, such that `X[j] - X[i] < 0`. (where i < j ). 
     * Condition is not met when `NUMPOINTS < 3`.
     * @param gpts
     * @return true if the condition is met, false otherwise
     */
    public boolean CMV11(int gpts) {
        assert(gpts >= 1);
        assert(gpts <= NUMPOINTS - 2);

        if (NUMPOINTS < 3) {
            return false;
        }
    
        for (int i = 0; i <= NUMPOINTS - gpts - 2; i++) {
            int j = i + gpts + 1;
    
            if (doubleCompare(x[j] - x[i], 0) == CompType.LT) {
                return true;
            }
        }
    
        return false;
    }

    /**
     * Determine if the two following conditions are both true (condition is not met when NUMPOINTS < 3):
     * 
     * 1. There exists at least one set of two data points, separated by exactly KPTS consecutive intervening points, 
     * which are a distance greater than LENGTH1 apart.
     * 
     * 2. There exists at least one set of two data points (which can be the same or different from the two data points above), 
     * separated by exactly KPTS consecutive intervening points, that are a distance less than LENGTH2 apart.
     * @param length1
     * @param length2
     * @param kpts
     * @return true if the condition is met, false otherwise
     */
    public boolean CMV12(double length1, double length2, int kpts){
        assert (0 <= length1 && 0 <= length2);
        if(NUMPOINTS < 3) return false;

        double x1;
        double y1;

        double x2;
        double y2;

        double dist;

        boolean length1Condition = false;
        boolean length2Condition = false;

        for(int i = 0; i < NUMPOINTS - (kpts + 1); i++) {
            x1 = x[i];
            y1 = y[i];

            x2 = x[i + kpts + 1];
            y2 = y[i + kpts + 1];

            dist = distance(x1, y1, x2, y2);

            if(!length1Condition) {
                if(doubleCompare(dist, length1) == CompType.GT) length1Condition = true;
            }
            if(!length2Condition) {
                if(doubleCompare(dist, length2) == CompType.LT) length2Condition = true;
            }

            if(length1Condition && length2Condition) return true;
        }

        return false;
    }

    /**
     * Function to determine if the two following conditions are both true (condition is not met when `NUMPOINTS < 5`):
     * 
    1. There exists at least one set of three data points, separated by exactly `APTS` and `BPTS` consecutive 
        intervening points, respectively, that cannot be contained within or on a circle of radius `RADIUS1`.

    2. There is at least one set of three data points with intervals of APTS and BPTS points, respectively, 
        that can be contained in or on a circle of radius RADIUS2.
     * @param apts
     * @param bpts
     * @param radius1
     * @param radius2
     * @return true if the condition is met, false otherwise
     */
    public boolean CMV13(int apts, int bpts, double radius1, double radius2) {
        assert(radius2 >= 0);
        
        if (NUMPOINTS < 5) {
            return false;
        }
        
        boolean check1 = false;
        boolean check2 = false; 
    
        for (int i = 0; i < NUMPOINTS - (apts + bpts + 2); i++) {
            double x1 = x[i];
            double y1 = y[i];
    
            double x2 = x[i + apts + 1];
            double y2 = y[i + apts + 1];
    
            double x3 = x[i + apts + bpts + 2];
            double y3 = y[i + apts + bpts + 2];
    
            if (smallestRadius(x1, y1, x2, y2, x3, y3) > radius1) {
                check1 = true;
            }
    
            if (smallestRadius(x1, y1, x2, y2, x3, y3) <= radius2) {
                check2 = true;
            }
            
            if(check1 == true && check2 == true)
                return true;
        }
        return false;
    }

    /**
     * Determine if the two following conditions are both true (condition is not met when NUMPOINTS < 5):
     * 
     * 1. There exists at least one set of three data points, separated by exactly EPTS and FPTS consecutive intervening points, respectively, 
     * that are the vertices of a triangle with an area greater than AREA1.
     * 
     * 2. There exist three data points (which can be the same or different from the three data points above) separated by exactly EPTS and FPTS 
     * consecutive intervening points, respectively, that are the vertices of a triangle with an area less than AREA2.
     * @param area1
     * @param area2
     * @param epts
     * @param fpts
     * @return
     */
    public boolean CMV14(double area1, double area2, int epts, int fpts){
        assert(area2 >= 0);
        if(NUMPOINTS < 5) return false;

        double x1;
        double y1;

        double x2;
        double y2;

        double x3;
        double y3;

        boolean area1Condition = false;
        boolean area2Condition = false;

        for(int i = 0; i < NUMPOINTS - (epts + fpts + 2); i++) {
            x1 = x[i];
            y1 = y[i];

            x2 = x[i + epts + 1];
            y2 = y[i + epts + 1];

            x3 = x[i + epts + fpts + 2];
            y3 = y[i + epts + fpts + 2];

            double triangleArea = triangleArea(x1, y1, x2, y2, x3, y3);

            if(!area1Condition) {
                if(doubleCompare(triangleArea, area1) == CompType.GT) area1Condition = true;
            }
            if(!area2Condition) {
                if(doubleCompare(triangleArea, area2) == CompType.LT) area2Condition = true;
            }

            if(area1Condition && area2Condition) return true;
        }

        return false;
    }

    /**
     * Helper function to calculate the distance between two points using shoelace formula
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public double distance(double x1, double y1, double x2, double y2){
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

    /**
     * helper function to calculate the radius of the smallest circle
     * containing three points
     * 
     * @return the radius of the smallest circle
     */
    public double smallestRadius(double x1, double y1,
            double x2, double y2,
            double x3, double y3) {
        double a = distance(x1, y1, x2, y2);
        double b = distance(x2, y2, x3, y3);
        double c = distance(x3, y3, x1, y1);
        // check if a is the longest side
        if (doubleCompare(a, b) == CompType.GT && doubleCompare(a, c) == CompType.GT) {
            double radius = a / 2.0;
            double center_x = (x1 + x2) / 2.0;
            double center_y = (y1 + y2) / 2.0;
            double distCenter2P3 = distance(center_x, center_y, x3, y3);
            // checks if the circle includes the third point (distCenter2P3 <= radius)
            if (!(doubleCompare(distCenter2P3, radius) == CompType.GT)) {
                return radius;
            }
        }
        // check if b is the longest side
        else if (doubleCompare(b, c) == CompType.GT) {
            double radius = b / 2.0;
            double center_x = (x2 + x3) / 2.0;
            double center_y = (y2 + y3) / 2.0;
            double distCenter2P1 = distance(center_x, center_y, x1, y1);
            // checks if the circles includes the first point
            if (!(doubleCompare(distCenter2P1, radius) == CompType.GT)) {
                return radius;
            }
        }
        // c is the longest side
        else {
            double radius = c / 2.0;
            double center_x = (x1 + x3) / 2.0;
            double center_y = (y1 + y3) / 2.0;
            double distCenter2P2 = distance(center_x, center_y, x2, y2);
            // checks if the circles includes the first point
            if (!(doubleCompare(distCenter2P2, radius) == CompType.GT)) {
                return radius;
            }
        }
        // if no circle of radius a/2, b/2 or c/2 contains the three point
        // then the smallest circle containing the three point is the
        // circumcircle, with its radius the circumradius
        double s = (a + b + c) / 2.0; // semiperimeter of the triangle
        double circumradius = (a * b * c) /
                (4 * pow(s * (a + b - s) * (a + c - s) * (b + c - s), 0.5));
        return circumradius;
    }

    /**
     * helper function to calculate the angle between three points given their coordinates
     * @return the angle between the three points
     */
    public double angleBetweenPoints(double x1, double y1, double x2, double y2, double x3, double y3) {
        double aX = x1 - x2;
        double aY = y1 - y2;
        double bX = x3 - x2;
        double bY = y3 - y2;

        double aDotB = aX * bX + aY * bY;

        double magnitudeA = Math.sqrt(aX*aX+aY*aY);
        double magnitudeB = Math.sqrt(bX*bX+bY*bY);

        double angle = Math.acos(aDotB / (magnitudeA * magnitudeB));

        return angle;
    }
}
