# Calculator
A calculator project to apply my courses of OOP and language theory

### Purpose
- understand how to build an AST from an input stream and how to evaluate it
- create an easily maintainable project
- learn how to test every single component of a whole project

### Grammar
The grammar used for this calculator is the following:
```
EXPR = [ SUM ] eof ;
SUM = PROD { ( '+' | '-' ) PROD } ;
PROD = TERM { ( '*' | '/' ) TERM } ;
TERM = [ '-' ] ( number | '(' EXPR ')' ) ;

eof = \n
number = ([0-9]+j?|j)
```
