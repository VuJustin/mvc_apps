package tools;

import java.util.LinkedList;
import java.util.List;

public class Publisher{
   private List<Subscriber> listSub = new LinkedList<>();
    public void notifySubscribers(){
        for(Subscriber s : listSub){
            s.update();
        }
    }
    public void subscribe(Subscriber s){
        listSub.add(s);
    }
    public void unSubscribe(Subscriber s){
        listSub.remove(s);
    }
}
