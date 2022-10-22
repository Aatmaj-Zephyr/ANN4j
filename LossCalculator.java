public class LossCalculator {
    public static double lossfunctionByLayer(OutputLayer outputLayer,OutputLayer expectedLayer){
        if(outputLayer.getListOfNeurons().size()!=expectedLayer.getListOfNeurons().size()){
            throw new RuntimeException("Length mismatch between lists of neurons and expected lists of neurons");
        }
        
        //Mean squared error
        double [] sum= new double[outputLayer.getListOfNeurons().size()];
        for(int i=0;i<outputLayer.getListOfNeurons().size();i++){
            sum[i]=Math.pow((outputLayer.getListOfNeurons().get(i).activation-expectedLayer.getListOfNeurons().get(i).activation),2);
        } 
        return NN.average(sum);
    }

    public static double lossfunction(OutputLayer outputLayer,double [] expectedLayer){

        //Overloaded for arrays as input

        if(outputLayer.getListOfNeurons().size()!=expectedLayer.length){
            throw new RuntimeException("Length mismatch between lists of neurons and expected lists of neurons");
        }
        
        //Mean squared error
        double [] sum= new double[outputLayer.getListOfNeurons().size()];
        for(int i=0;i<outputLayer.getListOfNeurons().size();i++){
            sum[i]=Math.pow((outputLayer.getListOfNeurons().get(i).activation-expectedLayer[i]),2);
        } 
        return NN.average(sum);
    }
}
