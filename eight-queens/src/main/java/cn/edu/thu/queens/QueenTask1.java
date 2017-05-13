package cn.edu.thu.queens;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class QueenTask1 extends RecursiveTask<Long>{
	private static final long serialVersionUID = -4733775792213903707L;
	private int chessboardSize;
	private int[] chessboard;
	private int start;
	private int end;
	private long count;
	
	private final int THRESHOLD = 2;
	
	public QueenTask1(int chessboardSize, int start, int end){
		this.chessboardSize = chessboardSize;
		this.chessboard = new int[chessboardSize];
		this.start = start;
		this.end = end;
		this.count = 0;
	}
	
	@Override
	protected Long compute() {
		if(end - start < THRESHOLD){
			for(int i = start; i < end; i++){
				chessboard[0] = i;
				caculateQueens(1);
			}			
			return count;
		}
		
		int middle = (end + start) / 2;
		QueenTask1 subtask1 = new QueenTask1(chessboardSize, start, middle);
		QueenTask1 subtask2 = new QueenTask1(chessboardSize, middle, end);
        invokeAll(subtask1, subtask2);
        
        Long result1 = subtask1.join();
        Long result2 = subtask2.join();
        return result1+result2;
	}
	
	private void caculateQueens(int level){
		for(int pos = 0; pos < chessboardSize; pos++){
			if(Utils.checkVaildPostition(chessboard, level, pos)){
				chessboard[level] = pos;
				
				if(level == chessboardSize - 1){
					count++;
					chessboard[level] = -1;
					return;
				}
				
				caculateQueens(level+1);
				chessboard[level] = -1;
			}
		}			
	}
	
	public static void main(String[] args) {
		int size = 17;
		ForkJoinPool pool = new ForkJoinPool(16);
		ForkJoinTask<Long> task = new QueenTask1(size, 0, size);
		long startTime = System.currentTimeMillis(); 
		Long result = pool.invoke(task);
		long endTime = System.currentTimeMillis();
		System.out.println(result);
		System.out.println("Time cost: "+(endTime-startTime)+"ms");
	}
}
