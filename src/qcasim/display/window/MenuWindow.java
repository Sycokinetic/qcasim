package qcasim.display.window;

import javax.swing.JFrame;
import javax.swing.JPanel;

import qcasim.Manager;
import qcasim.display.panel.MenuPanel;

public class MenuWindow extends Manager<JFrame>
{
    protected int width = 900;
    protected int height = width / 16 * 9;
    
    public MenuWindow()
    {
	this.element = new JFrame();
	this.children.add(new MenuPanel());
	
	this.element.setTitle("Automaton Simulator");
	this.element.setSize(width, height);
	this.element.setLocationRelativeTo(null);
	
	this.element.setContentPane((JPanel) this.children.get(0).getElement());
	this.element.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.element.setVisible(true);
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
    }

    @Override
    protected void start()
    {
    }

}
