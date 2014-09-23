package qcasim.cell;

import qcasim.InvalidNeighborListException;

public class Cell
{
    protected boolean curState;
    protected boolean nextState;

    protected Cell[] neighbors;
    protected int maxNeighbors;
    protected boolean isBordered;
    
    protected String rule;
    protected boolean[] birth;
    protected boolean[] survive;

    public Cell(boolean init, boolean isBordered)
    {
	this.curState = init;
	this.isBordered = isBordered;
	this.birth = new boolean[9];
	this.survive = new boolean[9];
	
	this.maxNeighbors = 8;
    }

    public Cell(boolean init, boolean isBordered, Cell[] cellList)
    {
	this.curState = init;
	this.isBordered = isBordered;
	this.neighbors = cellList;
	this.birth = new boolean[8];
	this.survive = new boolean[8];
	
	this.maxNeighbors = 8;
    }

    public boolean getCurState()
    {
	return this.curState;
    }

    public void setNeighbors(Cell[] cellList)
    {
	this.neighbors = cellList;
    }

    public void setRule(String r)
    {
	this.rule = r;
	
	String b = r.split("/")[0];
	for (int i = 1; i < b.length(); i++) this.birth[b.charAt(i) - '0'] = true;
	
	String s = r.split("/")[1];
	for (int i = 1; i < s.length(); i++) this.survive[s.charAt(i) - '0'] = true;
    }
    
    public void applyRule()
    {
	int numLive = 0;
	for (Cell c: this.neighbors)
	{
	    if (c.curState) numLive++;
	}
	
	if (!this.curState && this.birth[numLive]) this.nextState = true;
	else if (this.curState && !this.survive[numLive]) this.nextState = false;
    }
    
    public void prepare() throws InvalidNeighborListException
    {
	if (!validNeighbors())
	{
	    System.out.println(this.neighbors.length + ", " + this.maxNeighbors);
	    throw new InvalidNeighborListException();
	}

	this.applyRule();
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
}
