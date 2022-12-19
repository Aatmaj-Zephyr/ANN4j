package ann4j;

import java.util.*;

public class Neuron implements Observable {

    public ArrayList<Observer> observerList = new ArrayList<Observer>();
    private double activation;
    public int neuronNum;
    public int layerNum = -1;
    public ArrayList<Connection> leftConnections = new ArrayList<Connection>(); // Connections of the left side of the
                                                                                // neuron
    public ArrayList<Connection> rightConnections = new ArrayList<Connection>(); // Connections of the right side of the
                                                                                 // neuron
    public double bias;
    public double delta;
    public NeuronBehaviour myBehaviour;
    private int batchCounter;
    private ArrayList<Double> biasChangeWishlist;
    private int batchSize;
    private double biasLearningRate;
    double relevance;
    private boolean isIncluded = true;

    /**
     * This function returns the activation of the neuron
     * 
     * @return The activation value of the neuron.
     */
    public double getActivation() {
        return activation;
    }

    /**
     * This function sets the activation of the neuron to the value passed in as a
     * parameter
     * 
     * @param activation The activation of the neuron.
     */
    public void setActivation(double activation) {
        this.activation = activation;
    }

    /**
     * This function sets the behaviour of the neuron to the behaviour passed in as
     * a parameter.
     * 
     * @param myBehaviour The behaviour of the neuron.
     */
    public void setBehaviour(NeuronBehaviour myBehaviour) {
        this.myBehaviour = myBehaviour;

    }

    // This is the constructor of the `Neuron` class. It sets the activation of the
    // neuron to a
    // random value, sets the bias of the neuron to a random value, sets the batch
    // size to the
    // batch size of the parameter object, sets the bias learning rate to the bias
    // learning rate of
    // the parameter object, and creates a new ArrayList of doubles called
    // `biasChangeWishlist`.
    public Neuron() {
        setActivation(NN.getRandom());
        bias = NN.setBias();
        batchSize = parameter.getBatchsize();
        biasLearningRate = parameter.getBiasLearningRate();
        biasChangeWishlist = new ArrayList<Double>();
    }

    /**
     * This function sets the layer number of the neuron
     * 
     * @param layerNum The number of layers in the neural network.
     */
    public void setLayerNum(int layerNum) {
        this.layerNum = layerNum;

    }

    /**
     * This function returns the layer number of the neuron
     * 
     * @return The layerNum variable is being returned.
     */
    public int getLayerNum() {
        return layerNum;
    }

    /**
     * This function sets the number of neurons in the layer
     * 
     * @param neuronNum The number of neurons in the layer.
     */
    public void setNeuronNum(int neuronNum) {
        this.neuronNum = neuronNum;

    }

    /**
     * This function returns the number of neurons in the layer
     * 
     * @return The number of neurons in the layer.
     */
    public int getNeuronNum() {
        return neuronNum;
    }

    /**
     * This function adds a connection to the leftConnections list.
     * 
     * @param connection The connection to add to the list of connections.
     */
    public void addLeftConnections(Connection connection) {
        leftConnections.add(connection);
    }

    /**
     * This function adds a connection to the rightConnections list
     * 
     * @param connection The connection to add to the list of connections.
     */
    public void addRightConnections(Connection connection) {
        rightConnections.add(connection);
    }

    /**
     * It calculates the weighted sum of the left connections of the neuron
     * 
     * @return The weighted sum of the left connections.
     */
    public double getWeightedSum() {
        double sum = 0;
        // Iterating through the leftConnections ArrayList and adding the activation of
        // each connection to the sum.
        for (Connection i : leftConnections) {
            if (i.leftNeuron.isIncluded == true) {
                sum += i.calculateActivationForwardPropagation();
            }

        }

        return sum;
    }

    /**
     * This function returns the bias of the neuron
     * 
     * @return The bias of the neuron.
     */
    public double getBias() {
        return bias;
    }

