
public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		parameter.setMyInputFileReader("mnist_train.csv", "mnist"); 
		parameter.setParameters();
		parameter.setRectificationFunction("sigmoid");
		Trainer myTrainer = new Trainer();
		myTrainer.train(55553);

			// System.out.println(LayerManager.ConnectionHeap);

		

	}
}
