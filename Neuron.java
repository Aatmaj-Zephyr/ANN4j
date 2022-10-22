import java.util.*;
class Neuron{
    double activation;
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
    

    public void addLeftConnections(Connection connection) {
        leftConnections.add(connection);
    }

    public void addRightConnections(Connection connection) {
        rightConnections.add(connection);
    }
    
    private double getWeightedSum(){
        double sum=0;
        for(Connection i : leftConnections){
            sum += i.calculateActivationForwardPropagation();
            }
        return sum;
    }
    private double getBias(){
        return 0.1;
    }

    public void forwardPropagate(){
        this.activation = NN.rectify(getWeightedSum()+getBias());
        
    }



    
}