    /**
     * The activation of the neuron is set to the rectified value of the weighted
     * sum of the left
     * connections plus the bias
     */
    public void forwardPropagate() {
        // Writer.writeln("Forward propagating in neuron # "+this.neuronNum+" in
        // layer number "+this.layerNum); //test code
        // Setting the activation of the neuron to the rectified value of the weighted
        // sum of the left connections plus the bias.
        this.activation = parameter.rectify(getWeightedSum() + getBias());
        notifyObservers(
                "The neuron " + neuronNum + " in layer " + layerNum + " has been updated by forward propagation");

    }

    /**
     * This function returns the value of the delta variable
     * 
     * @return The value of the variable delta.
     */
    public double getDelta() {
        return delta;
    }

    /**
     * The neuron first sets its delta, then backpropagates through all of its left
     * connections, and
     * then changes its bias
     */
    public void backwardPropagate() {
        // This is the code that is called when the neuron is being backpropagated. It
        // first sets the
        // delta of the neuron, then backpropagates through all of the left connections,
        // and then
        // changes the bias of the neuron.
        setDelta();
        for (Connection i : leftConnections) {
            i.backPropagate();
        }
        changeBias();
        notifyObservers(
                "The neuron " + neuronNum + " in layer " + layerNum + " has been updated by backward propagation");

    }

    /**
     * The bias of the neuron is changed by adding the bias learning rate multiplied
     * by the delta of
     * the neuron to the bias change wishlist. If the batch counter is equal to the
     * batch size, the
     * batch counter is reset to 0. If the batch counter is equal to 0, the bias is
     * changed to the
     * average of the bias change wishlist. The bias change wishlist is then reset
     * to an empty
     * arraylist
     */
    private void changeBias() {
        // This is the code that changes the bias of the neuron. It is called in the
        // `backwardPropagate()`
        // function.
        batchCounter++;
        biasChangeWishlist.add(biasLearningRate * this.getDelta());

        if (batchCounter == batchSize) {
            batchCounter = 0;
        }
        if (batchCounter == 0) {
            this.bias = NN.average(biasChangeWishlist);
            biasChangeWishlist = new ArrayList<Double>();
        }
        notifyObservers("The bias of the neuron " + neuronNum + " in layer " + layerNum + "  has been changed");

    }

    /**
     * The delta difference of the neuron in layer has been changed
     */
    public void setDelta() {
        this.delta = myBehaviour.setDelta(this);
        notifyObservers(
                "The delta difference of the neuron " + neuronNum + " in layer " + layerNum + "  has been changed");

    }

    /**
     * The toString() function returns a string representation of the object
     * 
     * @return The activation of the neuron.
     */
    public String toString() {
        return "Neuron #" + neuronNum + " has activation " + activation + "\n";
    }

    /**
     * The function `relevancePropagate()` is called on the `myBehaviour` object,
     * which is of type
     * `Behaviour`, and the `this` object is passed as an argument
     */
    public void relevancePropagate() {
        myBehaviour.relevancePropagate(this);

    }

    /**
     * For each observer in the observerList, call the update function of that
     * observer, passing in the
     * info and this neuron, which will be typecasted later.
     * 
     * @param info The information that is being passed to the observers.
     */
    @Override
    public void notifyObservers(String info) {
        for (Observer i : observerList) {
            i.update(info, this);
        }
    }

    @Override
    // Adding an observer to the observerList.
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    /**
     * This function sets the value of the isIncluded variable to false, after which
     * it wont be included in forward propagation
     */
    public void exclude() {
        isIncluded = false;
    }

    /**
     * This function sets the isIncluded variable to true after which it will be
     * included in forward propagation
     */
    public void include() {
        isIncluded = true;
    }

    @Override
    // Removing the observer from the observer list.
    public void deregisterObserver(NeuronObserver neuronObserver) {
        observerList.remove(neuronObserver);

    }

}
