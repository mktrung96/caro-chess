/**
 * Copyright(C) 2019, Luvina Software Company 
 * DisplayPanel.java,Jun 13, 2019 TrungPT
 */
package com.luvina.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.luvina.entities.OCo;
import com.luvina.utils.Constant;
import com.luvina.view.DisplayFrame;

/**
 * Tạo button cho mỗi ô cờ
 * 
 * @author TrungPT
 * 
 */
public class ButtonCaroController extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int countNumberChess;
	private int locationX; // tọa độ theo trục x trên bàn cờ
	private int locationY; // tọa độ theo trục y trên bàn cờ
	// giá trị của ô cờ,
	// nếu value = -1 thì nghĩa là ô cờ đó chưa được đánh
	private int value; // = 0 nếu ô đã được đánh, -1 nếu ô chưa đc đánh
	private char nameOfCell; // Kí tự của mỗi ô cờ X : người đánh , O máy đánh, được dùng để so sánh với file
								// theco.txt

	/**
	 * Phương thức khởi tạo
	 */
	public ButtonCaroController() {
//		// khởi tạo giá trị mặc định cho value, value =-1 nghĩa là ô chưa đánh
		value = -1;
		// bắt sự kiện cho từng button
		this.addActionListener(this);
	}

	/*
	 * (non-Javadoc) Phương thức bắt sự kiện click của button
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) Bắt
	 * sự kiện click vào button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btClick = (JButton) e.getSource(); // get button hiện tại
		JPanel caroPanel = (JPanel) btClick.getParent(); // lấy components cha của button hiện tại
		DisplayFrame frame = (DisplayFrame) SwingUtilities.getWindowAncestor(caroPanel); // jFrame hiện tại
		// biến trạng thái checkWin
		boolean isWin = false;
		// biến đếm số nước đánh
		
		// Nếu giá trị value nút hiện tại là -1 và chưa có ai thắng thì mới cho đánh
		if (this.value == -1 && !isWin) {
			// trường hợp chưa có chiến thắng
			this.setIcon(Constant.button_X);
			// set nameOfCell cho button
			this.setNameOfCell('X');
			countNumberChess++;
			// đánh dấu rằng ô cờ đó đã được đánh
			this.value = 0;
			// khởi tạo đối tượng OCo
			OCo oCo = new OCo(locationX, locationY, nameOfCell);
			// checkWin cho ô cờ mà người chơi vừa đánh
			isWin = DisplayFrame.checkWin(oCo);
			// nếu không thắng
			if (!isWin) {
				// máy đánh
				boolean pcWin = DisplayFrame.computerPlay(oCo);
				countNumberChess++;
				// nếu máy đánh thắng
				if (pcWin) {
					// hiển thị màn hình hỏi người chơi lại không
					replay(showMessage('O'), frame);
				}
			}
			else {
				// người chơi thắng và hiển thị màn hình hỏi người chơi lại không
				replay(showMessage('X'), frame);
			}
			System.out.println(countNumberChess);
			// trường hợp không có ai chiến thắng
			if (countNumberChess == 400) {
				replay(showMessage('H'), frame);
			}
		}
		
	}

	/**
	 * hiển thị thông báo thắng, thua
	 * 
	 * @param type giá trị quân cờ
	 * @return lựa chọn mà người chơi đã chọn trên khung thông báo
	 */
	public int showMessage(char type) {
		// khởi tạo biến cờ kiểm tra đã có ai thắng chưa
		boolean flagWin = true;
		// biến chứa thông báo ra màn hình
		String winner = "";
		if (type == 'X') {
			winner = "YOU WIN";
			// gán giá trị cờ bằng false
			flagWin = false;
		} else if (type == 'O') {
			winner = "YOU LOSE";
			flagWin = false; // gán giá trị cờ bằng false
		}
		if (flagWin) { // nếu không có ai chiến thắng
			// hiển thị confirm cho người chọn
			int result = JOptionPane.showConfirmDialog(this, "HÒA RỒI \r\n" + "bạn có muốn chơi tiếp không",
					"Thông báo", JOptionPane.YES_NO_OPTION);
			return result;
		} 
		else {
			// hiển thị confirm cho người chọn
			int result = JOptionPane.showConfirmDialog(this, winner + "\r\n" + "bạn có muốn chơi tiếp không",
					"Thông báo", JOptionPane.YES_NO_OPTION);
			return result;
		}
	}

	/**
	 * tạo bàn cờ, dừng chương trình
	 * 
	 * @param select sự lựa chọn của người chơi khi ấn nút close frame
	 * @param frame  JFrame chứa button hiện tại
	 */
	private void replay(int select, DisplayFrame frame) {
		if (select == JOptionPane.YES_OPTION) { // nếu chọn có
			// đóng JFrame hiện tại
			frame.dispose();
			// Khởi tạo JFrame giống như trong main
			DisplayFrame displayFrame = new DisplayFrame();
			displayFrame.createBoardChess();
			// chọn không hoặc tắt chương trình
		} else if (select == JOptionPane.NO_OPTION || select == JOptionPane.CLOSED_OPTION) { //
			frame.dispose(); // đóng JFrame hiện tại
		}
	}

	/**
	 * @return the locationX
	 */
	public int getLocationX() {
		return locationX;
	}

	/**
	 * @param locationX the locationX to set
	 */
	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}

	/**
	 * @return the locationY
	 */
	public int getLocationY() {
		return locationY;
	}

	/**
	 * @param locationY the locationY to set
	 */
	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the nameOfCell
	 */
	public char getNameOfCell() {
		return nameOfCell;
	}

	/**
	 * @param nameOfCell the nameOfCell to set
	 */
	public void setNameOfCell(char nameOfCell) {
		this.nameOfCell = nameOfCell;
	}

}
