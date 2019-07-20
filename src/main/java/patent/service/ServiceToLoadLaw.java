package patent.service;

import patent.service.constant.PatentInfo;
import util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author ：Y.Bear
 * @date ：Created in 2019/6/21 9:47
 * @description：加载法律状态
 * @modified By：
 * @version: $
 */
public class ServiceToLoadLaw {
    LawQueryResult queryResult = new LawQueryResult();
    LawQuery query = new LawQuery();
    Soopat soopat = new Soopat();
    Random random = new Random();

    public void loadLaw() throws Exception {
        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        long start = System.currentTimeMillis();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入数据量");
        int num = Integer.parseInt(bufferedReader.readLine());
        System.out.println("请输入数据");
        for (int i = 0; i < num; i++) {
            String key = bufferedReader.readLine().trim();
            if (key.length() == 16) {
                key = StringUtils.removeFirstAndLast(StringUtils.removeFirstAndLast(key));
            }
            list.add(key);
        }
        FileOutputStream fos = null;
        int hitCount = 5;
        for (String keyword : list) {
            hitCount++;
            //String result = queryResult.getLawResult(keyword,hitCount);
            //String result = query.getLawPage(keyword);
            String result = soopat.getPage(PatentInfo.SooPAT_URL, keyword);
            String folder =
                    System.getProperty("user.dir") + System.getProperty("file.separator") + "htmls" + System.getProperty("file.separator") + keyword + ".html";
            File html = new File(folder);
            if (!html.exists()) {
                html.createNewFile();
            }
            System.out.println("当前网页大小:" + result.length());
            if (result.length() == 7398) {
                System.out.println("需要验证码！！！");
                Thread.sleep(10000);
                result = soopat.getPage(PatentInfo.SooPAT_URL, keyword);
            } else {
                System.out.println("成功！！！");
            }
            fos = new FileOutputStream(html);
            fos.write(result.getBytes(StandardCharsets.UTF_8));

            Thread.sleep(3000);
        }
        fos.close();
    }
}
