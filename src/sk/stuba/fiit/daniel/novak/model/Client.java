package sk.stuba.fiit.daniel.novak.model;

import java.net.*;
import java.nio.ByteBuffer;

/**
 * Created by doriot on 10/25/15.
 */
public class Client {

    private boolean bound = false;
    private int port;
    private InetAddress address;
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    private byte[] buffer;
    //private byte[] addr = new byte[4];
    private String addr;


    private int fragment;

    public Client() {
        buffer = new byte[65508];
    }

    public byte[] createHeader(byte flag, int fragmentCount) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        byteBuffer.put(flag);
        byteBuffer.putInt(fragmentCount);

        return byteBuffer.array();
    }

    // setter and getters
    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }

    public void setDatagramSocket(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public DatagramPacket getDatagramPacket() {
        return datagramPacket;
    }

    public void setDatagramPacket(DatagramPacket datagramPacket) {
        this.datagramPacket = datagramPacket;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public String getAddr() {
        return addr;
    }

    public boolean isBound() {
        return bound;
    }

    public void setBound(boolean bound) {
        this.bound = bound;
    }

    public void setFragment(int fragment) {
        this.fragment = fragment;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public int getFragmentSize() {
        return fragment;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
