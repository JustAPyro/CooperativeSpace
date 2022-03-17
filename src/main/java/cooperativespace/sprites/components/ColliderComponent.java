package cooperativespace.sprites.components;

import cooperativespace.utilities.UtilMath;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class ColliderComponent {

    // This determines if colliders should be drawn
    public final boolean showCollider = true;

    // The parent of this specific collider
    private Collider parent;

    // This array tracks all colliders that are created, for object collision detection
    private static final ArrayList<Collider> allColliderComponents = new ArrayList<Collider>();

    public ColliderComponent(Collider collider) {
        allColliderComponents.add(collider);
        parent = collider;
    }

    public void drawCollider(GraphicsContext gc) {
        gc.save();
        gc.setStroke(Color.RED);
        gc.strokeOval(parent)
    }


    public void update(Collider parent) {

        // Check for collision with any other Collider Component
        for (Collider collider : allColliderComponents) {

            if (collider == parent || collider.isReal()) {
                continue;
            }

            // Calculate the distance between the two points
            double distance = UtilMath.distanceBetweenPoints(
                    parent.getXPosition(), parent.getYPosition(),
                    collider.getXPosition(), collider.getYPosition()
            );

            // If distance is less than the sphere size of each, it's a collision
            if (distance < parent.getColliderSphereSize() + collider.getColliderSphereSize()) {
                System.out.println("Colided");
            }


        }

    }

}
