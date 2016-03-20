class Solution(object):
    def numberToWords(self, num):
        """
        :type num: int
        :rtype: str
        """
        if num == 0:
            return 'Zero'

        nums = {1: 'One', 2: 'Two', 3: 'Three', 4: 'Four', 5: 'Five', 6: 'Six',
                7: 'Seven', 8: 'Eight', 9: 'Nine', 10: 'Ten', 11: 'Eleven',
                12: 'Twelve', 13: 'Thirteen', 14: 'Fourteen', 15: 'Fifteen',
                16: 'Sixteen', 17: 'Seventeen', 18: 'Eighteen', 19: 'Nineteen',
                20: 'Twenty', 30: 'Thirty', 40: 'Forty', 50: 'Fifty', 60: 'Sixty',
                70: 'Seventy', 80: 'Eighty', 90: 'Ninety', 100: 'Hundred'}

        measurement = [(1000000000, 'Billion'), (1000000, 'Million'), (1000, 'Thousand')]

        result = []
        for k,v in measurement:
            count = num/k
            if count > 0:
                self.convertHundred(count, nums, result)
                result.append(v)
            num = num - count * k
        self.convertHundred(num, nums, result)
        return ' '.join(result)

    def convertHundred(self, num, nums, result):
        if num ==0:
            return

        h = num / 100
        if h in nums:
            result.append(nums[h] + ' Hundred')

        num -= 100 * h
        if num in nums:
            result.append(nums[num])
            return

        t = (num / 10) * 10
        if t in nums:
            result.append(nums[t])

        num -= t
        if num in nums:
            result.append(nums[num])

sol = Solution()
result = sol.numberToWords(1200056)
print result
