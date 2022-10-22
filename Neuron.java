import java.util.*;
class Neuron{
    double activation;
    Layer leftConnections;
    Layer rightConnections;
    void setLeftLayer(Layer leftConnections){
        this.leftConnections=leftConnections;
    }
    void setRightLayer(Layer rightConnections){
        this.rightConnections=rightConnections;
    }


    
    Neuron(double activation){
        this.activation = activation;
    }

    double getActivation(){
        return activation;
        }

    
}