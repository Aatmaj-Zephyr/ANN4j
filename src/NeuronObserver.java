package src;
import java.util.ArrayList;

 public class NeuronObserver implements Observer {
    private LayerManager model;

    /**
     * The update function is called when the observable object is changed
     * 
     * @param info       The message that the observable sends to the observer.
     * @param observable The object that is being observed.
     */
    ArrayList<Observable> observableList = new ArrayList<Observable>();

    public void update(String info, Observable observable) {
        Writer.writeln(info);
        Neuron temp = (Neuron) observable;
        Writer.write(temp);
    }

    public void setModel(LayerManager model) {
        // set the model to be observed
        this.model = model;
    }

    /**
     * Add a neuron to the list of objects to be observed.
     * 
     * @param layerNum  The layer number of the neuron you want to observe.
     * @param neuronNum The index of the neuron in the layer.
     */
    public void addNeuronToBeObserved(int layerNum, int neuronNum) {
        addObjectToBeObserved(model.getLayer(layerNum).getNeuron(neuronNum));
    }

    /**
     * This function adds an object to the list of objects that this object will
     * observe.
     * 
     * @param observable The object that is being observed, here it is neuron
     */
    public void addObjectToBeObserved(Observable observable) {
        observable.registerObserver(this);
        observableList.add(observable);
    }

    public void clear() {
        // Deregistering the observer from the observable.
        for (Observable i : observableList) {
            i.deregisterObserver(this);
        }
    }
}
