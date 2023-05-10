/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import javax.swing.JOptionPane;
import model.PasswordEncryptor;
import static model.PasswordEncryptor.encryptPassword;
import model.User;
import model.UserDAO;
import view.RegisterView;

/**
 *
 * @author joeziojr
 */
public class RegisterService {
    
    private final RegisterView registerView;
    private final UserDAO userDAO;

    public RegisterService(RegisterView register) {
        this.registerView = register;
        this.userDAO = new UserDAO();
    }
    
    
    //Método de cadastro
    public void cadastrar(){
        
        if (validarCampos()){
            User user = new User();
            user.setEmail(this.registerView.getTxtEmail().getText());
            user.setName(this.registerView.getTxtName().getText());
            user.setPassword(encryptPassword(this.registerView.getTxtPassword().getText()));
            
            userDAO.insert(user);
            JOptionPane.showMessageDialog(registerView, "User cadastrado com sucesso");
            
        }
        
        
    }
    
    
    /*Método que confere se os dados foram inseridos e se as senhas batem
        Ele ainda não verifica a existência do usuário no banco*/
    
    public boolean validarCampos(){
        
        char[] senha = this.registerView.getTxtPassword().getPassword();
        char[] recuperar = this.registerView.getTxtPasswordConfirm().getPassword();
        
        
        if ((this.registerView.getTxtEmail().getText().equals("") == false) &&
            (this.registerView.getTxtName().getText().equals("") == false) &&
            (this.registerView.getTxtPassword().getText().equals("") == false) &&
            (senha == recuperar)){
            return true;
            
        }
        else if((this.registerView.getTxtEmail().getText().equals("") == false) &&
                (this.registerView.getTxtName().getText().equals("") == false) &&
                (this.registerView.getTxtPassword().getText().equals("") == false) &&
                (senha != recuperar)){
            JOptionPane.showMessageDialog(registerView, "senhas não coincidem");
            return false;
        }else{
            JOptionPane.showMessageDialog(registerView, "Há campos vazios");
            return false;
        }
        
    }
    
    
    
    
}
