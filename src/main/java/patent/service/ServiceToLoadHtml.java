package patent.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月19日 下午2:31:21 类说明
 */
public class ServiceToLoadHtml {

	public void loadHtml() throws IOException, InterruptedException, ExecutionException, TimeoutException {
		List<String> list = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		long start = System.currentTimeMillis();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("请输入数据量");
		int num = Integer.parseInt(bufferedReader.readLine());
		for (int i = 0; i < num; i++) {
			String temp = bufferedReader.readLine().trim();
			if (temp.length() != 16) {
				if (temp.length() == 10) {
					if (temp.startsWith("9")) {
						StringBuilder builder = new StringBuilder("ZL19");
						temp = builder.append(temp.substring(0, 3)).append("00")
								.append(temp.substring(3, temp.length())).toString();
					} else {
						StringBuilder builder = new StringBuilder("ZL20");
						temp = builder.append(temp.substring(0, 3)).append("00")
								.append(temp.substring(3, temp.length())).toString();
					}
				} else if (temp.length() == 14) {
					temp = "ZL" + temp;
				}
			}
			list.add(temp);
		}
		ExecutorService pool = Executors.newFixedThreadPool(1);
		for (String keyword : list) {
			try {
				Callable<String> callable = new WorkToHtml(keyword);
				Future<String> submit = pool.submit(callable);
				String string = submit.get(60, TimeUnit.SECONDS);
				map.put(keyword, string);
			} catch (Exception e) {
				System.out.println("当前数据异常");
				map.put(keyword, "异常");
				e.printStackTrace();
			} finally {
				num--;
				System.out.println("剩余：" + num + "条");
			}
		}

		pool.shutdown();

		System.out.println("--------------------------分割线----------------------------");
		for (String string : list) {
			System.out.println(map.get(string));
		}
		System.out.println(System.currentTimeMillis() - start);
	}

}

class WorkToHtml implements Callable<String> {
	private final String keyword;

	public WorkToHtml(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String call() throws Exception {
		PatentGetList getList = new PatentGetList(keyword);
		Map<String, Object> urlParas = getList.getHtmlResult();
		urlParas.put("key", keyword);
		PatDetails details = new PatDetails();
		details.getResult(urlParas);
		String re = "test";
		return re;
	}

}