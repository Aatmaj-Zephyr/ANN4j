package ann4j;
import java.util.*;


abstract class Layer {
    public ArrayList<Neuron> listOfNeurons = new ArrayList<Neuron>();
    public int layerNum;
    public NeuronBehaviour myBehaviour;

    // Creating a new layer with a number of neurons.
    Layer(int numOfNeurons) {
        this.setBehaviour();

        // aggregation
        // Creating a new neuron and adding it to the list of neurons.
        for (int i = 0; i < numOfNeurons; i++) {
            Neuron newNeuron = new Neuron();
            newNeuron.setNeuronNum(i);
            newNeuron.setBehaviour(myBehaviour);
            addNeuron(newNeuron);
        }
    }

   /**
    * This function returns the number of neurons in the layer
    * 
    * @return The size of the list of neurons.
    */
    public int getSize() {
        return listOfNeurons.size();
    }

   /**
    * This function returns the neuron at the specified index in the list of neurons
    * 
    * @param neuronNum The index of the neuron you want to get.
    * @return The neuron at the given index.
    */
    public Neuron getNeuron(int neuronNum) {
        return listOfNeurons.get(neuronNum);
    }

   /**
    * The setBehaviour() function is an abstract function that is used to set the behaviour of the
    * animal.
    */
    public abstract void setBehaviour();
    /*
     * The delta of each neuron will be different according to it's type, that is if
     * it belongs to hidden layer or the output layer.
     * Hence we make a abstract method to calculate the delta of each neuron.
     * It will be implemented by the behaviours of concrete implementations of
     * Neuron behaviour
     * For calculating the delta of each neuron, the activation value will be
     * required. Also the actual expected value will be required.
     * Each implementation of the method will differ according to the formula.
     * This will be implemented to Strategy pattern.
     */

    /**
     * This function returns the list of neurons in the layer
     * 
     * @return The list of neurons in the layer.
     */
    public ArrayList<Neuron> getListOfNeurons() {
        return listOfNeurons;
    }

   /**
    * This function sets the layer number for each neuron in the layer
    * 
    * @param layerNum The layer number of the layer.
    */
    public void setLayerNum(int layerNum) {
        // Setting the layer number for each neuron in the layer.
        this.layerNum = layerNum;
        for (Neuron i : listOfNeurons) {
            i.setLayerNum(layerNum);
        }
    }

   /**
    * This function adds a neuron to the list of neurons
    * 
    * @param toBeAdded The neuron to be added to the list of neurons.
    */
    public void addNeuron(Neuron toBeAdded) {
        listOfNeurons.add(toBeAdded);
    }

   /**
    * The forwardPropagate function iterates through the list of neurons and calls the forwardPropagate
    * method on each neuron
    */
    public void forwardPropagate() {
        // Iterating through the list of neurons and calling the forwardPropagate method
        // on each neuron.
        for (Neuron i : listOfNeurons) {
            i.forwardPropagate();
        }
    }

    /**
     * This function returns a string that contains the layer number, the length of the list of
     * neurons, and the toString() function of each neuron in the list of neurons
     * 
     * @return The string representation of the layer.
     */
    public String toString() {
        String str = "Layer #" + layerNum + " has Length: " + listOfNeurons.size() + "\n";
        for (Neuron i : listOfNeurons) {
            str += i.toString();

        }
        return str + "\n";
    }

  /**
   * It prints the weights of the neurons
   * 
   * @return The weights of the neurons.
   */
    public String printWeights() {
        // Printing the weights of the neurons.
        String s = "";
        for (Neuron i : listOfNeurons) {
            s += i.leftConnections.toString();
        }
        return s;
    }

 /**
  * This function iterates through the list of neurons and calls the backwardPropagate method on each
  * neuron
  */
    public void backwardPropagate() {
        // Iterating through the list of neurons and calling the backwardPropagate
        // method
        // on each neuron.
        for (Neuron i : listOfNeurons) {
            i.backwardPropagate();
        }

    }

  /**
   * This function iterates through the list of neurons and calls the relevancePropagate method on each
   * neuron
   */
    public void relevancePropagate() {
        // Iterating through the list of neurons and calling the backwardPropagate
        // method
        // on each neuron.
        for (Neuron i : listOfNeurons) {
            i.relevancePropagate();
        }

    }

}
