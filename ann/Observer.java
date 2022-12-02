// The above code is an interface for an Observer to be implemented by NeuronObserves
package ann;
public interface Observer {

     void addObjectToBeObserved(Observable observable);

     void update(String info, Observable observable);
     public void clear();


}
