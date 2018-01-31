public enum Constants {
    LOWER_CASE_ALPHABETS("abcdefghijklmnopqrstuvwxyz"),
    UPPER_CASE_ALPHABETS("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    NUMBERS("0123456789"),
    SYMBOYLS("!@#$%^&*()-_");

    private final String alphabets;

    /**
     * Constructor for the constants enum
     * @param alphabets
     */
    Constants(String alphabets) {
        this.alphabets = alphabets;
    }

    /**
     * The getter method to get the string associated with the constant
     * @return String alphabets or numbers and symbols
     */
    public String getAlphabets() {
        return this.alphabets;
    }
}
