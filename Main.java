import java.io.FileNotFoundException;

public class Main
{   
	public static void main(String[] args) {
		System.out.println("Hello World");
		int[] layerArray= {784,2,10};
		LayerManager myLayerManager = new LayerManager(layerArray);
		//The new method must be outside the train method.


		double [] inputLayer = {2,3};
		double [] expectedLayer = {1};
		double label;
		//test drive
		// Training the network with random values.
		for(int i=0;i<=1;i++){
			try{
				MNISTDataBaseFileReader myMnistDataBaseFileReader = new MNISTDataBaseFileReader("mnist_test.csv");
				myMnistDataBaseFileReader.next();
				expectedLayer= myMnistDataBaseFileReader.getExpectedOutputArray();
				inputLayer= myMnistDataBaseFileReader.getInputArray();
				Trainer.train(myLayerManager, inputLayer, expectedLayer);
				label=myMnistDataBaseFileReader.getLabel();
				
			}
			
				catch(FileNotFoundException ex){}
			
		}
		
	
        
	}
}
