package Presentacion.GUI;

import Logic.GameLogic;
import Presentacion.JpanelBackgroud.JpanelBackGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGame extends JFrame implements ActionListener {
    private JpanelBackGame panel;
    private JLabel soles;
    private JLabel image1;
    private JLabel image2;
    private JLabel image3;
    private JButton btnStop1;
    private JButton btnStop2;
    private JButton btnStop3;
    private JButton btnFinishGame;
    private JButton btnPlay;
    private int saldoSoles;
    private String namePlayer;

    private String[] rutasImagenes = {"Imagenes/diamante.png",
            "Imagenes/girasol.png",
            "Imagenes/hielagizante.png",
            "Imagenes/lanzagisante.png",
            "Imagenes/sol.png"};
    private GameLogic gameLogic;

    public GUIGame(int saldoSoles, String namePlayer){
        this.saldoSoles = saldoSoles;
        this.namePlayer = namePlayer;
        setTitle("Tragaperras");
        setSize(830,202);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        beginComponents();
        addComponents();
    }

    private void addComponents() {
        panel.setLayout(null);

        soles.setText(Integer.toString(saldoSoles));
        soles.setBounds(80,85,50,50);
        soles.setForeground(Color.BLACK);
        soles.setFont(new Font("Arial", Font.BOLD, 20));

        image1.setBounds(330,16,90,110);
        image2.setBounds(430,16,90,110);
        image3.setBounds(530,16,90,110);

        btnStop1.setBounds(340,129,70,20);
        btnStop1.addActionListener(this);

        btnStop2.setBounds(440,129,70,20);
        btnStop2.addActionListener(this);

        btnStop3.setBounds(540,129,70,20);
        btnStop3.addActionListener(this);

        btnFinishGame.setBounds(55,130,70,20);
        btnFinishGame.addActionListener(this);

        btnPlay.setBounds(750,30,80,120);
        btnPlay.setOpaque(false);
        btnPlay.setContentAreaFilled(false);
        btnPlay.setBorderPainted(false);
        btnPlay.addActionListener(this);

        inicializarHilo();

        panel.add(soles);
        panel.add(image1);
        panel.add(image2);
        panel.add(image3);
        panel.add(btnStop1);
        panel.add(btnStop2);
        panel.add(btnStop3);
        panel.add(btnFinishGame);
        panel.add(btnPlay);

        getContentPane().add(panel);
    }

    private void inicializarHilo() {
        Thread gameThread = new Thread(gameLogic);
        gameThread.start();
    }

    public void loadImage (int index, String rutaImagen){
        ImageIcon icon = new ImageIcon(rutaImagen);

        if (index ==0){
            image1.setIcon(icon);
        } else if (index == 1) {
            image2.setIcon(icon);
        } else if (index == 2) {
            image3.setIcon(icon);
        }
    }

    private void beginComponents() {
        gameLogic = new GameLogic(this,rutasImagenes);

        panel = new JpanelBackGame("Imagenes/game.png");
        soles = new JLabel("0");
        image1 = new JLabel();
        image2 = new JLabel();
        image3 = new JLabel();
        btnStop1 = new JButton("Stop");
        btnStop2 = new JButton("Stop");
        btnStop3 = new JButton("Stop");
        btnFinishGame = new JButton("Salir");
        btnPlay = new JButton("Click");
    }

    public void calcularSoles(int cantidad,boolean sr){
        if (sr){
            saldoSoles += cantidad;
        } else {
            saldoSoles -= cantidad;
        }
        soles.setText(Integer.toString(saldoSoles));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object componente = e.getSource();

        if (componente.equals(btnPlay)){
            if (saldoSoles>=25){
                calcularSoles(25,false);
                this.gameLogic = new GameLogic(this,rutasImagenes);
                inicializarHilo();
            }else {
                JOptionPane.showMessageDialog(null,namePlayer+" No tienes la cantidad de soles disponibles para jugar de nuevo.\nPor favor ingrese creditos.");
                gameLogic.stopGame();
                GUIInicio guiInicio = new GUIInicio();
                guiInicio.setVisible(true);
                this.dispose();
            }
        }

        if (componente.equals(btnStop1)){
            gameLogic.stopThread(0);
        }

        if (componente.equals(btnStop2)){
            gameLogic.stopThread(1);
        }

        if (componente.equals(btnStop3)){
            gameLogic.stopThread(2);
        }

        if (componente.equals(btnFinishGame)){
            JOptionPane.showMessageDialog(null,namePlayer+" Gracias por jugar :3");
            gameLogic.stopGame();
            GUIInicio guiInicio = new GUIInicio();
            guiInicio.setVisible(true);
            this.dispose();
        }
    }
}
