package patent.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import patent.service.constant.PatentInfo;
import util.JsonToMap;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月9日 下午2:07:09 类说明
 */
public class DoPatSearch {
	public String word;

	public DoPatSearch(String word) {
		this.word = word;
	}

	OkHttpClient client = new OkHttpClient();

	private String post(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(PatentInfo.JSON, json);
		Request request = new Request.Builder().url(url).addHeader("Origin", PatentInfo.Origin)
				.addHeader("X-Requested-With", PatentInfo.X_Requested_With)
				.addHeader("User-Agent", PatentInfo.User_Agent).addHeader("Content-Type", PatentInfo.Content_Type)
				.addHeader("Referer", "http://www.patentstar.cn/My/SmartQuery.aspx")
				.addHeader("Accept-Encoding", PatentInfo.Accept_Encoding)
				.addHeader("Accept-Language", PatentInfo.Accept_Language)
				.addHeader("Cookie", PatentInfo.Cookie).post(body).build();
		client.newBuilder().connectTimeout(100, TimeUnit.SECONDS).writeTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS);
		Response response = client.newCall(request).execute();
		String string = response.body().string();
		return string;
	}

	private String bowlingJson(String word) {
		Map<String, Object> jsonBody = new HashMap<String, Object>();
		jsonBody.put("strSearchQuery", "F%20YY%20(" + word + "%2FAN)");
		jsonBody.put("_strSdbType", "CN");
		jsonBody.put("_sDoSrc", "0");
		return JsonToMap.mapToJson(jsonBody);
	}

	private String handler(String response) {
		Map<String, Object> map = JsonToMap.toMap(response);
		Object object = map.get("d");
		String valueOf = String.valueOf(object);
		List<String> list = JsonToMap.toList(valueOf, String.class);
		return list.get(1);
	}

	public String getResult() throws Exception {
		String json = bowlingJson(word);
		String post = post(PatentInfo.DoPatSearch_Url, json);
		String result = handler(post);
		return result;

	}
}
