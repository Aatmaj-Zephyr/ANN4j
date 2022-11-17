import java.util.*;

public class LayerManager {
    public static int numtobeExcluded=-1;
    // all connectoins must be in order of creation
    public static double lossFunction;

   
    ArrayList<Layer> listOfLayers = new ArrayList<Layer>(); // polymporphism
    public InputLayer InputLayer;
    public OutputLayer OutputLayer;
    public int positivePixels;
    public int negativePixels;
    public int zeroPixels;
    public static double[] ExpectedOutputArray; // this will be used by various algorithms, especially backpropagation
                                                // in the concrete implementations of layer class.



    public OutputLayer getOutputLayer() {
        return OutputLayer;
    }

    public InputLayer getInputLayer() {
        return InputLayer;
    }

    public double calculateMSE() {
        return MeanSquaredErrorCalculator.calculateMSE(this.getOutputLayer(), LayerManager.ExpectedOutputArray);
    }

    public void setExpectedOutputArray(double[] expectedOutputArray) {
        ExpectedOutputArray = expectedOutputArray;
    }

    public void setInputLayer(double[] inputLayerArray) {
        this.InputLayer.setInput(inputLayerArray);
    }

    LayerManager(int[] layerLengths) {
        // Adding a new InputLayer to the listOfLayers ArrayList.
        this.InputLayer = new InputLayer(layerLengths[0]);
        this.InputLayer.setLayerNum(0);
        listOfLayers.add(InputLayer);

        for (int i = 1; i < layerLengths.length - 1; i++) {
            // Adding a new HiddenLayer to the listOfLayers ArrayList.
            Layer hiddenLayer = new HiddenLayer(layerLengths[i]);
            hiddenLayer.setLayerNum(i);
            listOfLayers.add(hiddenLayer);
        }

        // Adding a new OutputLayer to the listOfLayers ArrayList.
        this.OutputLayer = new OutputLayer(layerLengths[layerLengths.length - 1]);
        OutputLayer.setLayerNum(layerLengths.length - 1);
        listOfLayers.add(OutputLayer);

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

    public void forwardPropagate() {
        // Calling the forwardPropagate() method on every layer in the listOfLayers
        // ArrayList.
        // dont forward propagate the input layer
        numtobeExcluded=-1;
        for (int i = 1; i <= listOfLayers.size() - 1; i++) {
            listOfLayers.get(i).forwardPropagate();
        }
        // Calculating the new loss function and storing it in the variable
        // lossFunction,
        // storing the old loss function in the variable old lossFunction

        LayerManager.lossFunction = calculateMSE();
    }
    public void forwardPropagatewithExclusion() {
        zeroPixels=0;
        positivePixels=0;
        negativePixels=0;
        // Calling the forwardPropagate() method on every layer in the listOfLayers
       

        numtobeExcluded=0; //previously was set -1

        for(;numtobeExcluded<784;numtobeExcluded++){
        
        for (int i = 1; i <= listOfLayers.size() - 1; i++) {
            listOfLayers.get(i).forwardPropagate();
        }
        // Calculating the new loss function and storing it in the variable
        // lossFunction,
        // storing the old loss function in the variable old lossFunction

        double temp = LayerManager.lossFunction-calculateMSE();

        //If your error is increasing when you remove a pixel, that pixel is important, i.e positive pixel.

// This is the code that is used to calculate the relevance of each pixel.
//System.out.print((int)(temp*10000+127)+",");
        
        if(temp==0){
            zeroPixels++;
           // System.out.print(127+",");
        }
        else if (temp<0){
           // System.out.print(0+",");
           negativePixels++;
        }
        else{
            //System.out.print(255+",");
            positivePixels++;
        }

        System.out.print((int)(parameter.rectify(InputLayer.listOfNeurons.get(numtobeExcluded).relevance*256-(temp*10000+128))*256)+",");
        //System.out.println();
    }
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

    public Layer getOutput() {
        // Returning the last layer in the listOfLayers ArrayList.
        return listOfLayers.get(listOfLayers.size() - 1);
    }

    public void backwardPropagate() {

        // backwardPropagate in reverse order
        for (int i = listOfLayers.size() - 1; i >= 0; i--) {
            listOfLayers.get(i).backwardPropagate();
        }

    }
    public void relevancePropagate(){
        // backwardPropagate in reverse order
        for (int i = listOfLayers.size() - 1; i >= 0; i--) {
           
                
            
            listOfLayers.get(i).relevancePropagate();
            
        }
    }
    // This is the code that is used to calculate the relevance of each pixel.
    public void relevancePropagate(int layerNumber,int neuronNumber) {
        for (int i = layerNumber; i >= 0; i--) {
            if(i==layerNumber){
                for(Neuron j: listOfLayers.get(layerNumber).listOfNeurons){
                    j.relevance=0;
                    if(j.getNeuronNum()==neuronNumber){
                        j.relevance=j.getActivation();
                    }
                }
                
            }
            else{
            listOfLayers.get(i).relevancePropagate();
            }
        }
        
        

        for(Neuron i:this.InputLayer.listOfNeurons){
            System.out.print((int)(NN.sigmoid(i.relevance)*255)+",");
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n ");
        

    }

}
