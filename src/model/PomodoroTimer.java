package model;

public class PomodoroTimer {
    //atributos
    private int seconds;
    
    //construtor
    public PomodoroTimer(int duration) {
        this.seconds = duration * 60;
    }
    
    //getters e setters
    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    
}