package com.test;



import java.net.URL;
import java.net.URLEncoder;

import com.ibeer.baidu.Base64Util;
import com.ibeer.baidu.FileUtil;
import com.ibeer.baidu.HttpUtil;

/**
* 通用文字识别
*/
public class GeneralBasic {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
	
    public static String generalBasic() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate";
        try {
            // 本地文件路径
            String filePath = "D:\\temp\\img\\a.jpg";
            filePath="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584935293399&di=6c2ec8c09be649333752942125d62c26&imgtype=0&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D3422062295%2C4257645496%26fm%3D214%26gp%3D0.jpg";
            URL urls = new URL(filePath);
            byte[] imgData = FileUtil.readFileByBytes(urls);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.213ed3c23006b82814eac29938393c8e.2592000.1587472157.282335-19000831";

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        GeneralBasic.generalBasic();
    }
}
