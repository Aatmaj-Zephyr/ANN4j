public class ModelEvaluator {
        //Used to evaluate the predictions of the model, example pricision, accuracy and confusion matrix.

    private int correctCounter;
    private int turnsCounter;


    public void updatePredictionData( Double prediction, double label, double confidance){
       // Updating the prediction data.
        turnsCounter++;
        if(prediction==label){
            correctCounter++;
        }
        
    }

    public Double getAccuracy(){
        return     (double) 100 * correctCounter / turnsCounter; //be careful for zero turns!  

    }
}
