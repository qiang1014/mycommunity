package cn.zhaoq.mycommunity.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="creator")
    private Integer creator;

    @Column(name="gmt_create")
    private Long gmt_create;

    @Column(name="gmt_modified")
    private Long gmt_modified;

    @Column(name="comment_count")
    private Integer comment_count;

    @Column(name="view_count")
    private Integer view_count;

    @Column(name="like_count")
    private Integer like_count;

    @Column(name="tag")
    private String tag;

}
