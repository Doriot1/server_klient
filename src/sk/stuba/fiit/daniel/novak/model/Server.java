package sk.stuba.fiit.daniel.novak.model;

/**
 * Created by doriot on 10/27/15.
 */

public class Server extends Thread {

    private byte[] buffer;
    private int port;
    private boolean isOpened = false;
    private boolean bound = false;

    public Server() {
        buffer = new byte[1000];
    }

    public byte[] toByteArray(int[] ints) {
        int index = 0;
        byte[] array = new byte[4 * ints.length];

        for (int i : ints) {
            array[index] = (byte) i;
            array[index + 1] = (byte) (i >> 8);
            array[index + 2] = (byte) (i >> 16);
            array[index + 3] = (byte) (i >> 24);
            index += 4;
        }
        return array;
    }

    public int[] fromByteArray(byte[] bytes) {
        int index = 0;
        int[] array = new int[bytes.length / 4];


        int length = bytes.length;
        for (int i = 0; i < length; i += 4) {
            array[index] = (bytes[i] & 0xFF)
                    | ((bytes[i + 1] << 8) & 0xFF00)
                    | ((bytes[i + 2] << 16) & 0xFF0000)
                    | ((bytes[i + 3] << 24) & 0xFF000000);
            index++;
        }
        return array;
    }

    public boolean isBound() {
        return bound;
    }

    public void setBound(boolean bound) {
        this.bound = bound;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public boolean getIsOpened() {
        return isOpened;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public void setOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }
}
