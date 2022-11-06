public class InputLayerNeuronBehaviour implements NeuronBehaviour{
    private static InputLayerNeuronBehaviour myInstance;
    private InputLayerNeuronBehaviour() {
    }
    public static InputLayerNeuronBehaviour getInstance(){
        if(myInstance==null){
        myInstance = new InputLayerNeuronBehaviour();
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