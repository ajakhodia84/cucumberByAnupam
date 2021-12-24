package framework.shared;

public class UrlHandler {

    private String url = "";

    public UrlHandler() {
    }

    public UrlHandler(String initialURL) {
        this.url = initialURL.endsWith("/") ? initialURL : initialURL + "/";
    }

    //example - urlPart = https://www.staging.domain.fr/fr/
    public UrlHandler append(String urlPart) {
        if (urlPart.toLowerCase().startsWith("http")) {
            this.url = urlPart.endsWith("/") ? urlPart : urlPart + "/";
        } else if (!urlPart.equals("/") && !urlPart.endsWith(".html")) {
            this.url = this.url
                    + (urlPart.startsWith("/") && urlPart.length() > 1 ? urlPart.substring(1) : urlPart) + "/";
        } else if (urlPart.endsWith(".html")) {
            this.url = this.url
                    + (urlPart.startsWith("/") && urlPart.length() > 1 ? urlPart.substring(1) : urlPart);
        }

        return this;
    }

    public String getUrl() {
        return this.url;
    }

}
