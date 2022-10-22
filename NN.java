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
    
    public static double rectify(double numToBeRectified){

        return sigmoid(numToBeRectified);
    }
    
    public static double sigmoid(double numToBeRectified){
        return 1 / (1 + Math.exp(-numToBeRectified) );
    }


    public static double average(double[] sum) {
        double temp = 0;
        for(double i : sum){
            temp += i;
        }
        temp = temp/ sum.length;
        return temp;
    }

}
