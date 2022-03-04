package cooperativespace.sprites;

import cooperativespace.core.Action;
import cooperativespace.utilities.UtilByte;

import java.util.HashSet;

public class PlayerSprite implements Sprite {

    public double x;
    public double y;
    double rot;

    public void update(HashSet<Action> actions) {
        if (actions.contains(Action.ACCELERATE) ^ actions.contains(Action.REVERSE)) {
            if (actions.contains(Action.ACCELERATE))
                x = x - 1;
            else
                x = x + 1;
        }
        if (actions.contains(Action.ROTATE_LEFT) ^ actions.contains(Action.ROTATE_RIGHT)) {
            if (actions.contains(Action.ROTATE_RIGHT))
                y = y + 1;
            else
                y = y - 1;
        }
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



    @Override
    public byte[] pack() {
        byte[] packet = new byte[6];

        byte[] bRot = UtilByte.shortToByteArray((short) rot);
        byte[] bX = UtilByte.shortToByteArray((short) x);
        byte[] bY = UtilByte.shortToByteArray((short) y);

        System.arraycopy(bX, 0, packet, 0, 2);
        System.arraycopy(bY, 0, packet, 2, 2);
        System.arraycopy(bRot, 0, packet, 4, 2);

        return packet;
    }

    @Override
    public void unpack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
