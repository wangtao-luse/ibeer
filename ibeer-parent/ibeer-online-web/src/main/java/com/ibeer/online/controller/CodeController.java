package com.ibeer.online.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.ibeer.common.BaseException;
import com.ibeer.common.ImageVerificationUtil;
import com.ibeer.dto.ImageVerificationDto;
import com.ibeer.dto.ImageVerificationVo;

public class CodeController {
	 /**
     * 获取滑动验证码
     * @param imageVerificationDto 验证码参数
     * @return 滑动验证码
     * @throws ServiceException 获取滑动验证码异常
     */
    public ImageVerificationVo selectSlideVerificationCode(ImageVerificationDto imageVerificationDto,HttpServletRequest request) {


        ImageVerificationVo imageVerificationVo = null;
        try {
//            //  原图路径，这种方式不推荐。当运行jar文件的时候，路径是找不到的，我的路径是写到配置文件中的。
//            String verifyImagePath = URLDecoder.decode(this.getClass().getResource("/").getPath() + "static/targets", "UTF-8");

String verificationImagePathPrefix = null;
String templateImagePathPrefix = null;
			//            获取模板文件，。推荐文件通过流读取， 因为文件在开发中的路径和打成jar中的路径是不一致的
//            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("static/template/1.png");
            File verifyImageImport = new File(verificationImagePathPrefix);
            File[] verifyImages = verifyImageImport.listFiles();

            Random random = new Random(System.currentTimeMillis());
            //  随机取得原图文件夹中一张图片
            File originImageFile = verifyImages[random.nextInt(verifyImages.length)];
           
			//  获取模板图片文件
            File templateImageFile = new File(templateImagePathPrefix + "/template.png");

            //  获取描边图片文件
            File borderImageFile = new File(templateImagePathPrefix + "/border.png");
            //  获取描边图片类型
            String borderImageFileType = borderImageFile.getName().substring(borderImageFile.getName().lastIndexOf(".") + 1);

            //  获取原图文件类型
            String originImageFileType = originImageFile.getName().substring(originImageFile.getName().lastIndexOf(".") + 1);
            //  获取模板图文件类型
            String templateImageFileType = templateImageFile.getName().substring(templateImageFile.getName().lastIndexOf(".") + 1);

            //  读取原图
            BufferedImage verificationImage = ImageIO.read(originImageFile);
            //  读取模板图
            BufferedImage readTemplateImage = ImageIO.read(templateImageFile);

            //  读取描边图片
            BufferedImage borderImage = ImageIO.read(borderImageFile);


            //  获取原图感兴趣区域坐标
            imageVerificationVo = ImageVerificationUtil.generateCutoutCoordinates(verificationImage, readTemplateImage);

            int Y  = imageVerificationVo.getY();
                    //  在分布式应用中，可将session改为redis存储
            request.getSession().setAttribute("imageVerificationVo", imageVerificationVo);

            //  根据原图生成遮罩图和切块图
            imageVerificationVo = ImageVerificationUtil.pictureTemplateCutout(originImageFile, originImageFileType, templateImageFile, templateImageFileType, imageVerificationVo.getX(), imageVerificationVo.getY());

            //   剪切图描边
            imageVerificationVo = ImageVerificationUtil.cutoutImageEdge(imageVerificationVo, borderImage, borderImageFileType);
            imageVerificationVo.setY(Y);
            imageVerificationVo.setType(imageVerificationDto.getType());



            //  =============================================
            //  输出图片
//            HttpServletResponse response = getResponse();
//            response.setContentType("image/jpeg");
//            ServletOutputStream outputStream = response.getOutputStream();
//            outputStream.write(oriCopyImages);
//            BufferedImage bufferedImage = ImageIO.read(originImageFile);
//            ImageIO.write(bufferedImage, originImageType, outputStream);
//            outputStream.flush();
            //  =================================================

        } catch (UnsupportedEncodingException e) {
        	  throw new BaseException(e.getMessage());
        } catch (IOException e) {
            throw new BaseException(e.getMessage());
        }

        return imageVerificationVo;
    }


}
