#!/usr/bin/env python

def compute(stack, num):
    if len(stack) > 0:
        if stack[-1] == '+':
            num = stack[-2] + num
            stack.pop()
            stack.pop()
        elif stack[-1] == '-':
            num = stack[-2] - num
            stack.pop()
            stack.pop()
    stack.append(num)

def calculate(s):
    """
    :type s: str
    :rtype: int
    """
    cur = 0
    stack = []
    while cur < len(s):
        c = s[cur]
        if c == '(' or c == '+' or c == '-':
            stack.append(c)
            cur += 1

        if c == ' ':
            cur += 1


        if c == ')':
            num = stack.pop()
            stack.pop()
            compute(stack, num)
            cur += 1

        if c >= '0' and c <= '9':
            #retrieve Number
            num = 0
            while cur < len(s) and s[cur] >= '0' and s[cur] <= '9':
                c = s[cur]
                num = num*10 + ord(c) - ord('0')
                cur += 1
            compute(stack, num)

    return stack[0]

print calculate("234455")
