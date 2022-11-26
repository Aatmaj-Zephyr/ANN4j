import java.util.*;

public class Neuron implements Observable {
    private double activation;
    protected int neuronNum;
    protected int layerNum = -1;
    protected ArrayList<Connection> leftConnections = new ArrayList<Connection>();
    protected ArrayList<Connection> rightConnections = new ArrayList<Connection>();
    protected double bias;
    protected double delta;
    protected NeuronBehaviour myBehaviour;
    private int batchCounter;
    private ArrayList<Double> biasChangeWishlist;
    private int batchSize;
    private double biasLearningRate;
    double relevance;

    protected double getActivation() {
        return activation;
    }

    protected void setActivation(double activation) {
        this.activation = activation;
    }

    protected void setBehaviour(NeuronBehaviour myBehaviour) {
        this.myBehaviour = myBehaviour;

    }

    protected Neuron() {
        setActivation(NN.getRandom());
        bias = NN.setBias();
        batchSize = parameter.getBatchsize();
        biasLearningRate = parameter.getBiasLearningRate();
        biasChangeWishlist = new ArrayList<Double>();
    }

    protected void setLayerNum(int layerNum) {
        this.layerNum = layerNum;

    }

    protected int getLayerNum() {
        return layerNum;
    }

    protected void setNeuronNum(int neuronNum) {
        this.neuronNum = neuronNum;

    }

    protected int getNeuronNum() {
        return neuronNum;
    }

    protected void addLeftConnections(Connection connection) {
        leftConnections.add(connection);
    }

    protected void addRightConnections(Connection connection) {
        rightConnections.add(connection);
    }

    protected double getWeightedSum() {
        double sum = 0;
        // Iterating through the leftConnections ArrayList and adding the activation of
        // each connection to the sum.
        for (Connection i : leftConnections) {
            if (i.leftNeuron.getLayerNum() == 0 & i.leftNeuron.getNeuronNum() == LayerManager.numtobeExcluded) {

            } else {
                sum += i.calculateActivationForwardPropagation();
            }

        }

        return sum;
    }

    protected double getBias() {
        return bias;
    }

    protected void forwardPropagate() {
        // System.out.println("Forward propagating in neuron # "+this.neuronNum+" in
        // layer number "+this.layerNum); //test code
        // Setting the activation of the neuron to the rectified value of the weighted
        // sum of the left connections plus the bias.
        this.activation = parameter.rectify(getWeightedSum() + getBias());
        notifyObservers("The neuron has been updated by forward propagation");

    }

    protected double getDelta() {
        return delta;
    }

    protected void backwardPropagate() {
        setDelta();
        for (Connection i : leftConnections) {
            i.backPropagate();
        }
        changeBias();
        notifyObservers("The neuron has been updated by backward propagation");

    }

    private void changeBias() {
        batchCounter++;
        biasChangeWishlist.add(biasLearningRate * this.getDelta());

        if (batchCounter == batchSize) {
            batchCounter = 0;
        }
        if (batchCounter == 0) {
            this.bias = NN.average(biasChangeWishlist);
            biasChangeWishlist = new ArrayList<Double>();
        }
        notifyObservers("The bias of the neuron has been changed");

    }

    protected void setDelta() {
        this.delta = myBehaviour.setDelta(this);
        notifyObservers("The delta difference of the neuron has been changed");

    }

    public String toString() {
        return "Neuron #" + neuronNum + " has activation " + activation + "\n";
    }

    /**
     * > The function `relevancePropagate()` is called on the `myBehaviour` object,
     * which is of type
     * `Behaviour`, and the `this` object is passed as an argument
     */
    protected void relevancePropagate() {
        myBehaviour.relevancePropagate(this);
        notifyObservers("Relevance value of the euron has been updated.");

    }

    @Override
    public void notifyObservers(String info) {
        for (Observer i : observerList) {
            i.update(info, this);
        }
    }

}
