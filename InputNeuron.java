class InputNeuron extends Neuron {

    InputNeuron(double value) {
        super(value);
    }

    void setLeftLayer(Layer leftConnections){
        throw new RuntimeException("Cannot add left connection to input neuron");
    }

}