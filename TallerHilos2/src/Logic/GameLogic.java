package Logic;

import Presentacion.GUI.GUIGame;

import javax.swing.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameLogic implements Runnable{
    private boolean isRunning;
    private boolean[] stopFlags = {false,false,false};
    private int[] imageIndices;
    private String[] rutasImagenes;
    private GUIGame guiGame;
    private Timer timer;
    private int milisecons;


    public GameLogic(GUIGame guiGame,String[] rutasImagenes) {
        this.guiGame = guiGame;
        this.rutasImagenes = rutasImagenes;
        isRunning = true;
        imageIndices = new int[3];
        milisecons = 50;

        for (int i = 0; i<3; i++){
            imageIndices[i] = new Random().nextInt(rutasImagenes.length);
        }
        TemporizadorGeneral();
    }

    private void TemporizadorGeneral() {
        timer = new Timer();
        int[] tiempos = {4000, 12000, 19000};

        for (int i = 0; i < tiempos.length; i++) {
            int finalI = i;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    stopThread(finalI);
                }
            },tiempos[i]);
        }
    }


    @Override
    public void run() {
        while (isRunning){
            for (int i = 0; i < 3; i++) {
                if (!stopFlags[i]){
                    changeImage(i);
                }
            }
            try {
                Thread.sleep(milisecons);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (stopFlags[0] && stopFlags[1] && stopFlags[2]){
                isRunning = false;
                verificarCoincidencias();
            }
        }
    }

    private void changeImage(int index) {
        int indexActual = imageIndices[index];
        if (indexActual < rutasImagenes.length-1){
            indexActual++;
        }else {
            indexActual = 0;
        }
        imageIndices[index] = indexActual;
        guiGame.loadImage(index,rutasImagenes[indexActual]);

    }

    private void temporizador(int miliseconds, int index) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();

                milisecons = 150;
                temporizadorFinal(3000,index);
            }
        },miliseconds);
    }

    private void temporizadorFinal(int miliseconds, int index) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();

                stopFlags[index] = true;
            }
        },miliseconds);
    }

    public void stopThread(int index){
        milisecons = 100;
        temporizador(2000, index);
    }

    private void verificarCoincidencias(){
        if ((imageIndices[0] == imageIndices[1]) && (imageIndices[1] == imageIndices[2])){
            JOptionPane.showMessageDialog(null,"Felicidades, hiciste 3 paridades\nObtienes 100 Soles");
            guiGame.calcularSoles(100,true);
        } else if ((imageIndices[0] == imageIndices[1]) || (imageIndices[1] == imageIndices[2]) || (imageIndices[0] == imageIndices[2])) {
            JOptionPane.showMessageDialog(null,"Felicidades, hiciste 2 paridades\nObtienes 50 Soles");
            guiGame.calcularSoles(50,true);
        }else{
            JOptionPane.showMessageDialog(null,"No obtuviste paridades, intenta de nuevo");
        }
    }

    public void stopGame(){
        isRunning = false;
    }
}
