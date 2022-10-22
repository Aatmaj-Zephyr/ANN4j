public class Connection {
    Neuron leftNeuron;
    Neuron rightNeuron;
    double weight;
    Connection(Neuron leftNeuron,Neuron rightNeuron) {
        this.leftNeuron = leftNeuron;
        this.rightNeuron = rightNeuron;
        leftNeuron.addLeftConnections(this);
        rightNeuron.addRightConnections(this);
        initializeWeights();
    }
    private void initializeWeights(){
        weight = NN.getSmallSignedRandom();
    }
    public double calculateActivationForwardPropagation(){
        return leftNeuron.getActivation()*weight;
    }
    public double calculateActivationBackPropagation(){
        return rightNeuron.getActivation()*weight;
    }

}
