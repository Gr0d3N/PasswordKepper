import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class Password {

    private String credential;
    private int passwordLength;
    private Instant lastUpdated;
    private boolean containSymbols;
    private boolean containNumbers;

    /**
     * Constructor for the Password class
     * This constructor is used to create a new password
     * @param passwordLength
     * @param containSymbols
     * @param containNumbers
     * @param serviceName
     * @param masterPassword
     */
    public Password(int passwordLength, boolean containSymbols, boolean containNumbers,
                   String serviceName, String masterPassword) throws NoSuchAlgorithmException {
        this.passwordLength = passwordLength;
        lastUpdated = Instant.now();
        this.containSymbols = containSymbols;
        this.containNumbers = containNumbers;
        this.credential = getPassword(serviceName, masterPassword, lastUpdated, containNumbers,
                containSymbols, passwordLength);
    }

    /**
     * Constructor for the password class
     * This constructor is used to pull the password fields and create a password object
     * @param passwordLength
     * @param containSymbols
     * @param containNumbers
     * @param lastUpdated
     * @param serviceName
     * @param masterPassword
     */
    public Password(int passwordLength, boolean containSymbols, boolean containNumbers,
                    Instant lastUpdated, String serviceName, String masterPassword) throws NoSuchAlgorithmException {
        this.passwordLength = passwordLength;
        this.containSymbols = containSymbols;
        this.containNumbers = containNumbers;
        this.lastUpdated = lastUpdated;
        this.credential = getPassword(serviceName, masterPassword, lastUpdated, containNumbers,
                containSymbols, passwordLength);
    }

    /**
     * The getter method for the password credential
     * @return String
     */
    public String getCredential() {
      return credential;
   }

    /**
     * The getter method for the password length
     * @return int
     */
    public int getPasswordLength() {
      return passwordLength;
   }

    /**
     * The setter method for the password length
     * @param passwordLength
     */
    public void setPasswordLength(int passwordLength) {
      this.passwordLength = passwordLength;
   }

    /**
     * The getter method for password last update date
     * @return Instant
     */
    public Instant getLastUpdated() {
      return lastUpdated;
   }

    /**
     * The setter method for the password last update date
     */
    public void setLastUpdated() {
      this.lastUpdated = Instant.now();
   }

    /**
     * The getter method for contain symbols
     * @return boolean
     */
    public boolean isContainSymbols() {
      return containSymbols;
   }

    /**
     * The setter method for contain symbols
     * @param containSymbols
     */
    public void setContainSymbols(boolean containSymbols) {
      this.containSymbols = containSymbols;
   }

    /**
     * The getter method for contain numbers
     * @return boolean
     */
    public boolean isContainNumbers() {
      return containNumbers;
   }

    /**
     * The setter method for contain numbers
     * @param containNumbers
     */
    public void setContainNumbers(boolean containNumbers) {
      this.containNumbers = containNumbers;
   }

    /**
     * The method converts a string to an array of bytes
     * Check this website for original code http://www.baeldung.com/sha-256-hashing-java
     * @param originalString
     * @return byte[] an array of bytes
     * @throws NoSuchAlgorithmException
     */
    private static byte[] stringToBytes(String originalString) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(
               originalString.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * The method converts an array of bytes to a string
     * Check this website for original code http://www.baeldung.com/sha-256-hashing-java
     * @param hash an array of bytes
     * @return String
     */
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * The method generates a credential using the stringToByte and bytesToHex methods
     * @param serviceName
     * @param masterPassword
     * @return String credential
     * @throws NoSuchAlgorithmException
     */
    public String getPassword(String serviceName, String masterPassword, Instant lastUpdated, boolean containNumbers,
                              boolean containSymbols, int passwordLength) throws NoSuchAlgorithmException {
        // Creating a hexdigest from the service name, the master password and the last updated epoch
        long lastUpdatedEpoch = lastUpdated.getEpochSecond();
        System.out.println(lastUpdatedEpoch);
        String originalString = serviceName + masterPassword + lastUpdatedEpoch;
        String hexdigest = bytesToHex(stringToBytes(originalString));


        // Convert the hexidigest to a BigInterger because converting it to Integer and using parseInt will not work
        BigInteger passBigInt= new BigInteger(hexdigest, 16);


        // Creating the alphabets, numbers and symblos selection
        Constants lowerCaseAlphabets = Constants.LOWER_CASE_ALPHABETS;
        Constants upperCaseAlphabets = Constants.UPPER_CASE_ALPHABETS;
        Constants symbols = Constants.SYMBOYLS;
        Constants numbers = Constants.NUMBERS;

        String alphabetSelection = lowerCaseAlphabets.getAlphabets() + upperCaseAlphabets.getAlphabets();

        if (containNumbers) {
            alphabetSelection += numbers.getAlphabets();
        }

        if (containSymbols) {
            alphabetSelection += symbols.getAlphabets();
        }

        // Getting the length of alphabets selection so the index lies in this range
        int alphabetSelectionLength = alphabetSelection.length();

        StringBuilder password = new StringBuilder();

        /*
         * Creating the password one letter at a time by getting and index which is the remainder of dividing the
         * passBigInt by the alphabetSelectionLength, then using that index to get one letter from alphabetSelection
         */
        while (password.length() < passwordLength) {
            BigInteger index = passBigInt.mod(BigInteger.valueOf(alphabetSelectionLength));
            passBigInt = passBigInt.divide(BigInteger.valueOf(alphabetSelectionLength));
            password.append(alphabetSelection.charAt(index.intValue()));
        }

        return password.toString();
    }
}
