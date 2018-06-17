/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Enums;

/**
 *
 * @author noda
 */
public enum RecordType {
    SALE,
    BUY;
    
    public String translateEnumToGUI(){
        switch(this){
            case BUY:{
                return "Compra";
            }
            case SALE:{
                return "Venda";
            }
        }
        return "";
    }
}
