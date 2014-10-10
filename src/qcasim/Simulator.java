package qcasim;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import qcasim.cell.Cell;
import qcasim.cell.Automaton;
import qcasim.display.Display;

public class Simulator
{
    protected static Display display = new Display();
    protected static Automaton automaton = new Automaton();
    protected static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    protected static ScheduledFuture<?> future;
    protected static boolean isRunning;

    protected static int tickCount = 0;
    protected static int tickLengthMills = 80;
    
    public static Automaton getAutomaton()
    {
	return automaton;
    }
    
    public static boolean isRunning()
    {
    	return !future.isDone();
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

	future = scheduler.scheduleAtFixedRate(automaton, 0, tickLengthMills, TimeUnit.MILLISECONDS);
    }

    public static void stop()
    {
	automaton.setRunning(false);
	try
	{
	    future.cancel(false);
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
