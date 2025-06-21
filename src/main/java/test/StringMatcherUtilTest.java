package test;

import org.cacanhdaden.quanlythuoc.util.StringMatcherUtil;

public class StringMatcherUtilTest {
    public static void main(String[] args) {
        // Test cases for isEmail method
        System.out.println(StringMatcherUtil.isEmail("")); // false
        System.out.println(StringMatcherUtil.isEmail("abcd@")); // false
        System.out.println(StringMatcherUtil.isEmail("abcd@efg")); // false
        System.out.println(StringMatcherUtil.isEmail("abcd@ef.uit.edu.vn")); // true
    }
}
