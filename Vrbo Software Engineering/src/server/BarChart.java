package server;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  

public class BarChart extends JPanel{
	private int[] value;
	private String[] languages;
	private String title;
	
	public BarChart(int[] val, String[] lang, String t) 
	{
		languages = lang;
		value = val;
		title = t;
	}
	
	public void paintComponent(Graphics graphics) 
	{
		super.paintComponent(graphics);
		
		if (value == null || value.length == 0)
			return;
		
		double minValue = 0;
		double maxValue = 0;
		for (int i = 0; i < value.length; i++) 
		{
			if (minValue > value[i])
				minValue = value[i];
			if (maxValue < value[i])
				maxValue = value[i];
		}
	
		Dimension dim = getSize();
		int clientWidth = dim.width;
		int clientHeight = dim.height;
		int barWidth = clientWidth / value.length;
		Font titleFont = new Font("Book Antiqua", Font.BOLD, 50);
		FontMetrics titleFontMetrics = graphics.getFontMetrics(titleFont);
		Font labelFont = new Font("Book Antiqua", Font.PLAIN, 20);
		FontMetrics labelFontMetrics = graphics.getFontMetrics(labelFont);
		int titleWidth = titleFontMetrics.stringWidth(title);
		int q = titleFontMetrics.getAscent();
		int p = (clientWidth - titleWidth) / 2;
		graphics.setFont(titleFont);
		graphics.drawString(title, p, q);
		int top = titleFontMetrics.getHeight();
		int bottom = labelFontMetrics.getHeight();
		if (maxValue == minValue)
			return;
		double scale = (clientHeight - top - bottom) / (maxValue - minValue);
		q = clientHeight - labelFontMetrics.getDescent();
		graphics.setFont(labelFont);
		for (int j = 0; j < value.length; j++) 
		{
			int valueP = j * barWidth + 1;
			int valueQ = top;
			int height = (int) (value[j] * scale);
			if (value[j] >= 0)
				valueQ += (int) ((maxValue - value[j]) * scale);
			else 
			{
				valueQ += (int) (maxValue * scale);
				height = -height;
			}
			graphics.setColor(Color.decode("#0e214b"));
			graphics.fillRect(valueP, valueQ, barWidth - 2, height);
			graphics.setColor(Color.BLACK);
			graphics.drawRect(valueP, valueQ, barWidth - 2, height);
			int labelWidth = labelFontMetrics.stringWidth(languages[j]);
			p = j * barWidth + (barWidth - labelWidth) / 2;
			graphics.drawString(languages[j], p, q);
		}
	}
}
