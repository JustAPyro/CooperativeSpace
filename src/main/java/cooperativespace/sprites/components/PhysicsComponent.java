package cooperativespace.sprites.components;

import static java.lang.Math.atan;
import static java.lang.Math.tan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import static java.lang.Math.toDegrees;

/**
 * Add this to a game object if you want it to have collisions or constant movement
 */
public class PhysicsComponent {

    public void update(Physics parent) {

        parent.modifyXPosition(parent.getXVelocity());
        parent.modifyYPosition(parent.getYVelocity());

    }
}
