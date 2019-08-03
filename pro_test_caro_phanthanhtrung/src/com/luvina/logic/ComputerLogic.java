/**
 * Copyright(C) 2019, Luvina Software Company 
 * Computer.java,Jun 14, 2019 TrungPT
 */
package com.luvina.logic;

import java.util.ArrayList;
import java.util.Random;

import com.luvina.controller.ButtonCaroController;
import com.luvina.entities.OCo;
import com.luvina.model.TheCoModel;
import com.luvina.utils.Constant;

/** 
 * Class lưu các phương thức logic giúp máy chơi
 * @author TrungPT
 * 
 */
public class ComputerLogic {
	private ArrayList<char[][]> listTheCo;// list danh sách các thế cờ đọc trong file

	/**
	 * phương thức khởi tạo máy chơi
	 */
	public ComputerLogic() {
		listTheCo = TheCoModel.getListMatrixByFile(); // lấy danh sách thế cờ trong file thế cờ
		// test đọc file theco
//		for (int i = 0; i < listTheCo.size(); i++) {
//			for (int j = 0; j < 5; j++) {
//				for (int j2 = 0; j2 < 5; j2++) {
//					System.out.print(listTheCo.get(i)[j][j2] );
//				}
//				System.out.println("");
//			}
//		}
//		System.out.println();
	}

	/**
	 * phương thức máy chơi
	 * 
	 * @param banCo bàn cờ máy đánh
	 * @return ô cờ máy đánh
	 */
	public OCo computerPlay(ButtonCaroController[][] banCo) {
		// tạo đối tượng ô cờ để lưu ô cờ cần đánh
		OCo oCoCanDanh = new OCo(); 
		// khởi tạo danh sách ma trận 5*5 từ bàn cờ
		ArrayList<char[][]> listMatrixBoard = getListMatrix(banCo);
		// index của ma trân 5*5 được lấy ra trong listTheCo
		int index = -1; 
		// nếu mảng thế cờ ko rỗng
		if (this.listTheCo.size() > 0) {
			// duyệt list thế cờ
			duyetListTheCo: for (char[][] matrixChildTheCo : this.listTheCo) {
				// duyet từng ma trận bàn cờ logic
				for (char[][] matrixChildBoard : listMatrixBoard) { 
					// nếu 2 ma trận khớp nhau
					if (checkBoard(matrixChildTheCo, matrixChildBoard)) { 
						// lấy vị trí của thế cờ trong mảng danh sách
						index = listMatrixBoard.indexOf(matrixChildBoard); 
						System.out.println(index);
						oCoCanDanh = this.getPointBesting(matrixChildTheCo); // gán giá trị ô cờ
						// nếu không có thế cờ nào trong listTheCo phừ hợp
						if (oCoCanDanh == null) {
							continue; // bỏ qua và lặp lại
						} else {
							break duyetListTheCo;
						}
					}
				}
			}
		}
		
		if (index == -1 || oCoCanDanh == null) { // nếu không tim thấy thế cờ trùng
			
			return playRandom(banCo);// máy đánh random
		} else {// tìm thấy thế cờ
			
			int r = index / (Constant.MAX_ROWS_BOARD - 4); // chỉ số hàng bắt đầu ma trận
			int c = index % (Constant.MAX_COLS_BOARD - 4); // chỉ số cột bắt đầu ma trận
			oCoCanDanh.setX(r + oCoCanDanh.getX()); // thay đổi gtri hàng trên bàn cờ
			oCoCanDanh.setY(c + oCoCanDanh.getY()); // thay đổi gtri cột trên bàn cờ
		}
		
		return oCoCanDanh;
	}

	/**
	 * máy đánh random các ô cờ rỗng
	 * 
	 * @param banCo bàn cờ caro
	 * @return ô cờ đánh random
	 */
	private OCo playRandom(ButtonCaroController[][] banCo) {
		
		OCo oCoCanDanh = new OCo(); // tạo đối tượng ô cờ để lưu ô cờ cần đánh
		Random random = new Random(); // khởi tạo 1 đối tượng random cho máy
		int row = 0; // khởi tạo 1 biến chứa hàng của vị trí  ô cờ cần đánh
		int column = 0; // khởi tạo 1 biến chứa cột của vị trí ô cờ cần đánh
		do {
			// random cho row trong khoảng từ 0 đến 19
			row = random.nextInt(Constant.MAX_ROWS_BOARD);
			// gán giá trị của cột cho 1 biến random
			column = random.nextInt(Constant.MAX_COLS_BOARD); 
			oCoCanDanh.setX(row); // set giá trị hàng của ô cờ máy cần đánh
			oCoCanDanh.setY(column); // set giá trị cột của ô cờ máy cần đánh
			
			// khi ô cờ random đã được đánh rồi thì cho random lại
		} while (banCo[row][column].getNameOfCell() != 'D');
		return oCoCanDanh; // trả về ô cờ cần đánh của máy
	}

