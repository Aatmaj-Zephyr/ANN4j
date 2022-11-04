import java.util.ArrayList;

public class Connection {
    Neuron leftNeuron;
    Neuron rightNeuron;
    double weight;
    private ArrayList<Double> weightBuffer = new ArrayList<Double>();

    Connection(Neuron leftNeuron, Neuron rightNeuron) {
        // This is the constructor for the Connection class. It is setting the left and
        // right neurons
        // for the connection, adding the connection to the left and right neurons,
        // initializing the
        // weight, and adding the connection to the connection heap.

        this.leftNeuron = leftNeuron;
        this.rightNeuron = rightNeuron;
        leftNeuron.addRightConnections(this);
        rightNeuron.addLeftConnections(this);
        initializeWeights();
    }

    private void initializeWeights() {
        this.weight = NN.getSmallSignedRandom();
    }

    public double calculateActivationForwardPropagation() {
        // System.out.println(leftNeuron.getActivation()*weight);
        // System.out.println(leftNeuron);
        return leftNeuron.getActivation() * this.weight;
    }

    public String toString() {
        return "Neuron #" + leftNeuron.getNeuronNum() + " in layer #" + leftNeuron.getLayerNum() + " and Neuron #"
                + rightNeuron.getNeuronNum() + " in Layer #" + rightNeuron.getLayerNum() + " are connected with weight "
                + weight + "\n";
    }

    public void backPropagate() {
        // This is the backpropagation algorithm. It is calculating the gradient of the
        // loss function
        // with respect to the weight. It then updates the weight by subtracting the
        // learning rate times
        // the gradient.
        
            weightBuffer.add(this.weight - LayerManager.learningRate * calculateGradient());
        if(LayerManager.change%LayerManager.batchsize==0){
            this.weight=NN.average(weightBuffer);
            weightBuffer.clear();
        }
        //this.weight = this.weight - LayerManager.learningRate * calculateGradient();
    }

    public double calculateGradient() {
        // System.out.println(LayerManager.deltaDifferenced);

        return LayerManager.deltaDifferenced * leftNeuron.getActivation();
    }
}
