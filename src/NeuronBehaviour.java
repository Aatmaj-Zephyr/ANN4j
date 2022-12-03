// Defining an interface for a NeuronBehaviour - sterategy paeetrn. To be implemented by behaviours of input layer, output layer and hidden layers 
package src;
public interface NeuronBehaviour {

    public double setDelta(Neuron i);
    public void relevancePropagate(Neuron neuron);

}
