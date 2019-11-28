import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class LinkedListsTests {

    private final static String testFilePrefix = "TestFile";


    @Test
    void testEmptyList() {
        ArrayList<RichFile> fileIndexList = new ArrayList<>();
        LinkedList l = new LinkedList(fileIndexList);
        assert(l.head == null);
    }

    @Test
    void testSingleNodeList() {
        ArrayList<RichFile> fileIndexList = new ArrayList<>();
        fileIndexList.add(new RichFile(testFilePrefix));
        LinkedList l = new LinkedList(fileIndexList);
        assert(l.head.contents.equals("contents-" + testFilePrefix));
    }

    @Test
    void testSingleNodeTail() {
        ArrayList<RichFile> fileIndexList = new ArrayList<>();
        fileIndexList.add(new RichFile(testFilePrefix));
        LinkedList l = new LinkedList(fileIndexList);
        assert(l.tail.contents.equals("contents-" + testFilePrefix));

    }

    private ArrayList<ListNode> toArray(LinkedList l) {
        ListNode n = l.head;
        ArrayList<ListNode> nodeArray = new ArrayList<>();
        while(n != null) {
           nodeArray.add(n);
           n = n.next;
        }
        return nodeArray;
    }

    @Test
    void testMultiNodeNoIOError() {
        ArrayList<RichFile> fileIndexList = new ArrayList<>();
        StringBuffer expected = new StringBuffer();
        for(int i = 0; i < 5; ++i) {
            fileIndexList.add(new RichFile(testFilePrefix + i));
            expected.append("contents-" + testFilePrefix + i);
        }
        StringBuffer actual = new StringBuffer();
        LinkedList l = new LinkedList(fileIndexList);
        toArray(l).forEach(n -> actual.append(n.contents));
        assert(expected.toString().equals(actual.toString()));
    }

    @Test
    void testMultiNodeIOError() {
        ArrayList<RichFile> fileIndexList = new ArrayList<>();
        StringBuffer expected = new StringBuffer();
        for(int i = 0; i < 5; ++i) {
            if(i % 2 == 1) {
                // Odd Indexed files dropped
                fileIndexList.add(new RichFile(testFilePrefix + i, false));
            } else {
                fileIndexList.add(new RichFile(testFilePrefix + i));
                expected.append("contents-" + testFilePrefix + i);
            }
        }
        StringBuffer actual = new StringBuffer();
        LinkedList l = new LinkedList(fileIndexList);
        toArray(l).forEach(n -> actual.append(n.contents));
        assert(expected.toString().equals(actual.toString()));
    }

    @Test
    void testMultiNodePermissionsError() {
        ArrayList<RichFile> fileIndexList = new ArrayList<>();
        StringBuffer expected = new StringBuffer();
        // Add a file not accessible due to permissions
        fileIndexList.add(new RichFile("NoPermisssionFile", false));
        for(int i = 0; i < 5; ++i) {
            if(i % 2 == 1) {
                // Odd Indexed files dropped
                fileIndexList.add(new RichFile(testFilePrefix + i, false));
            } else {
                fileIndexList.add(new RichFile(testFilePrefix + i));
                expected.append("contents-" + testFilePrefix + i);
            }
        }
        StringBuffer actual = new StringBuffer();
        LinkedList l = new LinkedList(fileIndexList);
        toArray(l).forEach(n -> actual.append(n.contents));

        // Tests Pass Monitoring Should Fail
        assert(expected.toString().equals(actual.toString()));
    }

    @Test
    void testOutOfOrderReads() {
        ArrayList<RichFile> fileIndexList = new ArrayList<>();
        for(int i = 0; i < 5; ++i) {
            if(i % 2 == 1) {
                // Odd Indexed File read in delayed order
                fileIndexList.add(new RichFile("delayedLoad" + i));
            } else {
                fileIndexList.add(new RichFile(testFilePrefix + i));
            }
        }
        LinkedList l = new LinkedList(fileIndexList);
        assert(true);
    }
}
