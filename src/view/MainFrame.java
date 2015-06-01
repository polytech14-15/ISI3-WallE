/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

/**
 *
 * @author Jérémy
 */
public class MainFrame extends JFrame implements Observer{

        public static final Dimension VGAP = new Dimension(1, 5);
    public static final Dimension HGAP = new Dimension(5, 1);

    private MapPanel map;
    private JComboBox typeRobot;
    private JComboBox typeEdge;
    private JComboBox typeNode;
    private Controller controller;

    public void quitter() {
        System.exit(0);
    }

    public MainFrame(Controller controller) {
        this.controller = controller;
        this.logoInit();
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
            }
        });
    }


    public void addMenuItem(JMenu m, String label, String command, int key) {
        JMenuItem menuItem;
        menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(controller);
        if (key > 0) {
            if (key != KeyEvent.VK_DELETE) {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            } else {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
            }
        }
    }

    //utilitaires pour installer des boutons et des menus
    public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton) p.add(new JButton(name));
        } else {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon(u);
                b = (JButton) p.add(new JButton(im));
            } else {
                b = (JButton) p.add(new JButton(name));
            }
            b.setActionCommand(name);
        }
        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0, 0, 0, 0));
        b.addActionListener(controller);
    }


    public void logoInit() {
        getContentPane().setLayout(new BorderLayout(10, 10));

        // Boutons
        JToolBar toolBar = new JToolBar();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(toolBar);

        getContentPane().add(buttonPanel, BorderLayout.EAST);

        addButton(toolBar, "Export", "Export", null);
        addButton(toolBar, "Back", "Back", null);
        addButton(toolBar, "Play", "Play", null);
//
//        toolBar.add(Box.createRigidArea(HGAP));

//        String[] typeRobots = {"a", "b", "c"};
        // Create the combo box
//        toolBar.add(Box.createRigidArea(HGAP));
//        JLabel typeRobot = new JLabel("   Type : ");
//        toolBar.add(typeRobot);
//        setTypeRobot(new JComboBox(typeRobots));
//        toolBar.add(getTypeRobot());
        toolBar.add(Box.createRigidArea(HGAP));

        // Ajoute le type des arrêtes
        String[] typeEdge = {"a", "b", "c"};
        toolBar.add(Box.createRigidArea(HGAP));
        JLabel typeEdgeLabel = new JLabel("   Type arrêtes : ");
        toolBar.add(typeEdgeLabel);
        setTypeEdge(new JComboBox(typeEdge));
        toolBar.add(getTypeEdge());

        // Ajoute le type des noeuds
        String[] typeNode = {"1", "2", "3"};
        toolBar.add(Box.createRigidArea(HGAP));
        JLabel typeNodeLabel = new JLabel("   Type noeuds : ");
        toolBar.add(typeNodeLabel);
        setTypeNode(new JComboBox(typeNode));
        toolBar.add(getTypeNode());

        // Menus
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);    // on installe le menu bar
        JMenu menuFile = new JMenu("File"); // on installe le premier menu
        menubar.add(menuFile);

        addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        JMenu menuCommandes = new JMenu("Commandes"); // on installe le premier menu
        menubar.add(menuCommandes);
        addMenuItem(menuCommandes, "Avancer", "Avancer", -1);

        JMenu menuHelp = new JMenu("Aide"); // on installe le premier menu
        menubar.add(menuHelp);
        addMenuItem(menuHelp, "Aide", "Help", -1);
        addMenuItem(menuHelp, "A propos", "About", -1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setMap(new MapPanel());
        getMap().setBackground(Color.white);
        getMap().setSize(new Dimension(600, 400));
        getMap().setPreferredSize(new Dimension(600, 400));

        getContentPane().add(getMap(), "Center");
        getMap().addMouseListener(controller);

        pack();
        setVisible(true);
    }



    /**
     * @return the map
     */
    public MapPanel getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(MapPanel map) {
        this.map = map;
    }

    /**
     * @return the typeRobot
     */
    public JComboBox getTypeRobot() {
        return typeRobot;
    }

    /**
     * @param typeRobot the typeRobot to set
     */
    public void setTypeRobot(JComboBox typeRobot) {
        this.typeRobot = typeRobot;
    }

    /**
     * @return the typeEdge
     */
    public JComboBox getTypeEdge() {
        return typeEdge;
    }

    /**
     * @param typeEdge the typeEdge to set
     */
    public void setTypeEdge(JComboBox typeEdge) {
        this.typeEdge = typeEdge;
    }

    /**
     * @return the typeNode
     */
    public JComboBox getTypeNode() {
        return typeNode;
    }

    /**
     * @param typeNode the typeNode to set
     */
    public void setTypeNode(JComboBox typeNode) {
        this.typeNode = typeNode;
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
