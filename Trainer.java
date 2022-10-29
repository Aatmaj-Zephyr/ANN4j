public class Trainer {
    private Trainer(){
        // Static class has no constructor.
    }
    public static void train(LayerManager myLayerManager, double[] actualLayer, double[] expectedLayer){


		myLayerManager.setInputLayer(actualLayer);
	
		myLayerManager.setExpectedOutputArray(expectedLayer);
	   
      
        myLayerManager.forwardPropagate();
        
        myLayerManager.backwardPropagate();

        System.out.println(myLayerManager.OutputLayer);	
	
    
    
		

    }
    
}
