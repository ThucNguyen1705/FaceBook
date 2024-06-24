package model;

import java.util.*;

public class SocialMediaNetworkManagement {
    private ArrayList<Node> listUser;
    private final Graph graphFriend;
    private final Graph graphRequest;
    private int id;
    private Node currentUser;
    private final String fileNameListUser;
    private final String fileNameFriend;
    private final String fileNameRequest;
    private final String fileNameUsername;
    private ArrayList<ArrayList<String>> listUsername;

    public SocialMediaNetworkManagement() {
        this.listUser = new ArrayList<>();
        this.graphFriend = new Graph();
        this.graphRequest = new Graph();
        this.id = 0;
        this.currentUser = null;
        this.fileNameListUser = "src\\data\\UserData.txt";
        this.fileNameFriend = "src\\data\\FriendData.txt";
        this.fileNameRequest = "src\\data\\RequestData.txt";
        this.fileNameUsername = "src\\data\\UsernameData.txt";
        this.listUsername = new ArrayList<>();
    }

    public String getFileNameListUser() {
        return fileNameListUser;
    }

    public String getFileNameFriend() {
        return fileNameFriend;
    }

    public String getFileNameRequest() {
        return fileNameRequest;
    }

    public String getFileNameUsername() {
        return fileNameUsername;
    }

    public Graph getGraphFriend() {
        return graphFriend;
    }

    public Graph getGraphRequest() {
        return graphRequest;
    }

    public ArrayList<ArrayList<String>> getListUsername() {
        return listUsername;
    }

    public void setListUsername(ArrayList<ArrayList<String>> listUsername) {
        this.listUsername = listUsername;
    }

    public Node getCurrentUser() {
        return currentUser;
    }

