package cn.edu.thu.queens;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class App {
	public App(){}
	
	public void run(int start, int end){
		for(int i = start; i <= end; i++){
			Queens queens = new Queens(i);
			queens.init();
			long startTime = System.currentTimeMillis(); 
			queens.caculateQueensWithoutParallel(0);
			long endTime = System.currentTimeMillis();
			long timeCost1 = endTime - startTime;
			queens.clear();
			
			ForkJoinPool pool = new ForkJoinPool(10);
			ForkJoinTask<Long> task = new QueenTask1(i, 0, i);
			startTime = System.currentTimeMillis(); 
			Long result = pool.invoke(task);
			endTime = System.currentTimeMillis();
			long timeCost2 = endTime - startTime;
			
			System.out.println(String.format("%d * %d, chessboard number: %d, native %d ms, parallel %d ms", i,i, result,timeCost1,timeCost2));
			
		}
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.run(8, 17);

	}

}
