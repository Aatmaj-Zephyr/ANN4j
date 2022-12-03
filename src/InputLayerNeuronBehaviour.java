package src;

public class InputLayerNeuronBehaviour implements NeuronBehaviour {
    private static InputLayerNeuronBehaviour myInstance;

    private InputLayerNeuronBehaviour() {
    }

    public static InputLayerNeuronBehaviour getInstance() {
        if (myInstance == null) {
            myInstance = new InputLayerNeuronBehaviour();
        }
        return myInstance;
    }

    @Override
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