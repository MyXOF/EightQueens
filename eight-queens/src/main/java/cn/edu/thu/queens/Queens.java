package cn.edu.thu.queens;

import java.util.ArrayList;
import java.util.List;

public class Queens {
	private int chessboardSize;
	private int[] chessboard;
	private List<int[]> chessboards;
	
	public Queens(int chessboardSize){
		this.chessboardSize = chessboardSize;
		this.chessboard = new int[chessboardSize];		
	}
	
	public void caculateQueensWithoutParallel(int level){
		for(int pos = 0; pos < chessboardSize; pos++){
			if(Utils.checkVaildPostition(chessboard, level, pos)){
				chessboard[level] = pos;
				
				if(level == chessboardSize - 1){
					if(!isChessboardExisted(chessboard)){
//						Utils.showChessboardWithID(count, chessboard, chessboardSize, chessboardSize);
						chessboards.add(chessboard.clone());
					}
					chessboard[level] = -1;
					return;
				}
				
				caculateQueensWithoutParallel(level+1);
				chessboard[level] = -1;
			}
		}		
	}
	
	public void init(){
		chessboards = new ArrayList<>();
	}
	
	public void clear(){
		if(chessboards == null){
			chessboards = new ArrayList<>();
		}
		chessboards.clear();
	}
	
	private boolean isChessboardExisted(int[] newChessboard){
		for(int[] chessboradExisted : chessboards){
			if(Utils.isTwoChessboardIsomorphism(chessboradExisted, newChessboard)) return true;
		}
		return false;
	}
	
	public List<int[]> getResult(){
		return chessboards;
	}
	
	public static void main(String[] args) {
		Queens queens = new Queens(13);
		queens.init();
		long startTime = System.currentTimeMillis(); 
		queens.caculateQueensWithoutParallel(0);
		long endTime = System.currentTimeMillis();
		System.out.println(queens.getResult().size());		
		System.out.println("Time cost: "+(endTime-startTime)+"ms");		
		queens.clear();
	}

}
