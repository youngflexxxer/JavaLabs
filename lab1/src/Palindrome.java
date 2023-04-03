public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            String s = args[i];
            if (isPalindrome(s)) {
                System.out.println(s + " - палиндром");
            } else {
                System.out.println(s + " - не палиндром");
            }
        }

    }
    public static boolean isPalindrome(String s) {
        String reverseString = reverseString(s);
        return s.equals(reverseString);
    }
    public static String reverseString(String input) {
        String output = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            output += input.charAt(i);
        }
        return output;
    }
}
