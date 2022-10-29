
public class Main
{   
	public static void main(String[] args) {
		System.out.println("Hello World");
		int[] layerArray= {3,5,3};
		LayerManager myLayerManager = new LayerManager(layerArray);
		//The new method must be outside the train method.


		double [] actualLayer = {1,0,0};
		double [] expectedLayer = {1,0,0};
		Trainer.train(myLayerManager, actualLayer, expectedLayer);
		Trainer.train(myLayerManager, actualLayer, expectedLayer);

	}
}
