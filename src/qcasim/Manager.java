package qcasim;

import java.util.ArrayList;

public abstract class Manager<T>
{
    protected static enum CascadeType
    {
	RESET, CYCLE, REVERT, STOP, START
    }

    protected ArrayList<Manager<?>> children;
    protected T element;

    public Manager()
    {
	this.children = new ArrayList<Manager<?>>();
    }

    public T getElement()
    {
	return this.element;
    }
    
    public void setElement(T elem)
    {
	this.element = elem;
    }
    
    protected void cascade(CascadeType c)
    {
	for (Manager<?> m : this.children)
	{
	    if (c == CascadeType.RESET) m.initChildren();
	    else if (c == CascadeType.CYCLE) m.cycleChildren();
	    else if (c == CascadeType.REVERT) m.revertChildren();
	    else if (c == CascadeType.STOP) m.stopChildren();
	    else if (c == CascadeType.START) m.startChildren();
	}
    }

    public void initChildren()
    {
	this.init();
	this.cascade(CascadeType.RESET);
    }

    public void cycleChildren()
    {
	this.cycle();
	this.cascade(CascadeType.CYCLE);
    }

    public void revertChildren()
    {
	this.revert();
	this.cascade(CascadeType.REVERT);
    }
    
    public void stopChildren()
    {
	this.stop();
	this.cascade(CascadeType.STOP);
    }
    
    public void startChildren()
    {
	this.start();
	this.cascade(CascadeType.START);
    }

    protected abstract void init();
    protected abstract void cycle();
    protected abstract void revert();
    protected abstract void stop();
    protected abstract void start();
}
