/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import javax.swing.JOptionPane;
import model.User;
import model.UserDAO;
import view.LoginView;
import view.RequestCodeView;

/**
 *
 * @author joeziojr
 */
public class LoginService {
    
    private LoginView loginView;
    private final UserDAO userDAO;

    public LoginService(LoginView login) {
        this.loginView = login;
        this.userDAO = new UserDAO();
    }
    
    //Método que  verifica a existência de uma conta, verificando o email
    public boolean fazerLogin(){
        if(validarCampos()){
            User us = userDAO.pesquisarPorEmail(loginView.getTxtName().getText(),
                    loginView.getTxtName().getText(), loginView.getTxtPassword().getText());
            if(us.getId() > 0){
                return true;
            }
            if(us.getId() == 0){
                JOptionPane.showMessageDialog(loginView, "User não cadastrado");
                return false;
            }
        }
        return false;
    }
    
   
    public boolean validarCampos(){
        if (this.loginView.getTxtName().getText().equals("") || (this.loginView.getTxtPassword().getText().equals(""))){
            JOptionPane.showMessageDialog(loginView, "Há campos vazios");
            return false;
            //Implementar lógica aqui
        }
        else{
            return true;
        }
    }
    
   
    
}
