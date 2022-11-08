
public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		parameter.setTrainingFileReader("mnist_train.csv", "mnist"); 
		parameter.setTestingFileReader("mnist_test.csv", "mnist"); 
		parameter.setParameters();
		parameter.setRectificationFunction("sigmoid");
		Trainer myTrainer = new Trainer();
		myTrainer.train(55553);
		myTrainer.test(9000);

			// System.out.println(LayerManager.ConnectionHeap);

		

	}
}
