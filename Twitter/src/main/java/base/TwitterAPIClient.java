package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TwitterAPIClient {

    protected String apiKey;
    protected String apiSecretKey;
    protected String accessToken;
    protected String accessTokenSecret;
    protected Properties properties;
    protected InputStream inputStream;
    protected String baseUri;

    public TwitterAPIClient() {
        this.baseUri = "https://api.twitter.com/1.1";
        this.properties = new Properties();
        this.inputStream = null;
        try {
            // path of the properties file
            this.inputStream = new FileInputStream("src/main/secret.properties");
            // load the properties file
            this.properties.load(this.inputStream);
            this.apiKey = this.properties.getProperty("apiKey");
            this.apiSecretKey = this.properties.getProperty("apiSecretKey");
            this.accessToken = this.properties.getProperty("accessToken");
            this.accessTokenSecret = this.properties.getProperty("accessTokenSecret");
        } catch (Exception e) {
            System.out.println("File not found");
            e.printStackTrace();
        } finally {
            try {
                this.inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
