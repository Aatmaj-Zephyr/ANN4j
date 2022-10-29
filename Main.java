import java.util.*;
public class Main
{   
	public static void main(String[] args) {
		System.out.println("Hello World");
		int[] layerArray= {3,5,3};
		LayerManager myLayerManager = new LayerManager(layerArray);


		double [] actualLayer = {1,0,0};
		myLayerManager.setInputLayer(actualLayer);


		double [] expectedLayer = {1,0,0};
		myLayerManager.setExpectedOutputArray(expectedLayer);

		System.out.print(myLayerManager);
        myLayerManager.forwardPropagate();
		System.out.println(myLayerManager);
		System.out.println(LayerManager.ConnectionHeap);
        myLayerManager.backwardPropagate();
		System.out.println(myLayerManager);
		System.out.println(LayerManager.ConnectionHeap);


	}
}
