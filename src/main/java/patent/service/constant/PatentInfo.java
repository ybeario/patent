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
	public static final String Cookie = "UM_distinctid=166660ccc581f-0677858ae966b4-5e452019-1fa400-166660ccc62191; ASP.NET_SessionId=34wyi2kkjb5uo0klgkkruvko; CNZZDATA4400375=cnzz_eid%3D1754060454-1539308883-null%26ntime%3D1539925740";
	public static final String Accept_Language = "Accept-Language";
	public static final String Accept_Encoding = "Accept-Encoding";
	public static final String User_Agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36";
	public static final String X_Requested_With = "X-Requested-With";
	public static final String Origin = "Origin";
	public static final String Content_Type = "application/json; charset=UTF-8";
}
