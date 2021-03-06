# 44. Wildcard Matching

Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.  
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:  
bool isMatch(const char *s, const char *p)

Some examples:  
isMatch("aa","a") → false  
isMatch("aa","aa") → true  
isMatch("aaa","aa") → false  
isMatch("aa", "*") → true  
isMatch("aa", "a*") → true  
isMatch("ab", "?*") → true  
isMatch("aab", "c*a*b") → false  

## Analyze

网上搜到的解法有3种：

1. recursive
2. iterator
3. dynamic programming

我自己觉得使用 dynamic programming 最不费脑子，所以特别建议使用DP求解。 

dp(i)(j) = 

- dp(i-1)(j-1) if dp(i) == dp(j) ;
- dp(i-1)(j) || dp(i)(j-1) if dp(j) =='*' ;
- false ;

Example: 

- string:  abbcbb
- pattern: ab?c*b

result: true

|   |   | a | b | ? | c | * | b |
|:-:|---|:-:|:-:|:-:|:-:|---|---|
|   | T | F | F | F | F | F | F |
| a | F | T | F | F | F | F | F |
| b | F | F | T | F | F | F | F |
| b | F | F | F | T | F | F | F |
| c | F | F | F | F | T | T | F |
| b | F | F | F | F | F | T | T |
| b | F | F | F | F | F | T | T |

- string:  abbcbb
- pattern: ab?c*c

result: false

|   |   | a | b | ? | c | * | c |
|:-:|---|:-:|:-:|:-:|:-:|---|---|
|   | T | F | F | F | F | F | F |
| a | F | T | F | F | F | F | F |
| b | F | F | T | F | F | F | F |
| b | F | F | F | T | F | F | F |
| c | F | F | F | F | T | T | F |
| b | F | F | F | F | F | T | F |
| b | F | F | F | F | F | T | F |

## Reference: 

https://www.youtube.com/watch?v=3ZDZ-N0EPV0
