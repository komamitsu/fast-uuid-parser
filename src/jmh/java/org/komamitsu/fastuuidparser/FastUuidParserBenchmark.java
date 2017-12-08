package org.komamitsu.fastuuidparser;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class FastUuidParserBenchmark
{
    static final String UUID_STR_ZEROS = "00000000-0000-0000-0000-000000000000";
    static final String UUID_STR_LOWER_FS = "ffffffff-ffff-ffff-ffff-ffffffffffff";
    static final String UUID_STR_UPPER_FS = "FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF";

    @Benchmark
    @OperationsPerInvocation(3)
    public void fromString()
    {
        FastUuidParser.fromString(UUID_STR_ZEROS);
        FastUuidParser.fromString(UUID_STR_LOWER_FS);
        FastUuidParser.fromString(UUID_STR_UPPER_FS);
    }
}
