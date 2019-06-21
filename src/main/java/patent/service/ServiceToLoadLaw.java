package patent.service;

import util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Y.Bear
 * @date ：Created in 2019/6/21 9:47
 * @description：加载法律状态
 * @modified By：
 * @version: $
 */
public class ServiceToLoadLaw {
    LawQueryResult queryResult = new LawQueryResult();

    public void loadLaw() throws IOException {
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
        for (String keyword : list) {
            String result = queryResult.getLawResult(keyword);
            String folder =
                    System.getProperty("user.dir") + System.getProperty("file.separator") + "htmls" + System.getProperty("file.separator") + keyword + ".html";
            File html = new File(folder);
            if (!html.exists()) {
                html.createNewFile();
            }
            fos = new FileOutputStream(html);
            fos.write(result.getBytes(StandardCharsets.UTF_8));
        }
        fos.close();
    }
}
