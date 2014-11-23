package com.battleship.buttons;

public class Ship {

	private Rectangle r;
	private int dir;
	private int size;
	
	public Ship( Rectangle r , int dir , int size ) {
		// TODO Auto-generated constructor stub
		this.r = r;
		this.dir = dir;
		this.size = size;
	}

	public Rectangle getR() {
		return r;
	}

	public void setR(Rectangle r) {
		this.r = r;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
