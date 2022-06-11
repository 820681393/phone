package com.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 加减法验证码 外面要处理的代码 放这里先
 *
 * ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
 *         //写入流中
 *         ImageIO.write(image, "JPEG", byteStream);
 *         //转换成字节
 *         byte[] bytes = byteStream.toByteArray();
 *         //转换成base64串
 *         String base64 = Base64.getEncoder().encodeToString(bytes).trim();
 *         base64 = "data:image/jpg;base64,"+base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
 */
public class ValidateCodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(ValidateCodeUtils.class);

    public static  BufferedImage processRequest() {
        try {
            // 在内存中创建图象
            int width = 70, height = 20;
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // 获取图形上下文
            Graphics g = image.getGraphics();
            // 生成随机类
            Random random = new Random();
            // 设定背景色
            g.setColor(getRandColor(200, 250));
            g.fillRect(0, 0, width, height);
            // 设定字体
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
            g.setColor(getRandColor(160, 200));
            for (int i = 0; i < 155; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                int xl = random.nextInt(12);
                int yl = random.nextInt(12);
                g.drawLine(x, y, x + xl, y + yl);
            }
            // 取随机产生的认证码(4位数字)
            int sRand = 0;
            // 是加法还是减法
            int math = random.nextInt(2);
            // 加法
            if (math == 0) {
                // 第一个数据
                int rand = random.nextInt(10);
                if (rand == 0) {
                    rand = 1;
                }
                //
                int rand1 = random.nextInt(10);
                g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
                g.drawString("" + (rand+rand1), 13 * 0 + 6, 16);

//                g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
//                g.drawString("" + rand1, 13 * 1 + 6, 16);
                // +号
                g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
                g.drawString("+", 13 * 2 + 6, 16);
                // 第二个数据
                int rand2 = random.nextInt(10);
                g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
                g.drawString("" + rand2, 13 * 3 + 6, 16);
                //等号
                g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
                g.drawString("=", 13 * 4 + 6, 16);
                sRand = rand + rand1 + rand2;
            } else {
                // 减法
                // 第一个数据
                int rand = random.nextInt(10);
                if (rand == 0) {
                    rand = 1;
                }
                rand = rand + 10;
                g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
                g.drawString("" + rand, 13 * 0 + 6, 16);
                //
//                int rand1 = random.nextInt(10);
//                g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
//                g.drawString("" + rand1, 13 * 1 + 6, 16);
                // -号
                g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
                g.drawString("-", 13 * 2 + 6, 16);
                // 第二个数据
                int rand2 = random.nextInt(10);
                g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
                g.drawString("" + rand2, 13 * 3 + 6, 16);
                //等号
                g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
                g.drawString("=", 13 * 4 + 6, 16);
                sRand = rand - rand2;
            }
            logger.info("验证码是{}",sRand);
            return image;
        } catch (Exception e) {
            logger.error("发生了异常{}",e);
            // logger.error(e.getLocalizedMessage(), e.fillInStackTrace());
        }
        return null;
    }

    private static Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}

