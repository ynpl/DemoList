package com.neufmer.ygfstore;

import com.wangxing.code.mvvm.manager.CacheInfoManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WangXing on 2019/6/18.
 */
public class Const {

    public static void main(String[] args) throws ParseException {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.get(Calendar.MONTH));
        System.out.println(instance.get(Calendar.DAY_OF_MONTH));
        System.out.println(instance.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println(instance.get(Calendar.WEEK_OF_MONTH));
        System.out.println(instance.get(Calendar.WEEK_OF_YEAR));

    }

    /********************************框架使用，固定写法*************************************/
    public static final String BASE_URL = "http://api-stg.inspection.ygfmlt.infoloop.cn:5021";
    public static final boolean HTTP_LOG = true;
    /**************************************************************************************/
    private static Map<Integer, String> errorCode = new ConcurrentHashMap<Integer, String>();

    static {
        errorCode.put(100000, "用户不存在");
        errorCode.put(100001, "密码错误");
        errorCode.put(100002, "角色不存在");
        errorCode.put(100003, "用户被停用");
        errorCode.put(100004, "验证码错误");
        errorCode.put(100005, "验证码过期");
        errorCode.put(100006, "新旧密码一致");
        errorCode.put(401000, "未经授权的请求");
        errorCode.put(401001, "token过期");
        errorCode.put(403000, "未经身份验证的请求");
        errorCode.put(406000, "参数类型错误");
        errorCode.put(406001, "请求参数缺失");
        errorCode.put(406002, "未满足的参数条件");
        errorCode.put(500000, "不一致的数据");
        errorCode.put(530000, "OSS上传失败");
        errorCode.put(999999, "严重错误");
    }

    //巡查任务状态
    public static String CREATED = "CREATED"; //创建
    public static String STARTED = "STARTED"; //已开始
    public static String COMPLETED = "COMPLETED"; //已完成
    public static String SUBMITED = "SUBMITED"; //已提交
    public static String UNSUBMITED = "UNSUBMITED"; //未已提交

    //巡查任务类型
    public static final String TEAM = "TEAM";//团体任务
    public static final String PERSON = "PERSON";//个人任务
    public static final String STORE = "STORE";//个人任务

    //巡查信息Type
    public static String SINGLECHOICE = "SingleChoice";
    public static String MULTIPLECHOICE = "MultipleChoice";
    public static String FILLINBLANK = "FillInBlank";
    public static String IMAGE = "Image";
    public static String SHORTANSWER = "ShortAnswer";

    public static String getTaskStatue(String status) {
        if (status.equals(CREATED)) {
            return "";
        } else if (status.equals(STARTED)) {
            return "";
        } else if (status.equals(COMPLETED)) {
            return "";
        } else if (status.equals(SUBMITED)) {
            return "";
        } else {
            return "";
        }
    }

    public static Map<String, String> header() {
        Map<String, String> header = new HashMap<>();
        header.put("auth-" + CacheInfoManager.getInstance().getUserId(), CacheInfoManager.getInstance().getUserToken());
        return header;
    }

    public static Map<Integer, String> errorCode() {
        return errorCode;
    }
}
