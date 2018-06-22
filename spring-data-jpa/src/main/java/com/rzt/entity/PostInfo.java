package com.rzt.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-06-01 14:23
 */
@Entity
@Table(name = "post_info")
public class PostInfo implements Serializable {

    @Id
    @Column(name = "post_id")
    private String postId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user = new User();

    //@ManyToMany(mappedBy = "postInfos", fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "post_tag"
            , joinColumns = {
            @JoinColumn(name = "post_id")}
            , inverseJoinColumns = {
            @JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags = new HashSet<>();

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "PostInfo{" +
                "postId='" + postId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", tags=" + tags.size() +
                '}';
    }
}
