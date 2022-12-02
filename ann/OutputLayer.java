package ann;

public class OutputLayer extends Layer {

    OutputLayer(int numOfNeurons) {
        super(numOfNeurons);
    }

    @Override
    protected void setBehaviour() {
        this.myBehaviour = OutputLayerNeuronBehaviour.getInstance();

    }

}
