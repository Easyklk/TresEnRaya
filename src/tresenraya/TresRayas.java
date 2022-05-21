/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tresenraya;

/**
 *
 * @author Easyklk
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TresRayas extends JFrame {

    public TresRayas() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Tres en Raya");
        setBounds(600, 300, 450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LaminaPrincipal laminaPrincipal = new LaminaPrincipal();
        add(laminaPrincipal);
        setResizable(false);
        setVisible(true);
        setIconImage(getIconImage());
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("resources/frameIcon.png"));
        return retValue;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TresRayas().setVisible(true);
        });
    }
}

class LaminaPrincipal extends JPanel {

    JPanel mid = new JPanel();
    boolean turno;
    Caja[][] cuadriculaCasilla = new Caja[3][3];

    public LaminaPrincipal() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        mid.setLayout(new GridLayout(3, 3));
        add(new Arriba(), BorderLayout.NORTH);
        add(mid, BorderLayout.CENTER);
        add(new Abajo(), BorderLayout.SOUTH);
    }

    class Arriba extends JPanel {

        ImageIcon icon = new ImageIcon("jugador1.png");
        ImageIcon iconDos = new ImageIcon("jugador2.png");
        ImageIcon retryIcon = new ImageIcon("retry_icon.png");

        public Arriba() {
            initComponents();
        }

        private void initComponents() {
            setLayout(new FlowLayout());
            JLabel jlTitulo = new JLabel("Bienvenido al Tres en Raya");
            JButton jbStart = new JButton("Comenzar");
            jlTitulo.setFont(new Font("Arial", Font.BOLD, 20));
            jlTitulo.setBorder(new EmptyBorder(10, 10, 10, 60));
            add(jlTitulo);
            add(jbStart);
            jbStart.addActionListener((ActionEvent e) -> {
                turno = false;
                crearBoton();
            });
        }

        class Casilla implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < cuadriculaCasilla.length; i++) {
                    for (int j = 0; j < cuadriculaCasilla.length; j++) {
                        if (e.getSource() == cuadriculaCasilla[i][j]) {
                            if (cambioTurno(cuadriculaCasilla[i][j])) {
                                cuadriculaCasilla[i][j].setPropiedad(1);
                            } else {
                                cuadriculaCasilla[i][j].setPropiedad(2);
                            }
                        }
                    }
                }
                if (comprobarGanar()) {
                    if (turno) {
                        mensaje("¡GANADOR JUGADOR 1!");
                    } else {
                        mensaje("¡GANADOR JUGADOR 2!");
                    }
                } else if (!empate()) {
                    for (int i = 0; i < cuadriculaCasilla.length; i++) {
                        for (int j = 0; j < cuadriculaCasilla.length; j++) {
                            cuadriculaCasilla[i][j].setBackground(Color.red);
                        }
                    }
                    mensaje("¡EMPATE!");
                }
            }

        }

        public void crearBoton() {
            mid.removeAll();
            for (int i = 0; i < cuadriculaCasilla.length; i++) {
                for (int j = 0; j < cuadriculaCasilla.length; j++) {
                    JButton jbCasilla = new JButton();
                    Caja c = new Caja(i, j, 0);
                    cuadriculaCasilla[i][j] = c;
                    mid.add(c);
                    mid.revalidate();
                    cuadriculaCasilla[i][j].addActionListener(new Casilla());
                }
            }
        }

        public boolean cambioTurno(JButton boton) {
            if (turno) {
                boton.setIcon(icon);
                boton.setDisabledIcon(icon);
                boton.setEnabled(false);
                turno = false;
            } else {
                boton.setIcon(iconDos);
                boton.setDisabledIcon(iconDos);
                boton.setEnabled(false);
                turno = true;
            }
            return turno;
        }

        public boolean comprobarGanar() {
            for (int i = 0; i < cuadriculaCasilla.length; i++) {
                if (cuadriculaCasilla[0][i].getPropiedad() == 1 && cuadriculaCasilla[1][i].getPropiedad() == 1 && cuadriculaCasilla[2][i].getPropiedad() == 1) {
                    colorVyH(1, 0);
                    return true;
                }
                if (cuadriculaCasilla[0][i].getPropiedad() == 2 && cuadriculaCasilla[1][i].getPropiedad() == 2 && cuadriculaCasilla[2][i].getPropiedad() == 2) {
                    colorVyH(2, 0);
                    return true;
                }
                if (cuadriculaCasilla[i][0].getPropiedad() == 1 && cuadriculaCasilla[i][1].getPropiedad() == 1 && cuadriculaCasilla[i][2].getPropiedad() == 1) {
                    colorVyH(1, 1);
                    return true;
                }
                if (cuadriculaCasilla[i][0].getPropiedad() == 2 && cuadriculaCasilla[i][1].getPropiedad() == 2 && cuadriculaCasilla[i][2].getPropiedad() == 2) {
                    colorVyH(2, 1);
                    return true;
                }
            }
            if (cuadriculaCasilla[0][0].getPropiedad() == 1 && cuadriculaCasilla[1][1].getPropiedad() == 1 && cuadriculaCasilla[2][2].getPropiedad() == 1) {
                colorDiagonal(1, 0);
                return true;
            }
            if (cuadriculaCasilla[0][0].getPropiedad() == 2 && cuadriculaCasilla[1][1].getPropiedad() == 2 && cuadriculaCasilla[2][2].getPropiedad() == 2) {
                colorDiagonal(2, 0);
                return true;
            }
            if (cuadriculaCasilla[2][0].getPropiedad() == 1 && cuadriculaCasilla[1][1].getPropiedad() == 1 && cuadriculaCasilla[0][2].getPropiedad() == 1) {
                colorDiagonal(1, 1);
                return true;
            }
            if (cuadriculaCasilla[2][0].getPropiedad() == 2 && cuadriculaCasilla[1][1].getPropiedad() == 2 && cuadriculaCasilla[0][2].getPropiedad() == 2) {
                colorDiagonal(2, 1);
                return true;
            }
            return false;
        }

        public void colorVyH(int propiedad, int tipoDiagonal) {
            for (int i = 0; i < cuadriculaCasilla.length; i++) {
                if (cuadriculaCasilla[0][i].getPropiedad() == propiedad && cuadriculaCasilla[1][i].getPropiedad() == propiedad && cuadriculaCasilla[2][i].getPropiedad() == propiedad && tipoDiagonal == 0) {
                    cuadriculaCasilla[0][i].setBackground(Color.GREEN);
                    cuadriculaCasilla[1][i].setBackground(Color.GREEN);
                    cuadriculaCasilla[2][i].setBackground(Color.GREEN);
                }
                if (cuadriculaCasilla[i][0].getPropiedad() == propiedad && cuadriculaCasilla[i][1].getPropiedad() == propiedad && cuadriculaCasilla[i][2].getPropiedad() == propiedad && tipoDiagonal == 1) {
                    cuadriculaCasilla[i][0].setBackground(Color.GREEN);
                    cuadriculaCasilla[i][1].setBackground(Color.GREEN);
                    cuadriculaCasilla[i][2].setBackground(Color.GREEN);
                }
            }

        }

        public void colorDiagonal(int propiedad, int tipoDiagonal) {
            if (cuadriculaCasilla[0][0].getPropiedad() == propiedad && cuadriculaCasilla[1][1].getPropiedad() == propiedad && cuadriculaCasilla[2][2].getPropiedad() == propiedad && tipoDiagonal == 0) {
                cuadriculaCasilla[0][0].setBackground(Color.GREEN);
                cuadriculaCasilla[1][1].setBackground(Color.GREEN);
                cuadriculaCasilla[2][2].setBackground(Color.GREEN);
            }

            if (cuadriculaCasilla[2][0].getPropiedad() == propiedad && cuadriculaCasilla[1][1].getPropiedad() == propiedad && cuadriculaCasilla[0][2].getPropiedad() == propiedad && tipoDiagonal == 1) {
                cuadriculaCasilla[2][0].setBackground(Color.GREEN);
                cuadriculaCasilla[1][1].setBackground(Color.GREEN);
                cuadriculaCasilla[0][2].setBackground(Color.GREEN);
            }
        }

        public boolean empate() {
            for (int i = 0; i < cuadriculaCasilla.length; i++) {
                for (int j = 0; j < cuadriculaCasilla.length; j++) {
                    if (cuadriculaCasilla[i][j].isEnabled() == true) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void mensaje(String jugador) {
            int confirmado = JOptionPane.showConfirmDialog(mid, "¿Volver a jugar?", jugador, JOptionPane.YES_NO_OPTION, 1, retryIcon);
            if (JOptionPane.OK_OPTION == confirmado) {
                turno = false;
                crearBoton();
            } else {
                System.exit(0);
            }
        }
    }

    class Abajo extends JPanel {

        public Abajo() {
            initComponents();
        }

        private void initComponents() {
            setLayout(new FlowLayout(FlowLayout.LEFT));
            setBackground(Color.BLACK);
            JLabel jlNombre = new JLabel("Autor: Isaac Romanillos Deza");
            jlNombre.setFont(new Font("Arial", Font.ITALIC, 12));
            jlNombre.setForeground(Color.WHITE);
            add(jlNombre);
        }
    }
}
