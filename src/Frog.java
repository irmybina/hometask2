import javax.swing.*;
import java.awt.*;

public class Frog extends Animal{
    boolean isCatched = false;
    char animalChar = 'f';
    public Image frog_image = loadImage("frog.png");
    public Frog(){
    }

    public void checkCollisions(){
        if (Field.fieldDots[destinationX][destinationY] == 't') {
            Field.inGame = false;
        }
    }

    @Override
    public void movements() {
        destinationX = animalX;
        destinationY = animalY;
//        findDestination();
        checkCollisions();
        Field.fieldDots[animalX][animalY] = '_';
        Field.fieldDots[destinationX][destinationY] = 'f';

    }
}
