/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Controller.Observable.Observables;
import Classes.Enums.ObservableType;
import desktoproject.Controller.Observable.AppObservable;

/**
 *
 * @author viniciusmn
 */
public class ObservableServer extends AppObservable{
    private static BrandObservable BRAND_INSTANCE; 
    private static BuyObservable BUY_INSTANCE;
    private static ClientObservable CLIENT_INSTANCE;
    private static EmployeeObservable EMPLOYEE_INSTANCE;
    private static JuridicalObservable JURIDICAL_INSTANCE;
    private static LegalObservable LEGAL_INSTANCE;
    private static ProductObservable PRODUCT_INSTANCE;
    private static SaleObservable SALE_INSTANCE;
    private static ServiceObservable SERVICE_INSTANCE;
    private static ServiceTypeObservable SERVICE_TYPE_INSTANCE;
    private static SupplierObservable SUPPLIER_INSTANCE;
    private static TransactionObservable TRANSACTION_INSTANCE;
    
    public static void clearAll(){
        getBrand().removeAll();
        getBuy().removeAll();
        getClient().removeAll();
        getEmployee().removeAll();
        getJuridical().removeAll();
        getLegal().removeAll();
        getProduct().removeAll();
        getSale().removeAll();
        getService().removeAll();
        getServiceType().removeAll();
        getSupplier().removeAll();
        getTransaction().removeAll();
    }
    
    public static void trigger(ObservableType type){
        switch(type){
            case BRAND:{
                getBrand().setChanged();
                break;
            }
            case BUY:{
                getBuy().setChanged();
                break;
            }
            case CLIENT:{
                getClient().setChanged();
                break;
            }
            case EMPLOYEE:{
                getEmployee().setChanged();
                break;
            }
            case JURIDICAL:{
                getJuridical().setChanged();
                break;
            }
            case LEGAL:{
                getLegal().setChanged();
                break;
            }
            case PRODUCT:{
                getProduct().setChanged();
                break;
            }
            case SALE:{
                getSale().setChanged();
                break;
            }
            case SERVICE:{
                getService().setChanged();
                break;
            }
            case SERVICE_TYPE:{
                getServiceType().setChanged();
                break;
            }
            case SUPPLIER:{
                getSupplier().setChanged();
                break;
            }
            case TRANSACTION:{
                getTransaction().setChanged();
                break;
            }
        }
    }
    
    public static BrandObservable getBrand(){
        return BRAND_INSTANCE==null?BRAND_INSTANCE = new BrandObservable():BRAND_INSTANCE;
    }
    
    public static BuyObservable getBuy(){
        return BUY_INSTANCE==null?BUY_INSTANCE = new BuyObservable():BUY_INSTANCE;
    }
    
    public static ClientObservable getClient(){
        return CLIENT_INSTANCE==null?CLIENT_INSTANCE = new ClientObservable():CLIENT_INSTANCE;
    }
    
    public static EmployeeObservable getEmployee(){
        return EMPLOYEE_INSTANCE==null?EMPLOYEE_INSTANCE = new EmployeeObservable():EMPLOYEE_INSTANCE;
    }
    
    public static JuridicalObservable getJuridical(){
        return JURIDICAL_INSTANCE==null?JURIDICAL_INSTANCE = new JuridicalObservable():JURIDICAL_INSTANCE;
    }
    
    public static LegalObservable getLegal(){
        return LEGAL_INSTANCE==null?LEGAL_INSTANCE = new LegalObservable():LEGAL_INSTANCE;
    }
    
    public static ProductObservable getProduct(){
        return PRODUCT_INSTANCE==null?PRODUCT_INSTANCE = new ProductObservable():PRODUCT_INSTANCE;
    }
    
    public static SaleObservable getSale(){
        return SALE_INSTANCE==null?SALE_INSTANCE = new SaleObservable():SALE_INSTANCE;
    }
    
    public static ServiceObservable getService(){
        return SERVICE_INSTANCE==null?SERVICE_INSTANCE = new ServiceObservable():SERVICE_INSTANCE;
    }
    
    public static ServiceTypeObservable getServiceType(){
        return SERVICE_TYPE_INSTANCE==null?SERVICE_TYPE_INSTANCE = new ServiceTypeObservable():SERVICE_TYPE_INSTANCE;
    }

    public static SupplierObservable getSupplier(){
        return SUPPLIER_INSTANCE==null?SUPPLIER_INSTANCE = new SupplierObservable():SUPPLIER_INSTANCE;
    }

    public static TransactionObservable getTransaction(){
        return TRANSACTION_INSTANCE==null?TRANSACTION_INSTANCE = new TransactionObservable():TRANSACTION_INSTANCE;
    }
}
