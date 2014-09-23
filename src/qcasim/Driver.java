package qcasim;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import qcasim.display.MainWindow;

public class Driver
{
    public static MainWindow window;
    public static Simulator sim;
    public static ScheduledExecutorService scheduler;
    public static ScheduledFuture<?> future;

    public static void main(String[] args)
    {
	scheduler = Executors.newScheduledThreadPool(1);
	sim = new Simulator();
	window = new MainWindow();
    }

    public static void start(int n)
    {
	sim.n = n;
	sim.i = n;
	sim.isRunning = true;
	
	future = scheduler.scheduleAtFixedRate(sim, 0, 50, TimeUnit.MILLISECONDS);
    }
}
