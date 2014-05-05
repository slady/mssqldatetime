# mssqldatetime

Java utility to convert MSSQL insert script Date and DateTime hex format to readable values.

# Input

the input.sql file:

```SQL
INSERT INTO XXX (Date1, Date2, DateTime1, DateTime2) VALUES (CAST(0x681B0B00 AS Date), CAST(0x963D0B00 AS Date), CAST(0x0000A31500000000 AS DateTime), CAST(0x0000A31500000000 AS DateTime))
```

# Usage

run the command:

```sh
    java -jar mssqldateformatconvert.jar < input.sql > output.sql
```

# Output

the output.sql file:

```SQL
INSERT INTO XXX (Date1, Date2, DateTime1, DateTime2)" VALUES ('1993-12-16', '2017-11-30', '2014-04-22 00:00:00.0', '2014-04-22 00:00:00.0')
```
