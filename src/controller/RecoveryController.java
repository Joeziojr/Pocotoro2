/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.RecoveryService;
import view.PasswordRecoveryView;
import view.RequestCodeView;

/**
 *
 * @author joeziojr
 */
public class RecoveryController implements ActionListener{
    
    private PasswordRecoveryView telaRecuperarSenha;
    private RecoveryService servico;
    private RequestCodeView requestCodeView;
    private LoginController telaLogin;

    public RecoveryController() {
        telaRecuperarSenha = new PasswordRecoveryView();
        requestCodeView = new RequestCodeView(telaRecuperarSenha, true);
        servico = new RecoveryService(telaRecuperarSenha, requestCodeView);
        
        telaRecuperarSenha.getBtnReset().addActionListener(this);
        requestCodeView.getBtnCancel().addActionListener(this);
        requestCodeView.getBtnSendCode().addActionListener(this);
        
        requestCodeView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(requestCodeView.getBtnSendCode())){
           // servico.enviarCodigo();
            requestCodeView.dispose();
            telaRecuperarSenha.setVisible(true);
            
        }else if(evento.getSource().equals(requestCodeView.getBtnCancel())){
            requestCodeView.dispose();
            telaLogin = new LoginController();
            
        }else if (evento.getSource().equals(telaRecuperarSenha.getBtnReset())){
           //A ser desenvolvido
            
        }
        
    }
    
    
    
}
