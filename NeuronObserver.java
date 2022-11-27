import java.util.ArrayList;
import java.util.Arrays;

public class NeuronObserver implements Observer {
    private LayerManager model;
    protected ArrayList<Arrays> confusionMatrix = new ArrayList<Arrays>();

   /**
    * The update function is called when the observable object is changed
    * 
    * @param info The message that the observable sends to the observer.
    * @param observable The object that is being observed.
    */
    public void update(String info, Observable observable) {
        System.out.println(info);
        Neuron temp = (Neuron) observable;
        System.out.print(temp);
    }

   
    void setModel(LayerManager model) {
        // set the model to be observed
        this.model = model;
    }

    /**
     * Add a neuron to the list of objects to be observed.
     * 
     * @param layerNum The layer number of the neuron you want to observe.
     * @param neuronNum The index of the neuron in the layer.
     */
    public void addNeuronToBeObserved(int layerNum, int neuronNum) {
        addObjectToBeObserved(model.getLayer(layerNum).getNeuron(neuronNum));
    }

   /**
    * This function adds an object to the list of objects that this object will observe.
    * @param observable The object that is being observed, here it is neuron
    */
    public void addObjectToBeObserved(Observable observable) {
        observable.registerObserver(this);
    }
   // public void updateConfusionMatrix(expectedOutputArray,actualOutputArray){

//    }

}
