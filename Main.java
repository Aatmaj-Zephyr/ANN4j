import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World");
		int[] layerArray = { 784, 16, 16, 10 };
		LayerManager myLayerManager = new LayerManager(layerArray);
		// The new method must be outside the train method.

		
		// test drive
		// Training the network with random values.
		try {
			InputFileReader myMnistDataBaseFileReader = new MNISTDataBaseFileReader("mnist_train.csv"); // must
																												// not
																												// be in
																												// for
																												// loop
            Trainer myTrainer = new Trainer(myLayerManager,myMnistDataBaseFileReader);
			myTrainer.train(55555);
			

			// System.out.println(LayerManager.ConnectionHeap);

		} catch (FileNotFoundException ex) {
		}

	}
}
