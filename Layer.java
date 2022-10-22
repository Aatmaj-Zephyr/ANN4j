import java.util.*;
class Layer {
    ArrayList<Neuron> listOfNeurons = new ArrayList<Neuron>();
    
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

    public String toString(){
        String str = "Layer:"+"\n"+"Length: " + listOfNeurons.size()+"\n";
        for(int i = 0; i < listOfNeurons.size(); i++){
            str += "Neuron #"+i+" has activation "+listOfNeurons.get(i).toString()+"\n";
        }
        return str+"\n";
    }
    
}
