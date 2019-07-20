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
    public static final String LawQuery_Url = "http://www.patentstar.cn/My/LawQuery.aspx";
    public static final String SooPAT_URL = "http://www1.soopat.com/Patent/";
    public static final String Cookie = "UM_distinctid=16a7ca489c310c-00902679dd0064-1333062-e1000-16a7ca489c4114; " +
            "__SDID=964173f9d8f944fe; ASP.NET_SessionId=c0z5wyfww5bw2vesbczrtlzt; " +
            "CNZZDATA4400375=cnzz_eid%3D372357135-1556865074-%26ntime%3D1561677295";
    public static final String Accept_Language = "Accept-Language";
    public static final String Accept_Encoding = "Accept-Encoding";
    public static final String User_Agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36";
    public static final String X_Requested_With = "X-Requested-With";
    public static final String Origin = "Origin";
    public static final String Content_Type_Json = "application/json; charset=UTF-8";
    public static final String Content_Type_X_www_form_urlencoded = "application/x-www-form-urlencoded; charset=UTF-8";
    public static final String Cache_Control = "no-cache";

}
