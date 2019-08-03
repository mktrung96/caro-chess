/**
 * Copyright(C) 2019, Luvina Software Company 
 * TheCo.java,Jun 14, 2019 TrungPT
 */
package com.luvina.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.luvina.utils.Constant;

/**
 * Đọc file TheCo.txt
 * 
 * @author TrungPT
 * 
 */
public class TheCoModel {
	/**
	 * Đọc dữ liệu từ file TheCo.txt
	 * 
	 * @return danh sách ma trận thế cờ
	 */
	public static ArrayList<char[][]> getListMatrixByFile() {
		ArrayList<char[][]> listMatrix = new ArrayList<char[][]>();
		try {
			File file = new File(Constant.FILE_NAME); // khởi tạo 1 đối tượng file
			// kiểm tra xem đối tượng file thế cờ có tồn tại
			if (file.exists()) {
				// khai báo 1 đối tượng file reader để đọc dữ liệu từ luồng đọc file
				FileReader fileReader = new FileReader(Constant.FILE_NAME);
				if (file != null) { // nếu file khác null
					BufferedReader bufferedReader = new BufferedReader(fileReader); // khởi tạo 1 luồng đê đọc file thế
																					// cờ
					// listMatrix các mảng 5*5
					listMatrix = getArrayChar(bufferedReader);
					bufferedReader.close(); // đóng luồng đọc file
				}
			}

		} 
		catch (IOException e) { // catch IOException là bắt cho cả FileReader rồi!
			Logger.getLogger(TheCoModel.class.getName()).log(Level.SEVERE, "lỗi đọc mảng các ma trận!", e);
			System.out.println("lỗi đọc mảng các ma trận!");
		}

		return listMatrix;

	}

	/**
	 * Lấy ra các mảng ma trận 5*5
	 * 
	 * @param br
	 * @return danh sách các mảng kí tự 2 chiều 5*5
	 * @throws IOException
	 */
	private static ArrayList<char[][]> getArrayChar(BufferedReader bufferedReader) throws IOException {
		// khởi tạo list
		ArrayList<char[][]> listArr = new ArrayList<char[][]>();
		// đọc từng dòng
		String line = bufferedReader.readLine();
		// trong khi file thế cờ chưa được đọc hết
		while (line != null) {
			char[][] matrix55 = new char[5][5]; // khởi tạo 1 mảng 2 chiều 5 * 5
			for (int hang = 0; hang < 5; hang++) { // duyệt theo hàng
				// kiểm dong đọc được khác null và đủ năm kí tự hay không
				if (line != null && line.length() == 5) {
					for (int cot = 0; cot < 5; cot++) { // duyệt theo cột
						matrix55[hang][cot] = line.charAt(cot); // gán giá trị đọc được
					}
				}
				// đọc dòng tiếp theo
				line = bufferedReader.readLine();
			}
			// nếu dữ liêu của thế cờ đọc được hợp lệ
			if (checkValue(matrix55)) { 
				// thêm ma trận thế cờ vừa đọc được vào danh sách thế cờ
				listArr.add(matrix55); 
			}
		}
		return listArr;
	}

	/**
	 * Kiểm tra dữ liệu trong ma trận thế cờ đọc được từ file TheCo.txt
	 * 
	 * @return true nếu có giá trị cần đánh, return false nếu không có ít nhất hoặc nhiều hơn 1 vị trí cần đánh
	 */
	private static boolean checkValue(char[][] arr) {
		// khởi tạo biến đếm vị trí cần đánh trên thế cờ
		int count = 0;
		// duyệt từng hàng của ma trận thế cờ
		for (char[] array : arr) {
			// duyệt từng cột của ma trận thế cờ
			for (char value : array) {
				// nếu kí tự bằng null
				if (value == (char) 0) {
					return false; //  mảng không dùng được
				} else if (value == 'T') {// T ô máy cần đánh vào
					count++; // tăng biến đếm lên 1
				}
				// O Quân cờ của máy, X quân cờ của người, D ô cờ trống , G don't care,
				else if (value != 'X' && value != 'O' && value != 'D' && value != 'G' && value != 'D') {
					// kiểm tra xem các kí tự của thế cờ có hợp lệ hay không
					return false; // không hợp lệ trả về false
				}

			}
		}
		// nếu không có ít nhất 1 vị trí hoặc nhiều hơn 1 vị trí cần đánh
		if (count != 1) {
			return false;
		}
		return true;
	}
}
