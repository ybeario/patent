package app;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import patent.service.ServiceToLoadHtml;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月19日 下午2:34:38 类说明
 */
public class Main {
	public static void main(String[] args) {
		ServiceToLoadHtml serviceToLoadHtml = new ServiceToLoadHtml();
		try {
			serviceToLoadHtml.loadHtml();
		} catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
			System.out.println("程序崩溃" + e);
		}
	}
}
