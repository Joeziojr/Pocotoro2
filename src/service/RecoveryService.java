/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.Properties;
import java.util.Set;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import model.User;
import model.UserDAO;
import view.RequestCodeView;
import view.PasswordRecoveryView;
//import javax.activation.DataHandler;

/**
 *
 * @author bia-eduao
 */
public class RecoveryService {
    
    private PasswordRecoveryView passwordRecoveryView;
    private RequestCodeView requestCodeView;
    private final UserDAO userDAO;
    //private User user;
    private int recoveryCode;
    
    User user = new User();

    public RecoveryService(PasswordRecoveryView passwordRecoveryView, RequestCodeView requestCodeView) {
        this.passwordRecoveryView = passwordRecoveryView;
        this.requestCodeView = requestCodeView;
        this.userDAO = new UserDAO();
    }
    
    
    //verifica a existência de um usuário a partir do nome ou email, sem a necessidade da senha
    public boolean verificarUser(){
        //User user = new User();
        this.user.setName(this.requestCodeView.getTxtName().getText());
        this.user.setEmail(this.requestCodeView.getTxtName().getText());
        this.user = userDAO.pesquisarUser(this.user.getName());
        
        if(this.user.getName().equals(this.requestCodeView.getTxtName().getText()) || 
                this.user.getEmail().equals(this.requestCodeView.getTxtName().getText())){
            return true;
        }
        return false;
    }
    
    
    public void enviarCodigo(){
        if(!this.requestCodeView.getTxtName().getText().equals("")){
            if(verificarUser()){
                this.recoveryCode = (int)(Math.random()*1000000);
                enviarCodigoPorEmail();
                JOptionPane.showMessageDialog(requestCodeView, "Código de recuperação"
                        + " enviado para o seu e-mail.");
                this.requestCodeView.setVisible(false);
                this.passwordRecoveryView.setVisible(true);
            } else{
                JOptionPane.showConfirmDialog(requestCodeView, "Usuário não encontrado. "
                        + "Certifique-se de tê-lo escrito corretamente.", "Usuário não encontrado", 0);
            }
            
        } else {
            JOptionPane.showMessageDialog(requestCodeView, "Por favor, informe seu nome"
                    + "ou email.", "Usuário não informado",0);
        }
    }
    
    
    public void enviarCodigoPorEmail(){
        
        Properties prop = new Properties();
        /** Parâmetros de conexão com servidor gmail*/
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(prop,
                new Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication("pocotoroconfig@gmail.com", "bhfdrrbgofusxzyx");
                    }
                });
        
        session.setDebug(true);
        
        try{
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("pocotoroconfig@gmail.com"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(this.user.getEmail()));
            msg.setSubject("Pocotoro: Recuperação de Senha");
            msg.setText("Seu código de recuperação é: " + this.recoveryCode);
            
            Transport.send(msg);
            
            System.out.println("Código Enviado");
        } catch(MessagingException e){
            throw new RuntimeException(e);
        }
    }
    
    
    public void redefinirSenha(){
     
        if(passwordRecoveryView.getTxtRecoveryCode().equals("")){
            JOptionPane.showMessageDialog(passwordRecoveryView, "Informe o código de recuperação", "Erro", 0);
        } else {
            int codigo = Integer.parseInt(passwordRecoveryView.getTxtRecoveryCode().getText());
            
            if(this.recoveryCode == codigo){
                
                if(passwordRecoveryView.getTxtNewPassword().getText().equals("") || passwordRecoveryView.getTxtPasswordConfirm().getText().equals("")){
                    
                    JOptionPane.showMessageDialog(requestCodeView, "Por favor, preencha todos os campos.");
                
                } else if(passwordRecoveryView.getTxtNewPassword().getText().equals(passwordRecoveryView.getTxtPasswordConfirm().getText())){
                    
                    String usuario = this.user.getName();
                    this.user = new User();
                    user.setName(usuario);
                    user.setPassword(passwordRecoveryView.getTxtNewPassword().getText());
                    userDAO.alterarSenha(user);
                    
                    JOptionPane.showMessageDialog(requestCodeView, "Senha redefinida com sucesso!");
                    passwordRecoveryView.dispose();
                } else {
                    JOptionPane.showMessageDialog(requestCodeView, "Senhas não conferem", "Erro", 0);
                }
            }
        }
    }
}
