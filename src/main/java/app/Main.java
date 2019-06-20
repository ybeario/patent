package app;

import patent.service.ServiceToLoadHtml;
import patent.service.ServiceToLoadJson;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月19日 下午2:34:38 类说明
 */
public class Main {
    public static void main(String[] args) {
        ServiceToLoadHtml serviceToLoadHtml = new ServiceToLoadHtml();
        ServiceToLoadJson serviceToLoadJson = new ServiceToLoadJson();

        try {
            //	serviceToLoadHtml.loadHtml();
            serviceToLoadJson.loadJson();
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("程序崩溃" + e);
        }
    }
}
