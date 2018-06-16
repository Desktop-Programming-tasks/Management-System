/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Observable.Observables;

import Classes.Enums.ObservableType;
import deskprojectserver.Observable.ServerObservable;


/**
 *
 * @author viniciusmn
 */
public class ClientObservable extends ServerObservable{
    protected ClientObservable(){}

    @Override
    public void setType() {
        this.type = ObservableType.CLIENT;
    }
}
