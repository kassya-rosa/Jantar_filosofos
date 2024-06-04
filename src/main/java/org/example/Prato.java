package org.example;

public class Prato {
    private int macarrao;

    public Prato(int macarrao) {
        this.macarrao = macarrao;
    }

    public synchronized boolean temMacarrao() {
        return macarrao > 0;
    }

    public synchronized void comer() {
        if (macarrao > 0) {
            macarrao--;
        }
    }
}
