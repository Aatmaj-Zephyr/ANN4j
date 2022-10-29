import java.util.*;
public class Main
{   
	public static void main(String[] args) {
		System.out.println("Hello World");
		int[] layerArray= {3,5,3};
		double [] expectedLayer = {1,0,0};
		LayerManager myLayerManager = new LayerManager(layerArray);
		System.out.print(myLayerManager);
        myLayerManager.forwardPropagate();
		System.out.println(myLayerManager);
		System.out.println(LayerManager.ConnectionHeap);
		System.out.println(LossCalculator.calculateLossFunction(myLayerManager.getOutputLayer(),expectedLayer));

	}
}
