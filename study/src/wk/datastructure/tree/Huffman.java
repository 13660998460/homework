package wk.datastructure.tree;

import static wk.util.StaticImport.sysout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Huffman {

    public static byte[] decode(byte[] source, Map<Byte, String> codes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < source.length - 1; i++) {
            sb.append(String.format("%8s", Integer.toBinaryString(source[i] & 0xFF)).replace(' ', '0'));
        }
        sb.append(String.format(Integer.toBinaryString(source[source.length - 1] & 0xFF)).replace(' ', '0'));

        Map<String, Byte> map = codes.entrySet().stream().collect(Collectors.toMap(Entry::getValue, Entry::getKey));
        List<Byte> list = new ArrayList<>();
        int start = 0;
        int end = 1;
        while (end <= sb.length()) {
            String key = sb.substring(start, end);
            if (map.containsKey(key)) {
                list.add(map.get(key));
                start = end;
            }
            end++;
        }
        byte[] result = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static byte[] encode(byte[] source) {
        StringBuilder sb = new StringBuilder();
        Map<Byte, String> huffmanCode = huffmanCode(source);
        sysout(huffmanCode);
        for (byte b : source) {
            sb.append(huffmanCode.get(b));
        }
        int len = (sb.length() + 7) / 8;
        byte[] result = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;
            if (i + 8 > sb.length()) {
                strByte = sb.substring(i);
            } else {
                strByte = sb.substring(i, i + 8);
            }
            result[index++] = (byte)Integer.parseInt(strByte, 2);
        }
        return result;
    }

    public static Map<Byte, String> huffmanCode(byte[] source) {
        Map<Byte, Integer> byteCount = new HashMap<>();
        for (int i = 0; i < source.length; i++) {
            byteCount.put(source[i], byteCount.getOrDefault(source[i], 0) + 1);
        }
        Node root = huffmanTree(byteCount);

        Map<Byte, String> codes = new HashMap<>();
        fill(root, "", codes);
        return codes;
    }

    public static Node huffmanTree(Map<Byte, Integer> byteCount) {
        LinkedList<Node> list = new LinkedList<>();
        byteCount.forEach((k, v) -> {
            list.add(new Node(k, v, null, null));
        });
        while (list.size() > 1) {
            Collections.sort(list);
            Node left = list.pollFirst();
            Node right = list.pollFirst();
            list.add(new Node(null, left.weight + right.weight, left, right));
        }
        return list.pollFirst();
    }

    private static void fill(Node node, String code, Map<Byte, String> codes) {
        if (node.left == null && node.right == null) {
            codes.put(node.element, code);
        }
        if (node.left != null) {
            fill(node.left, code.concat("0"), codes);
        }
        if (node.right != null) {
            fill(node.right, code.concat("1"), codes);
        }
    }

    static class Node extends wk.datastructure.tree.Node<Node> implements Comparable<Node> {
        Byte element;
        int weight;

        public Node(Byte element, int weight, Node left, Node right) {
            this.element = element;
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        @Override
        public int element() {
            return weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
