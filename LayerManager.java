import java.util.*;

public class LayerManager {
    private  int numtobeExcluded = -1;
    // all connectoins must be in order of creation
    protected static double lossFunction;

    ArrayList<Layer> listOfLayers = new ArrayList<Layer>(); // polymporphism
    protected InputLayer inputLayer;
    protected OutputLayer outputLayer;
    protected static ArrayList<Double> ExpectedOutputArrayList; // This will be used by various algorithms, especially
                                                                // backpropagation
    // in the concrete implementations of layer class.
    private static Double NeuronNumberToBeTestedinRelavancePropagation;

    protected OutputLayer getOutputLayer() {
        return outputLayer;
    }

    protected InputLayer getInputLayer() {
        return inputLayer;
    }

    protected double calculateMSE() {
        return MeanSquaredErrorCalculator.calculateMSE(this.getOutputLayer(), LayerManager.ExpectedOutputArrayList);
    }

    protected void setExpectedOutputArray(ArrayList<Double> expectedOutputArrayList) {
        ExpectedOutputArrayList = expectedOutputArrayList;
    }

    protected void setInputLayer(ArrayList<Double> inputLayerArray) {
        this.inputLayer.setInput(inputLayerArray);
    }

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

    private void joinAllLayers() {
        // Joining all the layers together.
        for (int i = 0; i < listOfLayers.size() - 1; i++) {
            joinLayer(listOfLayers.get(i), listOfLayers.get(i + 1));

        }
    }

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

    protected void forwardPropagate() {
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

    protected void forwardPropagatewithExclusionInputLayer() {

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
            // System.out.print((int)(temp*10000+127)+",");

            if (temp == 0) {
                System.out.print(0 + ",");
            } else if (temp < 0) {
                System.out.print(255 + ",");
            } else {
                System.out.print(0 + ",");
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

        // System.out.println();

    }

    public String toString() {
        String str = "";
        for (Layer i : listOfLayers) {
            str += i.toString();
        }
        str += "________________________________" + "\n" + "\n";
        str += "Loss is " + LayerManager.lossFunction + "\n" + "\n";
        return str;
    }

    protected Layer getOutput() {
        // Returning the last layer in the listOfLayers ArrayList.
        return listOfLayers.get(listOfLayers.size() - 1);
    }

    protected void backwardPropagate() {

        // backwardPropagate in reverse order
        for (int i = listOfLayers.size() - 1; i >= 0; i--) {
            listOfLayers.get(i).backwardPropagate();
        }

    }

    // This is the code that is used to calculate the relevance of each pixel.
    protected void relevancePropagate(int layerNumber, int neuronNumber) {
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
            System.out.print((int) (NN.sigmoid(i.relevance) * 255) + ",");
        }

    }

    public static Double getNeuronNumberToBeTestedinRelavancePropagation() {
        return NeuronNumberToBeTestedinRelavancePropagation;
    }

    public static void setNeuronNumberToBeTestedinRelavancePropagation(
            Double neuronNumberToBeTestedinRelavancePropagation) {
        NeuronNumberToBeTestedinRelavancePropagation = neuronNumberToBeTestedinRelavancePropagation;
    }

    public Layer getLayer(int layerNum) {
        return listOfLayers.get(layerNum);
    }

    protected  int getMostSignificantNeuronAsPrediction() {
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
    protected  double getconfidence() {
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

    protected ArrayList getMostSignificantNeuronAsPredictionInHiddenLayer() {
        // Finding the most significant neuron in the hidden layer.
        double temp = 0.9;
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
