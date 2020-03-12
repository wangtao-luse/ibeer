package com.ibeer.online.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibeer.common.BaseException;
import com.ibeer.common.code.ImageVerificationUtil;
import com.ibeer.common.constant.ConstantBase;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.dto.ImageVerificationDto;
import com.ibeer.dto.ImageVerificationVo;

@Controller
public class ImageVerificationController {
	@Value("${prefix.verificationImagePathPrefix}")
	private String verificationImagePathPrefix;
    /**
     * 获取滑动验证码
     * @param imageVerificationDto 验证码参数
     * @return 滑动验证码
     * @throws ServiceException 获取滑动验证码异常
     */
	@RequestMapping("/selectSlideVerificationCode")
	@ResponseBody
    public ResponseMessage selectSlideVerificationCode(ImageVerificationDto imageVerificationDto,HttpServletRequest request) {

       ResponseMessage responseMessage = ResponseMessage.getSucess();
        ImageVerificationVo imageVerificationVo = null;
        try {            
            File verifyImageImport = new File(verificationImagePathPrefix);
            File[] verifyImages = verifyImageImport.listFiles();

            Random random = new Random(System.currentTimeMillis());
            //  随机取得原图文件夹中一张图片
            File originImageFile = verifyImages[random.nextInt(verifyImages.length)];

            String templateImagePathPrefix = verificationImagePathPrefix;
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

			
			  // 读取原图 
              BufferedImage verificationImage = ImageIO.read(originImageFile); 
             //读取模板图
			  BufferedImage readTemplateImage = ImageIO.read(templateImageFile);
			  
			  // 读取描边图片 
			   BufferedImage borderImage = ImageIO.read(borderImageFile);
			  
			  
			  // 获取原图感兴趣区域坐标 
			  imageVerificationVo =ImageVerificationUtil.generateCutoutCoordinates(verificationImage,readTemplateImage);
			  
			  int Y = imageVerificationVo.getY(); // 在分布式应用中，可将session改为redis存储
			  request.getSession().setAttribute("imageVerificationVo",
			  imageVerificationVo);
			  
			  // 根据原图生成遮罩图和切块图 imageVerificationVo =
			  ImageVerificationUtil.pictureTemplateCutout(originImageFile,
			  originImageFileType, templateImageFile, templateImageFileType,
			  imageVerificationVo.getX(), imageVerificationVo.getY());
			  
			  // 剪切图描边 imageVerificationVo =
			  ImageVerificationUtil.cutoutImageEdge(imageVerificationVo, borderImage,
			  borderImageFileType); imageVerificationVo.setY(Y);
			  imageVerificationVo.setType(imageVerificationDto.getType());
			 

             responseMessage.setReturnResult(imageVerificationVo);

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

        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	ResponseMessage.getFailed(ConstantBase.FAILED_SYSTEM_ERROR);
		}
       
        return responseMessage; 
    }
	public static void main(String[] args) {
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < 30; i++) {
			System.out.println( random.nextInt(9));
		}
		
	}
}
