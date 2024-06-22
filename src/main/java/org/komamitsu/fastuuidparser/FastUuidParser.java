package org.komamitsu.fastuuidparser;

import java.util.Objects;
import java.util.UUID;

public final class FastUuidParser
{
    public static UUID fromString(String s)
    {
        Objects.requireNonNull(s);

        if (s.length() != 36
                || s.charAt(8) != '-'
                || s.charAt(13) != '-'
                || s.charAt(18) != '-'
                || s.charAt(23) != '-') {
            throw new IllegalArgumentException("Invalid UUID-format: " + s);
        }

        long mostSigBits = parseHexStrToLong(s, 0, 18);

        long leastSigBits = parseHexStrToLong(s, 19, 36);

        return new UUID(mostSigBits, leastSigBits);
    }

    private static long parseHexStrToLong(String s, int startPos, int endPos)
    {
        long result = 0;

        for (int cursor = startPos; cursor < endPos; cursor++) {

            final byte digit = hexToDigit(s, cursor);

            //skip signal
            if(digit == -1){
                continue;
            }

            //shift left 4 bit, make room for the latest byte
            result <<= 4;

            // Accumulating negatively avoids surprises near MAX_VALUE
            result -= digit;
        }

        return -result;
    }

    private static byte hexToDigit(String s, int position)
    {
        char c = s.charAt(position);
        if (c == '-') {
            return -1;
        }
        if (c >= '0' && c <= '9') {
            return (byte) (c - '0');
        }
        if (c >= 'a' && c <= 'f') {
            return (byte) (c - 'a' + 10);
        }
        if (c >= 'A' && c <= 'F') {
            return (byte) (c - 'A' + 10);
        }
        throw new IllegalArgumentException(String.format("Invalid UUID-format at position %d: %s", position, s));
    }
}

