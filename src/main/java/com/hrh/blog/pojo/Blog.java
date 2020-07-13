package com.hrh.blog.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/22 23:09
 * 博客详情
 * 加了@Entity注解才有实体类和数据库对应的能力
 * @Table(name = "t_blog")指定数据库的名，不写参数的话就是默认生成类名一致的表名
 * @Id声明表的主键
 * @GeneratedValue设置自增
 * @Temporal(TemporalType.TIMESTAMP)设置数据库的日期类型为TIMESTAMP
 * @ManyToOne表示t_blog表与 t_type表之间的多对一关系，在Type类里面也要做关系说明
 * @ManyToMany(cascade = {CascadeType.PERSIST})表示当新建一条博客数据的时候新增标签，那么标签类里也会新增该标签
 */
@Entity
@Table(name = "t_blog")
public class Blog {
    //主键
    @Id
    @GeneratedValue
    private long id;
    //标题
    private String  title;
    //内容 @Basic(fetch = FetchType.LAZY)懒加载，不会立马查找，只有用到的时候才查找 @Lob声明是个大字段类型，在数据库里会生成longtest类型，避免因内容过长而sql语句出错
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    //首图
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String firstPicture;
    //标记
    private String flag;
    //浏览次数
    private int views;
    //赞赏开启
    private boolean appreciation;
    //版权开启
    private boolean shareStatement;
    //评论开启
    private boolean commentabled;
    //是否发布
    private boolean published;
    //是否推荐
    private boolean recommend;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date cteateTime;
    //更新时间
    @Temporal(TemporalType.TIMESTAMP)
    private  Date updateTime;
    //分类类
    @ManyToOne
    private Type type;
    //标签类
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();
    //标签类的ids  @Transient是不需要该字段存储进数据库  不加该注解是默认保存进数据库的
    @Transient
    private String tagIds;
    //评论类
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
    //用户类
    @ManyToOne
    private User user;
    //博客描述（用于游客首页的博客预览）
    private String description;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCteateTime() {
        return cteateTime;
    }

    public void setCteateTime(Date cteateTime) {
        this.cteateTime = cteateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blog(String title, String content, String firstPicture, String flag, int views, boolean appreciation, boolean shareStatement, boolean commentabled, boolean published, boolean recommend, Date cteateTime, Date updateTime, Type type, List<Tag> tags, List<Comment> comments, User user) {
        this.title = title;
        this.content = content;
        this.firstPicture = firstPicture;
        this.flag = flag;
        this.views = views;
        this.appreciation = appreciation;
        this.shareStatement = shareStatement;
        this.commentabled = commentabled;
        this.published = published;
        this.recommend = recommend;
        this.cteateTime = cteateTime;
        this.updateTime = updateTime;
        this.type = type;
        this.tags = tags;
        this.comments = comments;
        this.user = user;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Blog() {

    }
    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    //1,2,3
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }
    //toString()是为了在日志里记录方便打印
    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", cteateTime=" + cteateTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