	/**
	 * xác định vị trí đánh của thế cờ
	 * 
	 * @param value giá trị đánh thế cờ
	 * @return ô cờ nếu tìm được
	 */
	public OCo getPointBesting(char[][] value) {
		OCo oCoCanDanh = new OCo(); // tạo đối tượng ô cờ để lưu ô cờ cần đánh
		for (int hang = 0; hang < 5; hang++) {// duyệt hàng
			for (int cot = 0; cot < 5; cot++) {// duyệt cột
				// nếu giá trị là T là ô cần đánh
				if (value[hang][cot] == 'T') {
					oCoCanDanh.setX(hang); // thay đổi gtri hàng cho vị trí ô cờ
					oCoCanDanh.setY(cot); // thay đổi gtri cột cho vị trí ô cờ
					return oCoCanDanh; // trả về ô cờ
				}
			}
		}
		return null; // trả về null nếu không tìm thấy ô đánh
	}

	/**
	 * kiểm tra so khớp thế cờ
	 * 
	 * @param matrixChildTheCo  thế cờ trong file thế cờ
	 * @param matrix thế cờ từ bàn cờ logic
	 * @return true nếu 2 thế cờ khớp
	 */
	private boolean checkBoard(char[][] matrixChildTheCo, char[][] matrix) {
		int cnt = 0; // khởi tạo biến đếm số ô
		label: for (int i = 0; i < 5; i++) { // duyệt theo hàng
			for (int j = 0; j < 5; j++) { // duyệt theo cột
				char c = matrixChildTheCo[i][j]; // lấy giá tri của ô cờ trong file thế cờ
				char statusValue = matrix[i][j]; // lấy giá trị ô cờ từ bàn cờ
				if (c == 'G') { // nếu giá trị là G: don't care
					cnt++; // tăng biến đếm lên 1
					continue;
				} else {// còn lại là các giá trị O, X, T, D
						// nếu giá trị của ô cờ thế cờ bằng giá trị bàn cờ
					if (c == statusValue) {
						cnt++; // tăng biến đếm lên 1
						continue;
						// nếu giá trị thế cờ D và bàn cờ là rống
					} else if (c == 'T' && statusValue == 'D') {
						cnt++;
						continue;
					} else {
						break label;// nếu không phải thoát vòng lặp
					}
				}
			}
		}
		if (cnt == 25) { // nếu số ô khớp là 25
			return true; // trả về giá trị true
		} else { // nếu số ô khớp khác 25
			return false; // trả về gái trị false
		}
	}

	/**
	 * lấy danh sách bàn cờ 5*5 trên bàn cờ
	 * @return mảng các ma trận 5*5
	 */
	public ArrayList<char[][]> getListMatrix(ButtonCaroController[][] banCo) {
		// khởi tạo danh sách mảng lưu ma trận bàn cờ con 5*5
		ArrayList<char[][]> list = new ArrayList<char[][]>();
		for (int i = 0; i <= Constant.MAX_ROWS_BOARD - 5; i++) { // duyệt theo hàng
			for (int j = 0; j <= Constant.MAX_COLS_BOARD - 5; j++) { // duyệt theo cột
				char[][] status = matrixBoard(banCo, i, j); // lấy ra ma trận bàn cờ 5*5
				list.add(status); // thêm vào danh sách bàn cờ
			}
		}
		return list; // trả về danh sách trạng thái bàn cờ
	}

	/**
	 * lấy ma trận 5*5 trong bàn cờ
	 * @param banCo mảng button chứa ô cờ 
	 * @param row   vị trí hang đầu
	 * @param col   vị trí cột đầu
	 * @return mảng kí tự 2 chiều 5*5
	 */
	public char[][] matrixBoard(ButtonCaroController[][] banCo, int row, int col) {
		char[][] matrix = new char[5][5]; // khởi tạo mảng 2 chiều 5*5
		for (int i = 0; i < 5; i++) { // duyet theo hàng
			for (int j = 0; j < 5; j++) { // duyệt theo cột
				// nếu button là rỗng
				if ("".equals(banCo[row + i][col + j].getValue())) {
					matrix[i][j] = (char) 'D'; // gán giá trị là D
				} else {
					// gán giá trị trên bàn cờ với từng giá trị trong ma trận 5*5
					matrix[i][j] = banCo[row + i][col + j].getNameOfCell();
				}
			}
		}
		return matrix; // tra về ma trận 5*5
	}
}
