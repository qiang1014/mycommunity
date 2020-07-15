package cn.zhaoq.mycommunity.domain;


import lombok.Data;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;

/**
 * user实体类，与数据库建立映射关系
 */


@Entity
@Table
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "token")
    private String token;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "gmt_create")
    private Long gmtCreate;

    @Column(name = "gmt_modified")
    private Long gmtModified;

    @Column(name = "avatar_url")
    private String avatar_url;

}
