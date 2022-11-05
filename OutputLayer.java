public class OutputLayer extends Layer {

    OutputLayer(int numOfNeurons) {
        super(numOfNeurons);
    }

    @Override
    public void calculateDelta(Neuron i) {
        double expectedValue = LayerManager.ExpectedOutputArray[i.getNeuronNum()];
        double activation = i.getActivation();
        i.delta= activation * (1 - activation) * (expectedValue - activation);
    }

}
