/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Utils;

import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

/**
 *
 * @author gabriel
 */
public class Validate {

    private String errorMessage;

    public Validate() {
        errorMessage = "";
    }

    private static final int NAME_MAX_LENGTH = 50;
    private static final int NICK_MIN_LENGTH = 4;
    private static final int NICK_MAX_LENGTH = 25;
    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 15;

    private static final String TEL_NUMBER_PATTERN = "0?[0-9]{2}[0-9]?[0-9]{4}[0-9]{4}";
    private static final String NICKNAME_PATTERN = "[a-z][a-z0-9]*";
    private static final String ADDRESS_NUMBER_PATTERN = "[0-9]*(.)?[0-9]*";
    private static final String CPF_PATTERN = "[0-9]{3}(.)[0-9]{3}(.)[0-9]{3}(-)[0-9]{2}";
    private static final String RG_PATTERN = "[0-9]{2}(.)[0-9]{3}(.)[0-9]{3}(-)[0-9]{1}";
    private static final String CNPJ_PATTERN = "[0-9]{2}(.)[0-9]{3}(.)[0-9]{3}||[0-9]{4}(-)[0-9]{2}";
    private static final String NUMBER_PATTERN = "[0-9]*";
    private static final String PRICE_PATTERN = "[0-9]*(,)?[0-9]*";

    public String getErrorMessage() {
        return errorMessage;
    }

    public void appendErrorMessage(String error) {
        this.errorMessage += error;
    }

    public boolean validateEmpty(String fieldName, String content) {
        if (content.isEmpty()) {
            errorMessage += (fieldName) + (" não pode ser vazio!\n");
            return false;
        }
        return true;
    }

    public void validateTelephone(String num) {
        String pattern = TEL_NUMBER_PATTERN;
        Pattern test = Pattern.compile(pattern);
        Matcher matcher = test.matcher(num);
        if (validateEmpty("Telefone", num)) {
            if (!matcher.matches()) {
                errorMessage += ("Formato de telefone inválido!\n");
            }
        }
    }

    public void validateName(String name) {
        if (validateEmpty("Nome", name)) {
            if (name.length() > NAME_MAX_LENGTH) {
                errorMessage += ("Nome muito grande!\n");
            }
        }
    }

    public void validateNick(String nick) {
        String pattern = NICKNAME_PATTERN;
        Pattern test = Pattern.compile(pattern);
        Matcher matcher = test.matcher(nick);
        if (validateEmpty("Nickname", nick)) {
            if (!matcher.matches()) {
                errorMessage += ("Formato de nickname inválido!\n");
            } else if (nick.length() < NICK_MIN_LENGTH) {
                errorMessage += ("Nickname muito curto!\n");
            } else if (nick.length() > NICK_MAX_LENGTH) {
                errorMessage += ("Nickname muito longo!\n");
            }
        }

    }

    public boolean validatePassword(String password) {
        if (validateEmpty("Senha", password)) {
            if (password.length() > PASSWORD_MAX_LENGTH) {
                errorMessage += ("Senha muito longa!\n");
            } else if (password.length() < PASSWORD_MIN_LENGTH) {
                errorMessage += ("Senha muito curta!\n");
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean validateConfirmPassword(String password) {
        if (password.isEmpty()) {
            errorMessage += "Por Favor digite a senha de confirmação";
            return false;
        }
        return true;
    }

    public void validateStreet(String street) {
        validateEmpty("Rua", street);
    }

    public void validateDistrict(String district) {
        validateEmpty("Bairro", district);
    }

    public void validateAddressNumber(String num) {
        if (validateEmpty("Número do endereço", num)) {
            String pattern = ADDRESS_NUMBER_PATTERN;
            Pattern test = Pattern.compile(pattern);
            Matcher matcher = test.matcher(num);
            if (!matcher.matches()) {
                errorMessage += ("Não é um número válido!\n");
            }
        }
    }

    public void validateNumber(String num) {
        String pattern = NUMBER_PATTERN;
        Pattern test = Pattern.compile(pattern);
        Matcher matcher = test.matcher(num);
        if (!matcher.matches()) {
            errorMessage += ("Não é um número!\n");
        }
    }
    
    public void validatePrice(String num){
        String pattern = PRICE_PATTERN;
        Pattern test = Pattern.compile(pattern);
        Matcher matcher = test.matcher(num);
        if (!matcher.matches()) {
            errorMessage += ("Não é um preço válido!\n");
        }
    }

    public void validateState(String state) {
        if (state == null) {
            errorMessage += "Selecione um estado válido\n";
        }
    }

    public void validateCity(String city) {
        if (city == null) {
            errorMessage += "Selecione uma cidade válida\n";
        }
    }

    public void validateCPF(String cpf) {
        String pattern = CPF_PATTERN;
        Pattern test = Pattern.compile(pattern);
        Matcher matcher = test.matcher(cpf);

        if (validateEmpty("CPF", cpf)) {
            if (!matcher.matches()) {
                errorMessage += ("Número de CPF inválido!\n");
            }
        }
    }

    public void validateCNPJ(String cnpj) {
        String pattern = CNPJ_PATTERN;
        Pattern test = Pattern.compile(pattern);
        Matcher matcher = test.matcher(cnpj);

        if (validateEmpty("CNPJ", cnpj)) {
            if (!matcher.matches()) {
                errorMessage += ("Número de CNPJ inválido!\n");
            }
        }
    }

    public void validateRG(String rg) {
        String pattern = RG_PATTERN;
        Pattern test = Pattern.compile(pattern);
        Matcher matcher = test.matcher(rg);

        if (validateEmpty("RG", rg)) {
            if (!matcher.matches()) {
                errorMessage += ("Número de RG inválido!\n");
            }
        }

    }

    public void passwordMatch(String pass1, String pass2) {
        if (!pass1.equals(pass2)) {
            errorMessage += ("Senhas diferentes!\n");
        }
    }

    public void emptyTableSelection(TableView table,String name) {
        if (table.getSelectionModel().getSelectedItems().isEmpty()) {
            errorMessage += ("Por favor selecione "+name+"\n");
        }
    }
    
    public void emptyComboBoxSelection(ComboBox combo,String name){
        if(combo.getSelectionModel().getSelectedItem()==null){
            errorMessage += ("Por favor selecione "+name+"\n");
        }
    }
    
    public boolean validateDatePicker(DatePicker picker,String name){
        if(picker.getValue()==null){
            errorMessage += ("Por favor selecione "+name+"\n");
            return false;
        }
        return true;
    }
    
    public void validateDate(DatePicker begin, DatePicker end){
        if(end.getValue().isBefore(begin.getValue())){
            errorMessage += "Data de fim antes do início\n";
        }
    }
}
