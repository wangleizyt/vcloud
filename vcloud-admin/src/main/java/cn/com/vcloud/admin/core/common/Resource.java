package cn.com.vcloud.admin.core.common;

import com.alibaba.fastjson.JSONObject;
import java.util.List;

/**
 * 处理嵌套查询结果时，MyBatis会根据bean定义的属性类型来初始化嵌套的成员变量，
 * 主要看其是不是Collection
 * 如果这里不定义，那么嵌套返回结果里就只能返回一对一的结果，而不是一对多的
 */
public class Resource extends JSONObject {
    private String resource;

    private List<JSONObject> handleList;

    public List<JSONObject> getHandleList() {
        return this.handleList;
    }

    public void setHandleList(final List<JSONObject> handleList) {
        this.handleList = handleList;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}