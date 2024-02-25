package model;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class tess extends JFrame {
    public tess() {
        setTitle("Panel Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));

        JPanel mainPanel = createMainPanel("ROBOT 1");
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createMainPanel(String robotName) {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Création du label "ROBOT" avec le nom du robot comme en-tête
        JLabel headerLabel = new JLabel(robotName, JLabel.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Création du JTextArea dans le côté droit
        JTextArea textArea = new JTextArea(10, 10);
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Création de trois petits tableaux dans le côté gauche
        JPanel tablePanel = new JPanel(new GridLayout(3, 1));
        for (int i = 0; i < 3; i++) {
            JPanel rowPanel = new JPanel(new FlowLayout());
            JTextArea cellTextArea = new JTextArea(2, 10);
            Border border = BorderFactory.createLineBorder(Color.BLACK); // Bordure noire
            cellTextArea.setBorder(border);
            rowPanel.add(cellTextArea, BorderLayout.CENTER);
            tablePanel.add(rowPanel);
        }
        mainPanel.add(tablePanel, BorderLayout.WEST);

        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            tess example = new tess();
            example.setVisible(true);
        });
    }
}
