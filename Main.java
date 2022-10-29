
public class Main
{   
	public static void main(String[] args) {
		System.out.println("Hello World");
		int[] layerArray= {2,2,1};
		LayerManager myLayerManager = new LayerManager(layerArray);
		//The new method must be outside the train method.


		double [] inputLayer = {2,3};
		double [] expectedLayer = {1};
		Trainer.train(myLayerManager, inputLayer, expectedLayer);
		Trainer.train(myLayerManager, inputLayer, expectedLayer);

		//test drive
		// Training the network with random values.
		for(int i=0;i<=10;i++){
			inputLayer[0]=NN.getRandom();
			inputLayer[1]=NN.getRandom();
			Trainer.train(myLayerManager, inputLayer, expectedLayer);
		}

	}
}
