/**
 * A library of string functions.
 */
public class MyString {
    public static void main(String args[]) {
        String hello = "hello";
        System.out.println(countChar(hello, 'h'));
        System.out.println(countChar(hello, 'l'));
        System.out.println(countChar(hello, 'z'));
        System.out.println(spacedString(hello));
        System.out.println("\nTesting subsetOf:");
        System.out.println("sap in space -> " + MyString.subsetOf("sap", "space") + " (expected: true)");
        System.out.println("spa in space -> " + MyString.subsetOf("spa", "space") + " (expected: true)");
        System.out.println("pass in space -> " + MyString.subsetOf("pass", "space") + " (expected: false)");
        System.out.println("c in space -> " + MyString.subsetOf("c", "space") + " (expected: true)");
        System.out.println("empty string in anything -> " + MyString.subsetOf("", "anything") + " (expected: true)"); //// Put
                                                                                                                      //// your
                                                                                                                      //// other
                                                                                                                      //// tests
                                                                                                                      //// here.
    }

    /**
     * Returns the number of times the given character appears in the given string.
     * Example: countChar("Center",'e') returns 2 and countChar("Center",'c')
     * returns 0.
     * 
     * @param str - a string
     * @param c   - a character
     * @return the number of times c appears in str
     */
    public static int countChar(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns true if str1 is a subset string str2, false otherwise
     * Examples:
     * subsetOf("sap","space") returns true
     * subsetOf("spa","space") returns true
     * subsetOf("pass","space") returns false
     * subsetOf("c","space") returns true
     *
     * @param str1 - a string
     * @param str2 - a string
     * @return true is str1 is a subset of str2, false otherwise
     */
    public static boolean subsetOf(String str1, String str2) {
        String temp = str2;
        Boolean con = true;
        for (int i = 0; i < str1.length(); i++) {
            char ch = str1.charAt(i);
            for (int j = 0; j < temp.length(); j++) {
                if (ch == temp.charAt(j)) {
                    con = false;
                    temp = remove(temp, ch);
                    j = temp.length();
                }
            }
            if (con) {
                return false;
            }
            con = true;
        }

        return true;
    }

    /**
     * Returns a string which is the same as the given string, with a space
     * character inserted after each character in the given string, except
     * for the last character.
     * Example: spacedString("silent") returns "s i l e n t"
     * 
     * @param str - a string
     * @return a string consisting of the characters of str, separated by spaces.
     */
    public static String spacedString(String str) {
        if (str == "") {
            return str;
        }
        String temp = "";
        for (int i = 0; i < str.length() - 1; i++) {
            temp += str.charAt(i) + " ";
        }
        temp += str.charAt(str.length() - 1);
        return temp;
    }

    /**
     * Returns a string of n lowercase letters, selected randomly from
     * the English alphabet 'a', 'b', 'c', ..., 'z'. Note that the same
     * letter can be selected more than once.
     * 
     * Example: randomStringOfLetters(3) can return "zoo"
     * 
     * @param n - the number of letter to select
     * @return a randomly generated string, consisting of 'n' lowercase letters
     */
    public static String randomStringOfLetters(int n) {
        String temp = "";
        for (int i = 0; i < n; i++) {
            int rnd = 97 + (int) (26 * Math.random());
            temp += (char) rnd;
        }
        return temp;
    }

    /**
     * Returns a string consisting of the string str1, minus all the characters in
     * the
     * string str2. Assumes (without checking) that str2 is a subset of str1.
     * Example: remove("meet","committee") returns "comit"
     * 
     * @param str1 - a string
     * @param str2 - a string
     * @return a string consisting of str1 minus all the characters of str2
     */
    public static String remove(String str1, String str2) {
        String temp = "";
        if (str2 == "") {
            return str1;
        }
        if (str2.length() == 0) {
            return remove(str1, str2.charAt(0));
        }
        boolean[] ans = new boolean[str1.length()];
        for (int i = 0; i < str2.length(); i++) {
            char ch = str2.charAt(i);
            for (int j = 0; j < str1.length(); j++) {
                if (ch == str1.charAt(j) && ans[j] == false) {
                    ans[j] = true;
                    j = str1.length();
                }
            }
        }
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == false) {
                temp += str1.charAt(i);
            }
        }
        return temp;
    }

    /**
     * Returns a string consisting of the string str1, minus ch.  
     * Examples:
     * remove("topical","l") returns "topica"
     * remove("tomato","t") returns "omato"
     * remove("top","o") returns "tp"
     * 
     * 
     * @param str1 - a string
     * @param ch - a char
     * @return a string consisting of str1 minus ch
     */
    public static String remove(String str1, char ch) {
        String temp = "";
        Boolean flag = true;
        for (int i = 0; i < str1.length(); i++) {
            if (ch == str1.charAt(i) && flag) {
                flag = false;
            } else {
                temp += str1.charAt(i);
            }
        }
        return temp;
    }

    /**
     * Returns a string consisting of the given string, with the given
     * character inserted randomly somewhere in the string.
     * For example, insertRandomly("s","cat") can return "scat", or "csat", or
     * "cast", or "cats".
     * 
     * @param ch  - a character
     * @param str - a string
     * @return a string consisting of str with ch inserted somewhere
     */
    public static String insertRandomly(char ch, String str) {
        // Generate a random index between 0 and str.length()
        int randomIndex = (int) (Math.random() * (str.length() + 1));
        // Insert the character at the random index
        String result = str.substring(0, randomIndex) + ch + str.substring(randomIndex);
        return result;
    }
}
