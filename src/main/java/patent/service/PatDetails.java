package patent.service;

import java.io.IOException;
import java.util.Map;

import org.jsoup.nodes.Document;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import patent.service.constant.PatentInfo;
import util.JsoupUtils;
import util.StringUtils;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月19日 下午3:02:24 类说明
 */
public class PatDetails {
	/*
	 * 构造url
	 *
	 */
	private String generateUrl(Map<String, Object> map) {
		StringBuilder builder = new StringBuilder("http://www.patentstar.cn/my/frmPatDetails.aspx?Id=");
		builder.append(map.get("StrANX")).append("&xy=").append(map.get("StrSerialNo")).append("&qy=%20(")
				.append(StringUtils.removeFirstAndLast(StringUtils.removeFirstAndLast(String.valueOf(map.get("key")))))
				.append("%2FAN)");
		System.out.println(builder.toString());
		return builder.toString();

	}

	/*
	 * 获取第一个页面
	 */
	private String getFirstPage(String url) throws IOException {
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
		String htmlString = response.body().string().trim();
		return htmlString;

	}

	private Response postToGetLawPage(String url, String __VIEWSTATE, String __EVENTVALIDATION) throws IOException {
		OkHttpClient client = new OkHttpClient();
		String bowlingBody = bowlingBody(__VIEWSTATE, __EVENTVALIDATION);
		System.out.println("bowlingBody:" + bowlingBody);
		RequestBody body = RequestBody.create(MediaType.get("text/plain; charset=utf-8"), bowlingBody);
		Request request = new Request.Builder().url(url).addHeader("Origin", PatentInfo.Origin)
				.addHeader("User-Agent", PatentInfo.User_Agent)
				.addHeader("Content-Type", PatentInfo.Content_Type_X_www_form_urlencoded)
				.addHeader("X-Requested-With", PatentInfo.X_Requested_With)
				.addHeader("Cache-Control", PatentInfo.Cache_Control).addHeader("X-MicrosoftAjax", "Delta=true")
				.addHeader("Accept", "*/*").addHeader("Referer", url)
				.addHeader("Accept-Encoding", PatentInfo.Accept_Encoding)
				.addHeader("Accept-Language", PatentInfo.Accept_Language).addHeader("Cookie", PatentInfo.Cookie)
				.post(body).build();
		Response response = client.newCall(request).execute();
		return response;
	}

	/*
	 * 构造访问法律状态页面的body
	 *
	 */
	private String bowlingBody(String __VIEWSTATE, String __EVENTVALIDATION) {
		__VIEWSTATE = __VIEWSTATE.replaceAll("=", "%3D");
		__EVENTVALIDATION = __EVENTVALIDATION.replaceAll("=", "%3D");
		StringBuilder builder = new StringBuilder(
				"ctl00%24ContentPlaceHolder1%24sm=ctl00%24ContentPlaceHolder1%24UpdatePanel1%7Cctl00%24ContentPlaceHolder1%24btnActiveTab&__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=");
		builder.append(__VIEWSTATE).append("&__VIEWSTATEENCRYPTED=&__EVENTVALIDATION=").append(__EVENTVALIDATION)
				.append("&ctl00%24ContentPlaceHolder1%24hidActiveTabTi=%E6%B3%95%E5%BE%8B%E7%8A%B6%E6%80%81&ctl00%24ContentPlaceHolder1%24hidActiveTabIdx=4&ctl00%24ContentPlaceHolder1%24iframeUrl=&ctl00%24ContentPlaceHolder1%24hidAutoIndexWord=&ctl00%24ContentPlaceHolder1%24HiddenField1Pdf=&TextBoxNote=&newclass=&txtNodeDes=&__ASYNCPOST=true&ctl00%24ContentPlaceHolder1%24btnActiveTab=Button");
		return builder.toString();

	}

	public String getResult(Map<String, Object> map) throws IOException {
		String url = generateUrl(map);
		String htmlString = getFirstPage(url);
		Document document = JsoupUtils.getDomFromString(htmlString);
		System.out.println(document.title() + "----------------");
		String __VIEWSTATE = JsoupUtils.getElementById(document, "__VIEWSTATE");
		System.out.println("__VIEWSTATE:" + __VIEWSTATE);
		String __EVENTVALIDATION = JsoupUtils.getElementById(document, "__EVENTVALIDATION");
		System.out.println("__EVENTVALIDATION:" + __EVENTVALIDATION);
		Response response = postToGetLawPage(url, __VIEWSTATE, __EVENTVALIDATION);
		String html = response.body().string();
		System.out.println(html);
		return " ";

	}
}
