package org.komamitsu.fastuuidparser;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class FastUuidParserBenchmark
{
    private int index;

    @Benchmark
    public void fromString()
    {
        int i = (index++ << 1) >>> 1;
        FastUuidParser.fromString(Uuids.UUIDS[i % Uuids.NUM_OF_UUIDS]);
    }
}
