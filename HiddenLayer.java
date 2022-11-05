public class HiddenLayer extends Layer {
    HiddenLayer(int numOfNeurons) {
        super(numOfNeurons);
    }

    @Override
    public double calculateDelta(Neuron i) {
      // Calculating the delta for the output layer.
        double activation = i.getActivation();
        double weightedSum=0;
        for(Connection j: i.rightConnections){
            weightedSum+=j.getBackwardWeightedSum();
        }
        return weightedSum*activation*(1-activation);
    }

}
