import ann4j.*;

public class Main {
	public static void main(String[] args) {
		// Setting the output file to be output.txt and enabling command line logging
		parameter.setOutputFile("output.txt", true);
		// Setting the number of neurons in each layer.
		parameter.setLayerArray(784, 32, 16, 16, 26);

		// Setting the training file to be emnist-letters-train.csv and the file type to
		// be mnist.
		parameter.setTrainingFileReader("emnist-letters-train.csv", "mnist");
		// Setting the testing file to be emnist-letters-test.csv and the file type to
		// be mnist.
		parameter.setTestingFileReader("emnist-letters-test.csv", "mnist");

		// Setting the learning rate to 1.
		parameter.setLearningRate(1);
		// Setting the learning rate for the bias to 1.
		parameter.setBiasLearningRate(1);
		// Setting the epsillion value for the relevance propagation algorithm.
		parameter.setEpsillion(0);
		// Setting the batch size to 10.
		parameter.setBatchsize(10);
		// Setting the rectification function to be sigmoid.
		parameter.setRectificationFunction("sigmoid");

		// Creating a new instance of the Trainer class.
		Trainer myTrainer = new Trainer();
		// Training the network with 88800 samples for 10 epochs
		myTrainer.train(88800, 10);
		// Creating a new instance of the NeuronObserver class this class will observe
		// the neurons and respond when every parameter is changed.
		NeuronObserver myNeuronObserver = new NeuronObserver();
		// Setting the model for the neuron observer.
		myNeuronObserver.setModel(myTrainer.getLayerManager());
		// Testing the network with 9990 samples.
		myTrainer.test(9990);

		// Adding the neuron at layer 1 and index 31 to be observed.
		myNeuronObserver.addNeuronToBeObserved(1, 31);
		// myTrainer.printConfusionMatrix();
		myTrainer.test(2);

		// It clears the neurons that are being observed.
		myNeuronObserver.clear();
		myNeuronObserver.addNeuronToBeObserved(2, 0);

		myTrainer.test(2);
		// xai algorithm for relevance propagation.
		myTrainer.relevancePropagate(2, 3);
		// xai algotithm for most significant input neurons
		myTrainer.forwardPropagatewithExclusionInputLayerOnKSamples(2);
		// It prints the parameters to the output file. //must be at end of file
		parameter.display();
	}
}
