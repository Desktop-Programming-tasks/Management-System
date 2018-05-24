/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql;

import deskprojectserver.Database.Configs;

/**
 *
 * @author gabriel
 */
public class MySqlConfigsHolder {
    private final Configs configs;
    
    private MySqlConfigsHolder() {
        configs=new Configs();
        configs.setTYPE("mysql");
        configs.setHOST("localhost");
        configs.setUSER("root");
        configs.setPASS("");
        configs.setPORT("3306");
        configs.setBASE("mydb");
        configs.setDRIV("com.mysql.jdbc.Driver");
        
    }
    public Configs getMysqlConfigs(){
        return configs;
    }
    
    public static MySqlConfigsHolder getInstance() {
        return MySqlConfigsHolderHolder.INSTANCE;
    }
    
    private static class MySqlConfigsHolderHolder {

        private static final MySqlConfigsHolder INSTANCE = new MySqlConfigsHolder();
    }
}
