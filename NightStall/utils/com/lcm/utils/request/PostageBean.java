package com.lcm.utils.request;

import java.util.List;

import com.lcm.entity.pojo.Y23PostageArea;
import com.lcm.entity.pojo.Y23PostagePiece;
import com.lcm.entity.pojo.Y23PostageTemplete;

public class PostageBean {
	private Y23PostageTemplete y23PostageTemplete;
	private List<Y23PostageArea> y23PostageAreas;
	private List<Y23PostagePiece> y23PostagePieces;
	
	public Y23PostageTemplete getY23PostageTemplete() {
		return y23PostageTemplete;
	}
	public void setY23PostageTemplete(Y23PostageTemplete y23PostageTemplete) {
		this.y23PostageTemplete = y23PostageTemplete;
	}
	public List<Y23PostageArea> getY23PostageAreas() {
		return y23PostageAreas;
	}
	public void setY23PostageAreas(List<Y23PostageArea> y23PostageAreas) {
		this.y23PostageAreas = y23PostageAreas;
	}
	public List<Y23PostagePiece> getY23PostagePieces() {
		return y23PostagePieces;
	}
	public void setY23PostagePieces(List<Y23PostagePiece> y23PostagePieces) {
		this.y23PostagePieces = y23PostagePieces;
	}
	
	
	
	
}
