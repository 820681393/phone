package com.core.utils;//package com.common.util;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.common.exception.DescribeException;
//import com.common.modules.dao.ICurrencyProductDao;
//import com.common.vo.CurrencyProduct;
//import org.redisson.api.RBucket;
//import org.redisson.api.RedissonClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class DataUtil_copy {
//
//    @Autowired
//    private RedissonClient redissonClient;
//    @Autowired
//    private ICurrencyProductDao iCurrencyProductDao;
//
//    private static final Logger logger  = LoggerFactory.getLogger(DataUtil_copy.class);
//
//
//    /*public BigDecimal exChange(String currency,BigDecimal money){
//        CurrencyProduct product = new CurrencyProduct();
//        product.setCurrencyMedium(currency);
//        product = iCurrencyProductDao.findCurrencyMedium(product);
//        if(product == null){
//            throw new DescribeException("产品错误","product error",-1);
//        }
//        //自发币
//        if(product.getCurrencyType().equals("2")){
//            RBucket bucket = redissonClient.getBucket(CommonRedisKey.issued_price);
//            BigDecimal ex = new BigDecimal(String.valueOf(bucket.get()));
//            BigDecimal price = money.divide(ex,6, BigDecimal.ROUND_HALF_UP);
//            return price;
//        }else{
//            BigDecimal ex = getNowPrice(product);
//            if(ex == null){
//                throw new DescribeException("汇率错误","Exchange rate error",-1);
//            }
//            BigDecimal price = money.divide(ex,6, BigDecimal.ROUND_HALF_UP);
//            return price;
//        }
//    }*/
//
//    public BigDecimal newExChange(String currency,BigDecimal money){
//        if(currency.equals("usdt_usdt")) {
//            return money;
//        }
//        CurrencyProduct product = new CurrencyProduct();
//        product.setCurrencyMedium(currency);
//        product = iCurrencyProductDao.findCurrencyMedium(product);
//        if(product == null){
//            throw new DescribeException("产品错误","product error",-1);
//        }
//        //自发币
//        if(product.getCurrencyType().equals("2")){
//            RBucket bucket = redissonClient.getBucket(CommonRedisKey.issued_price);
//            BigDecimal ex = new BigDecimal(String.valueOf(bucket.get()));
//            return money.multiply(ex);
//        }else{
//            BigDecimal ex = getNowPrice(product);
//            if(ex == null){
//                throw new DescribeException("汇率错误","Exchange rate error",-1);
//            }
//            return money.multiply(ex);
//        }
//    }
//
//    //得到产品价格汇率 btc_usdt
//    public JSONObject getRateAndPrice(String productName) {
//        String url = "http://api.zb.team/data/v1/ticker?market=" + productName;
//        String result = null;
//        try {
//            RBucket rBucket = redissonClient.getBucket(CommonRedisKey.price_rate_+productName);
//            if(rBucket != null && rBucket.get() != null){
//                logger.info("产品价格缓存{}",rBucket.get());
//                return (JSONObject) rBucket.get();
//            }
//            result = CommonUtil.sendGet(url);
//            JSONObject jsonObject = JSON.parseObject(result);
//            JSONObject object = jsonObject.getJSONObject("ticker");
//            Random random = new Random();
//            rBucket.set(object,10, TimeUnit.SECONDS);
//            return object;
//        } catch (Exception e) {
//            logger.error("获取产品价格异常{}异常{}",result,e);
//            return null;
//        }
//    }
//
//    //得到产品价格汇率 btc_usdt
//    public JSONObject getNowRateAndPrice(String productName) {
//        String url = "http://api.zb.team/data/v1/ticker?market=" + productName;
//        String result = null;
//        try {
////            RBucket rBucket = redissonClient.getBucket(CommonRedisKey.price_rate_+productName);
////            if(rBucket != null && rBucket.get() != null){
////                logger.info("产品价格缓存{}",rBucket.get());
////                return (JSONObject) rBucket.get();
////            }
//            result = CommonUtil.sendGet(url);
//            JSONObject jsonObject = JSON.parseObject(result);
//            JSONObject object = jsonObject.getJSONObject("ticker");
////            Random random = new Random();
////            rBucket.set(object,10, TimeUnit.SECONDS);
//            return object;
//        } catch (Exception e) {
//            logger.error("获取产品价格异常{}异常{}",result,e);
//            return null;
//        }
//    }
//
//
//    //得到产品价格汇率 btc_usdt
//    public JSONObject getDepth(String productName) {
//        String url = "https://api.zb.team/data/v1/depth?market="+productName+"&size=50";
//        String result = null;
//        try {
//            RBucket rBucket = redissonClient.getBucket("get_depth_"+productName);
//            if(rBucket != null && rBucket.get() != null){
//                logger.info("产品深度缓存{}",rBucket.get());
//                return (JSONObject) rBucket.get();
//            }
//            result = CommonUtil.sendGet(url);
//            JSONObject jsonObject = JSON.parseObject(result);
//            rBucket.set(jsonObject,5, TimeUnit.SECONDS);
//            return jsonObject;
//        } catch (Exception e) {
//            logger.error("深度异常{}异常{}",result,e);
//            return null;
//        }
//    }
//
//    //得到自发币所有信息
//    public JSONObject getIssuedCoinInfo(CurrencyProduct product) {
//        try {
//            JSONObject object = new JSONObject();
//            RBucket dayPrice = redissonClient.getBucket(CommonRedisKey.day_price);
//            RBucket second_price = redissonClient.getBucket(CommonRedisKey.issued_price);
//            RBucket high = redissonClient.getBucket(CommonRedisKey.day_high);
//            RBucket low = redissonClient.getBucket(CommonRedisKey.day_low);
//            RBucket dayVol = redissonClient.getBucket(CommonRedisKey.day_vol);
//            object.put("high",high.get());
//            object.put("low",low.get());
//            object.put("last",second_price.get());
//            object.put("open",dayPrice.get());
//            object.put("vol",dayVol.get());
//            BigDecimal dPrice = new BigDecimal(String.valueOf(dayPrice.get()));
//            BigDecimal nowPrice = new BigDecimal(String.valueOf(second_price.get()));
//            BigDecimal rate = nowPrice.subtract(dPrice).divide(dPrice,4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
//            object.put("riseRate",rate);
//            return object;
//        } catch (Exception e) {
//            logger.error("自发币数据错误{}",e);
//            return null;
//        }
//    }
//
//    //币种最新价格，最新汇率 eg product
//    public BigDecimal getNowPrice(CurrencyProduct product) {
//        //自发币
//        if(product.getCurrencyType().equals("2")){
//            BigDecimal price = null;
//            RBucket bucket = redissonClient.getBucket(CommonRedisKey.issued_price);
//            if(bucket != null && bucket.get() != null){
//                return new BigDecimal(String.valueOf(bucket.get()));
//            }else{
//                return price;
//            }
//        }
//        String url = "http://api.zb.team/data/v1/ticker?market=" + product.getCurrencyMedium();
//        String result = null;
//        try {
//            RBucket rBucket = redissonClient.getBucket(CommonRedisKey.last_price_+product.getCurrencyMedium());
//            if(rBucket != null && rBucket.get() != null){
//                logger.info("产品{}缓存直接返回价格{}",product.getCurrencyMedium(),rBucket.get());
//                return new BigDecimal(rBucket.get().toString());
//            }
//            result = CommonUtil.sendGet(url);
//            JSONObject jsonObject = JSON.parseObject(result);
//            String last = jsonObject.getJSONObject("ticker").getString("last");
//            Random random = new Random();
//            rBucket.set(last,10, TimeUnit.SECONDS);
//            logger.info("产品{}三方请求价格{}",product.getCurrencyMedium(),last);
//            return new BigDecimal(last);
//        } catch (Exception e) {
//            logger.error("获取价格异常{}异常{}",result,e);
//            return null;
//        }
//    }
//
//
//    public BigDecimal getForeignNowPrice(String productName) {
//        RBucket rBucket = redissonClient.getBucket(CommonRedisKey.last_price_+productName);
//        if(rBucket != null && rBucket.get() != null){
//            logger.info("产品{}缓存外汇直接返回价格{}",productName,rBucket.get());
//            return new BigDecimal(rBucket.get().toString());
//        }
//        //查询数据库
//        long formA;
//        formA = System.currentTimeMillis() / 1000 - 300 ;
//        long toB = formA + 300;
//        String urlA = "https://finnhub.io/api/v1/forex/candle?symbol=OANDA:"+productName+"&resolution=1&from="+formA+"&to="+toB+"&token=" + CommonConst.foreign_token;
//        String str = CommonUtil.sendGet(urlA);
//        if(str.contains("no_data")){
//            //返回1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六
//            //处理外汇礼拜六礼拜天不开奖
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(new Date(System.currentTimeMillis()));
//            long form;
//            int in = cal.get(Calendar.DAY_OF_WEEK);
//            if(in == 1){
//                form = MyDateUtil.getBackDayZeroTime(-2) / 1000;
//            }else if(in == 2){
//                form = MyDateUtil.getBackDayZeroTime(-3) / 1000;
//            }else if(in == 7){
//                form = MyDateUtil.getBackDayZeroTime(-1) / 1000;
//            }
//            else{
//                form = System.currentTimeMillis() / 1000 - 300 ;
//            }
//            long to = form + 300;
//            String url = "https://finnhub.io/api/v1/forex/candle?symbol=OANDA:"+productName+"&resolution=1&from="+form+"&to="+to+"&token=" + CommonConst.foreign_token;
//            str = CommonUtil.sendGet(url);
//        }
//        try {
//            JSONObject jsonObject = JSON.parseObject(str);
//            BigDecimal last = jsonObject.getJSONArray("c").getBigDecimal(0);
//            Random random = new Random();
//            rBucket.set(last,10, TimeUnit.SECONDS);
//            logger.info("产品{}三方外汇请求价格{}",productName,last);
//            return last;
//        } catch (Exception e) {
//            logger.error("获取外汇价格异常{}异常{}",str,e);
//            return null;
//        }
//
//    }
//
//}
