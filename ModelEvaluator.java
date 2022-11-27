import java.util.ArrayList;
import java.util.Arrays;
public class ModelEvaluator {
        //Used to evaluate the predictions of the model, example pricision, accuracy and confusion matrix.

    private int correctCounter;
    private int turnsCounter;
    private double trainingAccuracy;
    private double testingAccuracy;
    protected ArrayList<Arrays> confusionMatrix = new ArrayList<Arrays>();

    public void updateConfusionMatrix(ArrayList<Double> expectedOutputArray,int predictedNeuronNum){
        ArrayList<Double> temp = new ArrayList<Double>();
        for(int i = 0;i<=expectedOutputArray.size();i++){
            if(i==predictedNeuronNum){
                temp.add(i,1.0);
            }
            else{
                temp.add(i,0.0);
            }
        }
        updateConfusionMatrix(expectedOutputArray,temp);
    }
    public void updateConfusionMatrix(ArrayList<Double> expectedOutputArray,ArrayList<Double> actualOutputArray){
        
    }

    public void updatePredictionData( double prediction, double label, double confidance){
       // Updating the prediction data.
        turnsCounter++;
        if(prediction==label){
            correctCounter++;
        }
        
    }

    public void reset(){
        correctCounter=0;
        turnsCounter=0;
    }

    public double getAccuracy(){
        return     (double) 100 * correctCounter / turnsCounter; //be careful for zero turns!  

    }

    public void setTrainingaccuracy(double accuracy) {
        this.trainingAccuracy=accuracy;
    }

    public double getTrainingAccuracy() {
        return trainingAccuracy;
    }

    public void setTestingaccuracy(double accuracy) {
        this.testingAccuracy=accuracy;
    }

    public double getTestingAccuracy() {
        return testingAccuracy;
    }
}
