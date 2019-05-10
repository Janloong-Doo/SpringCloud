package com.janloong.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-06-11 16:59
 */
@Entity
public class Tag implements Serializable {

    @Id
    @Column(name = "tag_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    @Column(name = "tag_name")
    private String tagName;
    @Column(name = "create_time")
    private String createTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "post_tag"
            , joinColumns = {
            @JoinColumn(name = "tag_id")}
            , inverseJoinColumns = {
            @JoinColumn(name = "post_id")}
    )
    private Set<PostInfo> postInfos = new HashSet<>();

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Set<PostInfo> getPostInfos() {
        return postInfos;
    }

    public void setPostInfos(Set<PostInfo> postInfos) {
        this.postInfos = postInfos;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", postInfos=" + postInfos.size() +
                '}';
    }
}
