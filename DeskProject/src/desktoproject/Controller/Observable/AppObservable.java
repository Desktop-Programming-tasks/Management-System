/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Observable;
import java.util.ArrayList;

/**
 *
 * @author viniciusmn
 */
public abstract class AppObservable {
    private final ArrayList<AppObserver> subscribed;
    
    public AppObservable(){
        subscribed = new ArrayList<>();
    }
    
    private void notifyAllSubscribed(){
        subscribed.forEach((ob) -> {
            ob.update();
        });
    }
    
    public void setChanged(){
        notifyAllSubscribed();
    }
    
    public void addObserver(AppObserver ob){
        subscribed.add(ob);
    }
    
    public int countObservers(){
        return subscribed.size();
    }
    
    public void removeObserver(AppObserver ob){
        subscribed.remove(ob);
    }
    
    public void removeAll(){
        subscribed.clear();
    }
}
