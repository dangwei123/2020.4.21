给你一个整数数组 nums 和一个整数 k。

如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。

请返回这个数组中「优美子数组」的数目。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int n=nums.length;
        for(int i=0;i<n;i++){
            nums[i]&=1;
        }
        Map<Integer,Integer> map=new HashMap<>();
        int res=0;
        map.put(0,1);
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=nums[i];
            int pre=sum-k;
            if(map.containsKey(pre)){
                res+=map.get(pre);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return res;
    }
}

给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
class Solution {
    public String largestNumber(int[] nums) {
        int n=nums.length;
        String[] str=new String[n];
        for(int i=0;i<n;i++){
            str[i]=String.valueOf(nums[i]);
        }
        Arrays.sort(str,new Comparator<String>(){
            public int compare(String o1,String o2){
                return (o2+o1).compareTo(o1+o2);
            }
        });
        StringBuilder sb=new StringBuilder();
        for(String s:str){
            sb.append(s);
        }
        if(sb.charAt(0)=='0') return "0";
        return new String(sb);
    }
}





给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
class Solution {
    public int maxPoints(int[][] points) {
        int n=points.length;
        if(n<3) return n;
        int max=0;
        for(int i=0;i<n;i++){
            int same=1;
            for(int j=i+1;j<n;j++){
                int count=0;
                if(points[i][0]==points[j][0]&&points[i][1]==points[j][1]){
                    same++;
                }else{
                    count++;
                    long dx=points[i][0]-points[j][0];
                    long dy=points[i][1]-points[j][1];
                    for(int k=j+1;k<n;k++){
                        if(dy*(points[i][0]-points[k][0])==dx*(points[i][1]-points[k][1])){
                            count++;
                        }
                    }
                }
                max=Math.max(max,count+same);   
            }
            if(max>n/2) return max;
        }
        return max;
    }
}

给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

class Solution {
    public int trap(int[] height) {
        int n=height.length;
        if(n==0) return 0;
        int[] left=new int[n];
        int[] right=new int[n];
        int maxleft=height[0];
        for(int i=1;i<n;i++){
            left[i]=maxleft;
            maxleft=Math.max(maxleft,height[i]);
        }
        int maxright=height[n-1];
        for(int i=n-2;i>0;i--){
            right[i]=maxright;
            maxright=Math.max(maxright,height[i]);
        }
        int res=0;
        for(int i=1;i<n-1;i++){
            int h=Math.min(left[i],right[i]);
            if(h>height[i]){
                res+=h-height[i];
            }
        }
        return res;
    }
}

假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

注意：
总人数少于1100人。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        int n=people.length;
        Arrays.sort(people,(a,b)->a[0]==b[0]?a[1]-b[1]:b[0]-a[0]);
        List<int[]> list=new ArrayList<>();
        for(int[] p:people){
            list.add(p[1],p);
        }
        return list.toArray(new int[list.size()][]);
    }
}

