public class Trainer {
    private Trainer(){
        // Static class has no constructor.
    }
    public static void train(LayerManager myLayerManager, double[] actualLayer, double[] expectedLayer){


		myLayerManager.setInputLayer(actualLayer);
	
		myLayerManager.setExpectedOutputArray(expectedLayer);
        System.out.println(myLayerManager.OutputLayer);	

        myLayerManager.forwardPropagate();
        System.out.println(myLayerManager.OutputLayer);	


        myLayerManager.backwardPropagate();
        
       


        

        	
        int prediction = getMostSignificantNeuronAsPrediction(myLayerManager);
        System.out.println("prediction "+ prediction);
        System.out.println("value "+ myLayerManager.OutputLayer.listOfNeurons.get(prediction));

    }
    public static int getMostSignificantNeuronAsPrediction(LayerManager myLayerManager){
        double temp=0;
        int no=0;
        for(int i = 0 ; i<myLayerManager.OutputLayer.listOfNeurons.size();i++ ){
            double val = myLayerManager.OutputLayer.listOfNeurons.get(i).activation;
            if(val>temp){
                no=i;
                temp=val;
            }
        }
        return no;
    }
    
}
