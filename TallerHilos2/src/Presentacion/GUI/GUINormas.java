package Presentacion.GUI;

import Presentacion.JpanelBackgroud.JpanelBackNorms;
import persistencia.DAONormas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUINormas extends JFrame implements ActionListener{
    private JpanelBackNorms panel;
    private JPanel panelNormas;
    private JLabel[] norms;
    private JButton btnMenu;
    private DAONormas daoNormas;

    public GUINormas() {
        setTitle("Normas");
        setSize(614,367);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        beginComponents();
        llenarNormas();
        addComponents();
    }

    private void llenarNormas() {
        ArrayList<String> normas = daoNormas.dataNorms();

        for (int i = 0; i < normas.size(); i++) {
            norms[i].setText(normas.get(i));
        }
    }

    private void addComponents() {
        panel.setLayout(null);

        panelNormas.setForeground(Color.WHITE);
        panelNormas.setBounds(60,70,480,180);
        panelNormas.setOpaque(false);

        btnMenu.setBounds(60,272,500,35);
        btnMenu.setOpaque(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setBorderPainted(false);
        btnMenu.addActionListener(this);

        for (int i = 0; i < norms.length; i++) {
            panelNormas.add(norms[i]);
        }

        panel.add(panelNormas);
        panel.add(btnMenu);

        getContentPane().add(panel);
    }

    private void beginComponents() {
        daoNormas = new DAONormas();
        panel = new JpanelBackNorms("Imagenes/normas.png");
        panelNormas = new JPanel(new GridLayout(daoNormas.dataNorms().size(),1));
        norms = new JLabel[daoNormas.dataNorms().size()];
        btnMenu = new JButton();

        for (int i = 0; i < norms.length; i++) {
            norms[i] = new JLabel();
            norms[i].setForeground(Color.WHITE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object componente = e.getSource();

        if (componente.equals(btnMenu)){
            GUIInicio guiInicio = new GUIInicio();
            guiInicio.setVisible(true);
            this.dispose();
        }
    }
}
