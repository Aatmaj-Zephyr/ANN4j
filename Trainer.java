public class Trainer {
    public LayerManager myLayerManager;
    public double[] expectedLayer;
    public InputFileReader myMnistDataBaseFileReader;
    double[] inputLayer;
		double label;

    Trainer(LayerManager myLayerManager,  InputFileReader myMnistDataBaseFileReader){
        this.myLayerManager= myLayerManager;
        this.myMnistDataBaseFileReader= myMnistDataBaseFileReader;
    }
    public void train(int epochs){
        for (int i = 0; i <= epochs; i++) {

            // Getting the next image from the mnist database.
            myMnistDataBaseFileReader.next();

            // Getting the expected output array from the mnist database.
            expectedLayer = myMnistDataBaseFileReader.getExpectedOutputArray();
            // Getting the input array from the mnist database.
            inputLayer = myMnistDataBaseFileReader.getInputArray();
            // Getting the label of the image from the mnist database.
            label = myMnistDataBaseFileReader.getLabel();
            System.out.print(" actual " + label);
            

            train();

        }
    
    }
    public void train() {
        //Heart of the code

        myLayerManager.setInputLayer(inputLayer);

        myLayerManager.setExpectedOutputArray(expectedLayer);

        myLayerManager.forwardPropagate();
        myLayerManager.backwardPropagate();

        int prediction = getMostSignificantNeuronAsPrediction(myLayerManager);
        System.out.println(" prediction " + prediction);
    }

    public static int getMostSignificantNeuronAsPrediction(LayerManager myLayerManager) {
      // Finding the most significant neuron in the output layer.
        double temp = 0;
        int no = 0;
        for (int i = 0; i < myLayerManager.OutputLayer.listOfNeurons.size(); i++) {
            double val = myLayerManager.OutputLayer.listOfNeurons.get(i).activation;
            if (val > temp) {
                no = i;
                temp = val;
            }
        }
        return no;
    }

}
