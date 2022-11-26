// An interface that defines the methods that an observable object must implement.
public interface Observable {

    void notifyObservers(String info);

    void registerObserver(Observer observer);
}
