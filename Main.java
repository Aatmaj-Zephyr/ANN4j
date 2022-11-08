
public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		parameter.setTrainingFileReader("mnist_train.csv", "mnist"); 
		parameter.setTestingFileReader("mnist_test.csv", "mnist"); 
		parameter.setLayerArray(784,16,16,10);
		parameter.setLearningRate(1);
		parameter.setBiasLearningRate(1);

		parameter.setBatchsize(1);
		parameter.setRectificationFunction("sigmoid");
		Trainer myTrainer = new Trainer();
		myTrainer.train(55053);
		myTrainer.test(9000);

			// System.out.println(LayerManager.ConnectionHeap);

		

	}
}
