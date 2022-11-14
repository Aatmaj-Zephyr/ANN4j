import java.util.*;

class Neuron {
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

   public double getActivation() {
        return activation;
    }

   public void setActivation(double activation) {
        this.activation = activation;
    }
   public void setBehaviour(NeuronBehaviour myBehaviour){
    this.myBehaviour= myBehaviour;
   }
   public Neuron() {
        setActivation(NN.getRandom());
        bias = NN.setBias();
        batchSize=parameter.getBatchsize();
        biasLearningRate=parameter.getBiasLearningRate();
        biasChangeWishlist=new ArrayList<Double>();
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
            sum += i.calculateActivationForwardPropagation();

        }

        return sum;
    }

    public double getBias() {
        return bias;
    }

    public void forwardPropagate() {
        // System.out.println("Forward propagating in neuron # "+this.neuronNum+" in
        // layer number "+this.layerNum); //test code
        // Setting the activation of the neuron to the rectified value of the weighted
        // sum of the left connections plus the bias.
        this.activation = parameter.rectify(getWeightedSum() + getBias());

    }

    public double getDelta() {
        return delta;
    }

    public void backwardPropagate() {
        setDelta();
        for (Connection i : leftConnections) {
            i.backPropagate();
        }
        changeBias();

    }

    private void changeBias() {
        batchCounter++;
        biasChangeWishlist.add( biasLearningRate *this.getDelta());

        if(batchCounter==batchSize){
            batchCounter=0;
        }
        if(batchCounter==0){
            this.bias= NN.average(biasChangeWishlist);
            biasChangeWishlist=new ArrayList<Double>();        }
    }

    public void setDelta() {
        this.delta=myBehaviour.setDelta(this);
    }

    public String toString() {
        return "Neuron #" + neuronNum + " has activation " + activation + "\n";
    }

    public void relevancePropagate() {
        myBehaviour.relevancePropagate(this);
        
    }
    }

