package org.komamitsu.fastuuidparser;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FastUuidParserTest
{
    private static final String[] INVALID_UUID_STRINGS = {
            // "00000123-0123-0123-0123-000000000123" is valid
            "00000123-0123-0123-0123-0000000001234",
            "00000123-0123-0123-0123-00000000012",
            "00000123-0123-0123-01234-000000000123",
            "00000123-0123-0123-012-000000000123",
            "00000123-0123-01234-0123-000000000123",
            "00000123-0123-012-0123-000000000123",
            "00000123-01234-0123-0123-000000000123",
            "00000123-012-0123-0123-000000000123",
            "000001234-0123-0123-0123-000000000123",
            "0000012-0123-0123-0123-000000000123",
            "00000123-0123-0123-0123-00000000012g",
            "00000123-0123-0123-0123-00000000012G",
            "00000123-0123-0123-012g-000000000123",
            "00000123-0123-0123-012G-000000000123",
            "00000123-0123-012g-0123-000000000123",
            "00000123-0123-012G-0123-000000000123",
            "00000123-012g-0123-0123-000000000123",
            "00000123-012G-0123-0123-000000000123",
            "0000012g-0123-0123-0123-000000000123",
            "0000012G-0123-0123-0123-000000000123",
            "00000123-0123-0123-0123-g00000000123",
            "00000123-0123-0123-0123-G00000000123",
            "00000123-0123-0123-g123-000000000123",
            "00000123-0123-0123-G123-000000000123",
            "00000123-0123-g123-0123-000000000123",
            "00000123-0123-G123-0123-000000000123",
            "00000123-g123-0123-0123-000000000123",
            "00000123-G123-0123-0123-000000000123",
            "g0000123-0123-0123-0123-000000000123",
            "G0000123-0123-0123-0123-000000000123"
    };

    @Test
    void fromString()
    {
        for (int i = 0; i < 1000000; i++) {
            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            if (i % 2 == 0) {
                uuidStr = uuidStr.toUpperCase();
            }
            else {
                uuidStr = uuidStr.toLowerCase();
            }
            assertEquals(uuid, FastUuidParser.fromString(uuidStr));
        }

        {
            String uuidStr = "00000000-0000-0000-0000-000000000000";
            assertEquals(UUID.fromString(uuidStr), FastUuidParser.fromString(uuidStr));
        }

        {
            String uuidStr = "ffffffff-ffff-ffff-ffff-ffffffffffff";
            assertEquals(UUID.fromString(uuidStr), FastUuidParser.fromString(uuidStr));
        }

        {
            String uuidStr = "FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF";
            assertEquals(UUID.fromString(uuidStr), FastUuidParser.fromString(uuidStr));
        }
    }

    @Test
    void fromStringWithInvalidUUIDStr()
    {
        for (String invalidUUIDStr : INVALID_UUID_STRINGS) {
            assertThrows(IllegalArgumentException.class, () -> FastUuidParser.fromString(invalidUUIDStr));
        }
    }
}