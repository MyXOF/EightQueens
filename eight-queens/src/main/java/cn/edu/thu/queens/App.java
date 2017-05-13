package cn.edu.thu.queens;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class App {
	public App(){}
	
	/**
	 * Compare the efficiency differences between serial and parallel
	 * @param start start chessboard size
	 * @param end end chessboard size
	 */
	public void run(int start, int end){
		for(int i = start; i <= end; i++){
			Queens queens = new Queens(i);
			queens.init();
			long startTime = System.currentTimeMillis(); 
			queens.caculateQueensWithoutParallel(0);
			long endTime = System.currentTimeMillis();
			long timeCost1 = endTime - startTime;
			queens.clear();
			
			ForkJoinPool pool = new ForkJoinPool(16);
			ForkJoinTask<Long> task = new QueenTask1(i, 0, i);
			startTime = System.currentTimeMillis(); 
			Long result = pool.invoke(task);
			endTime = System.currentTimeMillis();
			long timeCost2 = endTime - startTime;
			
			System.out.println(String.format("%d * %d, chessboard number: %d, serial %d ms, parallel %d ms", i,i, result,timeCost1,timeCost2));
		}
	}
	
	/**
	 * Caculate different solutions in parallel
	 * @param start start chessboard size
	 * @param end end chessboard size
	 */
	public void runDiff(int start, int end){
		for(int i = start; i <= end; i++){
			long startTime = System.currentTimeMillis(); 
			ForkJoinPool pool = new ForkJoinPool(10);
			ForkJoinTask<List<int[]>> task = new QueensTask2(i, 0, i / 2 +1);
			List<int[]> result = pool.invoke(task);
			long endTime = System.currentTimeMillis();
			long timeCost = endTime - startTime;
			
			System.out.println(String.format("%d * %d, different chessboard number: %d, time cost %d ms", i,i, result.size(), timeCost));
		}
	}
	
	public static void main(String[] args) {
		App app = new App();
		if(args == null || args.length == 0){
			app.run(8, 17);
			return;
		}
		
		String mode = args[0];
		if(mode.equals("diff")){
			app.runDiff(8, 17);
		}else {
			app.run(8, 17);
		}
	}
}
