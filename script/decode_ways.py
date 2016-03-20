class Solution(object):
    def numDecodings(self, s):
        """
        :type s: str
        :rtype: int
        """
        if len(s) == 0 or s[0] == '0':
            return 0
        count = [1, 1]
        for i in range(1, len(s)):
            newCount = 0
            single = self.isLetter(s[i])
            double = self.isLetter(s[i-1:i+1])
            if not single and not double:
                return 0
            else:
                if single:
                    newCount += count[-1]
                if double:
                    newCount += count[-2]
            count.append(newCount)
        return count[-1]

    def isLetter(self, num):
        return num[0] != '0' and int(num) >= 1 and int(num) <= 26

sol = Solution()
result = sol.numDecodings("101")
print result
