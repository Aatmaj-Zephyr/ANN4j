// Defining an interface for a NeuronBehaviour - sterategy paeetrn. To be implemented by behaviours of input layer, output layer and hidden layers 
package ann4j;

public interface NeuronBehaviour {

    /**
     * This function sets the delta value of the neuron
     * 
     * @param i The neuron that we are calculating the delta for.
     * @return The delta value of the neuron.
     */
    public double setDelta(Neuron i);

    /**
     * This function propagates the relevance from the output layer to the input
     * layer
     * 
     * @param neuron The neuron that is being propagated.
     */
    public void relevancePropagate(Neuron neuron);

}
