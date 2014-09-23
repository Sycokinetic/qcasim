package qcasim.cell;

import qcasim.InvalidNeighborListException;

public abstract class Cell
{
    protected boolean curState;
    protected boolean nextState;

    protected Cell[] neighbors;
    protected int maxNeighbors;
    protected boolean isBordered;

    public Cell(boolean init, boolean isBordered)
    {
	this.curState = init;
	this.isBordered = isBordered;
	this.configure();
    }

    public Cell(boolean init, boolean isBordered, Cell[] cellList)
    {
	this.curState = init;
	this.isBordered = isBordered;
	this.neighbors = cellList;
	this.configure();
    }

    public boolean getCurState()
    {
	return this.curState;
    }

    public void setNeighbors(Cell[] cellList)
    {
	this.neighbors = cellList;
    }

    public void prepare() throws InvalidNeighborListException
    {
	if (!validNeighbors())
	{
	    System.out.println(this.neighbors.length + ", " + this.maxNeighbors);
	    throw new InvalidNeighborListException();
	}

	this.runRules();
    }

    public void update()
    {
	this.curState = this.nextState;
    }

    public void setState(boolean b)
    {
	this.curState = b;
    }
    
    protected boolean validNeighbors()
    {
	if (this.isBordered)
	{
	    if (this.neighbors.length > this.maxNeighbors) return false;
	    else return true;
	}
	else
	{
	    if (this.neighbors.length != this.maxNeighbors) return false;
	    else return true;
	}
    }

    protected void setMaxNeighbors(int n)
    {
	this.maxNeighbors = n;
    }
    
    protected abstract void configure();

    protected abstract void runRules();
}
