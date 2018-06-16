/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observable.Observables;

import Classes.Enums.ObservableType;
import Observable.ServerObservable;

/**
 *
 * @author viniciusmn
 */
public class LegalObservable extends ServerObservable{
    protected LegalObservable() {
    }

    @Override
    public void setType() {
        this.type = ObservableType.LEGAL;
    }
    
}
