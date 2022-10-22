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
        LayerManager.ConnectionHeap.add(this);
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
     return "Neuron #" + leftNeuron.getNeuronNum()+" in layer #"+leftNeuron.getLayerNum()+ " and Neuron #" + rightNeuron.getNeuronNum() + " in Layer #"+rightNeuron.getLayerNum()+" are connected with weight" + weight+"\n";
    }
    public void backPropogate() {
    }

}
