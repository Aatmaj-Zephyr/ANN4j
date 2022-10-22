import java.lang.Math;
public class NN {
    //helper class for providing the necessary methods for neural networks

    private NN(){} //Static class cannot be instantiated

    
    public static double getRandom(){
        return Math.random();
    }
    public static double sigmoid(double numToBeRectified){
        return 1/1+Math.exp(-numToBeRectified);
    }

}
