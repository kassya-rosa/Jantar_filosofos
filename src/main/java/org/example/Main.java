package org.example;

import java.util.concurrent.Semaphore;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final int NUM_FILOSOFOS = 5;
    public static void main(String[] args) {
        Garfo[] garfos = new Garfo[NUM_FILOSOFOS];
        Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];
        Prato prato = new Prato(10);

        // Inicializar garfos
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            garfos[i] = new Garfo();
        }

        // Semáforo para permitir apenas (NUM_FILOSOFOS - 1) filósofos comendo ao mesmo tempo
        Semaphore semaforoJantar = new Semaphore(NUM_FILOSOFOS - 1);

        // Inicializar filósofos
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            filosofos[i] = new Filosofo(i, garfos[i], garfos[(i + 1) % NUM_FILOSOFOS], semaforoJantar, prato);
            filosofos[i].start();
        }

        // Esperar que todos os filósofos terminem
        for (Filosofo filosofo : filosofos) {
            try {
                filosofo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}