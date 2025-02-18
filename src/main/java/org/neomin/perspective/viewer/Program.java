package org.neomin.perspective.viewer;

import org.neomin.perspective.viewer.graphics.RenderFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Scanner;

public class Program extends JFrame {

    public Program() {
        setTitle("JG-Render - File Select");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g2D) {
                super.paintComponent(g2D);
                g2D.setColor(Color.GRAY);

                g2D.drawLine(300, 150, 500, 150);
                g2D.drawLine(400, 50, 300, 150);
                g2D.drawLine(500, 150, 400, 50);

                g2D.setFont(new Font("BOLD", Font.BOLD, 48));
                g2D.drawString("!", 400, 125);

                g2D.setFont(new Font("BOLD", Font.BOLD, 24));
                g2D.drawString("Select your file:", 300, 250);
                g2D.drawString("Select JSON files to perspective view.", 200, 275);
            }
        };

       // JButton editorButton = new JButton("Open editor");
       // editorButton.setPreferredSize(new Dimension(150, 50));
       // editorButton.addActionListener(new AbstractAction() {
       //     @Override
       //     public void actionPerformed(ActionEvent e) {
       //         new EditorFrame();
       //     }
       // });

        JButton fileButton = new JButton("Select file here");
        fileButton.setPreferredSize(new Dimension(200, 50));
        fileButton.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final JFileChooser chooser = new JFileChooser();
                chooser.showSaveDialog(null);

                final File file = chooser.getSelectedFile();
                final StringBuilder json = new StringBuilder();

                try (final Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNext()) {
                        json.append(scanner.next());
                    }
                } catch (Exception e) {
                    return;
                }

                new RenderFrame(json.toString());
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        //buttonPanel.add(editorButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(150, 10)));
        buttonPanel.add(fileButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Program();
        });
    }
}