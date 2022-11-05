import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World");
		int[] layerArray = { 784, 16, 16, 10 };
		int batchsize=1;
		LayerManager myLayerManager = new LayerManager(layerArray,batchsize);
		// The new method must be outside the train method.

		
		// test drive
		// Training the network with random values.
		try {
			InputFileReader myMnistDataBaseFileReader = new MNISTDataBaseFileReader("mnist_test.csv"); // must
																												// not
																												// be in
																												// for
																												// loop
            Trainer myTrainer = new Trainer(myLayerManager,myMnistDataBaseFileReader);
			myTrainer.train(80);
			

			// System.out.println(LayerManager.ConnectionHeap);

		} catch (FileNotFoundException ex) {
		}

	}
}
