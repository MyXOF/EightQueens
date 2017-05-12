package cn.edu.thu.queens;

import java.util.ArrayList;
import java.util.List;

public class Queens {
	private int chessboardSize;
	private int[] chessboard;
	private int count;
	private List<int[]> chessboards;
	
	public Queens(int chessboardSize){
		this.chessboardSize = chessboardSize;
		this.chessboard = new int[chessboardSize];		
	}
	
	public void caculateQueensWithoutParallel(int level){
		
	}
	
	public void caculateQueensWithoutInParallel(int level){
		for(int pos = 0; pos < chessboardSize; pos++){
			if(checkVaildPostition(chessboard, level, pos)){
				chessboard[level] = pos;
				
				if(level == chessboardSize - 1){
					if(!isChessboardExisted(chessboard)){
						Utils.showChessboardWithID(count, chessboard, chessboardSize, chessboardSize);
						count++;
						chessboards.add(chessboard.clone());
					}
					chessboard[level] = -1;
					return;
				}
				
				caculateQueensWithoutInParallel(level+1);
				chessboard[level] = -1;
			}
		}
	}
	
	public void init(){
		chessboards = new ArrayList<>();
		count = 0;
	}
	
	public void clear(){
		count = 0;
		if(chessboards == null){
			chessboards = new ArrayList<>();
		}
		chessboards.clear();
	}
	
	private boolean checkVaildPostition(int[] board, int level, int pos){
		for(int i = 0; i < level; i++){
			if(board[i] == pos) return false;
			if(i - level == board[i] - pos) return false;
			if(i - level == pos - board[i]) return false;
		}
		return true;
	}
	
	private boolean isChessboardExisted(int[] newChessboard){
		for(int[] chessboradExisted : chessboards){
			if(isTwoChessboardIsomorphism(chessboradExisted, newChessboard)) return true;
		}
		return false;
	}
	
	private boolean isTwoChessboardIsomorphism(int[] chessboard1, int[] chessboard2){
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
	
	private boolean isTwoChessboardIsomorphismByRoate(int[] chessboard1, int[] chessboard2,int len){
		if(isTwoChessboardSame(chessboard1, chessboard2, len)) return true;
		
		int[] newChessboard = Utils.roateNinetyDegree(chessboard1);
		if(isTwoChessboardSame(newChessboard, chessboard2, len)) return true;
		
		newChessboard = Utils.roateNinetyDegree(newChessboard);
		if(isTwoChessboardSame(newChessboard, chessboard2, len)) return true;	
		
		newChessboard = Utils.roateNinetyDegree(newChessboard);
		if(isTwoChessboardSame(newChessboard, chessboard2, len)) return true;
		
		return false;
	}
	
	private boolean isTwoChessboardSame(int[] chessboard1, int[] chessboard2, int len){
		for(int i = 0; i < len; i++){
			if(chessboard1[i] != chessboard2[i]) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
//		Queens queens = new Queens(Config.SIZE_OF_CHESSBOARD);
		Queens queens = new Queens(10);
		queens.init();
		queens.caculateQueensWithoutInParallel(0);
		queens.clear();

	}

}
