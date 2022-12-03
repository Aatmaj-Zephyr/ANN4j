package src; 
import java.util.*;

public class Neuron implements Observable {

    public ArrayList<Observer> observerList = new ArrayList<Observer>();
    private double activation;
    public int neuronNum;
    public int layerNum = -1;
    public ArrayList<Connection> leftConnections = new ArrayList<Connection>();
    public ArrayList<Connection> rightConnections = new ArrayList<Connection>();
    public double bias;
    public double delta;
    public NeuronBehaviour myBehaviour;
    private int batchCounter;
    private ArrayList<Double> biasChangeWishlist;
    private int batchSize;
    private double biasLearningRate;
    double relevance;
    private boolean isIncluded=true;

    public double getActivation() {
        return activation;
    }

    public void setActivation(double activation) {
        this.activation = activation;
    }

    public void setBehaviour(NeuronBehaviour myBehaviour) {
        this.myBehaviour = myBehaviour;

    }

    public Neuron() {
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

    public void setLayerNum(int layerNum) {
        this.layerNum = layerNum;

    }

    public int getLayerNum() {
        return layerNum;
    }

    public void setNeuronNum(int neuronNum) {
        this.neuronNum = neuronNum;

    }

    public int getNeuronNum() {
        return neuronNum;
    }

    public void addLeftConnections(Connection connection) {
        leftConnections.add(connection);
    }

    public void addRightConnections(Connection connection) {
        rightConnections.add(connection);
    }

    public double getWeightedSum() {
        double sum = 0;
        // Iterating through the leftConnections ArrayList and adding the activation of
        // each connection to the sum.
        for (Connection i : leftConnections) {
            if (i.leftNeuron.isIncluded==true) {
                sum += i.calculateActivationForwardPropagation();
            }

        }

        return sum;
    }

    public double getBias() {
        return bias;
    }

    public void forwardPropagate() {
        // Writer.writeln("Forward propagating in neuron # "+this.neuronNum+" in
        // layer number "+this.layerNum); //test code
        // Setting the activation of the neuron to the rectified value of the weighted
        // sum of the left connections plus the bias.
        this.activation = parameter.rectify(getWeightedSum() + getBias());
        notifyObservers("The neuron "+neuronNum+" in layer " + layerNum +" has been updated by forward propagation");

    }

    public double getDelta() {
        return delta;
    }

    public void backwardPropagate() {
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

    public void setDelta() {
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
    public void relevancePropagate() {
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

   
    public void exclude() {
        isIncluded=false;
    }

    public void include() {
        isIncluded=true;
    }

    @Override
    public void deregisterObserver(NeuronObserver neuronObserver) {
       observerList.remove(neuronObserver);
        
    }
    
        
    }


