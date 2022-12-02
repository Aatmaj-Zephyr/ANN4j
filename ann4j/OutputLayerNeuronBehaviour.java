package ann4j;

public class OutputLayerNeuronBehaviour implements NeuronBehaviour {
    private static OutputLayerNeuronBehaviour myInstance;

    private OutputLayerNeuronBehaviour() {
    }

    protected static OutputLayerNeuronBehaviour getInstance() {
        if (myInstance == null) {
            myInstance = new OutputLayerNeuronBehaviour();
        }
        return myInstance;
    }

    @Override
    public double setDelta(Neuron i) {
        double expectedValue = LayerManager.ExpectedOutputArrayList.get(i.getNeuronNum());
        double activation = i.getActivation();
        return activation * (1 - activation) * (expectedValue - activation);
    }

    @Override
    public void relevancePropagate(Neuron neuron) {
        // Setting the relevance of the neuron to its activation.
        // This is a check to see if the neuron is the one that is the label. If it is,
        // then the relevance is set to the activation.
        if (LayerManager.ExpectedOutputArrayList.get(neuron.getNeuronNum()) == LayerManager
                .getNeuronNumberToBeTestedinRelavancePropagation()) {
            neuron.relevance = neuron.getActivation();
        } else {
            neuron.relevance = 0;
        }

    }

}