package cooperativespace.utilities;

public class UtilByte {

    public static int byteArrayToInt(byte[] b)
    {
        return   b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public void putShortInByteArray(Byte[] array, int position, short a) {
        array[position] = (byte) ((a >> 8) & 0xFF);
        array[position + 1] = (byte) (a & 0xFF);
    }

    public static byte[] shortToByteArray(short a) {
        return new byte[] {
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    public static byte[] intToByteArray(int a)
    {
        return new byte[] {
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }
}
