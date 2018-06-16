package deskprojectserver.Observable.Observables;

import Classes.Enums.ObservableType;
import deskprojectserver.Observable.ServerObservable;

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
