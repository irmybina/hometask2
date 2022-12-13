import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class Animal {
    int animalX;
    int animalY;
    int destinationX;
    int destinationY;
    int direction;
    protected Image loadImage(String image){
        ImageIcon img = new ImageIcon(image);
        return img.getImage();
    }
    protected void findDestination(){
        while(animalX == destinationX && animalY == destinationY){
            direction = new Random().nextInt(4);
            switch (direction) {
                case 0:
                    destinationY =animalY-1;
                    destinationX = animalX;
                    break;
                case 1:
                    destinationX =animalX+1;
                    destinationY = animalY;
                    break;
                case 2:
                    destinationY = animalY+1;
                    destinationX = animalX;
                    break;
                default:
                    destinationX = animalX-1;
                    destinationY = animalY;
            }
        }
    }
    public abstract void movements();

    protected abstract void checkCollisions();
}
