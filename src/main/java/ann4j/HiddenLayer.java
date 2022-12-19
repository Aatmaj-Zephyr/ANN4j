package ann4j;
 public class HiddenLayer extends Layer {
 // Calling the constructor of the super class.
  HiddenLayer(int numOfNeurons) {
    super(numOfNeurons);
  }

  @Override
  // Setting the behaviour of the hidden layer.
  public void setBehaviour() {
    this.myBehaviour = HiddenLayerNeuronBehaviour.getInstance();

  }

}
