package tools;

import java.util.HashSet;

public class Publisher {
    HashSet<Subscriber> subscribers = new HashSet<>();

    public void notifySubscribers(){
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

    public void subscribe(Subscriber t){
        subscribers.add(t);
    }

    public void unSubscribe(Subscriber s){
        subscribers.remove(s);
    }
}
