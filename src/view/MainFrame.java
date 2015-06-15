package view;

import controller.Controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JViewport;
import model.graph.TypeEdge;
import model.graph.TypeNode;

/**
 * Fenêtre principale contenant la map pour afficher la simulation et les boutons neccessaires pour ajouter des robots, des arrêtes et des noeuds.
 * Elle contient le controller pour afficher le graph et les éléments nécessaires. 
 * @author De Sousa Jérémy
 */
public class MainFrame extends javax.swing.JFrame implements Observer {

    private Controller controller;
    private MapPanel map;

    /**
     * Creates new form Frame
     *
     * @param controller : le controleur
     */
    public MainFrame(Controller controller) {
        applyTheme();

        this.controller = controller;
        this.map = new MapPanel(this.getController().getManager().getGraph());
        initComponents();
//        this.scrollMap.setSize(this.map.getPreferredSize());
//        this.scrollMap.setPreferredSize(this.map.getPreferredSize());
        scrollMap.setViewportView(this.map);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Ajoute le listes pour le type des arrêtes et des noeuds
        completJCombo();

        this.setVisible(true);
        
        //Ajoute les actionneurs
        btnBack.addActionListener(this.controller);
        btnExport.addActionListener(this.controller);
        btnImport.addActionListener(this.controller);
        btnPlay.addActionListener(this.controller);
        btnAddElement.addActionListener(this.controller);
        btnStop.addActionListener(this.controller);
        btnReset.addActionListener(this.controller);
        getMap().addMouseListener(this.controller);
        menuInstruction.addActionListener(this.controller);

        //Ajoute la bouton radio pour choisir le type de robot
        completRobotType();

        //Rend la fenêtre non redimensionnable
        this.setResizable(false);
        //Centre la fenêtre
        this.setLocationRelativeTo(null);
        
        desactivateSimulation();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupTypeRobot = new javax.swing.ButtonGroup();
        panel1 = new java.awt.Panel();
        typeEdge = new javax.swing.JComboBox();
        typeNode = new javax.swing.JComboBox();
        labelTypeEdge = new javax.swing.JLabel();
        labelTypeNode = new javax.swing.JLabel();
        btnExport = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        labelAlgo = new javax.swing.JLabel();
        comboAlgo = new javax.swing.JComboBox();
        panel2 = new java.awt.Panel();
        btnStop = new javax.swing.JButton();
        radioBtnOffRoad = new javax.swing.JRadioButton();
        radioBtnFeet = new javax.swing.JRadioButton();
        radioBtnTrackedRobot = new javax.swing.JRadioButton();
        labelTypeRobot = new javax.swing.JLabel();
        radioBtnFire = new javax.swing.JRadioButton();
        btnAddElement = new javax.swing.JButton();
        labelName = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();
        labelIntensity = new javax.swing.JLabel();
        textIntensity = new javax.swing.JTextField();
        scrollMap = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuHelp = new javax.swing.JMenu();
        menuInstruction = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WallE");

        labelTypeEdge.setText("Type edge : ");

        labelTypeNode.setText("Type node : ");

        btnExport.setText("Export");

        btnBack.setText("Back");

        btnPlay.setText("Play");

        btnReset.setActionCommand("Reset");
        btnReset.setLabel("Reset");

        btnImport.setText("Import");

        labelAlgo.setText("Algorithme :");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelTypeNode)
                                    .addComponent(labelAlgo))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(typeNode, 0, 85, Short.MAX_VALUE)
                                    .addComponent(comboAlgo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(labelTypeEdge)
                                .addGap(18, 18, 18)
                                .addComponent(typeEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnReset)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnExport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImport)
                .addGap(5, 5, 5)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTypeEdge)
                    .addComponent(typeEdge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTypeNode)
                    .addComponent(typeNode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAlgo)
                    .addComponent(comboAlgo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnPlay))
                .addGap(4, 4, 4)
                .addComponent(btnReset)
                .addGap(113, 113, 113))
        );

        btnStop.setText("Stop");

        radioBtnOffRoad.setText("OffRoadRobot");

        radioBtnFeet.setText("FeetRobot");

        radioBtnTrackedRobot.setText("TrackedRobot");

        labelTypeRobot.setText("Type robot : ");

        radioBtnFire.setText("Fire");

        btnAddElement.setText("Add");

        labelName.setText("Name :");

        labelIntensity.setText("Intensity (de 1 à 20) :");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(btnAddElement, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(labelName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(radioBtnFeet)
                                        .addComponent(radioBtnTrackedRobot))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnStop))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(radioBtnOffRoad)
                                        .addComponent(labelTypeRobot)
                                        .addGroup(panel2Layout.createSequentialGroup()
                                            .addComponent(radioBtnFire)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(labelIntensity)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(textIntensity, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGap(20, 20, 20))))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelTypeRobot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioBtnOffRoad)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radioBtnFeet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioBtnTrackedRobot))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnStop)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioBtnFire)
                    .addComponent(labelIntensity)
                    .addComponent(textIntensity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddElement)
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        menuHelp.setText("Help");

        menuInstruction.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, 0));
        menuInstruction.setText("Instructions");
        menuHelp.add(menuInstruction);

        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(scrollMap, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollMap, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddElement;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnStop;
    private javax.swing.JComboBox comboAlgo;
    private javax.swing.ButtonGroup groupTypeRobot;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel labelAlgo;
    private javax.swing.JLabel labelIntensity;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelTypeEdge;
    private javax.swing.JLabel labelTypeNode;
    private javax.swing.JLabel labelTypeRobot;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuInstruction;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JRadioButton radioBtnFeet;
    private javax.swing.JRadioButton radioBtnFire;
    private javax.swing.JRadioButton radioBtnOffRoad;
    private javax.swing.JRadioButton radioBtnTrackedRobot;
    private javax.swing.JScrollPane scrollMap;
    private javax.swing.JTextField textIntensity;
    private javax.swing.JTextField textName;
    private javax.swing.JComboBox typeEdge;
    private javax.swing.JComboBox typeNode;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        map.repaint();
    }

    public void paintAll() {
        panel1.repaint();
        panel2.repaint();
        map.repaint();
    }

    /**
     * @return the controller
     */
    public Controller getController() {
        return controller;
    }

    /**
     * @param controller the controller to set
     */
    public void setController(Controller controller) {
        this.controller = controller;
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
     * @return the typeEdge
     */
    public javax.swing.JComboBox getTypeEdge() {
        return typeEdge;
    }

    /**
     * @param typeEdge the typeEdge to set
     */
    public void setTypeEdge(javax.swing.JComboBox typeEdge) {
        this.typeEdge = typeEdge;
    }

    /**
     * @return the typeNode
     */
    public javax.swing.JComboBox getTypeNode() {
        return typeNode;
    }

    /**
     * @param typeNode the typeNode to set
     */
    public void setTypeNode(javax.swing.JComboBox typeNode) {
        this.typeNode = typeNode;
    }

    private void applyTheme() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /**
     * Ajoute les listes pour le type des arrêtes et des noeuds
     */
    private void completJCombo() {
        for(TypeNode tN : TypeNode.values()){
            typeNode.addItem(tN);
        }
        
        for(TypeEdge tE : TypeEdge.values()){
            typeEdge.addItem(tE);
        }

        String[] typeAlgo = {"BREADTHFIRST", "DEPTHFIRST"};
        getComboAlgo().addItem("BREADTHFIRST");
        getComboAlgo().addItem("DEPTHFIRST");
    }

    /**
     * @return the panel1
     */
    public java.awt.Panel getPanel1() {
        return panel1;
    }

    /**
     * @param panel1 the panel1 to set
     */
    public void setPanel1(java.awt.Panel panel1) {
        this.panel1 = panel1;
    }

    /**
     * @return the panel2
     */
    public java.awt.Panel getPanel2() {
        return this.panel2;
    }

    /**
     * @param panel2 the panel2 to set
     */
    public void setPanel2(java.awt.Panel panel2) {
        this.panel2 = panel2;
    }

    /**
     * @return the groupTypeRobot
     */
    public javax.swing.ButtonGroup getGroupTypeRobot() {
        return groupTypeRobot;
    }

    /**
     * @param groupTypeRobot the groupTypeRobot to set
     */
    public void setGroupTypeRobot(javax.swing.ButtonGroup groupTypeRobot) {
        this.groupTypeRobot = groupTypeRobot;
    }

    /**
     * @return the textName
     */
    public javax.swing.JTextField getTextName() {
        return textName;
    }

    /**
     * @param textName the textName to set
     */
    public void setTextName(javax.swing.JTextField textName) {
        this.textName = textName;
    }

    /**
     * Ajoute les boutons radio pour choisir le type de robot.
     */
    private void completRobotType() {
        radioBtnFeet.setActionCommand(radioBtnFeet.getText());
        radioBtnOffRoad.setActionCommand(radioBtnOffRoad.getText());
        radioBtnTrackedRobot.setActionCommand(radioBtnTrackedRobot.getText());
        radioBtnFire.setActionCommand(radioBtnFire.getText());
        groupTypeRobot.add(radioBtnFeet);
        groupTypeRobot.add(radioBtnOffRoad);
        groupTypeRobot.add(radioBtnTrackedRobot);
        groupTypeRobot.add(radioBtnFire);
        radioBtnFeet.setSelected(true);
    }

    /**
     * @return the textIntensity
     */
    public javax.swing.JTextField getTextIntensity() {
        return textIntensity;
    }

    /**
     * @param textIntensity the textIntensity to set
     */
    public void setTextIntensity(javax.swing.JTextField textIntensity) {
        this.textIntensity = textIntensity;
    }

    /**
     * @return the comboAlgo
     */
    public javax.swing.JComboBox getComboAlgo() {
        return comboAlgo;
    }

    /**
     * @param comboAlgo the comboAlgo to set
     */
    public void setComboAlgo(javax.swing.JComboBox comboAlgo) {
        this.comboAlgo = comboAlgo;
    }
    
    /**
     * Active les boutons nécéssaires à la simulation et désactive les autres.
     */
    public void activateSimulation(){
        btnAddElement.setEnabled(true);
        btnBack.setEnabled(false);
        btnExport.setEnabled(false);
        btnImport.setEnabled(false);
        btnPlay.setEnabled(false);
        btnReset.setEnabled(true);
        btnStop.setEnabled(true);                
    }
    
    /**
     * Désactive les boutons nécéssaires à la simulation et active les autres.
     */
    public void desactivateSimulation(){
        btnAddElement.setEnabled(true);
        btnBack.setEnabled(true);
        btnExport.setEnabled(true);
        btnImport.setEnabled(true);
        btnPlay.setEnabled(true);
        btnReset.setEnabled(true);
        btnStop.setEnabled(false);                
    }
}
