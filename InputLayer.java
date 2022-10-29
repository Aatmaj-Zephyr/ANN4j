public class InputLayer  extends Layer{

    InputLayer(int numOfNeurons, double[] inputLayerArray) {
        super(numOfNeurons);
        for( int i = 0; i<=inputLayerArray.length-1;i++){
            listOfNeurons.get(i).setActivation(inputLayerArray[i]);
        }
    }

    private double getWeightedSum(){
        //not applicable for input layer
     return 0;
    }


   

}
