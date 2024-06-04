package org.example;

import java.util.concurrent.Semaphore;

public class  Filosofo extends Thread {
    private final int id;
    private final Garfo garfoEsq;
    private final Garfo garfoDir;
    private final Semaphore semaforoJantar;
    private final Prato prato;

    public Filosofo(int id, Garfo garfoEsq, Garfo garfoDir, Semaphore semaforoJantar, Prato prato) {
        this.id = id;
        this.garfoEsq = garfoEsq;
        this.garfoDir = garfoDir;
        this.semaforoJantar = semaforoJantar;
        this.prato = prato;
    }

    @Override
    public void run() {
        try {
            while (prato.temMacarrao()) {
                // Tentando adquirir semáforo
                semaforoJantar.acquire();

                // Tentar pegar o garfo esquerdo
                garfoEsq.pegar();
                System.out.println("Filósofo " + id + " pegou o garfo esquerdo.");

                // Tentar pegar o garfo direito
                garfoDir.pegar();
                System.out.println("Filósofo " + id + " pegou o garfo direito.");

                // Comer
                prato.comer();
                System.out.println("Filósofo " + id + " está comendo.");

                // Liberar os garfos
                garfoDir.soltar();
                System.out.println("Filósofo " + id + " soltou o garfo direito.");
                garfoEsq.soltar();
                System.out.println("Filósofo " + id + " soltou o garfo esquerdo.");

                // Liberar o semáforo
                semaforoJantar.release();

                // Filósofo pensando (simulando com sleep)
                System.out.println("Filósofo " + id + " está pensando.");
                Thread.sleep((long) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
