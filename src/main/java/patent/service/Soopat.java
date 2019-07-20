package patent.service;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import patent.service.constant.PatentInfo;

import java.io.IOException;

/**
 * @author ：Y.Bear
 * @date ：Created in 2019/7/19 17:44
 * @description：利用soopat引擎获取专利信息
 * @modified By：
 * @version: $
 */
public class Soopat {
    public String getPage(String url, String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url + keyword).addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", PatentInfo.User_Agent)
                .addHeader("Accept",
                        "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8").build();
        Call call = client.newCall(request);
        Response response = call.execute();
        String htmlString = response.body().string().trim();
        return htmlString;

    }
}
