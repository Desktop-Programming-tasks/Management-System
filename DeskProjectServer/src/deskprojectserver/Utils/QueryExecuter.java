/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Utils;

import Exceptions.DatabaseErrorException;

/**
 *
 * @author gabriel
 */
public abstract class QueryExecuter {
    public abstract QueryResult execute() throws DatabaseErrorException;
}
