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

    public static String translateEnumToGUI(ServiceStatus status) {
        switch (status) {
            case DONE: {
                return "Terminado";
            }
            case ON_ESTIMATE: {
                return "Em orçamento";
            }
            case REFUSED: {
                return "Recusado";
            }
            case RUNNING: {
                return "Em andamento";
            }
            case WAITING_FOR_APPROVAL: {
                return "Aguardando aprovação";
            }
            case WAITING_FOR_WITHDRAWAL: {
                return "Aguardando retirada";
            }
            case WAITING_PAYMENT: {
                return "Aguardando pagamento";
            }
            default:
                return "";
        }
    }

    public static int enumToInt(ServiceStatus status) {
        switch (status) {
            case DONE: {
                return 7;
            }
            case ON_ESTIMATE: {
                return 1;
            }
            case REFUSED: {
                return 5;
            }
            case RUNNING: {
                return 6;
            }
            case WAITING_FOR_APPROVAL: {
                return 2;
            }
            case WAITING_FOR_WITHDRAWAL: {
                return 4;
            }
            case WAITING_PAYMENT: {
                return 3;
            }
            default:
                return -1;
        }
    }

    public static ServiceStatus intToEnum(int i) {
        switch (i) {
            case 1:
                return ServiceStatus.ON_ESTIMATE;
            case 2:
                return WAITING_FOR_APPROVAL;
            case 3:
                return WAITING_PAYMENT;
            case 4:
                return WAITING_FOR_WITHDRAWAL;
            case 5:
                return REFUSED;
            case 6:
                return RUNNING;
            case 7:
                return DONE;
            default:
                return null;
        }
    }
}
