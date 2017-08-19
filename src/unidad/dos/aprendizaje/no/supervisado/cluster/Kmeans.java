package unidad.dos.aprendizaje.no.supervisado.cluster;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Kmeans
{
	
	private Cluster[] clusters;
	
	public Kmeans(Cluster[] clusters){
		this.clusters = clusters;
		for(Cluster cluster : this.clusters)
			cluster.SelectRandomCenter();
	}
	
	public void iterate(){
		for(Cluster cluster : this.clusters){
			cluster.GetCenterElement().SetCoordinate(cluster.CalculateCenter());
		}
	}
	
	public void updateColors(){
		List<Item> allElements = new ArrayList<Item>();
		List<Item> centers = new ArrayList<Item>();
		
		for(Cluster cluster : this.clusters){
			allElements.addAll(cluster.GetElementList());
			centers.add(cluster.GetCenterElement());
		}
		
		for(Item item : allElements){
			for(Item center : centers){
				if(isNearly(item, center.GetCoordinate())){
					item.GetParentCluster().DeleteElement(item);
					item.SetParentCluster(center.GetParentCluster());
					item.GetParentCluster().AddElement(item);
				}
			}			
		}
	}
	
	private boolean isNearly(Item item, Point p){
		Point selfCenter = item.GetParentCluster().GetCenterElement().GetCoordinate();
		double selfDistance = Math.pow((item.GetCoordinate().x - selfCenter.x), 2) + Math.pow((item.GetCoordinate().y - selfCenter.y), 2);
		
		double pDistance = Math.pow((item.GetCoordinate().x - p.x), 2) + Math.pow((item.GetCoordinate().y - p.y), 2);
		
		return (pDistance < selfDistance);
	}
}
