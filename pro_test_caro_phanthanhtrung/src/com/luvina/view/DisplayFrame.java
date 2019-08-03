/**
 * Copyright(C) 2019, Luvina Software Company 
 * DisplayFrame.java,Jun 13, 2019 TrungPT
 */
package com.luvina.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.luvina.controller.ButtonCaroController;
import com.luvina.entities.OCo;
import com.luvina.logic.CaroLogic;
import com.luvina.utils.Constant;

/**
 * class JFrame tạo khung giao diện
 * 
 * @author TrungPT
 * 
 */
public class DisplayFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	// ma trận bàn cờ
	public static ButtonCaroController[][] banCo;

	/**
	 * Hàm khởi tạo DisplayFrame, khi gọi đến hàm này là khởi tạo ra khung bàn cờ
	 */
	public DisplayFrame() {
		banCo = new ButtonCaroController[20][20]; // khởi tạo mảng 2 chiều của 400 button
		Container container = this.getContentPane(); // Khởi tạo container là cửa sổ chứa những JPanel
		JPanel panelMain = new JPanel(); // panelMain: đối tượng chứa các element
		panelMain.setLayout(new GridLayout(20, 20)); // setLayout dạng lưới 20x20 cho panel
		// tạo ra ô cờ và thêm vào panel

		for (int cot = 0; cot < Constant.MAX_COLS_BOARD; cot++) {
			for (int hang = 0; hang < Constant.MAX_ROWS_BOARD; hang++) {
				// khởi tạo ô cờ
				banCo[hang][cot] = new ButtonCaroController();
				banCo[hang][cot].setLocationX(hang);
				banCo[hang][cot].setLocationY(cot);
				// set kí tự mặc định cho ô cờ là rỗng
				banCo[hang][cot].setNameOfCell('D');
				// set màu trắng cho button
				banCo[hang][cot].setBackground(Color.WHITE);
				panelMain.add(banCo[hang][cot]); // add button vào panel
			}
			// thêm panel vào container
			container.add(panelMain);
		}

	}

	/**
	 * Phương thức set các thuộc tính cho bàn cờ
	 */
	public void createBoardChess() {
		// set chiều dài bàn cờ là 800, chiều rộng là 800
		this.setSize(800, 800);
		// hiển nút close
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// vị trí hiển thị cửa sổ trên màn hình
		this.setLocationRelativeTo(null);
		// không cho thay đổi kích thước màn hình
		this.setResizable(false);
		// cho phép cửa sổ hiển thị trên màn hình
		this.setVisible(true);
	}

	/**
	 * Kiểm tra thắng thua sau ngay khi đánh ô cờ gần nhất
	 * 
	 * @param oCo
	 * @return true nếu thắng
	 */
	public static boolean checkWin(OCo oCo) {
		// khởi tạo class lưu các logic của chương trình
		CaroLogic caroLogic = new CaroLogic();
		{
			// trả về true nếu thắng
			return caroLogic.checkWin(oCo, banCo);
		}
	}

	/**
	 * Phương thức tạo nước chơi cho máy sau đó kiểm tra thắng thua
	 * 
	 * @param oCo
	 * @return true nếu máy thắng
	 */
	public static boolean computerPlay(OCo oCo) {
		// khởi tạo class lưu các logic của chương trình
		CaroLogic caroLogic = new CaroLogic();
		// tìm và trả về ô cờ cần đánh
		OCo oCoPC = caroLogic.computerPlay(oCo, banCo);
		// set nameOfCell cho button
		banCo[oCoPC.getX()][oCoPC.getY()].setNameOfCell('O');
		oCoPC.setValue('O'); // setValue cho ô cờ
		// setIcon cho button
		banCo[oCoPC.getX()][oCoPC.getY()].setIcon(Constant.button_O);
		// đánh dấu rằng ô cờ đó đã được đánh
		banCo[oCoPC.getX()][oCoPC.getY()].setValue(0); // setValue cho button
		// checkWin
		return checkWin(oCoPC);

	}
}
