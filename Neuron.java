import java.util.*;

public class Neuron implements Observable {

    public ArrayList<Observer> observerList = new ArrayList<Observer>();
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
       // This is the constructor of the `Neuron` class. It sets the activation of the neuron to a
       // random value, sets the bias of the neuron to a random value, sets the batch size to the batch
       // size of the parameter object, sets the bias learning rate to the bias learning rate of the
       // parameter object, and creates a new ArrayList of doubles called `biasChangeWishlist`.
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
        notifyObservers("The neuron "+neuronNum+" in layer " + layerNum +" has been updated by forward propagation");

    }

    protected double getDelta() {
        return delta;
    }

    protected void backwardPropagate() {
       // This is the code that is called when the neuron is being backpropagated. It first sets the
       // delta of the neuron, then backpropagates through all of the left connections, and then
       // changes the bias of the neuron.
        setDelta();
        for (Connection i : leftConnections) {
            i.backPropagate();
        }
        changeBias();
        notifyObservers("The neuron "+neuronNum+" in layer " + layerNum +" has been updated by backward propagation");

    }

    private void changeBias() {
    // This is the code that changes the bias of the neuron. It is called in the `backwardPropagate()`
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
        notifyObservers("The bias of the neuron "+neuronNum+" in layer " + layerNum +"  has been changed");

    }

    protected void setDelta() {
        this.delta = myBehaviour.setDelta(this);
        notifyObservers("The delta difference of the neuron "+neuronNum+" in layer " + layerNum +"  has been changed");

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

    }

    /**
     * For each observer in the observerList, call the update function of that observer, passing in the
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
    
        
    }


