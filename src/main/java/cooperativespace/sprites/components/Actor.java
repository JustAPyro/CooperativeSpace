package cooperativespace.sprites.components;

public interface Actor {

      double getYVelocity();

      void setYVelocity(double yVelocity);

      void modifyYVelocity(double increment);

      double getXVelocity();

      void setXVelocity(double xVelocity);

      void modifyXVelocity(double increment);

      double getRotation();

      void setRotation(double rotationPosition);

      void modifyRotation(double increment);

}
