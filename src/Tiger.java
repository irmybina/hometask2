import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Tiger extends Animal {
    public Image tiger_image = loadImage("tiger.png");
    private DirectionsWeight[][] memory = new DirectionsWeight[Field.dots][Field.dots];
    private int catchCount = 0;

    public Tiger(){
        fillMemory();
    }
    private void fillMemory(){
        for (int i=0; i<Field.dots; i++){
            for (int j=0; j<Field.dots; j++){
                double weight;
                switch(Field.fieldDots[i][j]){
                    case '_':
                        weight = -1;
                        break;
                    case 'f':
                        weight = 0;
                        break;
                    default:
                        weight = -10000;
                }
                memory[i][j] = new DirectionsWeight(weight);
            }
        }
        addBorders();
    }

    private void addBorders(){
        for (int i=0; i<Field.dots; i++){
            memory[i][0].setLeft(-1000000);
            memory[i][Field.dots-1].setRight(-1000000);
            memory[0][i].setUp(-1000000);
            memory[Field.dots-1][i].setDown(-1000000);
        }
    }

    @Override
    public void movements() {
        destinationX = animalX;
        destinationY = animalY;
        think();
        chooseDestination();
        checkCollisions();
    }
    private int whereGo(int x, int y){
        if (y > 0 && memory[x][y].getLeft() >= memory[x][y].getRight() && memory[x][y].getLeft() >= memory[x][y].getDown() && memory[x][y].getLeft() >= memory[x][y].getUp()) return 3;
        else if (x < Field.dots-1 && memory[x][y].getDown() >= memory[x][y].getRight() && memory[x][y].getDown() >= memory[x][y].getUp()) return 2;
        else if (y < Field.dots-1 && memory[x][y].getRight() >= memory[x][y].getUp()) return 1;
        else return 0;
    }
    private void chooseDestination(){
        if (new Random().nextDouble()>0.1){
            direction = whereGo(animalX, animalY);
            switch (direction) {
                case 0:
                    destinationY = animalY;
                    destinationX = animalX-1;
                    break;
                case 1:
                    destinationX = animalX;
                    destinationY = animalY+1;
                    break;
                case 2:
                    destinationY =  animalY;
                    destinationX = animalX+1;
                    break;
                default:
                    destinationX = animalX;
                    destinationY = animalY-1;
            }
        }
        else{
            findDestination();
            checkBorders();
        }
    }
    private double findMaxWeight(int i, int j){
        if (i < 0 || i > Field.dots-1 || j < 0 || j > Field.dots-1) return -1000;
        return Math.max(Math.max(memory[i][j].getLeft(), memory[i][j].getRight()), Math.max(memory[i][j].getDown(), memory[i][j].getUp()));
    }
    private void checkBorders(){
        if (destinationX <0 || destinationX>19 || destinationY <0 || destinationY>19){
            findDestination();
        }
    }
    protected void checkCollisions(){
        if (Field.fieldDots[destinationX][destinationY] == 'f'){
            Field.lastStepsCount = Field.stepsCount;
            if (Field.stepsCount > Field.maxSteps) Field.maxSteps = Field.stepsCount;
            if (Field.stepsCount < Field.minSteps) Field.minSteps = Field.stepsCount;
            Field.stepsCount = 0;
            switch(direction){
                case 0:
                    memory[animalX][animalY].setUp(-0.1);
                    break;
                case 1:
                    memory[animalX][animalY].setRight(-0.1);
                    break;
                case 2:
                    memory[animalX][animalY].setDown(-0.1);
                    break;
                default:
                    memory[animalX][animalY].setLeft(-0.1);
                    break;
            }
            Field.fieldDots[animalX][animalY] = '_';
            animalX = 0;
            animalY = 0;
            Field.fieldDots[destinationX][destinationY] = '_';
            Field.fieldDots[0][0] = 't';
            catchCount++;
            if (catchCount%3 == 0){
                Field.fieldDots[7][9] = 'f';
                Field.fieldDots[8][9] = 'f';
                Field.fieldDots[9][9] = 'f';
            }
        }
        else if (Field.fieldDots[destinationX][destinationY] != '0'){
            Field.fieldDots[animalX][animalY] = '_';
            Field.fieldDots[destinationX][destinationY] = 't';
            animalX = destinationX;
            animalY = destinationY;
        }
    }

    private void think(){
        memory[animalX][animalY].setUp(memory[animalX][animalY].getUp() + findMaxWeight(animalX-1, animalY)*0.9);
        memory[animalX][animalY].setDown(memory[animalX][animalY].getDown() + findMaxWeight(animalX+1, animalY)*0.9);
        memory[animalX][animalY].setLeft(memory[animalX][animalY].getLeft() + findMaxWeight(animalX, animalY-1)*0.9);
        memory[animalX][animalY].setRight(memory[animalX][animalY].getRight() + findMaxWeight(animalX, animalY+1)*0.9);
    }
}
