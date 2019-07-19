package problems.leetcode.leetcode61_80;

/*Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

        In a UNIX-style file system, a period . refers to the current directory.
        Furthermore, a double period .. moves the directory up a level.
        For more information, see: Absolute path vs relative path in Linux/Unix

        Note that the returned canonical path must always begin with a slash /,
        and there must be only a single slash / between two directory names.
        The last directory name (if it exists) must not end with a trailing /.
        Also, the canonical path must be the shortest string representing the absolute path.*/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SimplifyPath_71 {
    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>(); // stack represents canonical path
        String[] pathArray = path.split("/");
        for(int i = 0; i < pathArray.length; i++){
            if(!stack.empty() && pathArray[i].equals("..")){
                // we have to go a directory up -> ignore what is on top of the stack
                stack.pop();
            }
            // all others have to be added to stack, except for ".", ".." and ""
            if(! pathArray[i].equals("") &&! pathArray[i].equals(".") && ! pathArray[i].equals("..")){
                stack.push(pathArray[i]);
            }
        }
        List<String> pathList = new ArrayList<>(stack);
        return "/" + String.join("/",pathList);

    }
}
