package demo;

import java.util.ArrayList;
import java.util.List;

import util.JsonUtils;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月9日 下午2:58:09 类说明
 */
public class ListToJson {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("d");
		list.add("2");
		list.add("?<>");
		String json = JsonUtils.listToJson(list);
		List<String> objList = JsonUtils.toList(json,String.class);
		for (String string : objList) {
			System.out.println(string);
		}
	}
}
