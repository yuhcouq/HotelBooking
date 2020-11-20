package hotelbooking.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

public class SlugUtil { 

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    // makeSlug
    public static String makeSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        nowhitespace = nowhitespace.replace("Ä‘", "d").replaceAll("Ä?", "D");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    // substringWord
    public static String substringWord(String str, int position) {
        String strNew = "";
        if (str.length() <= position) {
            strNew = str;
        } else {
            strNew = str.substring(0, position);
            if (!str.substring(position, position + 1).equals(" ") || !str.substring(position, position).equals(" ")) {
                int lastSpaceIndex = strNew.lastIndexOf(" ");
                strNew = strNew.substring(0, lastSpaceIndex).concat("...");
            }
        }
        return strNew;
    }
    public static void main(String[] args) {
		System.out.println(makeSlug("123456"));
	}
}