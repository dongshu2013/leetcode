class Solution(object):
    def largestNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: str
        """
        strList = map(str, nums)
        strList.sort(self.comp)
        while len(strList) > 1 and strList[0] == '0' and strList[0] == '0':
            del strList[0]
        return "".join(strList)

    def comp(self, num1, num2):
        for ch1, ch2 in zip(num1 + num2, num2 + num1):
            if ch1 > ch2:
                return -1
            elif ch1 < ch2:
                return 1
        return 0

sol = Solution()
#print sol.comp("2", "2281") == 1
#print sol.comp("2", "22") == 0
#print sol.comp("34", "9") == 1
#print sol.comp("34", "349") == 1
#print sol.comp("121", "12") == 1
result = sol.largestNumber([4704,6306,9385,7536,3462,4798,5422,5529,8070,6241,9094,7846,663,6221,216,6758,8353,3650,3836,8183,3516,5909,6744,1548,5712,2281,3664,7100,6698,7321,4980,8937,3163,5784,3298,9890,1090,7605,1380,1147,1495,3699,9448,5208,9456,3846,3567,6856,2000,3575,7205,2697,5972,7471,1763,1143,1417,6038,2313,6554,9026,8107,9827,7982,9685,3905,8939,1048,282,7423,6327,2970,4453,5460,3399,9533,914,3932,192,3084,6806,273,4283,2060,5682,2,2362,4812,7032,810,2465,6511,213,2362,3021,2745,3636,6265,1518,8398])
print result
