package cn.niter.forum.service;

import cn.niter.forum.provider.JiGuangProvider;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
public class FileService {
    protected static final Logger LOG = LoggerFactory.getLogger(FileService.class);
    @Value("${file.path.image}")
    private String imageSavePath;

    @Value("${file.path.return}")
    private String imageReturnPath;

    public String upload(MultipartFile image, HttpServletRequest request) {
        if (image!=null) {// 判断上传的文件是否为空
            String path=null;// 文件路径
            String type=null;// 文件类型
            String fileName=image.getOriginalFilename();// 文件原名称
            // 判断文件类型
            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            if (type!=null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())||"JPEG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath=request.getSession().getServletContext().getRealPath("/");
//                    String path2 = request.getServletContext().getRealPath("/upload/excel");
                    ServletRequestContext ctx = new ServletRequestContext(request);
                    //获取上传文件尺寸大小
                    long requestSize = ctx.contentLength();

                    // 自定义的文件名称
                    String trueFileName=String.valueOf(System.currentTimeMillis()) + "." + type.toUpperCase(); //+fileName;
                    // 设置存放图片文件的路径
                    path=realPath+System.getProperty("file.separator")+trueFileName;
                    path=imageSavePath+trueFileName;
                    try {
                        // 转存文件到指定的路径
                        image.transferTo(new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                        LOG.error(e.getMessage());
                    }
                    String returnPath = imageReturnPath + trueFileName;
                    return returnPath;
                }else {
                    LOG.info("不是我们想要的文件类型,请按要求重新上传");
                }
            }else {
                LOG.info("文件类型为空");
            }
        }else {
            LOG.info("没有找到相对应的文件");
        }
        return "";

    }
}
