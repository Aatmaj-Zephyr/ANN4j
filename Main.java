import java.util.*;
public class Main
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		int[] layerArray= {3,5,3};
		LayerManager myLayerManager = new LayerManager(layerArray);
		System.out.print(myLayerManager);
        myLayerManager.forwardPropagate();
		System.out.print(myLayerManager);
	}
}
