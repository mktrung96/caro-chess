/**
 * Copyright(C) 2019, Luvina Software Company 
 * Main.java,Jun 13, 2019 TrungPT
 */
package com.luvina.controller;

import com.luvina.view.DisplayFrame;

/**
 * @author TrungPT
 * 
 */
public class Main {
	public static void main(String[] args) {
		// khởi tạo đối tượng JFrame khung giao diện của chương trình
		DisplayFrame displayFrame = new DisplayFrame();
		// tạo bàn cờ
		displayFrame.createBoardChess();
	}
}
