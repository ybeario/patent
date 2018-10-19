package patent.service;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import patent.service.constant.PatentInfo;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月19日 下午3:02:24 类说明
 */
public class PatDetails {

	private String generateUrl(Map<String, Object> map) {
		StringBuilder builder = new StringBuilder("http://www.patentstar.cn/my/frmPatDetails.aspx?Id=");
		builder.append(map.get("StrANX")).append("&xy=").append(map.get("StrSerialNo")).append("&qy=%20(")
				.append(map.get("key")).append("%2FAN)");
		return builder.toString();

	}

	private void get(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).addHeader("Upgrade-Insecure-Requests", "1")
				.addHeader("User-Agent", PatentInfo.User_Agent)
				.addHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
				.addHeader("Accept-Encoding", PatentInfo.Accept_Encoding)
				.addHeader("Accept-Language", PatentInfo.Accept_Language).addHeader("Cookie", PatentInfo.Cookie)
				.build();
		Call call = client.newCall(request);
		Response response = call.execute();
		System.out.println(response.body().string());
	}

	public String getResult(Map<String, Object> map) throws IOException {
		String url = generateUrl(map);
		get(url);
		return null;

	}
}
