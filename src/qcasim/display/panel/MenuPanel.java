package qcasim.display.panel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import qcasim.Manager;

public class MenuPanel extends Manager<JPanel>
{
    public MenuPanel()
    {
	this.element = new JPanel();
	this.children.add(new OptionPanel());
	this.children.add(new PreviewPanel());

	this.element.setLayout(new GridLayout(2, 1));
	this.element.add((JPanel) this.children.get(0).getElement());
	this.element.add((JPanel) this.children.get(1).getElement());
    }

    @Override
    protected void init()
    {
	// TODO Auto-generated method stub

    }

    @Override
    protected void cycle()
    {
	// TODO Auto-generated method stub

    }

    @Override
    protected void revert()
    {
	// TODO Auto-generated method stub

    }

    @Override
    protected void stop()
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    protected void start()
    {
	// TODO Auto-generated method stub
	
    }

}
