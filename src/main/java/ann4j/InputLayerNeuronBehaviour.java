package ann4j;

public class InputLayerNeuronBehaviour implements NeuronBehaviour {
    private static InputLayerNeuronBehaviour myInstance;

    // A private constructor. It is used to make sure that the class cannot be instantiated.
    private InputLayerNeuronBehaviour() {
    }

    /**
     * (singleton pattern) If the instance of the class is null, create a new instance of the class and return it
     * 
     * @return The instance of the class.
     */
    public static InputLayerNeuronBehaviour getInstance() {
        if (myInstance == null) {
            myInstance = new InputLayerNeuronBehaviour();
        }
        return myInstance;
    }

    @Override
   // Calculating the delta for the output layer.
    public double setDelta(Neuron i) {
        // Calculating the delta for the output layer.
        double activation = i.getActivation();
        double weightedSum = 0;
        for (Connection j : i.rightConnections) {
            weightedSum += j.getBackwardWeightedSum();
        }
        return weightedSum * activation * (1 - activation);
    }

    @Override
   // The above code is calculating the relevance of the neuron.
    public void relevancePropagate(Neuron neuron) {
        // Calculating the relevance of the neuron.

        neuron.relevance = 0;
        for (Connection i : neuron.rightConnections) {
            // Calculating the numerator of the relevance equation.
            double numerator = neuron.getActivation() * i.getWeight() * i.rightNeuron.relevance;
            double denominator = 0;
            // Calculating the denominator of the relevance equation.
            for (Connection k : i.rightNeuron.leftConnections) {
                denominator += k.leftNeuron.getActivation() * k.getWeight();
            }
            // Calculating the final relevance of the neuron.
            neuron.relevance += numerator / (denominator + parameter.getEpsillion());
        }

        // This is a rectification function. It is used to make sure that the relevance
        // is not negative.
        neuron.relevance = parameter.rectify(neuron.relevance);

    }

}