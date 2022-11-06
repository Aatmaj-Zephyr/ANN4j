public class HiddenLayerNeuronBehaviour implements NeuronBehaviour{
    private static HiddenLayerNeuronBehaviour myInstance;
    private HiddenLayerNeuronBehaviour() {
    }
    public static HiddenLayerNeuronBehaviour getInstance(){
        if(myInstance==null){
        myInstance = new HiddenLayerNeuronBehaviour();
        }
        return myInstance;
    }

    @Override
    public double setDelta(Neuron i) {
        // Calculating the delta for the output layer.
        double activation = i.getActivation();
        double weightedSum = 0;
        for (Connection j : i.rightConnections) {
            weightedSum += j.getBackwardWeightedSum();
        }
        return  weightedSum * activation * (1 - activation);
    }

}