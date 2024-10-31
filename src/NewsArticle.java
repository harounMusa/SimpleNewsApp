public class NewsArticle {
    private String title;
    private String imagePath;
    private String content;

    public NewsArticle(String title, String imagePath, String content) {
        this.title = title;
        this.imagePath = imagePath;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getContent() {
        return content;
    }
}

