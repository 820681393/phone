package com.mobile.controller;

import com.core.utils.ExcelUtils;
import com.core.utils.OcrTableImageUtils;
import com.core.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
@Api(value = "api接口", tags = "解析接口")
public class MobileSheetController {


    @PostMapping(value = "/sheet/parsing")
    @ResponseBody
    @ApiOperation(value = "表格解析", notes = "请求携带token,file参数文件上传")
    public ResponseResult<List<List<String>>> parsing(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws ParseException {
        return ResponseResult.successResult(ExcelUtils.analysis(file));
    }
    @PostMapping(value = "/image/identify")
    @ResponseBody
    @ApiOperation(value = "图片识别", notes = "请求携带token,file参数文件上传")
    public ResponseResult identify(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {
        return ResponseResult.successResult(OcrTableImageUtils.ocrTableImageIdentify(file.getInputStream()));
    }

}
