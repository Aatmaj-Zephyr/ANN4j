import java.util.*;
class Layer {
    ArrayList<Neuron> listOfNeurons = new ArrayList<Neuron>();
    int layerNum;

    Layer(int numOfNeurons){

        //aggregation
        for(int i=0;i<numOfNeurons;i++){
            Neuron newNeuron = new Neuron();
            newNeuron.setLayerNum(layerNum);
            newNeuron.setNeuronNum(i);
            addNeuron(newNeuron);
        }
    }
    public void setLayerNum(int layerNum) {
        this.layerNum = layerNum;
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
        String str = "Layer #"+ layerNum +" has Length: " + listOfNeurons.size()+"\n";
        for(Neuron i : listOfNeurons){
            str+=i.toString();
            
        }
        return str+"\n";
    }
    
}
