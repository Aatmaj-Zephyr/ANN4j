public class Trainer {
    private Trainer(){
        // Static class has no constructor.
    }
    public static void train(LayerManager myLayerManager, double[] actualLayer, double[] expectedLayer){


		myLayerManager.setInputLayer(actualLayer);
	
		myLayerManager.setExpectedOutputArray(expectedLayer);

		//System.out.print(myLayerManager);
        myLayerManager.forwardPropagate();
		//System.out.println(myLayerManager);
		System.out.println(LayerManager.ConnectionHeap);
        myLayerManager.backwardPropagate();
		//System.out.println(myLayerManager);
		System.out.println(LayerManager.ConnectionHeap);
		

    }
    
}
