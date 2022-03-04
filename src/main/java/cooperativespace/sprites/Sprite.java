package cooperativespace.sprites;

import javafx.scene.canvas.Canvas;

public interface Sprite {

    public void update();

    public void draw(Canvas canvas);

    public byte[] pack();

    public void unpack(byte[] playerPacket);

}
