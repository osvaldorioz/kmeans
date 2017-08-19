package unidad.dos.aprendizaje.no.supervisado.cluster;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class Item extends JComponent{
	private static final long serialVersionUID = 53456732234454L;

	private Cluster parentCluster;
	private Point coordinate;
	private boolean isCenter = false;
	
	public final int THICKNESS = 10;
	
	public void SetParentCluster(Cluster pc){
		this.parentCluster = pc;
		this.UpdateElement();
	}
	
	public Cluster GetParentCluster(){
		return this.parentCluster;
	}
	
	public void SetCoordinate(Point coor){
		this.coordinate = coor;

		int adjustmentX = -4;
		int adjustmentY = -3;
		double gapX = (Graphic.self.getWidth() - 2 * Graphic.self.edgeSpace) / Graphic.self.topX;
		double gapY = (Graphic.self.getHeight() - 2 * Graphic.self.edgeSpace) / Graphic.self.topY;
		
		int pixelX = Graphic.self.edgeSpace + (int)(this.GetCoordinate().y * gapX) + adjustmentX;
		int pixelY = Graphic.self.getHeight() - Graphic.self.edgeSpace - (int)(this.GetCoordinate().x * gapY) + adjustmentY;
		
		this.setLocation(pixelX, pixelY);
		
		this.UpdateElement();
	}
	
	public Point GetCoordinate(){
		return this.coordinate;
	}
	
	
	public Item(int x, int y){
		this.coordinate = new Point(x, y);
	}
	
	@Override
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(this.isCenter){
			g2.setColor(this.parentCluster.GetClusterColor());
			g2.fillOval(0, 0, THICKNESS, THICKNESS);
		}
		else{
			g2.setColor(this.parentCluster.GetClusterColor());
			//g2.drawOval(0, 0, thickness, thickness);
			g2.drawLine(THICKNESS/2-3, THICKNESS/2, THICKNESS/2+3, THICKNESS/2);
			g2.drawLine(THICKNESS/2, THICKNESS/2-3, THICKNESS/2, THICKNESS/2+3);
		}
	}
	
	private void UpdateElement(){
		this.setToolTipText("(" + this.GetClusterName() + ") " + "(" + this.GetCoordinate().x + ", " + this.GetCoordinate().y + ")");
		this.repaint();
	}
	
	public void Select(){
		this.isCenter = true;
	}
	
	public void UnSelect(){
		this.isCenter = false;
	}
	
	public String GetClusterName(){
		return this.parentCluster.GetName();
	}
}