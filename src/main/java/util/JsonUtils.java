package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月9日 上午11:23:34 类说明
 */
public class JsonUtils {

	/**
	 * 将Map转成json
	 * 
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<String, Object> map) {
		Gson gson = new Gson();
		return gson.toJson(map, Map.class);
	}

	/**
	 * 将List转成json
	 * 
	 * @param list
	 * @return
	 */
	public static String listToJson(List<String> list) {
		Gson gson = new Gson();
		return gson.toJson(list, List.class);
	}
	/**
	 * 将Json串转化为list
	 * @param <T>
	 * @param json
	 * @return
	 */
	public static <T> List<T> toList(String json,Class<T> clazz) {
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<List<T>>() {
		}.getType());

	}

	/**
	 * 依据json字符串返回Map对象
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> toMap(String json) {
		return JsonUtils.toMap(JsonUtils.parseJson(json));
	}

	/**
	 * 获取JsonObject
	 * 
	 * @param json
	 * @return
	 */
	public static JsonObject parseJson(String json) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObj = parser.parse(json).getAsJsonObject();
		return jsonObj;
	}

	/**
	 * 将JSONObjec对象转换成Map-List集合
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> toMap(JsonObject json) {
		Map<String, Object> map = new HashMap<String, Object>();
		Set<Map.Entry<String, JsonElement>> entrySet = json.entrySet();
		for (Iterator<Map.Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext();) {
			Map.Entry<String, JsonElement> entry = iter.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof JsonArray)
				map.put((String) key, toList((JsonArray) value));
			else if (value instanceof JsonObject)
				map.put((String) key, toMap((JsonObject) value));
			else
				map.put((String) key, value);
		}
		return map;
	}

	/**
	 * 将JSONArray对象转换成List集合
	 * 
	 * @param json
	 * @return
	 */
	public static List<Object> toList(JsonArray json) {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < json.size(); i++) {
			Object value = json.get(i);
			if (value instanceof JsonArray) {
				list.add(toList((JsonArray) value));
			} else if (value instanceof JsonObject) {
				list.add(toMap((JsonObject) value));
			} else {
				list.add(value);
			}
		}
		return list;
	}
}
