package edu.eci.arsw.samples;

import java.util.concurrent.CyclicBarrier;

public class Main {

	public static void main(String[] args) {
		int numHilos = (args.length > 0) ? Integer.parseInt(args[0]) : 20;

		HiloProc[] hilos=new HiloProc[numHilos];

		CyclicBarrier barrier=new CyclicBarrier(numHilos, new Runnable() {
			public void run() {
				long tiempoPromedio=0;
				for (int i=0;i<numHilos;i++){
					tiempoPromedio+=hilos[i].getResultado();
				}
				System.out.println("El tiempo promedio de la ejecución fue de:"+tiempoPromedio/numHilos);
			}
		});

		for (int i=0;i<numHilos;i++){
			hilos[i]=new HiloProc(i, barrier);
		}
		for (int i=0;i<numHilos;i++){
			hilos[i].start();
		}
	}

}
