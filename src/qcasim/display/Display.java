package qcasim.display;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import qcasim.Manager;
import qcasim.display.window.MenuWindow;
import qcasim.display.window.RenderWindow;

public class Display extends Manager<JFrame>
{
    protected MenuWindow menuWindowManager;
    protected RenderWindow renderWindowManager;

    public Display()
    {
	this.menuWindowManager = new MenuWindow();
	this.renderWindowManager = new RenderWindow();

	this.children.add(this.menuWindowManager);
	this.children.add(this.renderWindowManager);
    }

    public MenuWindow getMenuWindow()
    {
	return this.menuWindowManager;
    }

    public RenderWindow getRenderWindow()
    {
	return this.renderWindowManager;
    }

    public void alert(String m)
    {
	JOptionPane.showMessageDialog(this.menuWindowManager.getElement(), "Please enter the number of cycles to run, or 0 to cycle indefinitely.");
    }

    @Override
    protected void init()
    {
    }

    @Override
    protected void cycle()
    {
    }

    @Override
    protected void revert()
    {
    }
    
    @Override
    protected void stop()
    {
	// Do nothing
    }

    @Override
    protected void start()
    {
	// TODO Auto-generated method stub
	
    }
}
