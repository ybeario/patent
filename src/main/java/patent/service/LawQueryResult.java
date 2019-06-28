package patent.service;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import patent.service.constant.PatentInfo;

import java.io.IOException;

/**
 * @author ：Y.Bear
 * @date ：Created in 2019/6/21 9:22
 * @description：获取法律状态采用get方法
 * @modified By：
 * @version: $
 */
public class LawQueryResult {
    OkHttpClient client = new OkHttpClient();

    public String getUrl(String target, int num) {
        StringBuilder url = new StringBuilder(PatentInfo.LawQueryResult_Url + "?");
        url.append("type=").append("falv").append("&").append("hitCount=3&").append("SearchNo=00").append(num
        ).append("&").append("AppNo=").append(target);
        System.out.println(url);
        return url.toString();
    }

    public String run(String target, int num) throws IOException {

        Request request =
                new Request.Builder().url(getUrl(target, num)).addHeader(PatentInfo.Origin, "http://www.patentstar" +
                        ".cn").addHeader(
                        "Content-Type", "application/x-www-form-urlencoded").addHeader("User-Agent",
                        PatentInfo.User_Agent).addHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
                        "q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3").addHeader("Upgrade" +
                        "-Insecure-Requests", "1").addHeader("Cookie", PatentInfo.Cookie).build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.toString());
        return response.body().string();
    }

    public String getLawResult(String target, int num) throws IOException {
        String response = run(target, num);
        System.out.println(response);
        return response;
    }
}
