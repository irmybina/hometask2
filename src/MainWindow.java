import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow(){
        setTitle("Hometask2");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Field.dots*16+16, Field.dots*16+100);
        setLocation(400, 400);
        add(new Field());
        setVisible(true);
    }
    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
    }
}
