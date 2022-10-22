import java.util.*;

public class LayerManager {
    static ArrayList<Connection> ConnectionHeap = new ArrayList<Connection>(); //dump all connectoins here for eaier debugging purposes.
    static double lossfunction;
    
    ArrayList<Layer> listOfLayers = new ArrayList<Layer>(); // polymporphism
    public InputLayer InputLayer;
    public OutputLayer OutputLayer;
    public OutputLayer getOutputLayer() {
        return OutputLayer;
    }
    public InputLayer getInputLayer() {
        return InputLayer;
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
        this.OutputLayer = new OutputLayer(layerLengths[layerLengths.length-1]);
        OutputLayer.setLayerNum(layerLengths.length-1);
        listOfLayers.add(OutputLayer);

        joinAllLayers();
    }

    private void joinAllLayers() {
        // Joining all the layers together.
        for(int i=0; i<listOfLayers.size() - 1; i++) {
           joinLayer(listOfLayers.get(i),listOfLayers.get(i+1));
    }
}

    private void joinLayer(Layer layer, Layer layer2) {
       // Creating a connection between every neuron in layer 1 and every neuron in layer 2.
        for(Neuron i: layer.listOfNeurons){
            for(Neuron j: layer2.listOfNeurons){
                new Connection(i, j);
            }
        }
    }

    public void forwardPropagate() {
        // Calling the forwardPropagate() method on every layer in the listOfLayers ArrayList.
        for(Layer i: listOfLayers){
            i.forwardPropagate();
        }
    }

    public String toString(){
        String str="";
        for(Layer i : listOfLayers){
            str+=i.toString();
        }
        str+="________________________________"+"\n"+"\n";
        return str;
    }

    public Layer getOutput(){
// Returning the last layer in the listOfLayers ArrayList.
      return listOfLayers.get(listOfLayers.size()-1);
    }

    
}
