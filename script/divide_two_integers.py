class Solution(object):
    def divide(self, dividend, divisor):
        """
        :type dividend: int
        :type divisor: int
        :rtype: int
        """
        maxint = 2147483647
        minint = -maxint - 1

        if divisor == 0:
            return maxint

        if dividend == minint:
            if divisor == -1:
                return maxint
            elif divisor == minint:
                return 1
        elif divisor == minint:
            return 0

        isNeg = (dividend >= 0) ^ (divisor >= 0)
        dividend = abs(dividend)
        divisor = abs(divisor)

        count = 0
        while dividend >= (divisor << count):
            count += 1

        result = 0
        for c in reversed(range(count)):
            if dividend >= (divisor << c):
                result += 1 << c
                dividend -= (divisor << c)

        return -result if isNeg else result

sol = Solution()
print sol.divide(-20, -3)
