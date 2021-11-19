package models.api;

public class SupportPOJO {
    private String url;
    private String text;

    public SupportPOJO(){

    }

    public SupportPOJO(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "SupportPOJO{" +
                "url='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

