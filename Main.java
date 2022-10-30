import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World");
		int[] layerArray = { 784, 16, 16, 10 };
		LayerManager myLayerManager = new LayerManager(layerArray);
		// The new method must be outside the train method.

		double[] inputLayer;
		double[] expectedLayer;
		double label;
		// test drive
		// Training the network with random values.
		try {
			InputFileReader myMnistDataBaseFileReader = new MNISTDataBaseFileReader("mnist_test.csv"); // must
																												// not
																												// be in
																												// for
																												// loop
            Trainer myTrainer = new Trainer();
			for (int i = 0; i <= 700; i++) {

				myMnistDataBaseFileReader.next();
				expectedLayer = myMnistDataBaseFileReader.getExpectedOutputArray();
				inputLayer = myMnistDataBaseFileReader.getInputArray();
				label = myMnistDataBaseFileReader.getLabel();
				System.out.println("actuall " + label);
				myTrainer.train(myLayerManager, inputLayer, expectedLayer);

			}

			// System.out.println(LayerManager.ConnectionHeap);

		} catch (FileNotFoundException ex) {
		}

	}
}
