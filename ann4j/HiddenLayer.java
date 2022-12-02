package ann4j;
 public class HiddenLayer extends Layer {
  HiddenLayer(int numOfNeurons) {
    super(numOfNeurons);
  }

  @Override
  protected void setBehaviour() {
    this.myBehaviour = HiddenLayerNeuronBehaviour.getInstance();

  }

}
