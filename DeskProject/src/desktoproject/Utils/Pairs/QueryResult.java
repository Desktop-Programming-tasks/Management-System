/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Utils.Pairs;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author gabriel
 */
public class QueryResult {
    private ResultSet rs;
    private Statement st;

    public QueryResult(ResultSet rs, Statement st) {
        this.rs = rs;
        this.st = st;
    }
    public ResultSet getRs() {
        return rs;
    }

    public Statement getSt() {
        return st;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public void setSt(Statement st) {
        this.st = st;
    }
    
    
}
