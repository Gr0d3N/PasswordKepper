import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Random;

public class Service {

    private URL serviceURL;
    private String username;
    private String email;
    private String serviceName;
    private Password password;

    /**
     * Constructor for the Service class
     * This constructor is used to create a new class object by passing all fields
     * @param serviceURL
     * @param username
     * @param email
     * @param serviceName
     * @param passwordLength
     * @param containSymbols
     * @param containNumbers
     */
    public Service(String serviceURL, String username, String email, String serviceName,
                   int passwordLength, boolean containSymbols, boolean containNumbers,
                   String masterPassword) throws MalformedURLException, NoSuchAlgorithmException {
        this.serviceURL = new URL(serviceURL);
        this.username = username;
        this.email = email;
        this.serviceName = serviceName;
        password = new Password(passwordLength, containSymbols, containNumbers, serviceName, masterPassword);
    }

    /**
     * Constructor for the Service class
     * This constructor is used to create a new service object by pulling the fields from a database
     * @param serviceURL
     * @param username
     * @param email
     * @param serviceName
     * @param passwordLength
     * @param containSymbols
     * @param containNumbers
     * @param lastUpdated
     */
    public Service(String serviceURL, String username, String email, String serviceName,
                   int passwordLength, boolean containSymbols, boolean containNumbers,
                   String masterPassword, Instant lastUpdated) throws MalformedURLException, NoSuchAlgorithmException {
        this.serviceURL = new URL(serviceURL);
        this.username = username;
        this.email = email;
        this.serviceName = serviceName;
        password = new Password(passwordLength, containSymbols, containNumbers, lastUpdated,
                serviceName, masterPassword);
    }

    /**
     * The getter method for the service URL
     * @return URL
     */
    public URL getServiceURL() {
        return serviceURL;
    }

    /**
     * The setter method for the service URL
     * @param serviceURL
     */
    public void SetServiceURL(URL serviceURL) {
        this.serviceURL = serviceURL;
    }

    /**
     * The getter method for the username
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * The setter method for the username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * The getter method for the email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * The setter method for the email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The getter method for the service name
     * @return String
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * The setter method for the service name
     * @param serviceName
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * The getter method for the service password
     * @return Password object
     */
    public Password getPassword() {
        return password;
    }

    /**
     * The setter method for the service password
     * @param password
     */
    public void setPassword(Password password) {
        this.password = password;
    }

    public static void main(String[] args) throws MalformedURLException, NoSuchAlgorithmException {

        /*
        Instant ins = Instant.ofEpochSecond(1517355280);
        Service test = new Service ("https://facebook.com", "minahabib",
                "mina@minahabib.com", "facebook", 24, true,
                false, "raouffalse", ins);
         */

        Service test = new Service ("https://facebook.com", "minahabib",
                "mina@minahabib.com", "facebook", 24, true,
                false, "myMasterPassword");

        Password pass = test.getPassword();
        String finalPass = pass.getCredential();
        System.out.println(finalPass);
    }
}
