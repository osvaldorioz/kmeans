package unidad.dos.aprendizaje.no.supervisado.cluster;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cluster{

	private Color clusterColor;
	private List<Item> elementList;
	private Item centerElement = null;
	
	
	public void SetClusterColor(Color c){
		this.clusterColor = c;
	}
	
	public Color GetClusterColor(){
		return this.clusterColor;
	}
	
	public void SetCenterElement(Item item){
		this.centerElement = item;
	}
	
	public Item GetCenterElement(){
		return this.centerElement;
	}
	
	public List<Item> GetElementList(){
		return this.elementList;
	}
	

	public Cluster(Color elementColor){
		this.clusterColor = elementColor;
		this.elementList = new ArrayList<Item>();
	}
	
	public void AddElement(Item item){
		item.SetParentCluster(this);
		this.elementList.add(item);
	}
	
	public void DeleteElement(Item item){
		this.elementList.remove(item);
	}
	
	public boolean isReserve(int x, int y){
		for(Item item : this.elementList){
			if(item.GetCoordinate().x == x && item.GetCoordinate().y == y){
				return true;
			}
		}
		return false;
	}
	
	public void SelectRandomCenter(){
		Random rand = new Random();
		this.centerElement = this.elementList.get(rand.nextInt(this.elementList.size()));
		this.centerElement.Select();
		this.centerElement.repaint();
	}
	
	public Point CalculateCenter(){
		int x = 0;
		int y = 0;
		for(Item item : this.elementList){
			x += item.GetCoordinate().x;
			y += item.GetCoordinate().y;
		}
		x = x / this.elementList.size();
		y = y / this.elementList.size();
		
		return new Point(x, y);
	}
	
	public String GetName(){
		
		if(this.clusterColor.equals(Color.BLACK))
			return "Racimo negro";
		if(this.clusterColor.equals(Color.WHITE))
			return "Racimo blanco";
		if(this.clusterColor.equals(Color.BLUE))
			return "Racimo azul";
		if(this.clusterColor.equals(Color.RED))
			return "Racimo rojo";
		if(this.clusterColor.equals(Color.GREEN))
			return "Racimo verde";
		if(this.clusterColor.equals(Color.ORANGE))
			return "Racimo naranja";
		if(this.clusterColor.equals(Color.CYAN))
			return "Racimo turquesa";
		if(this.clusterColor.equals(Color.MAGENTA))
			return "Racimo magenta";
		if(this.clusterColor.equals(Color.PINK))
			return "Racimo rosa";
		if(this.clusterColor.equals(Color.YELLOW))
			return "Racimo amarillo";
		if(this.clusterColor.equals(Color.DARK_GRAY))
			return "Racimo gris";
		if(this.clusterColor.equals(Color.LIGHT_GRAY))
			return "Racimo gris claro";
		else
			return "Agrupación desconocida";
	}
}
