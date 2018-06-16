/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.mysql;

import deskprojectserver.Database.Configs;
import deskprojectserver.Database.Database;
import java.sql.SQLException;

/**
 *
 * @author gabriel
 */
public class MySqlHandler {

    private static MySqlHandler INSTANCE;
    private final Database db;
    private final Configs configs;

    private MySqlHandler() throws SQLException, ClassNotFoundException {
        configs = new Configs();
        configs.setTYPE("mysql");
        configs.setHOST("localhost");
        configs.setUSER("root");
        configs.setPASS("");
        configs.setPORT("3306");
        configs.setBASE("management-sys");
        configs.setDRIV("com.mysql.jdbc.Driver");
        db = Database.getInstance(configs);
    }

    public static MySqlHandler getInstance() throws SQLException, ClassNotFoundException {
        if (INSTANCE == null) {
            INSTANCE = new MySqlHandler();
        }
        return INSTANCE;
    }

    public Database getDb() {
        return db;
    }

}
