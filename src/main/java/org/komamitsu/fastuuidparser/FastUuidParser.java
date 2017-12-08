package org.komamitsu.fastuuidparser;

import java.util.UUID;

public final class FastUuidParser
{
    public static UUID fromString(String s)
    {
        if (s.length() != 36
                || s.charAt(8) != '-'
                || s.charAt(13) != '-'
                || s.charAt(18) != '-'
                || s.charAt(23) != '-') {
            throw new IllegalArgumentException("Invalid UUID string: " + s);
        }

        long mostSigBits = parseHexStrToLong(s.substring(0, 8));
        mostSigBits <<= 16;
        mostSigBits |= parseHexStrToLong(s.substring(9, 13));
        mostSigBits <<= 16;
        mostSigBits |= parseHexStrToLong(s.substring(14, 18));

        long leastSigBits = parseHexStrToLong(s.substring(19, 23));
        leastSigBits <<= 48;
        leastSigBits |= parseHexStrToLong(s.substring(24, 36));

        return new UUID(mostSigBits, leastSigBits);
    }

    private static long parseHexStrToLong(String s)
    {
        long result = 0;
        int i = 0, len = s.length();
        int digit;

        while (i < len) {
            // Accumulating negatively avoids surprises near MAX_VALUE
             // digit = Character.digit(s.charAt(i++), 16);
            digit = hexdigit(s.charAt(i++));
            if (digit < 0) {
                throw new IllegalArgumentException("Invalid UUID string: " + s);
            }
            result <<= 4;
            result -= digit;
        }
        return -result;
    }

    private static int hexdigit(char c)
    {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        else if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        }
        else if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        else {
            return -1;
        }
    }
}
