package cn.edu.thu.queens;

public class Utils {
	public static void showChessboardWithID(int id, int chessborad[],int row, int line){
		System.out.println("ID: "+id);
		showChessboard(chessborad, row, line);
		System.out.println("------------------");
	}
	
	public static void showChessboard(int chessborad[],int row, int line){
		StringBuilder header = new StringBuilder();
		header.append(" ");
		for(int i = 0; i < line;i++){
			header.append(" ");
			header.append((char)('a'+i));
			header.append(" ");
		}	
		System.out.println(header.toString());

		for(int r = 0; r < row; r++){
			StringBuilder builder = new StringBuilder();
			builder.append(row - r);
			for(int l = 0; l < line; l++){
				builder.append(chessborad[r] == l ? " Q " : " - ");
			}
			builder.append(row - r);
			System.out.println(builder.toString());
		}
		
		StringBuilder bottom = new StringBuilder();
		bottom.append(" ");
		for(int i = 0; i < line;i++){
			bottom.append(" ");
			bottom.append((char)('a'+i));
			bottom.append(" ");
		}
		System.out.println(bottom.toString());
	}
	
	public static int[] flipChessboardHorizontally(int[] chessboard){
		int len = chessboard.length;
		
		int[] newChessboard = new int[len];
		for(int i = 0; i < len; i++){
			newChessboard[i] = len - 1 - chessboard[i];
		}
		return newChessboard;
	}
	
	public static int[] flipChessboardVertically(int[] chessboard){
		int len = chessboard.length;
		
		int[] newChessboard = new int[len];
		for(int i = 0; i < len; i++){
			newChessboard[i] = chessboard[len - 1 - i];
		}
		return newChessboard;		
	}
	
	public static int[] roateNinetyDegree(int[] chessboard){
		int len = chessboard.length;
		
		int[] newChessboard = new int[len];
		for(int i = 0; i < len; i++){
			newChessboard[chessboard[i]] = i;
		}
		return newChessboard;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] chessboard = {
				1,3,0,2
		};
		showChessboard(chessboard, 4, 4);
	}

}
