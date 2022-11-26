public class InputLayer extends Layer {

    InputLayer(int numOfNeurons) {
        super(numOfNeurons);

    }

    protected void setInput(double[] inputLayerArray) {
        // This is setting the activation of each neuron in the input layer to the
        // corresponding value
        // in the input array.
        for (int i = 0; i <= inputLayerArray.length - 1; i++) {
            listOfNeurons.get(i).setActivation(inputLayerArray[i]);
        }
    }

    @Override
    protected void setBehaviour() {
        this.myBehaviour = InputLayerNeuronBehaviour.getInstance();

    }

}
