/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observable;

import Classes.Enums.ObservableType;
import java.util.ArrayList;

/**
 *
 * @author viniciusmn
 */
public abstract class MyObservable {
    private final ArrayList<MyObserver> subscribed;
    protected ObservableType type;
    
    public MyObservable(){
        subscribed = new ArrayList<>();
        setType();
    }
    
    private void notifyAllSubscribed(){
        subscribed.forEach((ob) -> {
            ob.update(type);
        });
    }
    
    public abstract void setType();
    
    public void setChanged(){
        notifyAllSubscribed();
    }
    
    public void addObserver(MyObserver ob){
        subscribed.add(ob);
    }
    
    public int countObservers(){
        return subscribed.size();
    }
    
    public void removeObserver(MyObserver ob){
        subscribed.remove(ob);
    }
}
