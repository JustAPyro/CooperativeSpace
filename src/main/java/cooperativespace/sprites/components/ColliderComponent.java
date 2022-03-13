package cooperativespace.sprites.components;

import java.util.ArrayList;

public class ColliderComponent {

    // This array tracks all colliders that are created, for object collision detection
    private static final ArrayList<Collider> allColliderComponents = new ArrayList<Collider>();

    public ColliderComponent(Collider collider) {
        allColliderComponents.add(collider);
    }

    public void update(Collider parent) {
        System.out.println("lol NOT checking for collisions");
    }




}
