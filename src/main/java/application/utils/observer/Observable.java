package application.utils.observer;

import java.util.*;

public class Observable {
    private final Collection<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
