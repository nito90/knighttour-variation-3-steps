# Knight's tour custom variation

## Overview

The application is a custom variation of the known knight's tour problem. The solution of this code is based in the Backtracking but until a specified depth, which is equals with 3 in our case.

## Details

1. App asks for valid data like size of board, start and end point's coordinations.
2. In order to choose the point and the correct coordinations the app will show an empty board.
3. It is customized to execute with Knight's move however we can extend it with all the other moves which are provided by chess.
4. Maximum moves = 3.
5. Print's the shortesh path OR one of the shortest paths OR error message if no solution was found.

## Prerequisites
- JDK 11 
- Gradle 6.0.1

## Application build and run
```
./gradlew clean build
gradle run -q --console=plain
```