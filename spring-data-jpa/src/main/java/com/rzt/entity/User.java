package com.rzt.entity;


import com.rzt.config.Group;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户
 * <p>
 * //@Column(precision=8, scale=2)  列精度定义
 * //private float hourlyRate;
 * //@Lob   大文本
 * //@Temporal 日期类
 * //@Column(name = "name", columnDefinition = "varchar(18)", length = 18)
 * //@Column(name = "START_DATE", columnDefinition = "DATE DEFAULT CURRENT_DATE")
 * //@Temporal(TemporalType.DATE)   calendar 日历映射到日期
 * //private java.util.Calendar dob;
 * //@Temporal(TemporalType.TIME)  日期映射到时间
 * //private java.util.Date dob;
 * //@Temporal(TemporalType.TIMESTAMP) 日期映射到时间戳
 * //private java.util.Date dob;
 * //@Transient 指定字段不持久化到数据库
 * //@GeneratedValue(strategy=GenerationType.IDENTITY) 主键自动生成及生成策略
 * //
 * //@SequenceGenerator(name="Emp_Gen", sequenceName="Emp_Seq")
 * //@Id @GeneratedValue(generator="Emp_Gen")  序列生成器
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-06-01 11:01
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

    @NotBlank(message = "id不能为空", groups = Group.update.class)
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;


    @NotBlank(message = "名字不能为空", groups = Group.Default.class)
    @Column(name = "name", length = 18)
    private String name;

    private String email;
    private String address;

    //@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    //@JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PostInfo> postInfos = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detail_id")
    //@PrimaryKeyJoinColumn
    private UserDetail userDetail;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private String test;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<PostInfo> getPostInfos() {
        return postInfos;
    }

    public void setPostInfos(Set<PostInfo> postInfos) {
        this.postInfos = postInfos;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", postInfosSize=" + postInfos.size() +
                ", userDetail=" + (userDetail == null ? null : userDetail.getDetailId()) +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", test='" + test + '\'' +
                '}';
    }
}
