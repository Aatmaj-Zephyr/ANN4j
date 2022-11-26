public interface Observer {

     void addObjectToBeObserved(Observable observable);

     void update(String info, Observable observable);

}
