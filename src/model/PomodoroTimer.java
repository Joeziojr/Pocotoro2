package model;

public class PomodoroTimer {
    //atributos
    private int seconds;
    private final int idPomodoro;
    private int duration;
    
    //construtor
    public PomodoroTimer(int duration, int idPomodoro) {
        this.seconds = duration * 60;
        this.idPomodoro = idPomodoro;
    }
    
    //getters e setters
    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getIdPomodoro() {
        return idPomodoro;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    
    
    
}
