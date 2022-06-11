package com.core.utils;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.ocr_api20210707.Client;
import com.aliyun.ocr_api20210707.models.RecognizeGeneralRequest;
import com.aliyun.ocr_api20210707.models.RecognizeGeneralResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.aliyun.tea.*;
import com.aliyun.ocr_api20210707.*;
import com.aliyun.ocr_api20210707.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;
import com.aliyun.teautil.*;
import com.aliyun.teautil.models.*;
import java.io.*;

public class OcrTableImageUtils {






        /**
         * 使用AK&SK初始化账号Client
         * @param accessKeyId
         * @param accessKeySecret
         * @return Client
         * @throws Exception
         */
        public static com.aliyun.ocr_api20210707.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
            Config config = new Config()
                    // 您的 AccessKey ID
                    .setAccessKeyId(accessKeyId)
                    // 您的 AccessKey Secret
                    .setAccessKeySecret(accessKeySecret);
            // 访问的域名
            config.endpoint = "ocr-api.cn-hangzhou.aliyuncs.com";
            return new com.aliyun.ocr_api20210707.Client(config);
        }




        public static JSONObject ocrTableImageIdentify(InputStream is)throws Exception {
            com.aliyun.ocr_api20210707.Client client = createClient(accessKeyId, accessKeySecret);
            RecognizeTableOcrRequest recognizeTableOcrRequest = new RecognizeTableOcrRequest()
                    .setNeedRotate(true)
                    .setSkipDetection(true)
                    .setBody(is)
                    .setLineLess(false);
            RuntimeOptions runtime = new RuntimeOptions();
            try {
                // 复制代码运行请自行打印 API 的返回值
                RecognizeTableOcrResponse recognizeTableOcrResponse=client.recognizeTableOcrWithOptions(recognizeTableOcrRequest, runtime);
                JSONObject js=JSONObject.parseObject(recognizeTableOcrResponse.getBody().getData().toString());
                return js;
            } catch (TeaException error) {
                // 如有需要，请打印 error
                com.aliyun.teautil.Common.assertAsString(error.message);
            } catch (Exception _error) {
                TeaException error = new TeaException(_error.getMessage(), _error);
                // 如有需要，请打印 error
                com.aliyun.teautil.Common.assertAsString(error.message);
            }
            return null;
        }








    private static final String accessKeyId="LTAI5tPa3G7PFdiFKM2NoDeG";
    private static final String accessKeySecret="gk1pd7bSmv2LmyZwW3PiZamIhPwq3F";




    public static byte[] getBytesByFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
