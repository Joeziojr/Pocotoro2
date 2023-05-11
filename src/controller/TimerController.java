package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.ServicePomodoroTimer;
import view.MainView;
import view.ConfigView;

/**
 *
 * @author joeziojr
 */
public class TimerController implements ActionListener{
    //atributos
    MainView mainView;
    ConfigView configView;
    ServicePomodoroTimer spt;
    int duracaoPomodoro, duracaoSB, duracaoLB, intervalLB;
    
    //contrutor
    public TimerController() {
        this.mainView = new MainView();
        this.configView = new ConfigView(this.mainView, true);
        
        this.duracaoPomodoro = 25;
        this.duracaoSB = 5;
        this.duracaoLB = 10;
        
        this.spt = new ServicePomodoroTimer(mainView, this.duracaoPomodoro, this.duracaoSB, this.duracaoLB);
        this.spt.setAutoStart(true);
        this.mainView.setVisible(true);
        
        this.mainView.getBtnStart().addActionListener(this);
        this.mainView.getBtnStop().addActionListener(this);
        this.mainView.getBtnLogin().addActionListener(this);
        this.mainView.getBtnSettings().addActionListener(this);
        this.mainView.getBtnReset().addActionListener(this);
        this.configView.getjButtonUpdate().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(mainView.getBtnStart())) {
            if(spt.getCont() != 1){
                spt.defineState();
            }
            else{
                spt.playTimer();
            }
            //mainView.getBtnStart().setEnabled(false);
        }
        if (ae.getSource().equals(mainView.getBtnStop())) {
            spt.stop();
            //mainView.getBtnStart().setEnabled(true);
        }
        if (ae.getSource().equals(mainView.getBtnLogin())) {
        }
        
        if(ae.getSource().equals(mainView.getBtnReset())){
            spt.stop();
            spt.defineState();
        }
        if (ae.getSource().equals(mainView.getBtnSettings())) {
            this.spt.stop();
            
            this.configView.getjSpinnerPomodoro().setValue(this.duracaoPomodoro);
            this.configView.getjSpinnerLB().setValue(this.duracaoLB);
            this.configView.getjSpinnerSB().setValue(this.duracaoSB);
            this.configView.getjSpinnerIntervalLB().setValue(this.intervalLB);
            this.configView.setVisible(true);
            
            
        }
        
        if(ae.getSource().equals(configView.getjButtonUpdate())){
            this.duracaoPomodoro = (int) configView.getjSpinnerPomodoro().getValue();
            this.duracaoSB = (int) configView.getjSpinnerSB().getValue();
            this.duracaoLB = (int) configView.getjSpinnerLB().getValue();
            
            this.spt.stop();
            this.spt = new ServicePomodoroTimer(mainView, this.duracaoPomodoro, this.duracaoSB, this.duracaoLB);

        }
    }
    
    
}
