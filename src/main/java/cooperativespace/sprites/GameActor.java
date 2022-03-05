package cooperativespace.sprites;

public abstract class GameActor {

    public void accelerate() {
        throw new UnsupportedOperationException("Forward not supported");
    }

    public void reverse() {
        throw new UnsupportedOperationException("Reverse Not Supported");
    }

    public void rotateRight() {
        throw new UnsupportedOperationException("Rotate Right Not Supported");
    }

    public void rotateLeft() {
        throw new UnsupportedOperationException("Rotate Left Not Supported");
    }

}
