/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.Properties;
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

/**
 *
 * @author bia-eduao
 */
public class RecoveryService {
    
    private PasswordRecoveryView passwordRecoveryView;
    private RequestCodeView requestCodeView;
    private final UserDAO userDAO;
    private User user;
    private int recoveryCode;

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
        this.user = userDAO.pesquisarUser(this.user.getName(), this.user.getEmail());
        
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
        /** Parâmetros de conexão com servidor Yahoo */
        prop.put("mail.smtp.host", "smtp.mail.yahoo.com");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "25");
        
        Session session = Session.getDefaultInstance(prop,
                new Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication("pocotoroteam@yahoo.com", "pocotopocotopocoto");
                    }
                });
        
        session.setDebug(true);
        
        try{
            
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("pocotoroteam@yahoo.com"));
            
            Address[] toUser = InternetAddress.parse(this.user.getEmail());
        
          //  msg.setRecipient(Message.RecipientType.TO, toUser);
            msg.setSubject("Pocotoro: Recuperação de Senha");
            msg.setText("Seu código de recuperação é: " + this.recoveryCode);
            
            Transport.send(msg);
            
            System.out.println("Código Enviado");
        } catch(MessagingException e){
            throw new RuntimeException(e);
        }
    }
}
