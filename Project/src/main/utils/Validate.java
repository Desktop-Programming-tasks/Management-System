/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gabriel
 */
public class Validate {
    private static final int  NAME_MAX_LENGTH = 50;
    private static final int  NICK_MIN_LENGTH = 4;
    private static final int  NICK_MAX_LENGTH = 25;
    private static final int  PASSWORD_MIN_LENGTH = 6;
    private static final int  PASSWORD_MAX_LENGTH = 15;
    
    private static final String TEL_NUMBER_PATTERN= "0?[0-9]{2}[0-9]?[0-9]{4}[0-9]{4}";
    private static final String NICKNAME_PATTERN= "[a-z][a-z0-9]*";
    private static final String ADDRESS_NUMBER_PATTERN= "[0-9]*";
    
    public static boolean validaNum(String num) {
        String pattern = TEL_NUMBER_PATTERN;
        Pattern test = Pattern.compile(pattern);
        Matcher matcher = test.matcher(num);
        if (num.isEmpty()) {
            return (false);
        } else if (!matcher.matches()) {
            return (false);
        } else {
            return (true);
        }
    }

    public static boolean validaNome(String nome) {
        if (nome.isEmpty()) {
            return (false);
        } else if (nome.length() > NAME_MAX_LENGTH) {
            return (false);
        } else {
            return (true);
        }
    }

    public static boolean validaNick(String nick) {
        String pattern = NICKNAME_PATTERN;
        Pattern test = Pattern.compile(pattern);
        Matcher matcher = test.matcher(nick);
        if (nick.isEmpty()) {
            return (false);
        } else if (!matcher.matches()) {
            return (false);
        } else if (nick.length() < NICK_MIN_LENGTH) {
            return (false);
        } else if (nick.length() > NICK_MAX_LENGTH) {
            return (false);
        } else {
            return (true);
        }
    }
    
    public static boolean validaPassword(String password){
        if(password.isEmpty()){
            return false;
        }
        if(password.length()>PASSWORD_MAX_LENGTH){
            return false;
        }
        return password.length() > PASSWORD_MIN_LENGTH;
    }

    public static boolean validaNumAddress(String num) {
        String pattern = ADDRESS_NUMBER_PATTERN;
        Pattern test = Pattern.compile(pattern);
        Matcher matcher = test.matcher(num);
        if(num.isEmpty()){
            return false;
        }
        return matcher.matches();
    }
    

}
