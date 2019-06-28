package patent.service;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jsoup.nodes.Document;
import patent.service.constant.PatentInfo;
import util.JsonUtils;
import util.JsoupUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Y.Bear
 * @date ：Created in 2019/6/28 8:21
 * @description：
 * @modified By：
 * @version: $
 */
public class LawQuery {

    private String getFirstPage(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", PatentInfo.User_Agent)
                .addHeader("Accept",
                        "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .addHeader("Cookie", PatentInfo.Cookie)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        String htmlString = response.body().string().trim();
        return htmlString;

    }


    public String getLawPage(String key) throws Exception {
        String htmlString = null;
        Response response = null;
        try {
            htmlString = getFirstPage(PatentInfo.LawQuery_Url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(htmlString);
        Document document = JsoupUtils.getDomFromString(htmlString);
        try {
            response = postToGetLaw(key, getViewState(document), get_EVENTVALIDATION(document));
            System.out.println("----------------------------");
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response.body().string();
    }

    public String getViewState(Document document) {
        String __VIEWSTATE = JsoupUtils.getElementById(document, "__VIEWSTATE");
        System.out.println("__VIEWSTATE:" + __VIEWSTATE);
        return __VIEWSTATE;
    }

    public String get_EVENTVALIDATION(Document document) {
        String __EVENTVALIDATION = JsoupUtils.getElementById(document, "__EVENTVALIDATION");
        System.out.println("__EVENTVALIDATION:" + __EVENTVALIDATION);
        return __EVENTVALIDATION;
    }

    private String bowlingJson(String key, String __VIEWSTATE, String __EVENTVALIDATION) {
        Map<String, Object> jsonBody = new HashMap<String, Object>();
        jsonBody.put("__VIEWSTATE", __VIEWSTATE);
        jsonBody.put("__EVENTVALIDATION", __EVENTVALIDATION);
        jsonBody.put("ctl00$ContentPlaceHolder1$btnChaXunFL", "查询");
        jsonBody.put("ctl00$ContentPlaceHolder1$chkHeTongBAJD$0", "on");
        jsonBody.put("ctl00$ContentPlaceHolder1$chkHeTongBAJD$1", "on");
        jsonBody.put("ctl00$ContentPlaceHolder1$chkHeTongBAJD$2", "on");
        jsonBody.put("ctl00$ContentPlaceHolder1$chkHeTongZT$0", "on");
        jsonBody.put("ctl00$ContentPlaceHolder1$chkHeTongZT$1", "on");
        jsonBody.put("ctl00$ContentPlaceHolder1$chkHeTongZT$2", "on");
        jsonBody.put("ctl00$ContentPlaceHolder1$txtAppNoFL", key);
        return JsonUtils.mapToJson(jsonBody);
    }

    private Response postToGetLaw(String key, String __VIEWSTATE, String __EVENTVALIDATION) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String json = bowlingJson(key, __VIEWSTATE, __EVENTVALIDATION);
        System.out.println("bowlingBody:" + json);
        RequestBody body = RequestBody.create(PatentInfo.JSON, json);
        Request request = new Request.Builder().url(PatentInfo.LawQuery_Url).addHeader("Origin", PatentInfo.Origin)
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", PatentInfo.User_Agent)
                .addHeader("Content-Type", PatentInfo.Content_Type_X_www_form_urlencoded)
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng," +
                        "*/*;q=0.8")
                .addHeader("Cookie", PatentInfo.Cookie)
                .post(body).build();
        Response response = client.newCall(request).execute();
        return response;
    }
}
