class Solution(object):
    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        numToCount = {}
        for num in nums:
            if num in numToCount:
                numToCount[num] += 1
            else:
                numToCount[num] = 1

        results = []
        size = len(nums)
        self.runPermute(0, numToCount, size, results, [])
        return results

    def runPermute(self, index, numToCount, size, results, result):
        if index == size:
            results.append(list(result))
        for key in numToCount:
            if numToCount[key] > 0:
                numToCount[key] -= 1
                result.append(key)
                self.runPermute(index + 1, numToCount, size, results, result)
                numToCount[key] += 1
                result.pop()

sol = Solution()
print sol.permute([1,2,3])
