package project.ece301.mantracker;

/**
 * Observable objects nofity observers
 */
public interface Observable {
    void addObserver(Observer observer);
    void notifyObservers();
}
