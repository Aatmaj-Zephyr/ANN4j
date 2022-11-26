import java.util.ArrayList;

public class Trainer {
        //template pattern

    protected LayerManager myLayerManager;
    protected ArrayList<Double> expectedLayer;
    protected InputFileReader trainingFileReader;
    protected InputFileReader testingFileReader;

    double[] inputLayer;
    double label;
    private int correctCounter=0;

    Trainer() {
		this.myLayerManager = new LayerManager(parameter.getLayerArray());
        this.trainingFileReader = parameter.getTrainingFileReader();
        this.testingFileReader = parameter.getTestingFileReader();

    }
    public LayerManager getLayerManager(){
        return myLayerManager;
    }

    protected void train(int epochs) {
        for (int j = 0; j <= 10; j++) {
            parameter.setTrainingFileReader("mnist_train.csv", "mnist"); 
            this.trainingFileReader = parameter.getTrainingFileReader();
        for (int i = 0; i <= epochs; i++) {

            // Getting the next image from the mnist database.
            trainingFileReader.next();

            // Getting the expected output array from the mnist database.
            expectedLayer = trainingFileReader.getExpectedOutputArray();

            // Getting the input array from the mnist database.
            inputLayer = trainingFileReader.getInputArray();

            // Getting the label of the image from the mnist database.
            label = trainingFileReader.getLabel();

            //System.out.print(" actual " + label);

            train();

        }}


    }
    
    protected void test(int epochs){
        for (int i = 0; i < epochs; i++) {
             // Getting the next image from the mnist database.
             testingFileReader.next();

             // Getting the expected output array from the mnist database.
             expectedLayer = testingFileReader.getExpectedOutputArray();
 
             // Getting the input array from the mnist database.
             inputLayer = testingFileReader.getInputArray();
 
             // Getting the label of the image from the mnist database.
             label = testingFileReader.getLabel();
 
 
             test();
        }
        System.out.println("accuracy "+(double) 100*correctCounter/epochs);
    }

    protected void forwardPropagatewithExclusion(int epochs){
        for (int i = 0; i < epochs; i++) {
             // Getting the next image from the mnist database.
             testingFileReader.next();

             // Getting the expected output array from the mnist database.
             expectedLayer = testingFileReader.getExpectedOutputArray();
 
             // Getting the input array from the mnist database.
             inputLayer = testingFileReader.getInputArray();
 
             // Getting the label of the image from the mnist database.
             label = testingFileReader.getLabel();
 
 
             forwardPropagatewithExclusion();
        }
        System.out.println("accuracy "+(double) 100*correctCounter/epochs);
    }

    private void forwardPropagatewithExclusion(){
        myLayerManager.forwardPropagate(); //for calculation of MSE
        myLayerManager.forwardPropagatewithExclusion();

    }
    protected void relevancePropagate(int layerNumber,int neuronNumber) {
        
        myLayerManager.relevancePropagate(layerNumber, neuronNumber);
    }
    private void test() {
        myLayerManager.setInputLayer(inputLayer);
        myLayerManager.setExpectedOutputArray(expectedLayer);
        myLayerManager.forwardPropagate(); 
        int prediction = getMostSignificantNeuronAsPrediction(myLayerManager);
        

        
        double confidence = getconfidence(myLayerManager);
      
        
       
        
       System.out.println("Digit "+label+" is predicted as "+prediction+" with confidence " + confidence);       
    
        System.out.print("\n");
        if(prediction == label){
            correctCounter++;
        }
        
    }
    protected void train() {
        // Heart of the code

        myLayerManager.setInputLayer(inputLayer);

        myLayerManager.setExpectedOutputArray(expectedLayer);

        myLayerManager.forwardPropagate();

        myLayerManager.backwardPropagate();

        int prediction = getMostSignificantNeuronAsPrediction(myLayerManager);
        
       // System.out.println(" prediction " + prediction);
    }

    protected static int getMostSignificantNeuronAsPrediction(LayerManager myLayerManager) {
        // Finding the most significant neuron in the output layer.
        double temp = 0;
        int no = 0;
        for (int i = 0; i < myLayerManager.OutputLayer.listOfNeurons.size(); i++) {
            double val = myLayerManager.OutputLayer.listOfNeurons.get(i).getActivation();
            if (val > temp) {
                no = i;
                temp = val;
            }
        }
        return no;
    }
    protected static double getconfidence(LayerManager myLayerManager) {
        // Finding the most significant neuron in the output layer.
        double temp = 0;
        int no = 0;
        for (int i = 0; i < myLayerManager.OutputLayer.listOfNeurons.size(); i++) {
            double val = myLayerManager.OutputLayer.listOfNeurons.get(i).getActivation();
            if (val > temp) {
                no = i;
                temp = val;
            }
        }
        return temp;
    }
    protected static ArrayList getMostSignificantNeuronAsPredictionInHiddenLayer(LayerManager myLayerManager) {
        // Finding the most significant neuron in the output layer.
        double temp = 0.9;
        ArrayList<Integer> no= new ArrayList<Integer>();
        for (int i = 0; i < myLayerManager.listOfLayers.get(myLayerManager.listOfLayers.size()-2).listOfNeurons.size(); i++) {
            double val = myLayerManager.listOfLayers.get(myLayerManager.listOfLayers.size()-2).listOfNeurons.get(i).getActivation();
            if (val > temp) {
                no.add(i);
            }
        }
        return no;
    }

}
