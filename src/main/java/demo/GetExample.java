package demo;
/**
* @author Y.bear
* @version 创建时间：2018年10月9日 上午11:02:17
* 类说明
*/

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetExample {
	OkHttpClient client = new OkHttpClient();

	String run(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	public static void main(String[] args) throws IOException {
		GetExample example = new GetExample();
		String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
		System.out.println(response);
	}
}