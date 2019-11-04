package dsc.dtu.retrofitroomworkshop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Step one of using Retrofit: DEFINE AN API REQUEST
 *
 * Retrofit requests are defined in an "Interface" rather than a class.
 * This is because Interfaces only contain method declarations, and nothing more.
 *
 * **Retrofit will automatically handle the hard part of defining these methods for us.**
 */
public interface LaunchPadService {

    /**
     * This method defines a GET request to the "/launchpads" endpoint of our server.
     *
     * Retrofit will parse this method as:
     * 1. Make a GET request to BASE_URL + "/launchpads"
     * 2. Expect to receive a list of LaunchPads
     *
     * So whenever we call the 'getAllLaunchPads()' method, Retrofit will make an HTTP GET request
     * to https://api.spacexdata.com/v3/launchpads and try to parse the response as a list of
     * LaunchPads.
     */
    @GET("launchpads")
    Call<List<LaunchPad>> getAllLaunchPads();

}