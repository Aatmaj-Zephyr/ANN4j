public class NeuronObserver implements Observer{
    private LayerManager model;
    public void update(String info,Observable observable){
        System.out.println(info);
        Neuron temp = (Neuron) observable;
        System.out.print(temp);

    }
    void setModel(LayerManager model){
        this.model = model;
    }
    public void addNeuronToBeObserved(int layerNum, int neuronNum) {
        addObjectToBeObserved(model.getLayer(layerNum).getNeuron(neuronNum));
    }
    public void addObjectToBeObserved(Observable observable){
    observable.registerObserver(this);
    }

}
