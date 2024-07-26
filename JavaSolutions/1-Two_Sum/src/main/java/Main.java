public class Main {
    public static void main(String[] args) {
        int[] nums = new int[] {2, 7, 11, 15};
        int target = 9;

        int[] answer = twoSum(nums, target);
        System.out.println(answer[0] + " " + answer[1]);
    }

    public static  int[] twoSum(int[] nums, int target) {
        int answeri = 0;
        int answerj = 0;

        for(int i = 0; i < nums.length; i++) {
            for(int j = i+1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target){
                   answeri = i;
                   answerj = j;
                }
            }
        }
        return new int[] {answeri, answerj};
    }
}
