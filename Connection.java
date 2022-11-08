import java.util.ArrayList;

public class Connection {
   public Neuron leftNeuron;
   public Neuron rightNeuron;
   public double weight;
   public ArrayList<Double> changeWishlist;

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
        this.changeWishlist=new ArrayList<Double>();

    }

    public void initializeWeights() {
        this.weight = NN.getSmallSignedRandom();
    }

    public double calculateActivationForwardPropagation() {

        // Calculating the activation of the left neuron times the weight of the
        // connection.
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

        changeWishlist.add(this.weight + parameter.getLearningRate() * rightNeuron.getDelta() * leftNeuron.getActivation());
        if(LayerManager.batchCounter==0){
            this.weight= NN.average(changeWishlist);
            changeWishlist=new ArrayList<Double>();

        }
    }

    public double getBackwardWeightedSum() {
        // Calculating the gradient of the loss function with respect to the weight.
        return this.weight * rightNeuron.getDelta();
    }
}
