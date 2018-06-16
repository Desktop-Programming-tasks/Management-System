/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Observable.Observables;

import deskprojectserver.Observable.ServerObserver;

/**
 *
 * @author viniciusmn
 */
public abstract class ObservablesHolder {
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
    
    public static void subscribeToAll(ServerObserver observer){
        System.out.println("---Subscribe all---");
        getBrand().addObserver(observer);
        System.out.println("Watching Brand observable...");
        getBuy().addObserver(observer);
        System.out.println("Watching Buy observable...");
        getClient().addObserver(observer);
        System.out.println("Watching Client observable...");
        getEmployee().addObserver(observer);
        System.out.println("Watching Employee observable...");
        getJuridical().addObserver(observer);
        System.out.println("Watching Juridical observable...");
        getLegal().addObserver(observer);
        System.out.println("Watching Legal observable...");
        getProduct().addObserver(observer);
        System.out.println("Watching Product observable...");
        getSale().addObserver(observer);
        System.out.println("Watching Sale observable...");
        getService().addObserver(observer);
        System.out.println("Watching Service observable...");
        getServiceType().addObserver(observer);
        System.out.println("Watching ServiceType observable...");
        getSupplier().addObserver(observer);
        System.out.println("Watching Supplier observable...");
        getTransaction().addObserver(observer);
        System.out.println("Watching Transaction observable...");
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
