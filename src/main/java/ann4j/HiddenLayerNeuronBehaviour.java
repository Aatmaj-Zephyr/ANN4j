package ann4j;

public class HiddenLayerNeuronBehaviour implements NeuronBehaviour {
    private static HiddenLayerNeuronBehaviour myInstance;

   // A private constructor.
    private HiddenLayerNeuronBehaviour() {
    }

   /**
    * The function returns an instance of the class if one exists, otherwise it creates a new
    * instance and returns it
    * 
    * @return The instance of the HiddenLayerNeuronBehaviour class.
    */
    public static HiddenLayerNeuronBehaviour getInstance() {
        if (myInstance == null) {
            myInstance = new HiddenLayerNeuronBehaviour();
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
    // Calculating the relevance of the neuron.
    public void relevancePropagate(Neuron neuron) {
        // Calculating the relevance of the neuron.
        neuron.relevance = 0;
        for (Connection i : neuron.rightConnections) {
            // Calculating the relevance of the neuron.
            double numerator = neuron.getActivation() * i.getWeight() * i.rightNeuron.relevance;
            double denominator = 0;
            // Calculating the denominator of the relevance equation.
            for (Connection k : i.rightNeuron.leftConnections) {
                denominator += k.leftNeuron.getActivation() * k.getWeight();
            }
            // Calculating the relevance of the neuron.
            neuron.relevance += numerator / (denominator + parameter.getEpsillion());
        }
        neuron.relevance = parameter.rectify(neuron.relevance);
    }

}