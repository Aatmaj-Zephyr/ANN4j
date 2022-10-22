import java.util.*;

public class LayerManager {
    static ArrayList<Connection> ConnectionHeap = new ArrayList<Connection>(); //dump all connectoins here for eaier debugging purposes.
    static double lossfunction;
    
    ArrayList<Layer> listOfLayers = new ArrayList<Layer>(); // polymporphism

    LayerManager(int[] layerLengths) {

        // Adding a new InputLayer to the listOfLayers ArrayList.
        Layer inputLayer = new InputLayer(layerLengths[0]);
        inputLayer.setLayerNum(0);
        listOfLayers.add(inputLayer);

        for (int i = 1; i < layerLengths.length - 1; i++) {
            // Adding a new HiddenLayer to the listOfLayers ArrayList.
            Layer hiddenLayer = new HiddenLayer(layerLengths[i]);
            hiddenLayer.setLayerNum(i);
            listOfLayers.add(hiddenLayer);
        }

        // Adding a new OutputLayer to the listOfLayers ArrayList.
        Layer outputLayer = new OutputLayer(layerLengths[layerLengths.length-1]);
        outputLayer.setLayerNum(layerLengths.length-1);
        listOfLayers.add(outputLayer);

        joinAllLayers();
    }

    private void joinAllLayers() {
        for(int i=0; i<listOfLayers.size() - 1; i++) {
           joinLayer(listOfLayers.get(i),listOfLayers.get(i+1));
    }
}

    private void joinLayer(Layer layer, Layer layer2) {
        for(Neuron i: layer.listOfNeurons){
            for(Neuron j: layer2.listOfNeurons){
                new Connection(i, j);
            }
        }
    }

    public void forwardPropagate() {
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
      return listOfLayers.get(listOfLayers.size()-1);
    }
}
