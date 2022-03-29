package com.shulv.study.jfinaldemo.base;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jfinal.kit.HttpKit;
import com.jfinal.upload.UploadFile;
import com.shulv.study.jfinaldemo.exception.SystemException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BaseCommand<T> {
    class Current {
        private static final ThreadLocal<BaseController> CONTROLLER_THREAD_LOCAL = new ThreadLocal<>();
    }

    T execute();

    static void set(BaseController baseController) {
        Current.CONTROLLER_THREAD_LOCAL.set(baseController);
    }

    static void invalidate() {
        Current.CONTROLLER_THREAD_LOCAL.remove();
    }

    default BaseController getController() {
        return Current.CONTROLLER_THREAD_LOCAL.get();
    }

    default HttpServletRequest getRequest() {
        return getController().getRequest();
    }

    default HttpServletResponse getResponse() {
        return getController().getResponse();
    }

    default UploadFile getFile() {
        return getController().getFile();
    }

    default List<UploadFile> getFiles() {
        return getController().getFiles();
    }

    /**
     * 提取RequestParameter中的数据
     */
    default Map<String, Object> extractRequestParam() {
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, String[]> paraMap = getRequest().getParameterMap();
        for (Map.Entry<String, String[]> entry : paraMap.entrySet()) {
            String[] values = entry.getValue();
            String value = (values != null && values.length > 0) ? values[0] : null;
            paramMap.put(entry.getKey(), "".equals(value) ? null : value);
        }
        return paramMap;
    }

    /**
     * 提取RequestBody中的数据
     */
    default Map<String, Object> extractRequestBody() {
        Map<String, Object> requestBody = new HashMap<>();
        // 获取 requestBody 中的参数
        String data = HttpKit.readData(getRequest());

        if (!StrUtil.isBlank(data)) {
            try {
                requestBody = JSONObject.parseObject(data, new TypeReference<>() {
                });
            } catch (Exception e) {
                throw new SystemException("获取RequestBody中的数据失败，RequestBody:" + data + ", 错误消息：" + e.getMessage());
            }
        }

        return requestBody;
    }

    /**
     * 提取RequestBody中的(JSONArray)数据
     */
    default JSONArray extractRequestBodyAsList() {
        JSONArray requestBody = new JSONArray();
        // 获取 requestBody 中的参数
        String data = HttpKit.readData(getRequest());

        if (!StrUtil.isBlank(data)) {
            try {
                requestBody = JSONArray.parseArray(data);
            } catch (Exception e) {
                throw new SystemException("获取RequestBody中的数据失败，RequestBody:" + data + ", 错误消息：" + e.getMessage());
            }
        }

        return requestBody;
    }
}
