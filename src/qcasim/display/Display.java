package qcasim.display;

import javax.swing.JOptionPane;

import qcasim.display.window.MenuWindow;
import qcasim.display.window.RenderWindow;

public class Display implements DisplayInterface
{
    protected MenuWindow menuWindow;
    protected RenderWindow renderWindow;
    
    public Display()
    {
	this.menuWindow = new MenuWindow();
	this.renderWindow = new RenderWindow();
    }
    
    public MenuWindow getMenuWindow()
    {
	return this.menuWindow;
    }
    
    public RenderWindow getRenderWindow()
    {
	return this.renderWindow;
    }
    
    public void alert(String m)
    {
	JOptionPane.showMessageDialog(this.menuWindow, "Please enter the number of cycles to run, or 0 to cycle indefinitely.");
    }
    
    public void cycle()
    {
	if (!this.renderWindow.isVisible()) this.renderWindow.setVisible(true);
	this.renderWindow.cycle();
	this.menuWindow.cycle();
    }

    @Override
    public void reset()
    {
	this.renderWindow.reset();
	this.menuWindow.reset();
    }
}
