package org.neomin.perspective.viewer.graphics;

import lombok.AllArgsConstructor;
import org.neomin.perspective.viewer.objects.Geometry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

@AllArgsConstructor
public class RenderPanel extends JPanel implements KeyListener {

    private static final int SCALE = 500;
    private static final int DISTANCE = 500;
    private static final int FRAME_RATE = 60;

    private final Geometry geometry;
    private int x, y, rotX, rotY, dist;

    public RenderPanel(Geometry geometry) {
        this.geometry = geometry;
        final Timer timer = new Timer(1000 / FRAME_RATE, e -> repaint());
        timer.start();
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    private int[] project(int x, int y, int z) {
        int scale = SCALE - dist;
        int x2D = (x * scale) / (z + DISTANCE) + (getWidth() / 2);
        int y2D = (y * scale) / (z + DISTANCE) + (getHeight() / 2);
        return new int[]{x2D, y2D};
    }

    private int[] rotate(int x, int y, int z, int angleX, int angleY) {
        double radX = Math.toRadians(angleX);
        double radY = Math.toRadians(angleY);

        double cosX = Math.cos(radX);
        double sinX = Math.sin(radX);

        double cosY = Math.cos(radY);
        double sinY = Math.sin(radY);

        int xRotY = (int) (x * cosY - z * sinY);
        int zRotY = (int) (x * sinY + z * cosY);

        int yRotX = (int) (y * cosX - zRotY * sinX);
        int zRotX = (int) (y * sinX + zRotY * cosX);

        return new int[]{xRotY, yRotX, zRotX};
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2D.setFont(new Font("BOLD", Font.BOLD, 18));
        g2D.drawString("(↑) To up", 675, 500);
        g2D.drawString("(↓) To Down", 675, 525);
        g2D.drawString("(→) To Right", 675, 550);
        g2D.drawString("(←) To Left", 675, 575);

        g2D.drawString("(O ↺) Rotate X", 520, 500);
        g2D.drawString("(L ↻) Rotate Y", 520, 525);
        g2D.drawString("(U ⨁) Zoom out", 520, 550);
        g2D.drawString("(H ⨂) Zoom in", 520, 575);

        int loaded_face = 0;


        if (geometry.getFaces() == null || Arrays.asList(geometry.getFaces()).isEmpty()) {
            for (int[] edge : geometry.getEdges()) {
                int[] rotated1 = rotate(geometry.getVertex()[edge[0]][0], geometry.getVertex()[edge[0]][1], geometry.getVertex()[edge[0]][2], rotX, rotY);
                int[] rotated2 = rotate(geometry.getVertex()[edge[1]][0], geometry.getVertex()[edge[1]][1], geometry.getVertex()[edge[1]][2], rotX, rotY);

                int[] point1 = project(rotated1[0], rotated1[1], rotated1[2]);
                int[] point2 = project(rotated2[0], rotated2[1], rotated2[2]);

                g2D.drawLine(point1[0] + x, point1[1] + y, point2[0] + x, point2[1] + y);
            }
        } else {
            for (int[] face : geometry.getFaces()) {
                int[] xPoints = new int[face.length];
                int[] yPoints = new int[face.length];

                for (int i = 0; i < face.length; i++) {
                    int[] rotated = rotate(geometry.getVertex()[face[i]][0], geometry.getVertex()[face[i]][1], geometry.getVertex()[face[i]][2], rotX, rotY);
                    int[] projected = project(rotated[0], rotated[1], rotated[2]);
                    xPoints[i] = projected[0] + x;
                    yPoints[i] = projected[1] + y;
                }

                if (geometry.getColors() != null || !Arrays.asList(geometry.getColors()).isEmpty()) {
                    g2D.setColor(new Color(geometry.getColors()[loaded_face][0], geometry.getColors()[loaded_face][1], geometry.getColors()[loaded_face][2]));
                }

                g2D.fillPolygon(xPoints, yPoints, face.length);
                g2D.setColor(Color.BLACK);
                g2D.drawPolygon(xPoints, yPoints, face.length);

                loaded_face++;
            }

            for (int[] edge : geometry.getEdges()) {
                int[] rotated1 = rotate(geometry.getVertex()[edge[0]][0], geometry.getVertex()[edge[0]][1], geometry.getVertex()[edge[0]][2], rotX, rotY);
                int[] rotated2 = rotate(geometry.getVertex()[edge[1]][0], geometry.getVertex()[edge[1]][1], geometry.getVertex()[edge[1]][2], rotX, rotY);

                int[] point1 = project(rotated1[0], rotated1[1], rotated1[2]);
                int[] point2 = project(rotated2[0], rotated2[1], rotated2[2]);

                g2D.drawLine(point1[0] + x, point1[1] + y, point2[0] + x, point2[1] + y);
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: y--; break;
            case KeyEvent.VK_DOWN: y++; break;
            case KeyEvent.VK_RIGHT: x++; break;
            case KeyEvent.VK_LEFT: x--; break;
            case KeyEvent.VK_O: rotX++; break;
            case KeyEvent.VK_L: rotY++; break;
            case KeyEvent.VK_U: dist++; break;
            case KeyEvent.VK_H: dist--; break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}