/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gabriel
 */
public class Database {

    private Configs config;
    private final Connection connection;

    ///////////////////////////////////////
    public Configs getConfig() {
        return config;
    }

    public void setConfig(Configs config) {
        this.config = config;
    }

    ////////////////////////////////////////
    private Database() throws SQLException, ClassNotFoundException {
        String url = "jdbc:";
        url += config.getTYPE() + "://";
        url += config.getHOST() + ":";
        url += config.getPORT() + "/";
        url += config.getBASE() + "?useUnicode=true&characterEncoding=utf-8";
        Class.forName(config.getDRIV());
        connection = DriverManager.getConnection(url, config.getUSER(), config.getPASS());
    }

    public Database getInstance() throws SQLException, ClassNotFoundException {
        if (INSTANCE == null || connection == null) {
            INSTANCE = new Database();
        }
        else if (connection.isClosed()) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    private Database INSTANCE;
    ///////////////////////////////////////
}
