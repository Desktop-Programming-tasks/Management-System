/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observable;

import Classes.Enums.ObservableType;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author viniciusmn
 */
public class Aggregator implements ServerObserver{
    
    private DataOutputStream output;
    private ArrayList<Socket> clients;

    public Aggregator() {
        this.clients = new ArrayList<>();
    }
    
    @Override
    public void update(ObservableType type) {
//        switch(type){
//            case BRAND:{
//                System.out.println("brand changed");
//                break;
//            }
//            case BUY:{
//                System.out.println("buy changed");
//                break;
//            }
//            case CLIENT:{
//                System.out.println("client changed");
//                break;
//            }
//            case EMPLOYEE:{
//                System.out.println("employee changed");
//                break;
//            }
//            case JURIDICAL:{
//                System.out.println("juridical changed");
//                break;
//            }
//            case LEGAL:{
//                System.out.println("legal changed");
//                break;
//            }
//            case PRODUCT:{
//                System.out.println("product changed");
//                break;
//            }
//            case SALE:{
//                System.out.println("sale changed");
//                break;
//            }
//            case SERVICE:{
//                System.out.println("service changed");
//                break;
//            }
//            case SERVICE_TYPE:{
//                System.out.println("service type changed");
//                break;
//            }
//            case SUPPLIER:{
//                System.out.println("supplier changed");
//                break;
//            }
//            case TRANSACTION:{
//                System.out.println("transaction changed");
//                break;
//            }
//        }

        System.out.println(type.name());
        clients.forEach(client -> {
            try {
                output = new DataOutputStream(client.getOutputStream());
                output.writeUTF(type.name());
            } catch (IOException ex) {
                Logger.getLogger(Aggregator.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
        
    public ArrayList<Socket> getClients() {
        return clients;
    }

    public void addClient(Socket client) {
        clients.add(client);
    }
}
