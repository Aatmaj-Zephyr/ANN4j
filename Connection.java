import java.util.ArrayList;

public class Connection {
   public Neuron leftNeuron;
   public Neuron rightNeuron;
   private double weight;
   public ArrayList<Double> changeWishlist;
private int batchCounter;
private double learningRate;  //avoid using static parameters as they incerase time complexity.
private int batchSize; //avoid using static parameters as they incerase time complexity.

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
        batchSize=parameter.getBatchsize();
        learningRate=parameter.getLearningRate();

    }
    public double getWeight() {
        return weight;
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

        changeWishlist.add(this.weight + learningRate * rightNeuron.getDelta() * leftNeuron.getActivation());
        batchCounter++;
        if(batchCounter==batchSize){
            batchCounter=0;
        }
        if(batchCounter==0){
            this.weight= NN.average(changeWishlist);
            changeWishlist=new ArrayList<Double>();

        }
    }

    public double getBackwardWeightedSum() {
        // Calculating the gradient of the loss function with respect to the weight.
        //USed in class neuron for calculation of delta.
        return this.weight * rightNeuron.getDelta();
    }
}
