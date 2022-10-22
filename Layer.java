import java.util.*;
class Layer {
    ArrayList<Neuron> listOfNeurons;
    
    Layer(int numOfNeurons){
        //aggregation
        for(int i=0;i<numOfNeurons;i++){
            addNeuron(new Neuron());
        }
    }
    void addNeuron(Neuron toBeAdded){
        listOfNeurons.add(toBeAdded);
    }

    void forwardPropagate(){
        for(Neuron i: listOfNeurons){
            i.forwardPropagate();
            }
    }

    

}
