package ru.ifmo.rain.feponov.hello;

import info.kgeorgiy.java.advanced.hello.HelloServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HelloUDPServer implements HelloServer {

    private ExecutorService sendThreadPool;
    private ExecutorService receiveThreadExec;
    private DatagramSocket socket;

    public static void main(String[] args) {
        if (args == null || args.length != 2) {
            System.err.println("Unexpected number of arguments, should be 2");
            return;
        }
        int port;
        int threads;
        try {
            port = Integer.parseInt(args[0]);
            threads = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Argument expected as a number: " + e.getMessage());
            return;
        } catch (NullPointerException e) {
            System.err.println("Expected non-null arguments: " + e.getMessage());
            return;
        }
        new HelloUDPServer().start(port, threads);
    }


    private DatagramPacket createSendPacket(String request, SocketAddress address) {
        var byteRequest = request.getBytes(StandardCharsets.UTF_8);
        return new DatagramPacket(byteRequest, request.length(), address);
    }

    private DatagramPacket createReceivePacket(int size) {
        var byteResponse = new byte[size];
        return new DatagramPacket(byteResponse, size);
    }


    @Override
    public void start(int port, int threads) {
        sendThreadPool = Executors.newFixedThreadPool(threads);
        receiveThreadExec = Executors.newSingleThreadExecutor();
        try {
            socket = new DatagramSocket(port);
            var bufferSize = socket.getReceiveBufferSize();
            receiveThreadExec.submit(() -> {
                while (!socket.isClosed() && !Thread.interrupted()) {
                    var receivePacket = createReceivePacket(bufferSize);
                    try {
                        socket.receive(receivePacket);
                    } catch (IOException e) {
                        if (!socket.isClosed()) {
                            System.err.println("Error while receiving from client: " + e.getMessage());
                        }
                    }
                    sendThreadPool.submit(() -> {
                        String stringResponse = "Hello, " + new String(
                                receivePacket.getData(),
                                receivePacket.getOffset(),
                                receivePacket.getLength(),
                                StandardCharsets.UTF_8
                        );
                        var sendPacket = createSendPacket(stringResponse, receivePacket.getSocketAddress());
                        try {
                            socket.send(sendPacket);
                        } catch (IOException e) {
                            System.err.println("Error while responding to client: " + e.getMessage());
                        }
                    });
                }

            });
        } catch (SocketException e) {
            System.err.println("Cannot open or bind socket: " + e.getMessage());
        }

    }

    @Override
    public void close() {
        try {
            receiveThreadExec.shutdownNow();
            sendThreadPool.shutdownNow();
            socket.close();
            try {
                sendThreadPool.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException ignored) {
            }
        } catch (NullPointerException ignored) {
        }
    }
}

