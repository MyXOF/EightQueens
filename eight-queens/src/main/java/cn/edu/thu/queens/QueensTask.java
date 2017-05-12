package cn.edu.thu.queens;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class QueensTask extends RecursiveTask<List<int[]>>{
	private static final long serialVersionUID = 7134244215582255826L;

	private int chessboardSize;
	private int[] chessboard;
	private List<int[]> chessboards;
	private int start;
	private int end;
	
	private final int THRESHOLD = 2;
	
	public QueensTask(int chessboardSize, int start, int end){
		this.chessboardSize = chessboardSize;
		this.chessboards = new ArrayList<>();
		this.chessboard = new int[chessboardSize];
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected List<int[]> compute() {
		if(end - start < THRESHOLD){
			for(int i = start; i < end; i++){
				chessboard[0] = i;
				caculateQueens(1);
			}			
			return chessboards;
		}
		
		int middle = (end + start) / 2;
		
		QueensTask subtask1 = new QueensTask(chessboardSize, start, middle);
		QueensTask subtask2 = new QueensTask(chessboardSize, middle, end);
        invokeAll(subtask1, subtask2);
        
        List<int[]> result1 = subtask1.join();
        List<int[]> result2 = subtask2.join();
        
        List<int[]> result = mergeResult(result1, result2);

        return result;
	}
	
	private void caculateQueens(int level){
		for(int pos = 0; pos < chessboardSize; pos++){
			if(Utils.checkVaildPostition(chessboard, level, pos)){
				chessboard[level] = pos;
				
				if(level == chessboardSize - 1){
					if(!isChessboardExisted(chessboard)){
//						Utils.showChessboard(chessboard, chessboardSize, chessboardSize);
						chessboards.add(chessboard.clone());
					}
					chessboard[level] = -1;
					return;
				}
				
				caculateQueens(level+1);
				chessboard[level] = -1;
			}
		}			
	}
	
	private boolean isChessboardExisted(int[] newChessboard){
		for(int[] chessboradExisted : chessboards){
			if(Utils.isTwoChessboardIsomorphism(chessboradExisted, newChessboard)) return true;
		}
		return false;
	}

	private static List<int[]> mergeResult(List<int[]> resultList1, List<int[]> resultList2){
		int len1 = resultList1.size();
		int len2 = resultList2.size();
		List<int[]> resultMerge = new ArrayList<>();
		
		if(len1 == 0) return resultList2;
		if(len2 == 0) return resultList1;
		
		for(int[] i : resultList1){
			resultMerge.add(i);
		}
		
		for(int[] i : resultList2){
			boolean flag = false;
			for(int[] j : resultList1){
				if(Utils.isTwoChessboardIsomorphism(i, j)){
					flag = true;
					break;
				}
			}
			if(!flag){
				resultMerge.add(i);
			}
		}
		return resultMerge;
	}
	
	public static void main(String[] args) {
		int size = 13;
		ForkJoinPool pool = new ForkJoinPool(10);
		ForkJoinTask<List<int[]>> task = new QueensTask(size, 0, size);
		long startTime = System.currentTimeMillis(); 
		List<int[]> result = pool.invoke(task);
		long endTime = System.currentTimeMillis();
		System.out.println(result.size());
		System.out.println("Time cost: "+(endTime-startTime)+"ms");
		
//		for(int[] i: result){
//			Utils.showChessboard(i, size, size);
//		}
		
//		List<int[]> l1 = new ArrayList<>();
//		List<int[]> l2 = new ArrayList<>();
//		int[] a1 = {3, 0, 2, 4, 1};
//		int[] a2 = {3, 1, 4, 2, 0};
//		int[] a3 = {4, 1, 3, 0, 2};
//		
//		l1.add(a1);
//		l1.add(a2);
//		l2.add(a3);
//		System.out.println(mergeResult(l1, l2).size());
		
	}
}
