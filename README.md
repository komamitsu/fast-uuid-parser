# Fast-UUID-Parser
[<img src="https://travis-ci.org/komamitsu/fast-uuid-parser.svg?branch=master"/>](https://travis-ci.org/komamitsu/fast-uuid-parser) [![Coverage Status](https://coveralls.io/repos/komamitsu/fast-uuid-parser/badge.svg?branch=master&service=github)](https://coveralls.io/github/komamitsu/fast-uuid-parser?branch=master)

A UUID string parser that is 5.5 times faster than `java.util.UUID.fromString()`.

```
Benchmark                            Mode  Cnt      Score    Error   Units
FastUuidParserBenchmark.fromString  thrpt   20  13001.895 ± 12.659  ops/ms
NormalUUIDBenchmark.fromString      thrpt   20   2363.129 ± 31.584  ops/ms
```

## Usage

You can use `FastUuidParser.fromString()` like `java.util.UUID.fromString()`.

```java
UUID uuid = FastUuidParser.fromString("81c578f9-5e6d-4d78-8729-f32acefc144b");
```

