package project.ece301.mantracker;

/**
 * Observable objects nofity observers
 */
public interface Observable {
    public void notifyOberservers();
    public void addOberserver(Observer observer);
}
