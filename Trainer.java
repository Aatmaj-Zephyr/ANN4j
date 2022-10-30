public class Trainer {

    
    public void train(LayerManager myLayerManager, double[] actualLayer, double[] expectedLayer) {

        myLayerManager.setInputLayer(actualLayer);

        myLayerManager.setExpectedOutputArray(expectedLayer);
        // System.out.println(myLayerManager.listOfLayers.get(1));

        myLayerManager.forwardPropagate();
        // System.out.println(myLayerManager.listOfLayers.get(1));

        myLayerManager.backwardPropagate();

        int prediction = getMostSignificantNeuronAsPrediction(myLayerManager);
        System.out.println("prediction " + prediction);
       // System.out.println("value " + myLayerManager.OutputLayer.listOfNeurons.get(prediction));
        System.out.println("loss " + LayerManager.deltaDifferenced);
    //    System.out.println(myLayerManager.OutputLayer);

    }

    public static int getMostSignificantNeuronAsPrediction(LayerManager myLayerManager) {
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
