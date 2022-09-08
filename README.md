### Hexlet tests and linter status:
[![Actions Status](https://github.com/datfeelbruh/java-project-lvl2/workflows/hexlet-check/badge.svg)](https://github.com/datfeelbruh/java-project-lvl2/actions)
[![differ-test](https://github.com/datfeelbruh/java-project-lvl2/actions/workflows/differ-test.yml/badge.svg)](https://github.com/datfeelbruh/java-project-lvl2/actions/workflows/differ-test.yml)
[![checkstyle](https://github.com/datfeelbruh/java-project-lvl2/actions/workflows/checkstyle.yml/badge.svg)](https://github.com/datfeelbruh/java-project-lvl2/actions/workflows/checkstyle.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/49b2d84c54c6081b188c/maintainability)](https://codeclimate.com/github/datfeelbruh/java-project-lvl2/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/49b2d84c54c6081b188c/test_coverage)](https://codeclimate.com/github/datfeelbruh/java-project-lvl2/test_coverage)

# Вычислитель отличий.
Данный проект реализовывает консольную утилиту для генерации различий между двумя JSON или YAML файлами.
Работает с абсолютными путями до файлов.
## Установка
Склонируйте репозиторий к себе на компьютер и сбилдите проект через:
```
gradlew build
```
### Опции
Вызов справки:
```
./build/install/app/bin/app -h
```
Формат вывод диффа:
```
./build/install/app/bin/app -f
```
####Stylish (вывод по умолчанию):
```
./build/install/app/bin/app -f stylish
{
    chars1: [a, b, c]
  - chars2: [d, e, f]
  + chars2: false
  - checked: false
  + checked: true
  - default: null
  + default: [value1, value2]
  - id: 45
  + id: null
  - key1: value1
  + key2: value2
    numbers1: [1, 2, 3, 4]
  - numbers2: [2, 3, 4, 5]
  + numbers2: [22, 33, 44, 55]
  - numbers3: [3, 4, 5]
  + numbers4: [4, 5, 6]
  + obj1: {nestedKey=value, isNested=true}
  - setting1: Some value
  + setting1: Another value
  - setting2: 200
  + setting2: 300
  - setting3: true
  + setting3: none
}
```
[![asciicast](https://asciinema.org/a/Dv5qh5tonvEbiELpkz9d9UvKa.svg)](https://asciinema.org/a/Dv5qh5tonvEbiELpkz9d9UvKa)
####Plain:
```
./build/install/app/bin/app -f plain
Property 'chars2' was updated. From [complex value] to false
Property 'checked' was updated. From false to true
Property 'default' was updated. From null to [complex value]
Property 'id' was updated. From 45 to null
Property 'key1' was removed
Property 'key2' was added with value: 'value2'
Property 'numbers2' was updated. From [complex value] to [complex value]
Property 'numbers3' was removed
Property 'numbers4' was added with value: [complex value]
Property 'obj1' was added with value: [complex value]
Property 'setting1' was updated. From 'Some value' to 'Another value'
Property 'setting2' was updated. From 200 to 300
Property 'setting3' was updated. From true to 'none'
```
[![asciicast](https://asciinema.org/a/BAz6w7stjmjkSpU3kPzx9gVQq.svg)](https://asciinema.org/a/BAz6w7stjmjkSpU3kPzx9gVQq)
####Json:
```
./build/install/app/bin/app -f json
{"chars1":{"value":["a","b","c"],"newValue":null,"modification":"unchanged"},"chars2":{"value":["d","e","f"],"newValue":false,"modification":"changed"},"checked":{"value":false,"newValue":true,"modification":"changed"},"default":{"value":null,"newValue":["value1","value2"],"modification":"changed"},"id":{"value":45,"newValue":null,"modification":"changed"},"key1":{"value":"value1","newValue":null,"modification":"deleted"},"key2":{"value":null,"newValue":"value2","modification":"added"},"numbers1":{"value":[1,2,3,4],"newValue":null,"modification":"unchanged"},"numbers2":{"value":[2,3,4,5],"newValue":[22,33,44,55],"modification":"changed"},"numbers3":{"value":[3,4,5],"newValue":null,"modification":"deleted"},"numbers4":{"value":null,"newValue":[4,5,6],"modification":"added"},"obj1":{"value":null,"newValue":{"nestedKey":"value","isNested":true},"modification":"added"},"setting1":{"value":"Some value","newValue":"Another value","modification":"changed"},"setting2":{"value":200,"newValue":300,"modification":"changed"},"setting3":{"value":true,"newValue":"none","modification":"changed"}}
```
[![asciicast](https://asciinema.org/a/A1cc4xSRpD7duu2q4yUuvtimT.svg)](https://asciinema.org/a/A1cc4xSRpD7duu2q4yUuvtimT)
