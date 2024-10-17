package com.example.project;
public class LinearCalculator{
    //FOR EXTRA CREDIT 
    //you should copy and paste all of your code from the LinearCalculator class
    // but NOT printInfo(). Please update it below

    //INSTANCE VARIABLES 
    //4 INTEGER variables (name them: x1,x2,y1,y2) 

    private int x1;
    private int x2;
    private int y1;
    private int y2;


    //CONSTRUCTOR
    //1 constructor with 2 String parameters. Each parameter represents a coordinate. 
    //For example, "(1,2)" and "(3,4)" would be two parameter values 
    //You will have to parse the string into 4 integers, representing the 2 points.
    public LinearCalculator(String coord1, String coord2) { // <--add 2 string parameters to this constructor
            // finds the string value for x1 and y1
            int firstParaCoord1 = coord1.indexOf("(");
            int commaCoord1 = coord1.indexOf(",");
            int lastParaCoord1 = coord1.indexOf(")");
            String x1String = coord1.substring(firstParaCoord1 + 1, commaCoord1);
            String y1String = coord1.substring(commaCoord1 + 1, lastParaCoord1);
    
            // finds the string value for x2 and y2
            int firstParaCoord2 = coord2.indexOf("(");
            int commaCoord2 = coord2.indexOf(",");
            int lastParaCoord2 = coord2.indexOf(")");
            String x2String = coord2.substring(firstParaCoord2 + 1, commaCoord2);
            String y2String = coord2.substring(commaCoord2 + 1, lastParaCoord2);

            // Parses the srings into integer values
            x1 = Integer.parseInt(x1String);
            y1 = Integer.parseInt(y1String);
            x2 = Integer.parseInt(x2String);
            y2 = Integer.parseInt(y2String);
    }



    //METHODS
    //getters and setters for the 4 instance variables (8 methods total) 
    public int getX1(){return x1;}
    public int getY1(){return y1;}
    public int getX2(){return x2;}
    public int getY2(){return y2;}
    public void setX1(int newX1){x1 = newX1;}
    public void setY1(int newY1){y1 = newY1;}
    public void setX2(int newX2){x2 = newX2;}
    public void setY2(int newY2){y2 = newY2;}


    //distance() -> returns a double. 
    //calculates the distance between the two points to the nearest HUNDREDTH and returns the value.
    public double distance(){
        //using the math.sqrt and math.pow code to find the distance using the distance formula
        double distance = Math.sqrt(Math.pow((double) x2 - x1, 2) + Math.pow((double) y2 - y1, 2));
        return roundedToHundredth(distance);
    }

    //yInt() -> returns a double.
    //calculates the y intercept of the equation and returns the value to the nearest HUNDREDTH
    //if y-int if undefined, should return -999.99
    public double yInt(){
        // figures out if there is a y intercept
        double slope = slope();
        if (slope == -999.99) {
            return -999.99;
        }

        // computes the y intercept and returns it
        double yIntercept = y1 - (x1 * slope);
        return roundedToHundredth(yIntercept);
    }

    //slope() -> returns a double. 
    //calculates the slope of the equations and returns the value to the nearest HUNDREDTH
    //if slope is undefined, should return -999.99
    public double slope(){
        // finds the numerator and denominator
        double numerator = ((double) y2 - y1);
        double denominator = ((double) x2 - x1);

        // checks if the denominator is 0
        if (denominator == 0){
            return -999.99;
        }

        // figures out the slope and rounds it to the nearest hundredth
        double slope = (double) numerator / denominator;
        slope = roundedToHundredth(slope);
        return slope;
    }

    //equations() -> returns a String.
    //calculates the final equation in y=mx+b form and returns the string
    //if the equation has no slope, the equation should return -> "undefined"
    //HINT: You may need other custom methods to decrease the amount of code in the equations() method
    public String equation(){
        // finds the slope and y intercept
        double slope = slope();
        double yIntercept = yInt();

        // figures out if the slope is undefined
        if (slope == -999.99) {
            return "undefined";
        }
        // returns the equation with a negative y intercept to not add "+"
        if (yIntercept < 0) {
            return "y=" + slope + "x" + yIntercept;
        }
        // returns the equation without the y intercept if it's 0
        if (yIntercept == 0) {
            return "y=" + slope + "x";
        }
        // returns the equation without mx if the slope is 0
        if (slope == 0) {
            return "y=" + yIntercept;
        }
        return "y=" + slope + "x+" + yIntercept;
    }


    //roundedToHundredth(double x)-> returns double
    //calculates the input to the nearest hundredth and returns that value
    public double roundedToHundredth(double x){
        return Math.round(x * 100.0) / 100.0; // I learned this from https://stackoverflow.com/questions/8825209/rounding-decimal-points
    }

    //You will need to concatenate to the string 
    //the results from findSymmetry() and Midpoint()
    public String printInfo(){
        // returns the info about the two points
        String str = "The two points are: (" + x1 + "," + y1  + ")";
        str += " and " + "(" + x2 + "," + y2 + ")";
        str += "\nThe equation of the line between these points is: " + equation();
        str += "\nThe slope of this line is: " + slope();
        str += "\nThe y-intercept of the line is: " + yInt();
        str += "\nThe distance between the two points is: " + distance();
        str += "\n" + findSymmetry();
        str += "\n" + Midpoint();
        return str;
    }

    //findSymmetry()-> returns a string 
    //the method should determine if there is symmetry between the two points
    // there should be  4 return statements 
    // return "Symmetric about the x-axis";
    // return "Symmetric about the y-axis";
    //return "Symmetric about the origin";
    //return "No symmetry";
    public String findSymmetry(){
        double midX;
        double midY;
        midX = ((double) x1 + x2) / 2;
        midY = ((double) y1 + y2) / 2;

        // sorts the midpoint and finds if there are any symmetry
        if (midX == 0 && midY == 0) {
            return "Symmetric about the origin";
        }
        else if(midX == 0) {
            return "Symmetric about the y-axis";
        }
        else if(midY == 0) {
            return "Symmetric about the x-axis";
        }
        else {
            return "No symmetry";
        }
    }

    //Midpoint()->return a string 
    //the method should calculate the midpoint between the two points
    //it should return "The midpoint of this line is: (0,0)";
    public String Midpoint(){
        // finds the points for the midpoint
        double midX;
        double midY;
        midX = ((double) x1 + x2) / 2;
        midY = ((double) y1 + y2) / 2;

        return "The midpoint of this line is: (" + midX + "," + midY + ")";
    }

}



