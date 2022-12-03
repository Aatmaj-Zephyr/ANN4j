package src;

public class OutputLayer extends Layer {

    OutputLayer(int numOfNeurons) {
        super(numOfNeurons);
    }

    @Override
    public void setBehaviour() {
        this.myBehaviour = OutputLayerNeuronBehaviour.getInstance();

    }

}
