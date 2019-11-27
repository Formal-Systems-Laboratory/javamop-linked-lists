import java.io.IOException;
import java.util.List;

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
        for (RichFile file : fileIndexList) {
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
