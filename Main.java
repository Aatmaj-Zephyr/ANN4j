import ann4j.*;

public class Main {
	public static void main(String[] args) {
		parameter.setOutputFile("output.txt",true);
		parameter.setLayerArray(784, 32, 16, 16, 26);
		parameter.setTrainingFileReader("emnist-letters-train.csv", "mnist");
		parameter.setTestingFileReader("emnist-letters-test.csv", "mnist");
		
		parameter.setLearningRate(1);
		parameter.setBiasLearningRate(1);
		parameter.setEpsillion(0);
		parameter.setBatchsize(10);
		parameter.setRectificationFunction("sigmoid");

		Trainer myTrainer = new Trainer();
		myTrainer.train(88800,1);
		NeuronObserver myNeuronObserver = new NeuronObserver();
		myNeuronObserver.setModel(myTrainer.getLayerManager());
		myTrainer.test(9990);

		myNeuronObserver.addNeuronToBeObserved(1, 31);
		//myTrainer.printConfusionMatrix();
		myTrainer.test(2);

		myNeuronObserver.clear();
		myNeuronObserver.addNeuronToBeObserved(2, 0);

		myTrainer.test(2);
		//myTrainer.relevancePropagate(2, 3);
		//myTrainer.forwardPropagatewithExclusionInputLayerOnKSamples(2);
		parameter.display();
	}
}
