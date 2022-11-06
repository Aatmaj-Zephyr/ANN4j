public class HiddenLayer extends Layer {
    HiddenLayer(int numOfNeurons) {
        super(numOfNeurons);
    }

   

    @Override
    public void setBehaviour() {
      this.myBehaviour= HiddenLayerNeuronBehaviour.getInstance();
        
    }

}
