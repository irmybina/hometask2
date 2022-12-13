import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Field extends JPanel implements ActionListener{
    private final int DOT_SIZE = 16;
    private final int SIZE = DOT_SIZE*dots;
    protected static final int dots = 10;
    protected static char[][] fieldDots = new char[dots][dots];
    private Timer timer;
    private Image enemy;
    private Tiger tiger;
    protected static boolean inGame = true;
    private Frog frog = new Frog();
    static int lastStepsCount = 0;
    static int minSteps = 1000;
    static int maxSteps = 0;
    static int stepsCount = 0;

    public Field(){
        setBackground(Color.white);
        loadImages();
        generateField();
        setFocusable(true);
    }

    private void loadImages(){
        ImageIcon noGo_icon = new ImageIcon("enemy.png");
        enemy = noGo_icon.getImage();
    }

    private void generateField(){
        for (int i = 0; i < dots; i++) {
            for (int j = 0; j < dots; j++) {
                fieldDots[i][j] = '_';
            }
        }
        fieldDots[0][0] = 't';
        fieldDots[3][5] = '0';
        fieldDots[4][5] = '0';
        fieldDots[5][5] = '0';
        fieldDots[6][5] = '0';
        fieldDots[7][9] = 'f';
        fieldDots[8][9] = 'f';
        fieldDots[9][9] = 'f';
        tiger = new Tiger();
        timer = new Timer(200, this);
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < dots; i++) {
            for (int j = 0; j < dots; j++) {
                if (fieldDots[j][i] == '0') {
                    g.drawImage(enemy, i * DOT_SIZE, j * DOT_SIZE, null);
                }
                if (fieldDots[j][i] == 't') {
                    g.drawImage(tiger.tiger_image, i * DOT_SIZE, j * DOT_SIZE, null);
                    tiger.animalX = j;
                    tiger.animalY = i;
                }
                if (fieldDots[j][i] == 'f' && Field.inGame) {
                    g.drawImage(frog.frog_image, i * DOT_SIZE, j * DOT_SIZE, null);
                    frog.animalX = j;
                    frog.animalY = i;
                }
                String steps = "Steps: " + stepsCount;
                String prevSteps = "prev: " + lastStepsCount;
                String minStepsStr = "min: " + minSteps;
                String maxStepsStr = "max: " + maxSteps;
                g.setColor(Color.black);
                g.drawString(steps, 10, 180);
                g.drawString(prevSteps, 100, 180);
                g.drawString(minStepsStr, 10, 200);
                g.drawString(maxStepsStr, 100, 200);
                }
            }
            tiger.movements();
            Field.stepsCount++;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

