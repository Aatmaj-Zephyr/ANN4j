package ann4j;
import java.util.ArrayList;

 public class InputLayer extends Layer {

    // Calling the constructor of the super class.
    InputLayer(int numOfNeurons) {
        super(numOfNeurons);

    }

   /**
    * This is setting the activation of each neuron in the input layer to the corresponding value in
    * the input array
    * 
    * @param inputLayerArray This is the array of inputs that you want to set the input layer to.
    */
    public void setInput(ArrayList<Double> inputLayerArray) {
        // This is setting the activation of each neuron in the input layer to the
        // corresponding value
        // in the input array.
        for (int i = 0; i <= inputLayerArray.size() - 1; i++) {
            listOfNeurons.get(i).setActivation(inputLayerArray.get(i));
        }
    }

    @Override
    // This is setting the behaviour of the neurons in the input layer to the behaviour of the input
    // layer neurons.
    public void setBehaviour() {
        this.myBehaviour = InputLayerNeuronBehaviour.getInstance();

    }

}
