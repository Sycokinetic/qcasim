package qcasim.cell;

import java.awt.Dimension;

import qcasim.Manager;
import qcasim.Simulator;

public class Automaton extends Manager<Cell> implements Runnable
{
    protected Dimension fieldDim;
    protected Cell[][] cellGrid;
    protected boolean[][] initGrid;
    
    protected boolean isRunning;
    protected boolean isBordered = false;
    protected Dimension initBoundary;
    protected int tickCountTarget;
    protected int ticksRemaining;
    protected String rule;

    public Automaton()
    {
	this.fieldDim = new Dimension(100, 100);
	this.setRule("B3/S23");
	
	this.init();
    }

    public Cell[][] getCellGrid()
    {
	return this.cellGrid;
    }

    public boolean[][] getInitGrid()
    {
	return this.initGrid;
    }

    public String getRule()
    {
	return this.rule;
    }
    
    public Dimension getDim()
    {
	return this.fieldDim;
    }
    
    public void setRunning(boolean b)
    {
	this.isRunning = b;
    }

    public void setTickTarget(int n)
    {
	this.tickCountTarget = n;
	this.ticksRemaining = n;
    }
    
    public void setRule(String r)
    {
	this.rule = r;
	Cell.setRule(r);
    }

    @Override
    public void init()
    {
	this.cellGrid = new Cell[this.fieldDim.height][this.fieldDim.width];
	this.initGrid = new boolean[this.fieldDim.height][this.fieldDim.width];

	for (int i = 0; i < this.cellGrid.length; i++)
	{
	    for (int j = 0; j < this.cellGrid[i].length; j++)
	    {
		boolean b = false;
		double r = Math.random();
		if (r > 0.5) b = true;
		this.cellGrid[i][j] = new Cell(b, this.isBordered);
		this.initGrid[i][j] = b;
	    }
	}

	for (int i = 0; i < this.cellGrid.length; i++)
	{
	    for (int j = 0; j < this.cellGrid[i].length; j++)
	    {
		Cell[] neighbors = new Cell[8];
		int k = 0;

		for (int a = -1; a <= 1; a++)
		{
		    for (int b = -1; b <= 1; b++)
		    {
			int dx = i + a;
			int dy = j + b;

			if (dx < 0) dx = this.cellGrid.length - 1;
			else if (dx >= this.cellGrid.length) dx = 0;

			if (dy < 0) dy = this.cellGrid[i].length - 1;
			else if (dy >= this.cellGrid[i].length) dy = 0;

			if (a != 0 || b != 0)
			{
			    neighbors[k] = this.cellGrid[dx][dy];
			    k++;
			}
		    }
		}

		this.cellGrid[i][j].setNeighbors(neighbors);
	    }
	}
    }

    @Override
    protected void cycle()
    {
	if (this.isRunning)
	{
	    for (Cell[] clist : cellGrid)
	    {
		for (Cell c : clist)
		    c.prepare();
	    }

	    for (Cell[] clist : cellGrid)
	    {
		for (Cell c : clist)
		    c.update();
	    }

	    Simulator.getDisplay().cycleChildren();

	    if (tickCountTarget != 0)
	    {
		ticksRemaining--;
		if (ticksRemaining <= 0) this.isRunning = false;
	    }
	}
    }

    @Override
    protected void revert()
    {
	for (int i = 0; i < this.fieldDim.height; i++)
	{
	    for (int j = 0; j < this.fieldDim.width; j++)
	    {
		this.cellGrid[i][j].setState(this.initGrid[i][j]);
	    }
	}
    }

    @Override
    protected void stop()
    {
	// Do Nothing
    }
    
    public void run()
    {
	this.cycle();
    }

    @Override
    protected void start()
    {
	// TODO Auto-generated method stub
	
    }
}
