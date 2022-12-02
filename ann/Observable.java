// An interface that defines the methods that an observable object must implement.
package ann;
public interface Observable {

    void notifyObservers(String info);

    void registerObserver(Observer observer);

    void deregisterObserver(NeuronObserver neuronObserver);
}
