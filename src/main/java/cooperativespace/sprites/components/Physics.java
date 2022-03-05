package cooperativespace.sprites.components;

public interface Physics {

    // - - - Information Required for Physics - - -

    double getXVelocity();

    double getYVelocity();

    // - - - Information Modified by Physics - - -

    double getXPosition();

    void setXPosition(double x);

    void modifyXPosition(double increment);

    double getYPosition();

    void setYPosition(double y);

    void modifyYPosition(double increment);

}
