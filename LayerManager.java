import java.util.*;

public class LayerManager {
    ArrayList<Layer> listOfLayers; // polymporphism

    LayerManager(int[] layerLengths) {

        // Adding a new InputLayer to the listOfLayers ArrayList.
        listOfLayers.add(new InputLayer(layerLengths[0]));

        for (int i = 1; i < layerLengths.length - 1; i++) {
            // Adding a new HiddenLayer to the listOfLayers ArrayList.
            listOfLayers.add(new HiddenLayer(layerLengths[i]));
        }

        // Adding a new OutputLayer to the listOfLayers ArrayList.
        listOfLayers.add(new OutputLayer(layerLengths[layerLengths.length]));

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
}
