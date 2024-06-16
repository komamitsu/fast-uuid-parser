package org.komamitsu.fastuuidparser;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

public final class FastUuidParser
{
    public static UUID fromString(String s)
    {
        Objects.requireNonNull(s);
        byte[] bytes = s.getBytes(StandardCharsets.US_ASCII);

        if (bytes.length != 36
                || bytes[8] != '-'
                || bytes[13] != '-'
                || bytes[18] != '-'
                || bytes[23] != '-') {
            throw new IllegalArgumentException("Invalid UUID-format: " + s);
        }

        long mostSigBits = parseHexStrBytesToLong(bytes, 0, 18);

        long leastSigBits = parseHexStrBytesToLong(bytes, 19, 36);

        return new UUID(mostSigBits, leastSigBits);
    }

    private static long parseHexStrBytesToLong(byte[] bytes, int startPos, int endPos)
    {
        long result = 0;

        for (int cursor = startPos; cursor < endPos; cursor++) {

            final byte digit = hexStrBytesToDigit(bytes, cursor);

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

    private static byte hexStrBytesToDigit(byte[] bytes, int position)
    {
        switch (bytes[position]) {
            case '-': return -1;
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case 'a':
            case 'A': return 10;
            case 'b':
            case 'B': return 11;
            case 'c':
            case 'C': return 12;
            case 'd':
            case 'D': return 13;
            case 'e':
            case 'E': return 14;
            case 'f':
            case 'F': return 15;
            default:  throw new IllegalArgumentException(String.format("Invalid UUID-format at position %d: %s", position, new String(bytes)));
        }
    }
}

