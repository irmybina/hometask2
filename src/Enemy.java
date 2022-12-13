public class Enemy extends Animal{

    @Override
    public void movements() {
        doNothing();
    }
    @Override
    protected void checkCollisions() {
        doNothing();
    }
    private void doNothing(){}
}
