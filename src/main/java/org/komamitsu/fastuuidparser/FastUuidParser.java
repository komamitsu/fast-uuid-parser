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

            final int digit = hexToDigit(s, cursor);

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

    private static int hexToDigit(String s, int position)
    {
        int b = s.charAt(position);
        if (b == '-') {
            return -1;
        }
        if (b >= '0' && b <= '9') {
            return b - '0';
        }

        int indexFromA = (b - 'A') % ('a' - 'A');
        if (indexFromA <= 5) {
            return indexFromA + 10;
        }
        throw new IllegalArgumentException(String.format("Invalid UUID-format at position %d: %s", position, s));
    }
}

