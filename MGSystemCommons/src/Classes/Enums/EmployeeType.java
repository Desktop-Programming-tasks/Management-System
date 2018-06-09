/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Enums;

/**
 *
 * @author ecsanchesjr
 */
public enum EmployeeType {
    MANAGER,
    COMMOM;
    
    public static String translateEnumToGUI(EmployeeType type){
        switch(type){
            case COMMOM:{
                return "Normal";
            }
            case MANAGER:{
                return "Gerente";
            }
            default:
                return "";
        }
    }
}
