import java.util.*;
class Neuron{
    double activation;
    int layerNum;
    int neuronNum;
    ArrayList<Connection> leftConnections = new ArrayList<Connection>();
    ArrayList<Connection> rightConnections = new ArrayList<Connection>();

    double getActivation(){
        return activation;
    }


    void setActivation(double activation){
        this.activation = activation;
    }
    Neuron(){
        setActivation(NN.getRandom()); 
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
    
    private double getWeightedSum(){
        double sum=0;
        // Iterating through the leftConnections ArrayList and adding the activation of each connection to the sum.
        for(Connection i : leftConnections){
            sum += i.calculateActivationForwardPropagation();
            }
        return sum;
    }
    private double getBias(){
        return 0.1;
    }

    public void forwardPropagate(){
        // Setting the activation of the neuron to the rectified value of the weighted sum of the left connections plus the bias.
        this.activation = NN.rectify(getWeightedSum()+getBias());
        
    }

    public void backwardPropagate(){
       for(Connection i : leftConnections){
         i.backPropagate();
       }
       //change bias
       
    }
    public String toString(){
        return "Neuron #"+neuronNum+" has activation " +activation +"\n";
    }



    
}