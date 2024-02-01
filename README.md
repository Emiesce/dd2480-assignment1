# DD2480 Assignment 1 (Group 11)

This project is created for "Assignment 1: DECIDE" for the course DD2480 VT24 - Software Engineering Fundamentals at KTH Royal Insitute of Technology.

### DECIDE() Function to determine hypothetical anti-ballistic missile launch

This project is designed to implement a function called DECIDE(), which outputs a boolean signal to determine whether to launch a hypothetical anti-ballistic missile based on several conditions obtained through the inputted radar tracking information available upon the function call, where each parameter and input is first given by the user.

The DECIDE() function evaluates radar tracking information to determine if an interceptor should be launched. It considers 15 Launch Interceptor Conditions (LICs) based on input data points given by the user. The function assigns boolean values to a Conditions Met Vector (CMV) for each LIC and utilizes a Logical Connector Matrix (LCM) to define joint considerations. The combination of LCM and CMV forms a Preliminary Unlocking Matrix (PUM). The Preliminary Unlocking Vector (PUV) indicates relevant LICs for the launch decision. The Final Unlocking Vector (FUV) is generated by combining PUM values, and only if all FUV elements are true, will the launch-unlock signal be generated.

## How to Run
The project was created using Java, as well as JUnit5 for unit testing. To run the test file, use the IntelliJ IDE and follow the [documentation](https://www.jetbrains.com/help/idea/junit.html#intellij) to have the necessary dependencies to run the test files. To run the main file, initialise the input variables within "Decide.java", and run the following commands to compile and then execute the program:
```bash
$ javac Decide.java
$ java Decide.java
```

## Roadmap

* The program has been completed and is now frozen

Unless a new issue or bug arises, this program should not be modified.


## Statement of Contributions

**Alexander**: Implemented CMV8, 10, 12 and 14 along with their tests, Reviewed CMV 7, 9, 11 and 13. Refactored the base code to allow for easier testing and since using static keyword is not the normal Java convention. Set up and researched how to use JUnit for Unit testing to pave the way for the tests. Created helper function triangleArea that takes 3 points and calculates the area that those points form.

**Iley**: Implemented CMV7, 9, 11, 13 along with their tests. Reviewed CMV8, 12, 14. Also created the helper function angleBetweenPoints and a test for it.

**Rached**: Implemented PUMCreator and CVMCreator. Performed initial commit for parameters and function prototypes, and created test file.

**Roxanne**: Implemented CMV0-6 along with their tests, Reviewed CMV PUMCreator, FUVCreator, Decide() and PUMCreator(). Created helper function smallestRadius and distance which respectively give the radius of the smallest circle containing three given points and calculate the distance between two points.

**Sung Kit**: Implemented FUVCreator(), Decide() and their corresponding test cases, as well as assist in implementing CMVCreator(). Reviewed CMV0-6. Created GitHub repository and standardised commit and pull request rules. Wrote README.md and Ways of Working. 

## Way-of-Working

Our self-evaluation determined that our team has reached the “In Place” State of the Way-of-Working. All team members have access and are using the tools (e.g. GitHub, Java, JUnit) and practices we have implemented (e.g. pull request, commit) to complete this project. All are involved in inspecting and adapting (e.g. reviewing each other's pull requests, and reminding each other of standardised practices on Telegram). However, the team still requires reminders on how to properly apply the practices we have standardised as well as how to use the tools properly (e.g. integrating JUnit into our program and developing proper test cases) before we can move on to the next state which is "Working Well".

## Documentation
### Classes

#### class [Parameters](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L8C5-L28)
```java
public class Parameters
```
Class containing an initialised list of parameters that are inputted into various functions in the program. Values must be assigned by the user before running.

### Functions

#### void [main](https://github.com/Emiesce/dd2480-assignment1/blob/366f5f4aca55043c96d0d8cb75c5dbdfeffd1ad6/Decide.java#L63C1-L73C6)
```java
public void main(String[] args)
```
The main() function creates the CMV, PUM, and FUV to determine whether to launch or not.

#### boolean [decide](https://github.com/Emiesce/dd2480-assignment1/blob/366f5f4aca55043c96d0d8cb75c5dbdfeffd1ad6/Decide.java#L74)
```java
boolean decide()
```
Decide checks the array elements of FUV to determine the launch.

#### boolean[] [FUVCreator](https://github.com/Emiesce/dd2480-assignment1/blob/366f5f4aca55043c96d0d8cb75c5dbdfeffd1ad6/Decide.java#L85C1-L102C6)
```java
boolean[] FUVCreator()
```
Assigns boolean values to array elements in FUV according to conditions determined by array elements from PUV and PUM.

#### void [PUMCreator](https://github.com/Emiesce/dd2480-assignment1/blob/366f5f4aca55043c96d0d8cb75c5dbdfeffd1ad6/Decide.java#L104-L120)
```java
void PUMCreator()
```
Assigns boolean values to array elements in PUM according to conditions determined by array elements in LCM and CMV.

#### void [CMVCreator](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L122-L175)
```java
void CMVCreator()
```
Runs the functions CMV0 - CMV14 and assigns the boolean outputs to their corresponding array elements in CMV

#### boolean [CMV0](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L177-L193)
```java
boolean CMV0(double length1)
```
Determine if there exists at least one set of two consecutive data points that are a distance greater than `LENGTH1` apart.

#### boolean [CMV1](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L195-L217)
```java
boolean CMV1(double radius1)
```
Determine if there exists at least one set of three consecutive data points that cannot all be contained within or on a circle of radius `RADIUS1`.

#### boolean [CMV2](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L219-L263)
```java
boolean CMV2(double epsilon)
```
Determine if there exists at least one set of three consecutive data points which form an angle such that: angle < (`PI − EPSILON`) or angle > (`PI + EPSILON`). The second of the three consecutive points is always the vertex of the angle. If either the first point or the last point (or both) coincides with the vertex, the angle is undefined.

#### boolean [CMV3](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L265-L288)
```java
boolean CMV3(double area1)
```
Determine if there exists at least one set of three consecutive data points that are the vertices of a triangle with an area greater than `AREA1`.

#### boolean [CMV4](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L290-L348)
```java
boolean CMV4(int qpts, int quads)
```
Determine if there exists at least one set of `QPTS` consecutive data points that lie in more than `QUADS` quadrants.

#### boolean [CMV5](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L350-L359)
```java
boolean CMV5()
```
Determine if there exists at least one set of two consecutive data points, (`X[i]`,`Y[i]`) and (`X[j]`,`Y[j]`), such that `X[j] - X[i] < 0`. (where i = j-1).

#### boolean [CMV6](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L361-L390)
```java
boolean CMV6(double dist, int npts)
```
Determine if there exists at least one set of `NPTS` consecutive data points such that at least one of the points lies a distance greater than `DIST` from the line joining the first and last of these `NPTS` points. Condition is not met when `NUMPOINTS < 3`.

#### boolean [CMV7](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L392-L413)
```java
boolean CMV7(int kpts, double length1)
```
Determine if there exists at least one set of two data points separated by exactly `KPTS` consecutive intervening points that are a distance greater than `LENGTH1` apart. The condition is not met when `NUMPOINTS < 3`.

#### boolean [CMV8](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L417-L443)
```java
boolean CMV8(double radius1, int apts, int bpts)
```
Determine if there exists at least one set of three data points separated by exactly `APTS` and `BPTS` consecutive intervening points, respectively, that cannot be contained within or on a circle of radius `RADIUS1`.

#### boolean [CMV9](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L445-L471)
```java
public boolean CMV9(int cpts, int dpts, double epsilon)
```
Determine if there exists at least one set of three data points separated by exactly `CPTS` and `DPTS` consecutive intervening points, respectively, that form an angle such that: angle < (`PI − EPSILON`) or angle > (`PI + EPSILON`). The second point of the set of three points is always the vertex of the angle. If either the first point or the last point (or both) coincide with the vertex, the angle is undefined.

#### boolean [CMV10](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L473-L502)
```java
public boolean CMV10(double area1, int epts, int fpts)
```
Determine if there exists at least one set of three data points separated by exactly `EPTS` and `FPTS` consecutive intervening points, respectively, that are the vertices of a triangle with an area greater than `AREA1`. Condition is not met when `NUMPOINTS < 5`.

#### boolean [CMV11](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L505-L522)
```java
public boolean CMV11(int gpts)
```
Determine if there exists at least one set of two data points, (`X[i]`,`Y[i]`) and (`X[j]`,`Y[j]`), separated by exactly `GPTS` consecutive intervening points, such that `X[j] - X[i] < 0`. (where i < j ). Condition is not met when `NUMPOINTS < 3`.

#### boolean [CMV12](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L524-L559)
```java
boolean CMV12(double length1, double length2, int kpts)
```
Determine if the two following conditions are both true (condition is not met when `NUMPOINTS < 3`):
1. There exists at least one set of two data points, separated by exactly `KPTS` consecutive intervening points, which are a distance greater than `LENGTH1` apart.
2. There exists at least one set of two data points (which can be the same or different from the two data points above), separated by exactly `KPTS` consecutive intervening points, that are a distance less than `LENGTH2` apart.

#### boolean [CMV13](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L561-L593)
```java
public boolean CMV13(int apts, int bpts, double radius1, double radius2)
```
Determine if the two following conditions are both true (condition is not met when `NUMPOINTS < 5`):
1. There exists at least one set of three data points, separated by exactly `APTS` and `BPTS` consecutive intervening points, respectively, that cannot be contained within or on a circle of radius `RADIUS1`.
2. There exists at least one set of three data points (which can be the same or different from the three data points above) separated by exactly `APTS` and `BPTS` consecutive intervening points, respectively, that can be contained in or on a circle of radius `RADIUS2`.

#### boolean [CMV14](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L595-L634)
```java
boolean CMV14(double area1, double area2, int epts, int fpts)
```
Determine if the two following conditions are both true (condition is not met when `NUMPOINTS < 5`):
1. There exists at least one set of three data points, separated by exactly `EPTS` and `FPTS` consecutive intervening points, respectively, that are the vertices of a triangle with an area greater than `AREA1`.
2. There exist three data points (which can be the same or different from the three data points above) separated by exactly `EPTS` and `FPTS` consecutive intervening points, respectively, that are the vertices of a triangle with an area less than `AREA2`.

#### double [distance](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L636-L638)
```java
double distance(double x1, double y1, double x2, double y2)
```
Helper function to calculate the Euclidean distance between two points in a two-dimensional space.

#### double [triangleArea](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L644-L648)
```java
 public double triangleArea(double x1, double y1,
                               double x2, double y2,
                               double x3, double y3)
```
Helper function to calculate the area of 3 points/triangle using the shoelace formula.

#### double [smallestRadius](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L656-L702)
```java
public double smallestRadius(double x1, double y1,
            double x2, double y2,
            double x3, double y3)
```
Helper function to calculate the radius of the smallest circle containing three points/coordinates.

#### double [angleBetweenPoints](https://github.com/Emiesce/dd2480-assignment1/blob/41a8d747ac75f96ba778b42c23d49db67f1f0546/Decide.java#L705-L721)
```java
public double angleBetweenPoints(double x1, double y1, double x2, double y2, double x3, double y3)
```
Helper function to calculate the angle between three points/coordinates.
