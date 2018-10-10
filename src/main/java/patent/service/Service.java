package patent.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月9日 下午3:41:35 类说明
 */
public class Service {
	public static void main(String[] args)
			throws IOException, InterruptedException, ExecutionException, TimeoutException {
		List<String> list = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 49; i++) {
			list.add(scanner.nextLine());
		}
		// final List<Future> threadList = new ArrayList<Future>();
		ExecutorService pool = Executors.newFixedThreadPool(1);
		for (String keyword : list) {
			try {
				Callable<String> callable = new work(keyword);
				Future<String> submit = pool.submit(callable);
				String string = submit.get(20, TimeUnit.SECONDS);
				map.put(keyword, string);
			} catch (Exception e) {
				System.out.println("当前数据异常");
			}

			// threadList.add(submit);
		}
		// for (Future future : threadList) {
		// Thread t = new Thread(new Runnable() {
		// @Override
		// public void run() {
		// try {
		// future.get(20, TimeUnit.SECONDS);
		// } catch (InterruptedException e) {
		// future.cancel(true);
		// } catch (ExecutionException e) {
		// future.cancel(true);
		// } catch (TimeoutException e) {
		// future.cancel(true);
		// }
		// }
		// });
		// t.start();
		//
		// }
		pool.shutdown();
		map.forEach((k, v) -> {
			System.out.println(k + ":" + v);
		});
	}

}

class work implements Callable<String> {
	private final String keyword;

	public work(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String call() throws Exception {
		PatentGetList getList = new PatentGetList(keyword);
		String result = getList.getResult();
		System.out.println(Thread.currentThread().getName() + ": 获取数据--->>" + result);
		return result;
	}

}