    public ArrayList<Node> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<Node> listUser) {
        this.listUser = listUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void createUser(Node user) {
        user.setId(id);
        this.graphFriend.addNode(user);
        this.graphRequest.addNode(user);
        this.listUser.add(user);
        this.id++;
        this.addUsername(user.getData(), user.getId());
    }

    public void addUsername(String username, int id) {
        String[] str = username.split(" ");
        loop:
        for (int i = 0; i < str.length; i++) {
            String element = str[i] + " - " + id;
            if (this.listUsername.size() == i) {
                this.listUsername.add(new ArrayList<>());
                this.listUsername.get(i).add(element);
                continue;
            }
            for (int j = 0; j < this.listUsername.get(i).size(); j++) {
                if (this.listUsername.get(i).get(j).compareTo(element) > 0) {
                    this.listUsername.get(i).add(j, element);
                    continue loop;
                }
            }
            this.listUsername.get(i).add(element);
        }
    }

    public int binarySearch(ArrayList<String> list, String value) {
        int start = 0;
        int end = list.size() - 1;
        while (start <= end) {
            int middle = start + (end - start) / 2;
            if (list.get(middle).contains(value)) {
                return middle;
            } else if (list.get(middle).compareTo(value) > 0) {
                end = middle - 1;
            } else if (list.get(middle).compareTo(value) < 0) {
                start = middle + 1;
            }
        }
        return -1;
    }

    public Set<String> getIdSearchFriend(String name) {
        ArrayList<Integer> indexs = new ArrayList<>();
        for (ArrayList<String> list : this.listUsername) {
            indexs.add(this.binarySearch(list, name));
        }

        Set<String> set = new HashSet<>();
        for (int i = 0; i < indexs.size(); i++) {
            if (indexs.get(i) != (-1)) {
                set.add(this.listUsername.get(i).get(indexs.get(i)));
                int left = indexs.get(i) - 1;
                int right = indexs.get(i) + 1;
                while (left >= 0) {
                    if (this.listUsername.get(i).get(left).contains(name)) {
                        set.add(this.listUsername.get(i).get(left));
                        left--;
                    } else {
                        break;
                    }
                }
                while (right < this.listUsername.get(i).size()) {
                    if (this.listUsername.get(i).get(right).contains(name)) {
                        set.add(this.listUsername.get(i).get(right));
                        right++;
                    } else {
                        break;
                    }
                }
            }
        }
        return set;
    }

    public void choiceUser(int id) {
        this.currentUser = this.listUser.get(id);
    }

    public void addFriend(int idDst) {
        this.graphFriend.addEdge(this.currentUser.getId(), idDst);
        this.graphFriend.addEdge(idDst, this.currentUser.getId());
    }

    public List<Node> getListFriend() {
        List<Node> listFriend = this.graphFriend.getAlist().get(this.currentUser.getId());
        return listFriend.subList(1, listFriend.size());
    }

    public void addRequest(int id) {
        if (this.graphFriend.checkEdge(id, this.currentUser.getId()) ||
                this.graphRequest.checkEdge(id, this.currentUser.getId()) ||
                this.graphRequest.checkEdge(this.currentUser.getId(), id)) {
            return;
        }
        this.graphRequest.addEdge(id, this.currentUser.getId());
    }

    public boolean isFriend(int id) {
        return this.graphFriend.checkEdge(id, this.currentUser.getId());
    }

    public List<Node> getListRequest() {
        List<Node> request = this.graphRequest.getAlist().get(this.currentUser.getId());
        request = request.subList(1, request.size());
        Collections.reverse(request);
        return request;
    }

    public void removeRequest(int id) {
        this.graphRequest.removeEdge(this.currentUser.getId(), id);
    }

    public void addContent(Content content) {
        this.currentUser.getContentList().add(content);
//        System.out.println(this.currentUser.getContentList());
    }

    public void removeFriend(int idDst) {
        this.graphFriend.removeEdge(this.currentUser.getId(), idDst);
        this.graphFriend.removeEdge(idDst, this.currentUser.getId());
    }

    public List<Content> getListContent() {
        List<List<Content>> contentList = new ArrayList<>();
        contentList.add(this.currentUser.getContentList());

        List<Node> listFriend = this.getListFriend();
        for (Node node : listFriend) {
            contentList.add(node.getContentList());
        }

        return this.mergeKLists(contentList, contentList.size() - 1);
    }

    public List<Content> sortedMerge(List<Content> l1, List<Content> l2) {
        List<Content> result = new ArrayList<>();
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < l1.size() && idx2 < l2.size()) {
            if (l1.get(idx1).getValueTimeStamp() <= l2.get(idx2).getValueTimeStamp()) {
                result.add(l1.get(idx1));
                idx1++;
            } else {
                result.add(l2.get(idx2));
                idx2++;
            }
        }
        while (idx1 < l1.size()) {
            result.add(l1.get(idx1));
            idx1++;
        }
        while (idx2 < l2.size()) {
            result.add(l2.get(idx2));
            idx2++;
        }

        return result;
    }

    public List<Content> mergeKLists(List<List<Content>> lists, int last) {
        while (last != 0) {
            int i = 0;
            int j = last;
            while (i < j) {
                lists.set(i, this.sortedMerge(lists.get(i), lists.get(j)));
                i++;
                j--;
                if (i >= j) {
                    last = j;
                }
            }
        }
        return lists.get(0);
    }


    public List<Node> getMutualFriends(int idUser1, int idUser2) {
        List<Node> friendsUser1 = this.graphFriend.getAlist().get(idUser1);
        List<Node> friendsUser2 = this.graphFriend.getAlist().get(idUser2);
        List<Node> mutualFriend = new ArrayList<>(friendsUser1);
        mutualFriend.retainAll(friendsUser2);
        mutualFriend.remove(this.listUser.get(idUser1));
        mutualFriend.remove(this.listUser.get(idUser2));
        System.out.println("mutual friend " + idUser1 + " " + idUser2 + " " + mutualFriend);
        return mutualFriend;
    }

    private static class priorityNode implements Comparable<priorityNode> {
        Node user;
        int priority;

        public priorityNode(Node user, int priority) {
            this.user = user;
            this.priority = priority;
        }


        @Override
        public int compareTo(priorityNode o) {
            return o.priority - this.priority;
        }
    }

    public ArrayList<Node> getSuggestFriend(int src, int quantity) {
        Queue<Node> queue = this.graphFriend.breadthFirstSearch(src, 2);
        System.out.println(queue);

        Queue<priorityNode> priorityNodeQueue = new PriorityQueue<>();
        while (!queue.isEmpty()) {
            Node user = queue.poll();
            priorityNodeQueue.offer(new priorityNode(user, getMutualFriends(src, user.getId()).size()));
        }

        int count = 0;
        ArrayList<Node> list = new ArrayList<>();
        while (!priorityNodeQueue.isEmpty() && count < quantity) {
            list.add(priorityNodeQueue.poll().user);
            count++;
        }
        System.out.println(list);
        return list;
    }

    public ArrayList<Node> getSearchFriend(String name, int quantity) {
        int[] distance = this.graphFriend.shortestPath(this.currentUser.getId());

        Queue<priorityNode> priorityNodeQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (String s : this.getIdSearchFriend(name)) {
            int id = Integer.parseInt(s.split(" - ")[1]);
            priorityNodeQueue.offer(new priorityNode(
                    this.listUser.get(id), distance[id]));
        }

        int count = 0;
        ArrayList<Node> listSearch = new ArrayList<>();
        while (!priorityNodeQueue.isEmpty() && count < quantity) {
            listSearch.add(priorityNodeQueue.poll().user);
            count++;
        }

        return listSearch;
    }
}
