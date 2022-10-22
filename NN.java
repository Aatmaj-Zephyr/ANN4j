import java.lang.Math;
public class NN {
    //helper class for providing the necessary methods for neural networks

    private NN(){} //Static class cannot be instantiated


    public static double getRandom(){
        return Math.random();
    }

    public static double getSmallSignedRandom(){
        //returns numbers from -0.5 tp 0.5
        return Math.random()-1;
    }
    
    /**
     * The rectify function takes a number and returns the sigmoid of that number
     * 
     * @param numToBeRectified The number to be rectified.
     * @return The sigmoid of the number passed in.
     */
    public static double rectify(double numToBeRectified){
        return sigmoid(numToBeRectified);
    }
    
    public static double sigmoid(double numToBeRectified){
        return 1 / (1 + Math.exp(-numToBeRectified) );
    }


    
    public static double average(double[] sum) {

        /**
     * It takes an array of doubles, adds them all together, divides by the length of the array, and
     * returns the result
     * 
     * @param sum an array of doubles
     * @return The average of the array.
     */

        double temp = 0;
        for(double i : sum){
            temp += i;
        }
        temp = temp/ sum.length;
        return temp;
    }

}
