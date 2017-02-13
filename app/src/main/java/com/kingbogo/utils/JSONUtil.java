package com.kingbogo.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JSONUtil
{


    private JSONUtil()
    {
        throw new AssertionError();
    }

    /**
     * 生成一个空的JSON对象
     *
     * @return 空的JSON对象
     */
    public static JSONObject newJSONObject()
    {
        return new JSONObject();
    }

    /**
     * 根据JSON数据封装一个新的JSON对象
     *
     * @param content JSON数据
     *
     * @return 新的JSON数据
     */
    public static JSONObject newJSONObject(String content)
    {
        if (CheckUtil.isEmpty(content))
        {
            return null;
        }

        JSONObject retObj = null;
        try
        {
            retObj = new JSONObject(content);
        }
        catch (Exception e)
        {
            LogUtil.e(e);
        }
        return retObj;
    }

    /**
     * 根据JSON数据封装一个新的JSON数据数组
     *
     * @param content JSON数据
     *
     * @return JSON数组对象
     */
    public static JSONArray newJSONArray(String content)
    {
        JSONArray jArray = null;
        try
        {
            jArray = new JSONArray(content);
        }
        catch (Exception e)
        {
            LogUtil.e(e);
        }
        return jArray;
    }

    /**
     * get Long from jsonObject
     */
    public static Long getLong(JSONObject jsonObject, String key, Long defaultValue)
    {
        if (jsonObject == null || CheckUtil.isEmpty(key))
        {
            return defaultValue;
        }

        try
        {
            return jsonObject.getLong(key);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get Long from jsonData
     */
    public static Long getLong(String jsonData, String key, Long defaultValue)
    {
        if (CheckUtil.isEmpty(jsonData))
        {
            return defaultValue;
        }

        try
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getLong(jsonObject, key, defaultValue);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    public static long getLong(JSONObject jsonObject, String key, long defaultValue)
    {
        return getLong(jsonObject, key, (Long) defaultValue);
    }

    public static long getLong(String jsonData, String key, long defaultValue)
    {
        return getLong(jsonData, key, (Long) defaultValue);
    }

    /**
     * get Int from jsonObject
     */
    public static Integer getInt(JSONObject jsonObject, String key, Integer defaultValue)
    {
        if (jsonObject == null || CheckUtil.isEmpty(key))
        {
            return defaultValue;
        }

        try
        {
            return jsonObject.getInt(key);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get Int from jsonData
     */
    public static Integer getInt(String jsonData, String key, Integer defaultValue)
    {
        if (CheckUtil.isEmpty(jsonData))
        {
            return defaultValue;
        }

        try
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getInt(jsonObject, key, defaultValue);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    public static int getInt(JSONObject jsonObject, String key, int defaultValue)
    {
        return getInt(jsonObject, key, (Integer) defaultValue);
    }

    public static int getInt(String jsonData, String key, int defaultValue)
    {
        return getInt(jsonData, key, (Integer) defaultValue);
    }

    public static Double getDouble(JSONObject jsonObject, String key, Double defaultValue)
    {
        if (jsonObject == null || CheckUtil.isEmpty(key))
        {
            return defaultValue;
        }

        try
        {
            return jsonObject.getDouble(key);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get Double from jsonData
     */
    public static Double getDouble(String jsonData, String key, Double defaultValue)
    {
        if (CheckUtil.isEmpty(jsonData))
        {
            return defaultValue;
        }

        try
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getDouble(jsonObject, key, defaultValue);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    public static double getDouble(JSONObject jsonObject, String key, double defaultValue)
    {
        return getDouble(jsonObject, key, (Double) defaultValue);
    }

    public static double getDouble(String jsonData, String key, double defaultValue)
    {
        return getDouble(jsonData, key, (Double) defaultValue);
    }

    /**
     * get String from jsonObject
     */
    public static String getString(JSONObject jsonObject, String key, String defaultValue)
    {
        if (jsonObject == null || CheckUtil.isEmpty(key))
        {
            return defaultValue;
        }

        try
        {
            return jsonObject.getString(key);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get String from jsonData
     */
    public static String getString(String jsonData, String key, String defaultValue)
    {
        if (CheckUtil.isEmpty(jsonData))
        {
            return defaultValue;
        }

        try
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getString(jsonObject, key, defaultValue);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get String from jsonObject
     */
    public static String getStringCascade(JSONObject jsonObject, String defaultValue, String... keyArray)
    {
        if (jsonObject == null || CheckUtil.isEmpty(keyArray))
        {
            return defaultValue;
        }

        String data = jsonObject.toString();
        for (String key : keyArray)
        {
            data = getStringCascade(data, key, defaultValue);
            if (data == null)
            {
                return defaultValue;
            }
        }
        return data;
    }

    /**
     * get String from jsonData
     */
    public static String getStringCascade(String jsonData, String defaultValue, String... keyArray)
    {
        if (CheckUtil.isEmpty(jsonData))
        {
            return defaultValue;
        }

        String data = jsonData;
        for (String key : keyArray)
        {
            data = getString(data, key, defaultValue);
            if (data == null)
            {
                return defaultValue;
            }
        }
        return data;
    }

    /**
     * get String array from jsonObject
     */
    public static String[] getStringArray(JSONObject jsonObject, String key, String[] defaultValue)
    {
        if (jsonObject == null || CheckUtil.isEmpty(key))
        {
            return defaultValue;
        }

        try
        {
            JSONArray statusArray = jsonObject.getJSONArray(key);
            if (statusArray != null)
            {
                String[] value = new String[statusArray.length()];
                for (int i = 0; i < statusArray.length(); i++)
                {
                    value[i] = statusArray.getString(i);
                }
                return value;
            }
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
        return defaultValue;
    }

    /**
     * get String array from jsonData
     */
    public static String[] getStringArray(String jsonData, String key, String[] defaultValue)
    {
        if (CheckUtil.isEmpty(jsonData))
        {
            return defaultValue;
        }

        try
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getStringArray(jsonObject, key, defaultValue);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get String list from jsonObject
     */
    public static List<String> getStringList(JSONObject jsonObject, String key, List<String> defaultValue)
    {
        if (jsonObject == null || CheckUtil.isEmpty(key))
        {
            return defaultValue;
        }

        try
        {
            JSONArray statusArray = jsonObject.getJSONArray(key);
            if (statusArray != null)
            {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < statusArray.length(); i++)
                {
                    list.add(statusArray.getString(i));
                }
                return list;
            }
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
        return defaultValue;
    }

    /**
     * get String list from jsonData
     */
    public static List<String> getStringList(String jsonData, String key, List<String> defaultValue)
    {
        if (CheckUtil.isEmpty(jsonData))
        {
            return defaultValue;
        }

        try
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getStringList(jsonObject, key, defaultValue);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get JSONObject from jsonObject
     */
    public static JSONObject getJSONObject(JSONObject jsonObject, String key, JSONObject defaultValue)
    {
        if (jsonObject == null || CheckUtil.isEmpty(key))
        {
            return defaultValue;
        }

        try
        {
            return jsonObject.getJSONObject(key);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get JSONObject from jsonData
     */
    public static JSONObject getJSONObject(String jsonData, String key, JSONObject defaultValue)
    {
        if (CheckUtil.isEmpty(jsonData))
        {
            return defaultValue;
        }

        try
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getJSONObject(jsonObject, key, defaultValue);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get JSONObject from jsonObject
     */
    public static JSONObject getJSONObjectCascade(JSONObject jsonObject, JSONObject defaultValue, String... keyArray)
    {
        if (jsonObject == null || CheckUtil.isEmpty(keyArray))
        {
            return defaultValue;
        }

        JSONObject js = jsonObject;
        for (String key : keyArray)
        {
            js = getJSONObject(js, key, defaultValue);
            if (js == null)
            {
                return defaultValue;
            }
        }
        return js;
    }

    /**
     * get JSONObject from jsonData
     */
    public static JSONObject getJSONObjectCascade(String jsonData, JSONObject defaultValue, String... keyArray)
    {
        if (CheckUtil.isEmpty(jsonData))
        {
            return defaultValue;
        }

        try
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getJSONObjectCascade(jsonObject, defaultValue, keyArray);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get JSONArray from jsonObject
     */
    public static JSONArray getJSONArray(JSONObject jsonObject, String key, JSONArray defaultValue)
    {
        if (jsonObject == null || CheckUtil.isEmpty(key))
        {
            return defaultValue;
        }

        try
        {
            return jsonObject.getJSONArray(key);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get JSONArray from jsonData
     */
    public static JSONArray getJSONArray(String jsonData, String key, JSONArray defaultValue)
    {
        if (CheckUtil.isEmpty(jsonData))
        {
            return defaultValue;
        }

        try
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getJSONArray(jsonObject, key, defaultValue);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get Boolean from jsonObject
     */
    public static boolean getBoolean(JSONObject jsonObject, String key, Boolean defaultValue)
    {
        if (jsonObject == null || CheckUtil.isEmpty(key))
        {
            return defaultValue;
        }

        try
        {
            return jsonObject.getBoolean(key);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get Boolean from jsonData
     */
    public static boolean getBoolean(String jsonData, String key, Boolean defaultValue)
    {
        if (CheckUtil.isEmpty(jsonData))
        {
            return defaultValue;
        }

        try
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getBoolean(jsonObject, key, defaultValue);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return defaultValue;
        }
    }

    /**
     * get map from jsonObject.
     */
    public static Map<String, String> getMap(JSONObject jsonObject, String key)
    {
        return JSONUtil.parseKeyAndValueToMap(JSONUtil.getString(jsonObject, key, null));
    }

    /**
     * get map from jsonData.
     */
    public static Map<String, String> getMap(String jsonData, String key)
    {

        if (jsonData == null)
        {
            return null;
        }
        if (jsonData.length() == 0)
        {
            return new HashMap<>();
        }

        try
        {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getMap(jsonObject, key);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return null;
        }
    }

    /**
     * parse key-value pairs to map. ignore empty key, if getValue exception, put empty value
     */
    public static Map<String, String> parseKeyAndValueToMap(JSONObject sourceObj)
    {
        if (sourceObj == null)
        {
            return null;
        }

        Map<String, String> keyAndValueMap = new HashMap<>();
        for (Iterator iter = sourceObj.keys(); iter.hasNext(); )
        {
            String key = (String) iter.next();
            putMapNotEmptyKey(keyAndValueMap, key, getString(sourceObj, key, ""));

        }
        return keyAndValueMap;
    }

    /**
     * parse key-value pairs to map. ignore empty key, if getValue exception, put empty value
     */
    public static Map<String, String> parseKeyAndValueToMap(String source)
    {
        if (CheckUtil.isEmpty(source))
        {
            return null;
        }

        try
        {
            JSONObject jsonObject = new JSONObject(source);
            return parseKeyAndValueToMap(jsonObject);
        }
        catch (JSONException e)
        {
            LogUtil.e(e);
            return null;
        }
    }

    private static boolean putMapNotEmptyKey(Map<String, String> map, String key, String value)
    {
        if (map == null || CheckUtil.isEmpty(key))
        {
            return false;
        }

        map.put(key, value);
        return true;
    }
}
