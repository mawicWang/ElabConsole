package com.duofuen.elab.rest;

import com.duofuen.elab.domain.Image;
import com.duofuen.elab.domain.ImageRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@RestController
@RequestMapping()
public class ImageController {
    private static final Logger LOGGER = LogManager.getLogger();

    private final ImageRepository imageRepository;

    @Autowired
    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping(value = "/img")
    public void viewImage(Integer id, HttpServletResponse response) throws IOException {
        LOGGER.info("==>restful method viewImage called, id: {}", id);

        Optional<Image> image = imageRepository.findById(id);
        if (!image.isPresent()) {
            response.setStatus(404);
            response.setContentType("text/plain;charset=utf8");
            response.getWriter().append("No Image Found");
            return;
        }
        byte[] data = image.get().getContent();

        response.setContentType("image/" + image.get().getType());
        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }

//    @Transactional
//    @PostMapping(value = "/uploadImage")
//    public BaseResponse uploadImage(@RequestBody Map<String, String> map) {
//        LOGGER.info("==>restful method uploadImage called");
//
//        BaseResponse baseResponse;
//
//        String imgEncode = map.get(Const.Rest.IMG);
//        if (imgEncode.isEmpty()) {
//            baseResponse = BaseResponse.fail("img is empty");
//        } else {
//            LOGGER.info("image size {}", imgEncode.length());
//
//            Base64 decoder = new Base64();
//            try {
//                //判断图片的格式
//                String data = "data:image";
//                int sIndex = imgEncode.indexOf(data);
//                String chanImgStr = imgEncode.substring(sIndex + data.length());
//                int eIndex = chanImgStr.indexOf(";");
//                String imageType = chanImgStr.substring(1, eIndex);
//                // 取得实际编码
//                String base = "base64,";
//                int bIndex = imgEncode.indexOf(base);
//                imgEncode = imgEncode.substring(bIndex + 7);
//
//                //Base64解码
//                byte[] imgByte = decoder.decode(imgEncode);
//                for (int i = 0; i < imgByte.length; ++i) {
//                    if (imgByte[i] < 0) {//调整异常数据
//                        imgByte[i] += 256;
//                    }
//                }
////                //生成jpeg图片
////                String imgFilePath = "test.gif";//新生成的图片
////                OutputStream out = new FileOutputStream(imgFilePath);
////                out.write(imgByte);
////                out.flush();
////                out.close();
//
//                // save to db
//                Image image = new Image();
//                image.setImageType(imageType);
//                image.setImageContent(imgByte);
//                image.setCreateTime(Date.from(Instant.now()));
//                imageRepository.save(image);
//
//                RbImage rbImage = new RbImage();
//                rbImage.setImageId(image.getId());
//
//
//                baseResponse = BaseResponse.success(rbImage);
//            } catch (Exception e) {
//                LOGGER.error(e);
//                baseResponse = BaseResponse.fail("upload image fail");
//            }
//
//        }
//
//        return baseResponse;
//    }
}
