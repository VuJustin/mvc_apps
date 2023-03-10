package tools;

class Reactor extends Publisher {
    private String name;
    private double temperature = 5000;
    public static double criticalTemperature = 7000;

    public Reactor(String name) {
        this.name = name;
    }
    public void incTemperature(double amt) {
        temperature += amt;
        notifySubscribers();
    }

    public void decTemperature(double amt) {
        temperature -= amt;
        notifySubscribers();
    }

    public double getTemperature() {
        return temperature;
    }

    public String getName() { return name; }
}

class Thermometer implements Subscriber {
    private Reactor myReactor; // a dedicated subscriber
    public Thermometer(Reactor r) {
        myReactor = r;
        myReactor.subscribe(this);
    }
    public void update() {
        System.out.println(myReactor.getName() + " temerature = " + myReactor.getTemperature());
    }
}

class Alarm implements Subscriber {
    private Reactor myReactor = null; // a dedicated subscriber

    // non-dedicated subscriber
    public void setMyReactor(Reactor r) {
        if (myReactor != null) myReactor.unSubscribe(this);
        myReactor = r;
        myReactor.subscribe(this);
    }
    public void update() {
        if (myReactor != null && myReactor.getTemperature() > myReactor.criticalTemperature) {
            System.out.println("Warning! " + myReactor.getName() + " is too hot! \u0007");
        }
    }
}
public class PubSubTest {
    public static void main(String args[]) {
        Reactor threeMile = new Reactor("Three Mile");
        Reactor chernobyl = new Reactor("Chernobyl");
        Thermometer t1 = new Thermometer(threeMile);
        Thermometer t2 = new Thermometer(chernobyl);
        Alarm a = new Alarm();
        a.setMyReactor(chernobyl);

        chernobyl.incTemperature(1000);
        threeMile.incTemperature(1200);
        chernobyl.incTemperature(1200);
        a.setMyReactor(threeMile);
        threeMile.incTemperature(1400);
    }
}

/*
output:

Chernobyl temerature = 6000.0
Three Mile temerature = 6200.0
Warning! Chernobyl is too hot! 
Chernobyl temerature = 7200.0
Three Mile temerature = 7600.0
Warning! Three Mile is too hot! 
*/