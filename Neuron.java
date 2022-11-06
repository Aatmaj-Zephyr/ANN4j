import java.util.*;

class Neuron {
    double activation;
   public int neuronNum;
   public int layerNum = -1;
   public ArrayList<Connection> leftConnections = new ArrayList<Connection>();
   public ArrayList<Connection> rightConnections = new ArrayList<Connection>();
    public double bias;
    public double delta;
    public NeuronBehaviour myBehaviour;

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
        this.activation = NN.rectify(getWeightedSum() + getBias());

    }

    public double getDelta() {
        return delta;
    }

    public void backwardPropagate() {
        setDelta();
        for (Connection i : leftConnections) {
            i.backPropagate();
        }
        // TO-DO change bias

    }

    public void setDelta() {
        this.delta=myBehaviour.setDelta(this);
    }

    public String toString() {
        return "Neuron #" + neuronNum + " has activation " + activation + "\n";
    }

}