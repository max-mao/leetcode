//https://leetcode.com/problems/text-justification/
//Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
//
//        You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
//
//        Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
//
//        For the last line of text, it should be left justified and no extra space is inserted between words.
//
//        Note:
//
//        A word is defined as a character sequence consisting of non-space characters only.
//        Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
//        The input array words contains at least one word.

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < words.length) {
            int last = index +1;
            int count = words[index].length();
            while (last < words.length) {
                if (count + words[last].length() + 1 > maxWidth) {
                    break;
                }
                count = count + words[last].length() + 1;
                last++;
            }

            StringBuilder sb = new StringBuilder();
            int diff = last - index - 1;
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    sb.append(words[i] + " ");
                }
                sb.deleteCharAt(sb.length() -1);
                for (int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");
                }
            } else {
                int space = (maxWidth - count) / diff;
                int r = (maxWidth - count) % diff;
                for (int i = index; i < last; i++) {
                    sb.append(words[i]);
                    if (i < last - 1) {
                        for (int j =0; j <= (space +((i - index) < r ? 1 : 0)) ; j++) {
                            sb.append(" ");
                        }
                    }
                }
            }
            index = last;
            result.add(sb.toString());
        }

        return result;
    }
}