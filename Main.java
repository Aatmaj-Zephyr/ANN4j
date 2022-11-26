
public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World");
		parameter.setLayerArray(784,32,16,16,10);
		parameter.setTrainingFileReader("mnist_train.csv", "mnist"); 
		parameter.setTestingFileReader("mnist_test.csv", "mnist"); 
		parameter.setLearningRate(1);
		parameter.setBiasLearningRate(1);
		parameter.setEpsillion(0);
		parameter.setBatchsize(10);
		parameter.setRectificationFunction("sigmoid");
		Trainer myTrainer = new Trainer();
		myTrainer.train(5);
		NeuronObserver myNeuronObserver = new NeuronObserver();
		myNeuronObserver.setModel(myTrainer.getLayerManager());
		myNeuronObserver.addNeuronToBeObserved(1,31);

		myTrainer.test(2);
		myTrainer.relevancePropagate(2, 3);
		//myTrainer.forwardPropagatewithExclusion(2);


		

	}
}
