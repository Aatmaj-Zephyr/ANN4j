package ann4j;
import java.util.*;

 public class LayerManager {
    private  int numtobeExcluded = -1;
    // all connectoins must be in order of creation
    public static double lossFunction;

    ArrayList<Layer> listOfLayers = new ArrayList<Layer>(); // polymporphism
    public InputLayer inputLayer;
    public OutputLayer outputLayer;
    public static ArrayList<Double> ExpectedOutputArrayList; // This will be used by various algorithms, especially
                                                                // backpropagation
    // in the concrete implementations of layer class.
    private static Double NeuronNumberToBeTestedinRelavancePropagation;

   /**
    * This function returns the output layer of the neural network
    * 
    * @return The output layer of the neural network.
    */
    public OutputLayer getOutputLayer() {
        return outputLayer;
    }

    /**
     * This function returns the input layer of the neural network.
     * 
     * @return The input layer of the neural network.
     */
    public InputLayer getInputLayer() {
        return inputLayer;
    }

    /**
     * This function calculates the mean squared error of the network
     * 
     * @return The mean squared error of the output layer.
     */
    public double calculateMSE() {
        return MeanSquaredErrorCalculator.calculateMSE(this.getOutputLayer(), LayerManager.ExpectedOutputArrayList);
    }

    /**
     * This function takes an ArrayList of Doubles as an argument and sets the ExpectedOutputArrayList
     * variable to the argument
     * 
     * @param expectedOutputArrayList This is the array list of expected outputs.
     */
    public void setExpectedOutputArray(ArrayList<Double> expectedOutputArrayList) {
        ExpectedOutputArrayList = expectedOutputArrayList;
    }

    /**
     * This function takes an ArrayList of Doubles and sets the inputLayer's input to that ArrayList
     * 
     * @param inputLayerArray The input layer array.
     */
    public void setInputLayer(ArrayList<Double> inputLayerArray) {
        this.inputLayer.setInput(inputLayerArray);
    }

    // This is the constructor of the LayerManager class. It takes an array of integers as an argument
    // and creates a neural network with the number of neurons in each layer as specified by the array.
    LayerManager(int[] layerLengths) {
        // Adding a new InputLayer to the listOfLayers ArrayList.
        this.inputLayer = new InputLayer(layerLengths[0]);
        this.inputLayer.setLayerNum(0);
        listOfLayers.add(inputLayer);

        for (int i = 1; i < layerLengths.length - 1; i++) {
            // Adding a new HiddenLayer to the listOfLayers ArrayList.
            Layer hiddenLayer = new HiddenLayer(layerLengths[i]);
            hiddenLayer.setLayerNum(i);
            listOfLayers.add(hiddenLayer);
        }

        // Adding a new OutputLayer to the listOfLayers ArrayList.
        this.outputLayer = new OutputLayer(layerLengths[layerLengths.length - 1]);
        outputLayer.setLayerNum(layerLengths.length - 1);
        listOfLayers.add(outputLayer);

        joinAllLayers();
    }

   /**
    * > Joining all the layers together
    */
    private void joinAllLayers() {
        // Joining all the layers together.
        for (int i = 0; i < listOfLayers.size() - 1; i++) {
            joinLayer(listOfLayers.get(i), listOfLayers.get(i + 1));

        }
    }

    /**
     * Joining two layers together.
     * For every neuron in layer 1, create a connection to every neuron in layer 2.
     * 
     * @param layer The layer that will be connected to the next layer.
     * @param layer2 The layer that the neurons in layer1 will be connected to.
     */
    private void joinLayer(Layer layer, Layer layer2) {
        // Creating a connection between every neuron in layer 1 and every neuron in
        // layer 2.
        for (Neuron i : layer.listOfNeurons) {
            for (Neuron j : layer2.listOfNeurons) {
                // Adding the connectoin in the connnection heap.
                new Connection(i, j);
            }
        }
    }

    /**
     * The forwardPropagate() method is called on every layer in the listOfLayers ArrayList, except the
     * input layer
     */
    public void forwardPropagate() {
        // Calling the forwardPropagate() method on every layer in the listOfLayers
        // ArrayList.
        // dont forward propagate the input layer
        numtobeExcluded = -1;
        for (int i = 1; i <= listOfLayers.size() - 1; i++) {
            listOfLayers.get(i).forwardPropagate();
        }
        // Calculating the new loss function and storing it in the variable
        // lossFunction,
        // storing the old loss function in the variable old lossFunction

        LayerManager.lossFunction = calculateMSE();
    }

    /**
     * The function calculates the relevance of each pixel in the input image by excluding each pixel
     * and calculating the loss function
     */
    public void forwardPropagatewithExclusionInputLayer() {

        // Calling the forwardPropagate() method on every layer in the listOfLayers

        numtobeExcluded = 0; // previously was set -1
        double temparray[] = new double[inputLayer.getSize()];

        for (; numtobeExcluded < temparray.length; numtobeExcluded++) {
            // Excluding the neuron from the input layer.
            listOfLayers.get(0).getNeuron(numtobeExcluded).exclude();
            for (int i = 1; i <= listOfLayers.size() - 1; i++) {

                listOfLayers.get(i).forwardPropagate();
            }
            // Calculating the new loss function and storing it in the variable
            // lossFunction,
            // storing the old loss function in the variable old lossFunction

            double temp = LayerManager.lossFunction - calculateMSE();

            // If your error is increasing when you remove a pixel, that pixel is important,
            // i.e positive pixel.
            // which means that the value if temp is negative

            // This is the code that is used to calculate the relevance of each pixel.
            // Writer.write((int)(temp*10000+127)+",");

            if (temp == 0) {
                Writer.write(0 + ",");
            } else if (temp < 0) {
                Writer.write(255 + ",");
            } else {
                Writer.write(0 + ",");
            }

            if (temp == 0) {
                temparray[numtobeExcluded] = 0;
            } else if (temp < 0) {
                temparray[numtobeExcluded] = 255;// -temp*1000;
            } else {
                temparray[numtobeExcluded] = 0;
            }
            // The values do not change for incorrect predictions.


            // Including the neuron that was excluded in the previous line for the next loop
            listOfLayers.get(0).getNeuron(numtobeExcluded).include();
        }
        // this.setInputLayer(temparray);

        // Writer.writeln();

    }

   /**
    * This function returns a string representation of the neural network
    * 
    * @return The string representation of the network.
    */
    public String toString() {
        String str = "";
        for (Layer i : listOfLayers) {
            str += i.toString();
        }
        str += "________________________________" + "\n" + "\n";
        str += "Loss is " + LayerManager.lossFunction + "\n" + "\n";
        return str;
    }

   /**
    * Return the last layer in the listOfLayers ArrayList.
    * 
    * @return The last layer in the listOfLayers ArrayList.
    */
    public Layer getOutput() {
        // Returning the last layer in the listOfLayers ArrayList.
        return listOfLayers.get(listOfLayers.size() - 1);
    }

   /**
    * For each layer in the network, starting from the last layer, call the backwardPropagate
    * function of that layer
    */
    public void backwardPropagate() {

        // backwardPropagate in reverse order
        for (int i = listOfLayers.size() - 1; i >= 0; i--) {
            listOfLayers.get(i).backwardPropagate();
        }

    }

    
    /**
     * The function takes in the layer number and the neuron number of the output layer and then
     * calculates the relevance of each pixel in the input layer
     * 
     * @param layerNumber The layer number of the neuron that you want to calculate the relevance of.
     * @param neuronNumber The number of the neuron in the output layer that you want to find the
     * relevance of.
     */
    public void relevancePropagate(int layerNumber, int neuronNumber) {
        // This is the code that is used to calculate the relevance of each pixel.
        for (int i = layerNumber; i >= 0; i--) {
            if (i == layerNumber) {
                for (Neuron j : listOfLayers.get(layerNumber).listOfNeurons) {
                    j.relevance = 0;
                    if (j.getNeuronNum() == neuronNumber) {
                        j.relevance = j.getActivation();
                    }
                }

            } else {
                listOfLayers.get(i).relevancePropagate();
            }
        }

        for (Neuron i : this.inputLayer.listOfNeurons) {
            Writer.write((int) (NN.sigmoid(i.relevance) * 255) + ",");
        }

    }

    /**
     * This function returns the number of neurons to be tested in relevance propagation
     * 
     * @return The number of neurons to be tested in relevance propagation.
     */
    public static Double getNeuronNumberToBeTestedinRelavancePropagation() {
        return NeuronNumberToBeTestedinRelavancePropagation;
    }

   /**
    * This function sets the number of neurons to be tested in relevance propagation
    * 
    * @param neuronNumberToBeTestedinRelavancePropagation This is the number of neurons that you want
    * to test in the relevance propagation.
    */
    public static void setNeuronNumberToBeTestedinRelavancePropagation(
            Double neuronNumberToBeTestedinRelavancePropagation) {
        NeuronNumberToBeTestedinRelavancePropagation = neuronNumberToBeTestedinRelavancePropagation;
    }

   /**
    * This function returns the layer at the specified index
    * 
    * @param layerNum The layer number you want to get.
    * @return The layer at the specified index.
    */
    public Layer getLayer(int layerNum) {
        return listOfLayers.get(layerNum);
    }

    /**
     * It returns the index of the neuron in the output layer that has the highest activation value
     * 
     * @return The most significant neuron number in the output layer.
     */
    public  int getMostSignificantNeuronNumAsPrediction() {
        // Finding the most significant neuron number in the output layer.
        double temp = 0;
        int no = 0;
        for (int i = 0; i < outputLayer.listOfNeurons.size(); i++) {
            double val = outputLayer.listOfNeurons.get(i).getActivation();
            if (val > temp) {
                no = i;
                temp = val;
            }
        }
        return no;
    }


   /**
    * It returns the confidence of the most significant neuron in the output layer
    * 
    * @return The most significant neuron confidance in the output layer.
    */
    public  double getconfidence() {
        // Finding the most significant neuron confidance in the output layer.
        double temp = 0;
        int no = 0;
        for (int i = 0; i < outputLayer.listOfNeurons.size(); i++) {
            double val = outputLayer.listOfNeurons.get(i).getActivation();
            if (val > temp) {
                no = i;
                temp = val;
            }
        }
        return temp;
    }

    /**
     * It returns the index of the most significant neuron in the hidden layer
     * 
     * @return The index of the most significant neuron in the hidden layer.
     */
    public ArrayList getMostSignificantNeuronAsPredictionInHiddenLayer() {
        // Finding the most significant neuron in the hidden layer.
        double temp = 0.0;
        ArrayList<Integer> no = new ArrayList<Integer>();
        for (int i = 0; i < listOfLayers.get(listOfLayers.size() - 2).listOfNeurons
                .size(); i++) {
            double val = listOfLayers.get(listOfLayers.size() - 2).listOfNeurons.get(i)
                    .getActivation();
            if (val > temp) {
                no.add(i);
            }
        }
        return no;
    }
}
