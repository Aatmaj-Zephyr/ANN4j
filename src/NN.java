package src; 
import java.lang.Math;
import java.util.ArrayList;

public class NN {
    // helper class for providing the necessary methods for neural networks

    private NN() {
    } // Static class cann4jot be instantiated

    public static double getRandom() {
        return Math.random();
    }

    public static double getSmallSignedRandom() {
        // returns numbers from -0.5 to 0.5
        return (Math.random() - 0.5);
    }

    /**
     * The tanh function takes a number and returns the hyperbolic tangent of that
     * number
     * 
     * @param numToBeRectified The number to be rectified.
     * @return The hyperbolic tangent of the number.
     */
    public static double tanh(double numToBeRectified) {
        return (Math.tanh(numToBeRectified) + 1) / 2;
    }

    /**
     * > The softplus function is a smooth approximation of the rectifier function
     * 
     * @param numToBeRectified The number to be rectified.
     * @return The softplus function is being returned.
     */
    public static double softplus(double numToBeRectified) {
        return Math.log(1 + Math.exp(numToBeRectified));
    }

    /**
     * The sigmoid function takes a number and returns a number between 0 and 1
     * 
     * @param numToBeRectified The number to be rectified.
     * @return The sigmoid function is being returned.
     */

    public static double sigmoid(double numToBeRectified) {
        return 1 / (1 + Math.exp(-numToBeRectified));
    }

    /**
     * If the number is greater than 0, return the number. Otherwise, return 0
     * 
     * @param numToBeRectified The number to be rectified.
     * @return The return value is the number that is passed in if it is greater
     *         than 0, otherwise it
     *         returns 0.
     */
    public static double relu(double numToBeRectified) {
        if (numToBeRectified > 0) {
            return numToBeRectified;
        }
        return 0;
    }

    /**
     * If the number is greater than 0, return the number, else return 0.1 times the
     * number
     * 
     * @param numToBeRectified The number to be rectified.
     * @return the value of the number to be rectified if it is greater than 0, and
     *         if it is less than 0,
     *         it is returning 0.1 times the number to be rectified.
     */
    public static double leakyrelu(double numToBeRectified) {
        if (numToBeRectified > 0) {
            return numToBeRectified;
        }
        return 0.1 * numToBeRectified;
    }

    public static double average(double[] sum) {

        /**
         * It takes an array of doubles, adds them all together, divides by the length
         * of the array, and
         * returns the result
         * 
         * @param sum an array of doubles
         * @return The average of the array.
         */

        double temp = 0;
        for (double i : sum) {
            temp += i;
        }
        temp = temp / sum.length;
        return temp;
    }

    public static double setBias() {
        return NN.getRandom() * 0.1;
    }

    public static double average(ArrayList<Double> input) {

        double sum = 0;
        for (int i = 0; i < input.size(); i++) {
            sum = sum + input.get(i);
        }
        return sum / input.size();
    }

}
