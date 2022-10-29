public class Connection {
    Neuron leftNeuron;
    Neuron rightNeuron;
    double weight;
    private double oldWeight = 0;
    Connection(Neuron leftNeuron,Neuron rightNeuron) {
        // This is the constructor for the Connection class. It is setting the left and right neurons
        // for the connection, adding the connection to the left and right neurons, initializing the
        // weight, and adding the connection to the connection heap.
        
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
     return "Neuron #" + leftNeuron.getNeuronNum()+" in layer #"+leftNeuron.getLayerNum()+ " and Neuron #" + rightNeuron.getNeuronNum() + " in Layer #"+rightNeuron.getLayerNum()+" are connected with weight " + weight+"\n";
    }
    public void backPropogate() {
       // This is the backpropogation algorithm. It is calculating the gradient of the loss function
       // with respect to the weight. It then updates the weight by subtracting the learning rate times
       // the gradient.
        double gradient = calculateGradient();
        oldWeight = weight;
        weight = weight - LayerManager.learningRate * gradient;
    }
    public double calculateGradient(){
        return (oldWeight - weight)/(LayerManager.oldLossFunction-LayerManager.lossFunction);
    }
}
