import javax.management.RuntimeErrorException;

public class OutputNeuron extends Neuron{
    OutputNeuron(double value) {
        super(value);
    }

    void setRightLayer(Layer rightConnections){
        throw new RuntimeException("Cannot add right connection to input neuron");
    }
}
