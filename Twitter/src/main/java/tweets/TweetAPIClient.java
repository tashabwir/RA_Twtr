package tweets;

import base.TwitterAPIClient;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

/**
 * Tweet client class that consists of all the different APIs of Twitter's Tweets
 * (ex: Post, retrieve and engage with tweets...)
 */
public class TweetAPIClient extends TwitterAPIClient{
    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    private final String GET_USER_TWEETS_ENDPOINT = "/statuses/user_timeline.json";

    /**
     * Retrieves all the user's tweets from their timeline
     *
     * @return a validatable response.
     */
    public ValidatableResponse getUserTimelineTweets() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUri + this.GET_USER_TWEETS_ENDPOINT)
                .then();
    }

    /**
     * Creates a tweet from the user's twitter.
     *
     * @param tweet The actual tweet to be tweeted.
     * @return a validatable response.
     */
    public ValidatableResponse createTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUri + this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    /**
     * Deletes a tweet from the user's twitter.
     *
     * @param tweetId The tweet id to be used for deletion.
     * @return a validatable response.
     */
    public ValidatableResponse deleteTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUri + this.DELETE_TWEET_ENDPOINT)
                .then();
    }
}
