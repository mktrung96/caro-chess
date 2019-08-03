/**
 * Copyright(C) 2019, Luvina Software Company 
 * OCo.java,Jun 14, 2019 TrungPT
 */
package com.luvina.entities;

/**
 * @author TrungPT
 * 
 */
public class OCo {
	int x; // tọa độ trục X của ô cờ
	int y; // tọa độ trục Y của ô cờ
	char value; // giá trị X, O 
	

	/**
	 * @param x
	 * @param y
	 * @param value
	 */
	public OCo(int x, int y, char value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}

	
	/**
	 * 
	 */
	public OCo() {
		
	}


	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the value
	 */
	public char getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(char value) {
		this.value = value;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OCo [x=" + x + ", y=" + y + ", value=" + value + "]";
	}

	
}
