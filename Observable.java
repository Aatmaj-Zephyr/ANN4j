public interface Observable {

    void notifyObservers(String info);

    void registerObserver(Observer observer);
}
