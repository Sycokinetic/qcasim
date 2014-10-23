package qcasim;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import qcasim.cell.Automaton;
import qcasim.cell.Cell;
import qcasim.display.Display;

public class Simulator
{
    protected static Display display = new Display();
    protected static Automaton automaton = new Automaton();
    protected static ScheduledExecutorService scheduler;
    protected static ScheduledFuture<?> future;

    protected static int tickCount = 0;
    protected static int tickLengthMills = 80;

    public static Automaton getAutomaton()
    {
	return automaton;
    }

    public static boolean isRunning()
    {
	if (future != null) return !future.isDone();
	else return false;
    }

    public static Cell[][] getCellGrid()
    {
	return automaton.getCellGrid();
    }

    public static boolean[][] getInitGrid()
    {
	return automaton.getInitGrid();
    }

    public static int getTickCount()
    {
	return tickCount;
    }

    public static void setTickCount(int n)
    {
	tickCount = n;
    }

    public static void setRule(String r)
    {
	automaton.setRule(r);
    }

    public static String getRule()
    {
	return automaton.getRule();
    }

    public static Display getDisplay()
    {
	return display;
    }

    public static void start()
    {
	automaton.setTickTarget(tickCount);
	automaton.setRunning(true);
	automaton.startChildren();
	display.startChildren();

	scheduler = Executors.newScheduledThreadPool(2);
	future = scheduler.scheduleAtFixedRate(automaton, 0, tickLengthMills, TimeUnit.MILLISECONDS);
    }

    public static void stop()
    {
	automaton.setRunning(false);
	try
	{
	    future.cancel(false);
	    scheduler.shutdownNow();
	    try
	    {
		scheduler.awaitTermination(10, TimeUnit.SECONDS);
	    }
	    catch (InterruptedException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	catch (NullPointerException e)
	{
	    // Do nothing
	}

	automaton.stopChildren();
	display.stopChildren();
    }

    public static void init()
    {
	automaton.initChildren();
	display.initChildren();
    }

    protected static void cycle()
    {
	automaton.cycleChildren();
	display.cycleChildren();
    }

    public static void revert()
    {
	automaton.revertChildren();
	display.revertChildren();
    }
}
