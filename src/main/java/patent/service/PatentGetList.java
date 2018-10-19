package patent.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import patent.service.constant.PatentInfo;
import util.JsonToMap;
import util.StringUtils;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月9日 上午10:38:40 类说明
 *          以获取专利之星获取主分类号为模板，学会使用httpclient，为后期封装为get,post等请求做准备
 */
public class PatentGetList {
	public String keyword;
	public String word;

	public PatentGetList(String keyword) {
		this.keyword = keyword;
		this.word = keyword.substring(2, keyword.length());
	}

	OkHttpClient client = new OkHttpClient();

	private String post(String url, String json, String NO) throws IOException {
		RequestBody body = RequestBody.create(PatentInfo.JSON, json);
		Request request = new Request.Builder().url(url).addHeader("Origin", PatentInfo.Origin)
				.addHeader("X-DevTools-Emulate-Network-Conditions-Client-Id", "1BEA51B7890315B52AFC5D8446A53834")
				.addHeader("X-Requested-With", PatentInfo.X_Requested_With)
				.addHeader("User-Agent", PatentInfo.User_Agent)
				.addHeader("Referer",
						"http://www.patentstar.cn/My/frmPatentList.aspx?db=CN&No=" + NO + "&kw=" + keyword
								+ "&Nm=1&etp=&Query=%20(" + word + "%2FAN)&Qsrc=0")
				.addHeader("Accept-Encoding", PatentInfo.Accept_Encoding)
				.addHeader("Accept-Language", PatentInfo.Accept_Language)
				.addHeader("Cookie", PatentInfo.Cookie).post(body).build();
		client.newBuilder().connectTimeout(100, TimeUnit.SECONDS).writeTimeout(100, TimeUnit.SECONDS).readTimeout(100,TimeUnit.SECONDS);
		Response response = client.newCall(request).execute();
		String string = response.body().string();

		return string;
	}

	private String bowlingJson(String NO) {
		Map<String, Object> jsonBody = new HashMap<String, Object>();
		jsonBody.put("Type", "CN");
		jsonBody.put("NodeId", NO);
		jsonBody.put("SourceType", "FI");
		jsonBody.put("ItemCount", "1");
		jsonBody.put("pageindex", "1");
		jsonBody.put("rows", "10");
		jsonBody.put("Sort", "PD|DESC");
		return JsonToMap.mapToJson(jsonBody);
	}

	private String handler(String string) {
		try {
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
		} catch (Exception e) {
			throw e;
		}

	}

	public String getResult() throws Exception {
		DoPatSearch doPatSearch = new DoPatSearch(word);
		String NO = doPatSearch.getResult();
		String json = bowlingJson(NO);
		String post = post(PatentInfo.GetPageList_Url, json, NO);
		String result = handler(post);
		return result;

	}
}
