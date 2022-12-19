package ann4j;

public class OutputLayerNeuronBehaviour implements NeuronBehaviour {
    // A singleton pattern.
    private static OutputLayerNeuronBehaviour myInstance;

    // A private constructor for singleton pattern.
    private OutputLayerNeuronBehaviour() {
    }

    /**
     * If myInstance is null, create a new instance of OutputLayerNeuronBehaviour and assign it to
     * myInstance. Then return myInstance.
     * 
     * @return The instance of the class.
     */
    public static OutputLayerNeuronBehaviour getInstance() {
        if (myInstance == null) {
            myInstance = new OutputLayerNeuronBehaviour();
        }
        return myInstance;
    }

    @Override
    // Setting the value of delta. This is the delta rule for the output layer.
    public double setDelta(Neuron i) {
        double expectedValue = LayerManager.ExpectedOutputArrayList.get(i.getNeuronNum());
        double activation = i.getActivation();
        return activation * (1 - activation) * (expectedValue - activation);
    }

    @Override
   // This is the relevance propagation for the output layer.
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