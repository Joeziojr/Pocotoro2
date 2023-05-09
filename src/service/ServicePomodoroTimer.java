package service;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import model.PomodoroTimer;
import view.MainView;

/**
 *
 * @author gabriel
 */
public class ServicePomodoroTimer {
    //atributos
    private MainView mainView;
    private Timer timer;
    private int seconds;
    private PomodoroTimer pomodoro;
    
    //construtor
    public ServicePomodoroTimer(MainView mainView, int duracao) {
        this.mainView = mainView;
        this.pomodoro = new PomodoroTimer(duracao);
        this.seconds = pomodoro.getSeconds();
    }
    
    //métodos
    public void start() {
        this.timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (seconds <= 0) {
                    timer.cancel();
                    Toolkit.getDefaultToolkit().beep();
                    System.out.println("Pomodoro concluído!");
                } else {
                    int minutes = seconds / 60;
                    int remainingSeconds = seconds % 60;
                    if (seconds > 0) { 
                        //tarefa a ser realizada
                        System.out.printf("Tempo restante: %d minutos %d segundos%n", minutes, remainingSeconds);
                        mainView.getLblMinutes().setText(String.format("%02d", minutes));
                        mainView.getLblSeconds().setText(String.format("%02d", remainingSeconds));
                    }
                    seconds--;
                }
            }
        }, 0, 1000);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            System.out.println("Temporizador parado.");
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
