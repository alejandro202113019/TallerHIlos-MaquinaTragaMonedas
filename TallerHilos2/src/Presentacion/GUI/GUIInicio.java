package Presentacion.GUI;

import Presentacion.JpanelBackgroud.JpanelBackInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIInicio extends JFrame implements ActionListener {
    private JpanelBackInicio panel;
    private JTextField txtname;
    private JTextField txtSuns;
    private JButton btnNormas;
    private JButton btnPlayGame;
    private JButton btnExit;

    public GUIInicio() {
        setTitle("Inicio");
        setSize(614, 367);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        beginComponents();
        addComponents();
    }

    private void addComponents() {
        panel.setLayout(null);

        txtname.setBackground(new Color(97, 65, 20));
        txtname.setBounds(60, 140, 490, 22);
        txtname.setFont(new Font("Arial", Font.BOLD, 16));
        txtname.setHorizontalAlignment(SwingConstants.CENTER);
        txtname.setForeground(Color.WHITE);

        txtSuns.setBackground(new Color(97, 65, 20));
        txtSuns.setBounds(60, 207, 490, 22);
        txtSuns.setFont(new Font("Arial", Font.BOLD, 16));
        txtSuns.setHorizontalAlignment(SwingConstants.CENTER);
        txtSuns.setForeground(Color.WHITE);

        btnNormas.setBounds(45, 274, 170, 34);
        btnNormas.setOpaque(false);
        btnNormas.setContentAreaFilled(false);
        btnNormas.setBorderPainted(false);
        btnNormas.addActionListener(this);

        btnPlayGame.setBounds(220, 274, 170, 34);
        btnPlayGame.setOpaque(false);
        btnPlayGame.setContentAreaFilled(false);
        btnPlayGame.setBorderPainted(false);
        btnPlayGame.addActionListener(this);

        btnExit.setBounds(398, 274, 170, 34);
        btnExit.setOpaque(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        btnExit.addActionListener(this);

        panel.add(txtname);
        panel.add(txtSuns);
        panel.add(btnNormas);
        panel.add(btnPlayGame);
        panel.add(btnExit);

        getContentPane().add(panel);
        setVisible(true);
    }

    private void beginComponents() {
        panel = new JpanelBackInicio("Imagenes/inicio.png");
        txtname = new JTextField();
        txtSuns = new JTextField("0");
        btnNormas = new JButton();
        btnPlayGame = new JButton();
        btnExit = new JButton();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object componente = e.getSource();

        if (componente.equals(btnNormas)) {
            GUINormas guiNormas = new GUINormas();
            guiNormas.setVisible(true);
            this.dispose();
        }

        if (componente.equals(btnPlayGame)) {
            if (!txtname.getText().isEmpty()) {
                if (!txtSuns.getText().isEmpty()) {
                    try {
                        int saldoSoles = Integer.parseInt(txtSuns.getText());

                        if (saldoSoles >= 100) {
                            int nuevoSaldo = saldoSoles - 25;
                            GUIGame guiGame = new GUIGame(nuevoSaldo,txtname.getText());
                            guiGame.setVisible(true);
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "No tienes suficientes soles para jugar");
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "El valor ingresado por apostar no es válido.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre");
            }
        }

        if (componente.equals(btnExit)) {
            JOptionPane.showMessageDialog(null, "Gracias por utilizar nuestros servicios");
            System.exit(0);
        }
    }
}
