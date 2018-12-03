package project.ece301.mantracker;

public interface Observable {
    void addObserver(Observer observer);
    void notifyObservers();
}
