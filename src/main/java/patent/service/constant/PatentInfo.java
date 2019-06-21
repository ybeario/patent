package patent.service.constant;

import okhttp3.MediaType;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月9日 下午2:11:15 类说明
 */
public class PatentInfo {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static final String GetPageList_Url = "http://www.patentstar.cn/comm/GetList.aspx/GetPageList";
    public static final String DoPatSearch_Url = "http://www.patentstar.cn/my/SmartQuery.aspx/DoPatSearch";
    public static final String LawQueryResult_Url = "http://www.patentstar.cn/My/LawQueryResult.aspx";
    public static final String Cookie = "UM_distinctid=16b7254f09423e-067a455667214d-e343166-100200-16b7254f095165; " +
            "CNZZDATA4400375=cnzz_eid%3D96484810-1560988433-%26ntime%3D1561081401; ASP" +
            ".NET_SessionId=tlcnhvcqqh3vc4fn4vw3czix";
    public static final String Accept_Language = "Accept-Language";
    public static final String Accept_Encoding = "Accept-Encoding";
    public static final String User_Agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like " +
            "Gecko) Chrome/67.0.3396.87 Safari/537.36";
    public static final String X_Requested_With = "X-Requested-With";
    public static final String Origin = "Origin";
    public static final String Content_Type_Json = "application/json; charset=UTF-8";
    public static final String Content_Type_X_www_form_urlencoded = "application/x-www-form-urlencoded; charset=UTF-8";
    public static final String Cache_Control = "no-cache";

}
