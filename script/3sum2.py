class Solution(object):
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """

        if len(nums)<3:
            return []
        i=0
        j=1
        lists=[]
        while i<=len(nums)-2:
            j=i+1
            while j<=len(nums)-1:
                nums1 = list(nums)
                nums1.remove(nums[i])
                nums1.remove(nums[j])
                r = -nums[i]-nums[j]
                if r in nums1:
                    list1=[nums[i],nums[j],r]
                    list1.sort()
                    if list1 not in lists:
                        lists.append(list1)
                j+=1
            i+=1
        return lists

sol = Solution()
result = sol.threeSum([-1,1,-3,2,-4])
print result
