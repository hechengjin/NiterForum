package cn.niter.forum.cache;

import cn.niter.forum.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO product = new TagDTO();
        product.setCategoryName("产品");
        product.setTags(Arrays.asList("firemail", "wyyt", "Doraemon"));
        tagDTOS.add(product);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("开发平台");
        framework.setTags(Arrays.asList("Mozilla", "Qt", "electron"));
        tagDTOS.add(framework);


        TagDTO common = new TagDTO();
        common.setCategoryName("常用");
        common.setTags(Arrays.asList("笔记", "邮箱", "备忘", "资源", "管理", "教育", "开源", "原创", "讨论", "闲聊", "教程", "公告", "灌水", "测试"));
        tagDTOS.add(common);

        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("python", "java", "c++", "c", "html5", "javascript", "css", "html", "node.js", "typescript", "c#"));
        tagDTOS.add(program);


//        TagDTO server = new TagDTO();
//        server.setCategoryName("服务器");
//        server.setTags(Arrays.asList("tomcat", "linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存", "负载均衡", "unix", "hadoop", "windows-server"));
//        tagDTOS.add(server);
//
//        TagDTO db = new TagDTO();
//        db.setCategoryName("数据库");
//        db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));
//        tagDTOS.add(db);
//
//        TagDTO tool = new TagDTO();
//        tool.setCategoryName("开发工具");
//        tool.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode", "intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));
//        tagDTOS.add(tool);

        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> StringUtils.isBlank(t) || !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
