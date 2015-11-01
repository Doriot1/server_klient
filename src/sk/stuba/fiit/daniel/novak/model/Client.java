package sk.stuba.fiit.daniel.novak.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.*;

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
    private int fragmentCount;


    private static final int KEEP_ALIVE= 1;
    private static final int CONFIRM = 2;
    private static final int TEXT = 3;
    //private static final int TEXT = 1;


    private int fragment;

    public Client() {
        buffer = new byte[65508];
    }

    public byte[] createHead(int flag, int fragmentCount){
        this.fragmentCount = fragmentCount;

        int[] headInts = new int[]{flag, fragmentCount};
        byte[] headBytes;
        headBytes = toByteArray(headInts);
        return headBytes;
    }

    public byte[] mergeHeadAndData(byte[] head, byte[] dataBytes) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(head);
        outputStream.write(dataBytes);

        return outputStream.toByteArray();
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

    public int getFragmentCount() {
        return fragmentCount;
    }

    public void setFragmentCount(int fragmentCount) {
        this.fragmentCount = fragmentCount;
    }
}
