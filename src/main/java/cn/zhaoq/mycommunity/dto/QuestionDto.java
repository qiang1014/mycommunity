package cn.zhaoq.mycommunity.dto;

import cn.zhaoq.mycommunity.domain.User;
import lombok.Data;

@Data
public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private Integer creator;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private String tag;

    private User user;
}
