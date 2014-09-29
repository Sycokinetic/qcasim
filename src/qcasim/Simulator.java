package qcasim;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import qcasim.cell.Cell;
import qcasim.display.Display;

public class Simulator
{
    protected static Display display;
    protected static SimField simField;
    protected static ScheduledExecutorService scheduler;
    protected static ScheduledFuture<?> future;

    protected static int tickCount = 0;
    protected static int tickLengthMills = 80;
    
    public static Cell[][] getCellField()
    {
	return simField.cellList;
    }
    
    public static boolean[][] getInitField()
    {
	return simField.initList;
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
	simField.tickCountTarget = tickCount;
	simField.ticksRemaining = tickCount;
	simField.isRunning = true;

	future = scheduler.scheduleAtFixedRate(simField, 0, tickLengthMills, TimeUnit.MILLISECONDS);
    }

    public static void stop()
    {
	simField.setRunning(false);
	try
	{
	    future.cancel(false);
	}
	catch (NullPointerException e)
	{
	}
    }

    public static void restart()
    {
	stop();
	simField.restart();
    }
    
    public static void reset()
    {
	stop();
	simField.reset();
    }
    
    public static void main(String[] args)
    {
	scheduler = Executors.newScheduledThreadPool(1);
	simField = new SimField();
	display = new Display();
	//window = new MenuWindow();
    }
}
