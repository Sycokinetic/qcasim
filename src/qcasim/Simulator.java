package qcasim;

import java.awt.Dimension;

import qcasim.cell.Cell;

public class Simulator implements Runnable
{
    public Dimension dim;
    public Cell[][] cellList;
    public boolean[][] init;
    
    public boolean isRunning;
    public boolean isBordered = false;
    public int n;
    public int i;
    
    public Simulator()
    {
	this.dim = new Dimension(300, 500);
	this.cellList = new Cell[this.dim.height][this.dim.width];
	this.init = new boolean[this.dim.height][this.dim.width];
	
	this.init();
    }

    @Override
    public void run()
    {
	if (this.isRunning)
	{
	    for (Cell[] clist : cellList)
	    {
		for (Cell c : clist)
		    c.prepare();
	    }

	    for (Cell[] clist : cellList)
	    {
		for (Cell c : clist)
		    c.update();
	    }

	    Driver.window.cycle();

	    if (n != 0)
	    {
		i--;
		if (i <= 0) this.isRunning = false;
	    }
	}
    }
    
    public void restart()
    {
	for (int i = 0; i < this.dim.height; i++)
	{
	    for (int j = 0; j < this.dim.width; j++)
	    {
		this.cellList[i][j].setState(this.init[i][j]);
	    }
	}
    }
    
    public void reset()
    {
	this.stop();
	this.init();
    }
    
    public void stop()
    {
	this.isRunning = false;
	try {
	    Driver.future.cancel(false);
	} catch (NullPointerException e)
	{
	    // Do Nothing
	}
    }
    
    public void init()
    {
	for (int i = 0; i < this.cellList.length; i++)
	{
	    for (int j = 0; j < this.cellList[i].length; j++)
	    {
		boolean b = false;
		double r = Math.random();
		if (r > 0.5) b = true;
		this.cellList[i][j] = new Cell(b, this.isBordered);
		this.cellList[i][j].setRule("B3/S23");
		this.init[i][j] = b;
	    }
	}

	for (int i = 0; i < this.cellList.length; i++)
	{
	    for (int j = 0; j < this.cellList[i].length; j++)
	    {
		Cell[] neighbors = new Cell[8];
		int k = 0;

		for (int a = -1; a <= 1; a++)
		{
		    for (int b = -1; b <= 1; b++)
		    {
			int dx = i + a;
			int dy = j + b;

			if (dx < 0) dx = this.cellList.length - 1;
			else if (dx >= this.cellList.length) dx = 0;

			if (dy < 0) dy = this.cellList[i].length - 1;
			else if (dy >= this.cellList[i].length) dy = 0;

			if (a != 0 || b != 0)
			{
			    neighbors[k] = this.cellList[dx][dy];
			    k++;
			}
		    }
		}

		this.cellList[i][j].setNeighbors(neighbors);
	    }
	}
    }
}
