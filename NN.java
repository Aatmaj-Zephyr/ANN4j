import java.lang.Math;
import java.util.ArrayList;

public class NN {
    // helper class for providing the necessary methods for neural networks

    private NN() {
    } // Static class cannot be instantiated

    public static double getRandom() {
        return Math.random();
    }

    public static double getSmallSignedRandom() {
        // returns numbers from -0.5 to 0.5
        return (Math.random() - 0.5);
    }

    public static double rectify(double numToBeRectified) {
        return sigmoid(numToBeRectified);
    }

    public static double sigmoid(double numToBeRectified) {
        return 1 / (1 + Math.exp(-numToBeRectified));
    }

    public static double relu(double numToBeRectified) {
        if (numToBeRectified > 0) {
            return numToBeRectified;
        }
        return 0;
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

        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            sum += input.get(i);
        }
        return sum / input.size();
    }

}
