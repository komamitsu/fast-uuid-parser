package org.komamitsu.fastuuidparser;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(Theories.class)
public class FastUuidParserTest
{
    @DataPoints
    public static final String[] INVALID_UUID_STR = {
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
    public void fromString()
    {
        for (int i = 0; i < 1000000; i++) {
            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            assertThat(FastUuidParser.fromString(uuidStr), is(uuid));
        }

        {
            String uuidStr = "00000000-0000-0000-0000-000000000000";
            assertThat(FastUuidParser.fromString(uuidStr), is(UUID.fromString(uuidStr)));
        }

        {
            String uuidStr = "ffffffff-ffff-ffff-ffff-ffffffffffff";
            assertThat(FastUuidParser.fromString(uuidStr), is(UUID.fromString(uuidStr)));
        }

        {
            String uuidStr = "FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF";
            assertThat(FastUuidParser.fromString(uuidStr), is(UUID.fromString(uuidStr)));
        }
    }

    @Theory
    @Test(expected = IllegalArgumentException.class)
    public void fromStringWithInvalidUUIDStr(String invalidUUIDStr)
    {
        FastUuidParser.fromString(invalidUUIDStr);
    }
}