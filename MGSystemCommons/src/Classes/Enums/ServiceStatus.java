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
public enum ServiceStatus {
    ON_ESTIMATE,
    WAITING_FOR_APPROVAL,
    WAITING_PAYMENT,
    WAITING_FOR_WITHDRAWAL,
    REFUSED,
    RUNNING,
    DONE;
    
    public static String translateEnumToGUI(ServiceStatus status){
        switch(status){
            case DONE:{
                return "Terminado";
            }
            case ON_ESTIMATE:{
                return "Em orçamento";
            }
            case REFUSED:{
                return "Recusado";
            }
            case RUNNING:{
                return "Em andamento";
            }
            case WAITING_FOR_APPROVAL:{
                return "Aguardando aprovação";
            }
            case WAITING_FOR_WITHDRAWAL:{
                return "Aguardando retirada";
            }
            case WAITING_PAYMENT:{
                return "Aguardando pagamento";
            }
            default:
                return "";
        }
    }
}
