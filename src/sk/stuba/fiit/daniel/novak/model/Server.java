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

    public boolean isBound() {
        return bound;
    }

    public void setBound(boolean bound) {
        this.bound = bound;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort(){
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

    public void setOpened(boolean isOpened){
        this.isOpened = isOpened;
    }
}
