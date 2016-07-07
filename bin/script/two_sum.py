class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        """
        index1=0
        index2=1
        while index1<=len(nums)-2:
            k=nums[index1]
            index2=index1+1
            while index2<=len(nums)-1:
                if k+nums[index2]==target:
                    return index1+1, index2+1
                else:
                    index2+=1
            index1+=1
        """
        dic={}
        for i,j in enumerate(nums):
            i=dic[j]
        for k in dic:
            re=target-dic[k]
            if re in dic.value():
                return k,dic[re]
        return 0
