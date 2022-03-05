package cooperativespace.sprites;

public interface GameActor {

    double getSpeed();

    void setSpeed(double speed);

    void modifySpeed(double increment);

    double getRotation();

    void setRotation();

    void modifyRotation();

}
