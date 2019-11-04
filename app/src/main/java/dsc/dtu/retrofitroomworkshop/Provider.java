package dsc.dtu.retrofitroomworkshop;

import retrofit2.Retrofit;

/**
 * A utility class to hold instances of Retrofit, Network Services and some configuration
 */
public class Provider {

    /**
     * We need to tell retrofit the URL of the server to which we want to make requests.
     * This URL is almost always a constant, so it is best to store it in a separate final field.
     */
    private static final String BASE_URL = "https://api.spacexdata.com/v3/";

    /**
     * A static variable to hold our Retrofit instance. Retrofit instances must be reused as much
     * as possible, so we should try to make and use only one instance. We accomplish this by storing
     * it in a static field, so that it is initialized only once and associated to the class Provider
     * rather than an instance of this class.
     */
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build();

    /**
     * A static final field to hold an instance of the LaunchPad service created by Retrofit for us.
     *
     * Creating a service instance is an expensive operation, so we should only keep around one instance
     * of it.
     */
    private static final LaunchPadService launchPadService = retrofit.create(LaunchPadService.class);


    public static LaunchPadService getLaunchPadService() {
        return launchPadService;
    }

}
