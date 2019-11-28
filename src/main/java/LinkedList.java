import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class LinkedList {
    List<RichFile> fileIndexList;

    ListNode head, tail;

    public LinkedList(List<RichFile> fileIndexList) {
        this.fileIndexList = fileIndexList;
        head = null;
        tail = null;
        loadList();
    }

    /**
     * Read Nodes In order. Move to next file in list on "any" exception
     **/
    void loadList() {
        List<RichFile> delayedLoadFragment = new ArrayList<>();
        List<RichFile> remainingFragment = new ArrayList<>();
        for (RichFile file : fileIndexList) {
            if(file.filepath.startsWith("delayedLoad")) {
                delayedLoadFragment.add(file);
            } else {
                remainingFragment.add(file);
            }
        }

        loadListHelper(remainingFragment);
        loadListHelper(delayedLoadFragment);
    }

    void loadListHelper(List<RichFile> indexFileFragment) {
        for (RichFile file : indexFileFragment) {
            try {
                ListNode node = readNode(file);
                if (head == null) {
                    head = node;
                    tail = head;
                } else {
                    tail.next = node;
                    tail = node;
                }
            } catch(Throwable e) {
                continue;
            }
        }
    }


    ListNode readNode(RichFile nodeFile) throws Throwable {
       ListNode node = new ListNode(nodeFile.read());
       return node;
    }
}
