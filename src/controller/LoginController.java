/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.LoginService;
import view.LoginView;
import view.MainView;
import view.PasswordRecoveryView;

/**
 *
 * @author joeziojr
 */
public class LoginController implements ActionListener {
    
    private LoginView telaLogin;
    private LoginService loginService;
    private TimerController telaPrincipal;
    private RegisterController telaDeCadastro;
    private RecoveryController telaRecuperarSenha;

    
    public LoginController() {
        
        telaLogin = new LoginView(null, true);
        loginService = new LoginService(telaLogin);
        telaLogin.getBtnLogin().addActionListener(this);
        telaLogin.getBtnPasswordRecover().addActionListener(this);
        telaLogin.getBtnRegister().addActionListener(this);
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(telaLogin.getBtnLogin())){
            if(loginService.fazerLogin()){
                telaLogin.dispose();
                telaPrincipal = new TimerController();
            }
        }
        else if(evento.getSource().equals(telaLogin.getBtnRegister())){
            telaLogin.dispose();
            telaDeCadastro = new RegisterController();
        }
        else if(evento.getSource().equals(telaLogin.getBtnPasswordRecover())){
            telaLogin.dispose();
            telaRecuperarSenha = new RecoveryController();
        }
    }

  
  
    
    
}
