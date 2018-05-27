/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author gabriel
 */
public class OperationNotAllowed extends Exception{
//Jogar quando o usuário tentar realizar alguma operação inválida ie: remover uma legal ou juridical persons    
    public OperationNotAllowed() {
        super("Opeation Not Allowed");
    }
    
}
