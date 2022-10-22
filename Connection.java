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

    public String toString(){
     return "Neuron #" + leftNeuron.getLayerNum()+" and Neuron #" + rightNeuron.getLayerNum() + "are connected with weight" + weight;
    }

}
