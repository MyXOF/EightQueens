package cn.edu.thu.queens;

import java.util.HashSet;
import java.util.Set;

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

	public static  boolean checkVaildPostition(int[] board, int level, int pos){
		for(int i = 0; i < level; i++){
			if(board[i] == pos) return false;
			if(i - level == board[i] - pos) return false;
			if(i - level == pos - board[i]) return false;
		}
		return true;
	}
	
	public static boolean isTwoChessboardIsomorphism(int[] chessboard1, int[] chessboard2){
		int len1 = chessboard1.length;
		int len2 = chessboard2.length;
		
		if(len1 != len2) return false;
		
		if(isTwoChessboardIsomorphismByRoate(chessboard1, chessboard2, len1)) return true;
		
		int[] newboard1 = Utils.flipChessboardHorizontally(chessboard1);
		if(isTwoChessboardIsomorphismByRoate(newboard1, chessboard2, len1)) return true;
		
		int[] newboard2 = Utils.flipChessboardVertically(chessboard1);
		if(isTwoChessboardIsomorphismByRoate(newboard2, chessboard2, len1)) return true;
		
		int[] newboard3 = Utils.flipChessboardHorizontally(newboard2);
		if(isTwoChessboardIsomorphismByRoate(newboard3, chessboard2, len1)) return true;	
		
		return false;
	}
	
	public static boolean isTwoChessboardIsomorphismByRoate(int[] chessboard1, int[] chessboard2,int len){
		if(isTwoChessboardSame(chessboard1, chessboard2, len)) return true;
		
		int[] newChessboard = Utils.roateNinetyDegree(chessboard1);
		if(isTwoChessboardSame(newChessboard, chessboard2, len)) return true;
		
		newChessboard = Utils.roateNinetyDegree(newChessboard);
		if(isTwoChessboardSame(newChessboard, chessboard2, len)) return true;	
		
		newChessboard = Utils.roateNinetyDegree(newChessboard);
		if(isTwoChessboardSame(newChessboard, chessboard2, len)) return true;
		
		return false;
	}
	
	public static Set<String> getAllIsomorphicChessboards(int[] chessboard){
		Set<String> chessboards = new HashSet<String>();
		
		addChessboards(chessboards, chessboard);
		
		int[] newboard1 = Utils.flipChessboardHorizontally(chessboard);
		addChessboards(chessboards, newboard1);
		
		int[] newboard2 = Utils.flipChessboardVertically(chessboard);
		addChessboards(chessboards, newboard2);

		int[] newboard3 = Utils.flipChessboardHorizontally(newboard2);
		addChessboards(chessboards, newboard3);

		return chessboards;
	}
	
	private static void addChessboards(Set<String> chessboards, int[] chessboard){
		chessboards.add(convertArrayToString(chessboard));
		
		int[] newboard1 = roateNinetyDegree(chessboard);
		chessboards.add(convertArrayToString(newboard1));
		
		int[] newboard2 = roateNinetyDegree(newboard1);
		chessboards.add(convertArrayToString(newboard2));

		int[] newboard3 = roateNinetyDegree(newboard2);
		chessboards.add(convertArrayToString(newboard3));
	}
	
	public static String convertArrayToString(int[] input){
		StringBuilder builder = new StringBuilder();
		for(int i: input){
			builder.append(i);
		}
		return builder.toString();
	}
	
	public static boolean isTwoChessboardSame(int[] chessboard1, int[] chessboard2, int len){
		for(int i = 0; i < len; i++){
			if(chessboard1[i] != chessboard2[i]) return false;
		}
		return true;
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
