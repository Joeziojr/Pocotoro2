/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import service.RegisterService;
import view.LoginView;
import view.RegisterView;

/**
 *
 * @author joeziojr
 */
public class RegisterController implements ActionListener {
    
    private RegisterView registro;
    private RegisterService registerService;
    private LoginController telaLogin;

    //Instancio uma tela de cadastro e um service de cadastro
    public RegisterController() {
        registro = new RegisterView(null, true);
        registerService = new RegisterService(registro);
        registro.getBtnRegister().addActionListener(this);
        registro.getBtnLogin().addActionListener(this);
        registro.setVisible(true);

    }
    
    

    @Override
    public void actionPerformed(ActionEvent evento) {
        if  (evento.getSource().equals(registro.getBtnRegister())){
            registerService.cadastrar();
        }
        else if(evento.getSource().equals(registro.getBtnLogin())){
            registro.dispose();
            telaLogin = new LoginController();
        }
    }
    
    
    
}
