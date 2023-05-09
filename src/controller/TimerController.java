package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.ServicePomodoroTimer;
import view.MainView;

/**
 *
 * @author joeziojr
 */
public class TimerController implements ActionListener{
    //atributos
    MainView mainView;
    ServicePomodoroTimer spt;
    
    //contrutor
    public TimerController() {
        this.mainView = new MainView();
        this.spt = new ServicePomodoroTimer(mainView, 25);
        this.mainView.setVisible(true);
        this.mainView.getBtnStart().addActionListener(this);
        this.mainView.getBtnStop().addActionListener(this);
        this.mainView.getBtnLogin().addActionListener(this);
        this.mainView.getBtnSettings().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(mainView.getBtnStart())) {
            spt.start();
            mainView.getBtnStart().setEnabled(false);
        }
        if (ae.getSource().equals(mainView.getBtnStop())) {
            spt.stop();
            mainView.getBtnStart().setEnabled(true);
        }
        if (ae.getSource().equals(mainView.getBtnLogin())) {
        }
        if (ae.getSource().equals(mainView.getBtnSettings())) {
        }
    }
    
    
}
