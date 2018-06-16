package deskprojectserver.Observable.Observables;

import Classes.Enums.ObservableType;
import deskprojectserver.Observable.ServerObservable;

/**
 *
 * @author viniciusmn
 */
public class JuridicalObservable extends ServerObservable{

    protected JuridicalObservable() {
    }

    @Override
    public void setType() {
        this.type = ObservableType.JURIDICAL;
    }
}
