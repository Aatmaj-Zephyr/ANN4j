import java.util.ArrayList;

public interface Observable {
    public ArrayList<Observer> observerList = new ArrayList<Observer>();

    void notifyObservers(String info);

    default void registerObserver(Observer observer) {
        observerList.add(observer);
    }

}
