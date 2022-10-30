import java.util.*;

class Layer {
    ArrayList<Neuron> listOfNeurons = new ArrayList<Neuron>();
    int layerNum;

    Layer(int numOfNeurons) {

        // aggregation
        // Creating a new neuron and adding it to the list of neurons.
        for (int i = 0; i < numOfNeurons; i++) {
            Neuron newNeuron = new Neuron();
            newNeuron.setNeuronNum(i);
            addNeuron(newNeuron);
        }
    }

    public ArrayList<Neuron> getListOfNeurons() {
        return listOfNeurons;
    }

    public void setLayerNum(int layerNum) {
        // Setting the layer number for each neuron in the layer.
        this.layerNum = layerNum;
        for (Neuron i : listOfNeurons) {
            i.setLayerNum(layerNum);
        }
    }

    void addNeuron(Neuron toBeAdded) {
        listOfNeurons.add(toBeAdded);
    }

    void forwardPropagate() {
        // Iterating through the list of neurons and calling the forwardPropagate method
        // on each neuron.
        for (Neuron i : listOfNeurons) {
            i.forwardPropagate();
        }
    }

    public String toString() {
        String str = "Layer #" + layerNum + " has Length: " + listOfNeurons.size() + "\n";
        for (Neuron i : listOfNeurons) {
            str += i.toString();

        }
        return str + "\n";
    }

}
