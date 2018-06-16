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
public abstract class ServerObservable{
    protected ObservableType type;
    protected final ArrayList<ServerObserver> subscribed;
    
    public ServerObservable() {
        subscribed = new ArrayList<>();
        setType();
    }
    
    private void notifyAllSubscribed(){
        subscribed.forEach((ob) -> {
            ob.update(type);
        });
    }
    
    public void setChanged(){
        notifyAllSubscribed();
    }
    
    public void addObserver(ServerObserver ob){
        subscribed.add(ob);
    }
    
    public int countObservers(){
        return subscribed.size();
    }
    
    public void removeObserver(ServerObserver ob){
        subscribed.remove(ob);
    }

    public abstract void setType();
}
