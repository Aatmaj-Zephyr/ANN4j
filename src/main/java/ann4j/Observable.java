// An interface that defines the methods that an observable object must implement.
package ann4j;
public interface Observable {

   /**
    * Notify all observers of a change in the subject.
    * 
    * @param info The information that is being sent to the observers.
    */
    void notifyObservers(String info);

   /**
    * Register an observer to be notified when the data changes.
    * 
    * @param observer The observer to register.
    */
    void registerObserver(Observer observer);

   /**
    * Deregisters the observer from the observable
    * 
    * @param neuronObserver The observer to be deregistered.
    */
    void deregisterObserver(NeuronObserver neuronObserver);
}
