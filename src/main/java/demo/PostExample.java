package demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.JsonToMap;
import util.StringUtils;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月9日 上午11:14:28 类说明
 */
public class PostExample {
	public static final String keyword = "ZL201110178685.5";
	public static final String word = keyword.substring(2, keyword.length() - 2);
	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	public static final String url = "http://www.patentstar.cn/comm/GetList.aspx/GetPageList";
	OkHttpClient client = new OkHttpClient();

	String post(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).addHeader("Origin", "http://www.patentstar.cn")
				.addHeader("X-DevTools-Emulate-Network-Conditions-Client-Id", "1BEA51B7890315B52AFC5D8446A53834")
				.addHeader("X-Requested-With", "XMLHttpRequest")
				.addHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36")
				.addHeader("Referer",
						"http://www.patentstar.cn/My/frmPatentList.aspx?db=CN&No=009&kw=" + keyword
								+ "&Nm=1&etp=&Query=%20(" + word + "%2FAN)&Qsrc=0")
				.addHeader("Accept-Encoding", "gzip, deflate").addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
				.addHeader("Cookie",
						"ASP.NET_SessionId=q1wiydje5ux20malzctng22p; UM_distinctid=1665687846c125-0c10417d24a4b6-5e452019-1fa400-1665687846f1d; CNZZDATA4400375=cnzz_eid%3D1216538782-1539048737-null%26ntime%3D1539048737")
				.post(body).build();
		Response response = client.newCall(request).execute();
		String string = response.body().string();

		return string;
	}

	String bowlingJson() {
		Map<String, Object> jsonBody = new HashMap<String, Object>();
		jsonBody.put("Type", "CN");
		jsonBody.put("NodeId", "028");
		jsonBody.put("SourceType", "FI");
		jsonBody.put("ItemCount", "1");
		jsonBody.put("pageindex", "1");
		jsonBody.put("rows", "10");
		jsonBody.put("Sort", "PD|DESC");
		return JsonToMap.mapToJson(jsonBody);
	}

	String handler(String string) {
		Map<String, Object> map = JsonToMap.toMap(string);
		Object object = map.get("d");
		string = String.valueOf(object);
		string = string.replace("\\", "");
		string = StringUtils.removeFirstAndLast(string);
		map = JsonToMap.toMap(string);
		object = map.get("rows");
		string = String.valueOf(object);
		string = StringUtils.removeFirstAndLast(string);
		map = JsonToMap.toMap(string);
		string = StringUtils.removeFirstAndLast(String.valueOf(map.get("StrMainIPC")));
		return string;

	}

	public static void main(String[] args) throws IOException {
		PostExample example = new PostExample();
		String json = example.bowlingJson();
		String response = example.post(url, json);
		System.out.println(response);
		String result = example.handler(response);
		System.out.println(result);
	}
}
