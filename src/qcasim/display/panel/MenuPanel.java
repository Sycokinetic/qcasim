package qcasim.display.panel;

import java.awt.GridLayout;

public class MenuPanel extends SimPanel
{
    private static final long serialVersionUID = 1L;

    private PreviewPanel previewPanel;
    private OptionPanel optionPanel;

    public MenuPanel()
    {
	this.previewPanel = new PreviewPanel(1);
	this.optionPanel = new OptionPanel();

	this.setLayout(new GridLayout(2, 1));
	this.add(this.optionPanel);
	this.add(this.previewPanel);
    }

    public void cycle()
    {
	this.previewPanel.cycle();
	this.optionPanel.cycle();
    }

    @Override
    public void reset()
    {
	this.optionPanel.reset();
	this.previewPanel.reset();
    }
}
