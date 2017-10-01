package cn.lewis.all_in.dto;

public class BlogDTO {
    private String blogNo; //编号
    private String title; //题目
    private String abstracts; //摘要
    private String type; //类别
    private String content; //内容

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public String getBlogNo() {
        return blogNo;
    }

    public void setBlogNo(String blogNo) {
        this.blogNo = blogNo;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BlogDTO{" +
                "blogNo='" + blogNo + '\'' +
                ", title='" + title + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
