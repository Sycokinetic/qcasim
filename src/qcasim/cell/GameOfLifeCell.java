package qcasim.cell;

public class GameOfLifeCell extends Cell
{
    public GameOfLifeCell(boolean init, boolean isBordered)
    {
	super(init, isBordered);
    }
    
    public GameOfLifeCell(boolean init, boolean isBordered, GameOfLifeCell[] cellList)
    {
	super(init, isBordered, cellList);
    }
    
    @Override
    protected void configure()
    {
	this.setMaxNeighbors(8);
    }

    @Override
    protected void runRules()
    {
	int numLive = 0;

	for (Cell c: this.neighbors)
	{
	    if (c.curState) numLive++;
	}
	
	if (this.curState)
	{
	    if (numLive < 2 || numLive > 3) this.nextState = false;
	}
	else
	{
	    if (numLive == 3) this.nextState = true;
	}
    }

}
