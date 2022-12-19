// The above code is an interface for an Observer to be implemented by NeuronObserves
package ann4j;
public interface Observer {

     /**
      * Add an object to the list of objects to be observed.
      * 
      * @param observable The object to be observed.
      */
     void addObjectToBeObserved(Observable observable);

/**
 * The update function is called when the observable object is changed.
 * 
 * @param info The information that is passed to the observer.
 * @param observable The Observable object.
 */
     void update(String info, Observable observable);
     public void clear();


}
