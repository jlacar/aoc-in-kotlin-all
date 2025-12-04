# Day X - (title)

## Notes

```text
numeric keypad:
 7 8 9
 4 5 6
 1 2 3
 . 0 A
 
 directional keypad: 
 . ^ A
 < v >
```

| keypad  | code1              | code2        | code3    | code 4   |
|---------|--------------------|--------------|----------|----------|
| you     | <vA<AA>>^AvAA<^A>A | v<<A>>^AvA^A | 9        | A        |
| robot 3 | v<<A>>^A           | <A>A         | vA<^AA>A | <vAAA>^A |
| robot 2 | <A                 | ^A           | >^^A     | vvvA     |
| robot 1 | 0                  | 2            | 9        | A        |
  
## Design

numericKeypad = Keypad(numbers)
                """
                789
                456
                123
                .0A
                """);

area3Keypad = Keypad(area2Keypad)
area2Keypad(area1Keypad)
area1Keypad(numericKeypad)
numericKeypad.keyIn(0)

area3Keypad.buttonPressesFor('029A')

## Discoveries