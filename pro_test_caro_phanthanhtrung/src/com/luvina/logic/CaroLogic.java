/**
 * Copyright(C) 2019, Luvina Software Company 
 * CaroLogic.java,Jun 15, 2019 TrungPT
 */
package com.luvina.logic;

import com.luvina.controller.ButtonCaroController;
import com.luvina.entities.OCo;
import com.luvina.utils.Constant;

/** Class lưu các phương thức logic của hệ thống
 * @author TrungPT
 * 
 */
public class CaroLogic {
	/**
	 * Kiểm tra thắng thua
	 * 
	 * @param oCo
	 * @param banCo
	 * @return true nếu thắng
	 */
	public boolean checkWin(OCo oCo, ButtonCaroController[][] banCo) {
		// kiểm tra hàng ngang, hàng dọc, chéo trái, chéo phải
		if (checkHangNgang(oCo, banCo) || checkHangDoc(oCo, banCo) || cheoTrai(oCo, banCo) || cheoPhai(oCo, banCo)) {
			return true;
		}
		return false;
	}

	/**
	 * Kiểm tra hàng ngang
	 */
	private boolean checkHangNgang(OCo oCo, ButtonCaroController[][] banCo) {
		// khởi tạo biến đếm số cờ có giá trị giống nhau
		int cnt = 0; 
		int col = oCo.getY() - 4; // vị trí cột ô cờ đầu tiên lấy ra
		while (col <= oCo.getY() + 4) { // xét các giá trị cột thảo mãn
			if (col >= 0 && col < Constant.MAX_COLS_BOARD) { // nếu cột nằm trong bàn cờ logic
				// nếu giá trị ô cờ đang duyệt bằng giá trị ô cờ vừa đánh
				if (banCo[oCo.getX()][col].getNameOfCell() == oCo.getValue()) {
					cnt++; // tăng biến đếm lên 1
				} else {
					cnt = 0; // nếu không khớp cho biến đếm bằng 0
				}
			}
			if (cnt == 5) { // nếu 5 ô liên tiếp cùng giá trị
				return true; // trả về true
			}
			col++; // tăng số cột lên 1
		}
		return false; // trả về false

	}

	/**
	 * Kiểm tra thắng thua hàng dọc
	 * @param oCo
	 * @param banCo
	 * @return true nếu thắng
	 */
	private boolean checkHangDoc(OCo oCo, ButtonCaroController[][] banCo) {

		int cnt = 0; // khởi tạo biến đếm số cờ có giá trị giống nhau
		int row = oCo.getX() - 4; // vị trí hàng ô cờ đầu tiên lấy ra
		while (row <= oCo.getX() + 4) { // xét các giá trị hàng thảo mãn
			if (row >= 0 && row < Constant.MAX_ROWS_BOARD) { // nếu hàng nằm trong bàn cờ logic
				// nếu giá trị ô cờ đang duyệt bằng giá trị ô cờ vừa đánh
				if (banCo[row][oCo.getY()].getNameOfCell() == oCo.getValue()) {
					cnt++; // tăng biến đếm lên 1
				} else {
					cnt = 0; // nếu không khớp cho biến đếm bằng
				}
			}
			if (cnt == 5) { // nếu 5 ô liên tiếp cùng giá trị
				return true; // trả về true
			}
			row++; // tăng số hàng lên 1
		}
		return false; // trả về false

	}

	/**
	 * Kiểm tra thắng thua theo chéo trái
	 * @param oCo
	 * @param banCo
	 * @return
	 */
	private boolean cheoTrai(OCo oCo, ButtonCaroController[][] banCo) {
		int cnt = 0; // khởi tạo biến đếm số cờ có giá trị giống nhau
		int row = oCo.getX() - 4; // vị trí hàng ô cờ đầu tiên lấy ra
		int col = oCo.getY() - 4; // vị trí cột ô cờ đầu tiên lấy ra
		while (row <= oCo.getX() + 4 && col <= oCo.getY() + 4) { // xét các giá trị hàng thảo mãn
			// nếu hàngnằm trong bàn cờ logic
			if (row >= 0 && col >= 0 && row < Constant.MAX_ROWS_BOARD && col < Constant.MAX_COLS_BOARD) {
				// nếu giá trị ô cờ đang duyệt bằng giá trị ô cờ vừa đánh
				if (banCo[row][col].getNameOfCell() == oCo.getValue()) {
					cnt++; // tăng biến đếm lên 1
				} else {
					cnt = 0; // nếu không khớp cho biến đếm bằng
				}
			}
			if (cnt == 5) { // nếu 5 ô liên tiếp cùng giá tr
				return true;
			}
			row++; // tăng số hàng lên 1
			col++; // tăng số cột lên 1
		}
		return false;
	}

	/**
	 * Kiểm tra thắng thua theo chéo phải
	 * @param oCo
	 * @param banCo
	 * @return true nếu thắng
	 */
	private boolean cheoPhai(OCo oCo, ButtonCaroController[][] banCo) {
		int cnt = 0; // khởi tạo biến đếm số cờ có giá trị giống nhau
		int row = oCo.getX() + 4; // vị trí hàng ô cờ đầu tiên lấy ra
		int col = oCo.getY() - 4; // vị trí cột ô cờ đầu tiên lấy ra
		while (row >= oCo.getX() - 4 && col <= oCo.getY() + 4) { // xét các giá trị hàng thảo mãn
			// nếu hàng nằm trong bàn cờ logic
			if (row >= 0 && col >= 0 && row < Constant.MAX_ROWS_BOARD && col < Constant.MAX_COLS_BOARD) {
				// nếu giá trị ô cờ đang duyệt bằng giá trị ô cờ vừa đánh
				if (banCo[row][col].getNameOfCell() == oCo.getValue()) {
					cnt++; // tăng biến đếm lên 1
				} else {
					cnt = 0; // nếu không khớp cho biến đếm bằng
				}
			}
			if (cnt == 5) { // nếu 5 ô liên tiếp cùng giá tr
				return true;
			}
			row--; // giảm số hàng xuống 1
			col++; // tăng số cột lên 1
		}
		return false;

	}
	/**
	 * phương thức trả về ô cờ cần đánh
	 * @param oCo ô cờ người chơi vừa đánh xong
	 * @param banCo bàn cờ đang đánh
	 * @return 
	 */
	public OCo computerPlay(OCo oCo, ButtonCaroController[][] banCo) {
		ComputerLogic computerLogic = new ComputerLogic(); // Khởi tạo logic máy chơi
		OCo oCoPC = computerLogic.computerPlay(banCo); // ô cờ máy cần đánh
		return oCoPC;
	}
}
