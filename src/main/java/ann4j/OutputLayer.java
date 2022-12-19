package ann4j;

public class OutputLayer extends Layer {

    // Calling the constructor of the super class.
    OutputLayer(int numOfNeurons) {
        super(numOfNeurons);
    }

    @Override
   // Setting the behaviour of the neurons in the output layer.
    public void setBehaviour() {
        this.myBehaviour = OutputLayerNeuronBehaviour.getInstance();

    }

}
