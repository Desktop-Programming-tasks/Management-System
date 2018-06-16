/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observable;

import Classes.Enums.ObservableType;

/**
 *
 * @author viniciusmn
 */
public interface ServerObserver{
    public abstract void update(ObservableType type);
}
