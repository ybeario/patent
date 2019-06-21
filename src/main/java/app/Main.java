package app;

import patent.service.ServiceToLoadHtml;
import patent.service.ServiceToLoadJson;
import patent.service.ServiceToLoadLaw;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月19日 下午2:34:38 类说明
 */
public class Main {
    public static void main(String[] args) {
        ServiceToLoadHtml serviceToLoadHtml = new ServiceToLoadHtml();
        ServiceToLoadJson serviceToLoadJson = new ServiceToLoadJson();
        ServiceToLoadLaw serviceToLoadLaw = new ServiceToLoadLaw();
        try {
            //	serviceToLoadHtml.loadHtml();
            // serviceToLoadJson.loadJson();
            serviceToLoadLaw.loadLaw();
        } catch (Exception e) {
            System.out.println("程序崩溃" + e);
        }
    }
}
