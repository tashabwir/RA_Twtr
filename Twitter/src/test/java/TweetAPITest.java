
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweets.TweetAPIClient;

import java.util.UUID;

public class TweetAPITest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }

    @Test
    public void testUserCanTweetSuccessfully() {
        // 1. User sends a tweet.
        String tweet = "Tweet " + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);

        // 2. Verify that the tweet was successfully sent.
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }

    // Write an API test where user cannot tweet the same tweet twice in a row
    @Test
    public void testUserCannotTweetTheSameTweetTwiceInARow() {
        // 1. User sends a tweet.
        String tweet = "Tweet " + UUID.randomUUID().toString(); // ABC123
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);

        // 2. Verify that the tweet was successfully sent.
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);

        // 3. User sends the same tweet again.
        response = this.tweetAPIClient.createTweet(tweet);

        // 4. Verify that the tweet was unsuccessful.
        response.statusCode(403);
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(expectedMessage, actualMessage);
    }
}
