class Solution(object):
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        result = set()
        if len(nums) < 3:
            return result

        nums.sort()
        twoSum = {}
        for i in range(len(nums)):
            for j in range(i):
                self.updateTwoSum(twoSum, nums[i], nums[j])
            if i >= 1 and i < length - 1 and -nums[i+1] in twoSum:
                self.createTriplet(nums[i+1], twoSum[-nums[i+1]], result)

        return map(list, result)

    def updateTwoSum(self, twoSum, ni, nj):
        s = ni + nj
        if s in twoSum:
            twoSum[s].update([(nj, ni)])
        else:
            twoSum[s] = set([(nj, ni)])

    def createTriplet(self, num, sets, result):
        for pair in sets:
            l = list(pair)
            l.append(num)
            result.update([tuple(l)])

sol = Solution()
result = sol.threeSum([0, 0, 0])
print result
