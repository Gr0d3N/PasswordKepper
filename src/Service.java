import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;

public class Service {

    private URL serviceURL;
    private String username;
    private String email;
    private String serviceName;
    private int passwordLength;
    private Instant lastUpdated;
    private boolean containSymbols;
    private boolean containNumbers;

    /**
     * Constructor for the Service class
     * @param serviceURL
     * @param username
     * @param email
     * @param serviceName
     * @param passwordLength
     * @param containSymbols
     * @param containNumbers
     */
    public Service(String serviceURL, String username, String email, String serviceName,
                   int passwordLength, boolean containSymbols, boolean containNumbers) throws MalformedURLException {
        this.serviceURL = new URL(serviceURL);
        this.username = username;
        this.email = email;
        this.serviceName = serviceName;
        this.passwordLength = passwordLength;
        this.lastUpdated = Instant.now();
        this.containSymbols = containSymbols;
        this.containNumbers = containNumbers;

    }

    /**
     * The getter method for the service URL
     * @return URL
     */
    public URL getServiceURL() {
        return serviceURL;
    }

    /**
     * The getter method for the user name
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * The getter method for the last updated date
     * @return Instant
     */
    public Instant getLastUpdated() {
        return lastUpdated;
    }

    /**
     * The getter method for the email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * The getter method for the service name
     * @return String
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * The getter method for the password length
     * @return int
     */
    public int getPasswordLength() {
        return passwordLength;
    }

    /**
     * The getter method for contains symbols
     * @return boolean
     */
    public boolean isContainSymbols() {
        return containSymbols;
    }

    /**
     * The getter method for contain numbers
     * @return boolean
     */
    public boolean isContainNumbers() {
        return containNumbers;
    }

    public static void main(String[] args) throws MalformedURLException {
        Service test = new Service("https://facebook.com", "minahabib", "mina@minahabib.com",
                "Facebook", 24, true, false);
        System.out.println(test.getLastUpdated().getEpochSecond());
    }

}
