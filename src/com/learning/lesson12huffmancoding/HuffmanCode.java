package com.learning.lesson12huffmancoding;

import com.common.utils.ObjectUtil;
import com.learning.lesson11huffmantree.HuffmanTree;
import com.learning.lesson11huffmantree.HuffmanTreeNode;

import java.io.*;
import java.util.*;

/**
 * 哈夫曼编码
 *
 * @author Zongheng Ma
 * @date 2020/7/28
 */
public class HuffmanCode {

    public static void main(String[] args) {

        /*
         * 字符串的压缩
         */
        String content = "hey,do you like java as much as i like";
        System.out.printf("原始长度为 %d\n", content.length());
        byte[] bytes = content.getBytes();
        // 压缩
        byte[] huffmanCodes = huffmanCompress(bytes);
        // 解压缩
        byte[] decodeResult = decompress(codeTable, huffmanCodes);
        System.out.println(new String(decodeResult));

        /*
         * 文件的压缩
         */
        String src = "E:\\src.jpg";
        String zip = "E:\\des.zip";
        String unZip = "E:\\unZip.jpg";
        // 文件压缩
        long t1 = System.currentTimeMillis();
        zipFile(src, zip);
        long t2 = System.currentTimeMillis();
        // 文件解压
        unZipFile(zip, unZip);
        long t3 = System.currentTimeMillis();
        System.out.printf("压缩耗时 %s ms，解压耗时 %s ms\n", t2 - t1, t3 - t2);
    }


    /**
     * 压缩文件
     *
     * @param src 源文件路径
     * @param des 压缩文件路径
     */
    public static void zipFile(String src, String des) {
        // 创建输入流
        FileInputStream fis = null;
        // 创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            // 读取文件至byte数组
            fis = new FileInputStream(src);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            System.out.printf("原始长度为 %s\n", bytes.length);
            // 压缩
            byte[] huffmanBytes = huffmanCompress(bytes);

            // 输出压缩文件
            os = new FileOutputStream(des);
            oos = new ObjectOutputStream(os);
            // 以对象流的方式写入哈夫曼编码，是为了以后解压时
            oos.writeObject(huffmanBytes);
            // 写入编码表
            oos.writeObject(codeTable);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                assert fis != null;
                fis.close();
                assert os != null;
                os.close();
                assert oos != null;
                oos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * 解压缩文件
     *
     * @param zipFile 待解压文件路径
     * @param desFile 解压文件路径
     */
    public static void unZipFile(String zipFile, String desFile) {

        // 定义输入流
        InputStream inputStream = null;
        // 定义对象输入流
        ObjectInputStream objectInputStream = null;
        // 定义文件输出流
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(zipFile);
            objectInputStream = new ObjectInputStream(inputStream);
            // 获取压缩后的byte数组
            byte[] huffmanBytes = (byte[]) objectInputStream.readObject();
            codeTable = ObjectUtil.castObject(objectInputStream.readObject());
            byte[] bytes = decompress(codeTable, huffmanBytes);
            outputStream = new FileOutputStream(desFile);
            outputStream.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                assert outputStream != null;
                outputStream.close();
                objectInputStream.close();
                inputStream.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * 编码表
     */
    private static Map<Byte, String> codeTable;

    /**
     * 存储可能不足一个字节的字符
     */
    private static String lastByte = "";

    /**
     * 将原始byte数组进行哈夫曼压缩
     *
     * @param bytes 原始数组
     * @return 经过哈夫曼压缩的byte数组
     */
    public static byte[] huffmanCompress(byte[] bytes) {
        // step1 根据序列构建哈夫曼树
        List<HuffmanTreeNode> nodeList = getNodes(bytes);
        HuffmanTreeNode root = HuffmanTree.createHuffTree(nodeList);
        // step2 获取哈夫曼编码表
        codeTable = HuffmanTree.getCodingTable(root);
        // step3 根据编码表压缩byte数组
        return transfer(bytes, codeTable);
    }


    /**
     * 将byte数组转为结点的list
     *
     * @param bytes byte数组
     * @return 结点数组
     */
    private static List<HuffmanTreeNode> getNodes(byte[] bytes) {
        // 字符与出现次数（即哈夫曼树的权）的映射
        Map<Byte, Integer> map = new HashMap<>(32);
        // 遍历bytes，将字符与其出现次数放入map
        for (byte b : bytes) {
            map.merge(b, 1, Integer::sum);
        }

        // 把map中的键值对转换成node加入到list中
        List<HuffmanTreeNode> nodeList = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodeList.add(new HuffmanTreeNode(entry.getKey(), entry.getValue()));
        }
        return nodeList;
    }


    /**
     * 将原始byte数组根据编码表转换为哈夫曼编码
     *
     * @param codeTable 编码表
     * @param origin    原始数组
     * @return 哈夫曼编码后的byte数组
     */
    private static byte[] transfer(byte[] origin, Map<Byte, String> codeTable) {
        // 将原数组转换成Huffman编码字符
        StringBuilder huffmanStr = new StringBuilder();
        for (byte item : origin) {
            huffmanStr.append(codeTable.get(item));
        }
        // 压缩数组的长度
        int len = huffmanStr.length() / 8;
        if (huffmanStr.length() % 8 != 0) {
            // 编码长度不能被8整除，保存多余的不足8位的部分
            lastByte = huffmanStr.substring(len * 8);
        }
        // 压缩存储的byte数组
        byte[] huffmanCode = new byte[len];
        for (int i = 0; i < huffmanCode.length; i++) {
            huffmanCode[i] = (byte) Integer.parseInt(huffmanStr.substring(i * 8, i * 8 + 8), 2);
        }
        System.out.printf("压缩后的长度为 %d\n", "".equals(lastByte) ? len : len + 1);
        return huffmanCode;
    }


    /**
     * 封装解压缩的方法
     *
     * @param bytes 经过压缩的byte数组
     * @return 解压后的byte数组
     */
    public static byte[] decompress(byte[] bytes) {
        return decompress(codeTable, bytes);
    }


    /**
     * 将一个byte转为二进制字符串
     *
     * @param b 一个byte
     * @return b对应的二进制字符串（补码）
     */
    private static String byteToString(byte b) {
        // 使用int暂存b
        int temp = b;
        // 补高位，和256（1 0000 0000）进行按位或
        temp |= 256;
        String str = Integer.toBinaryString(temp);
        return str.substring(str.length() - 8);
    }


    /**
     * 对压缩数据进行解码
     *
     * @param codeTable   编码表
     * @param huffmanCode 压缩的数组
     * @return 原字符串对应的byte数组
     */
    private static byte[] decompress(Map<Byte, String> codeTable, byte[] huffmanCode) {
        // 先得到压缩数组对应的编码字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte value : huffmanCode) {
            stringBuilder.append(byteToString(value));
        }
        stringBuilder.append(lastByte);
        // 把字符串按编码表进行解码(先将编码表反转，根据编码找原数值)
        Map<String, Byte> map = new HashMap<>(32);
        for (Map.Entry<Byte, String> entry : codeTable.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 扫描StringBuilder
        List<Byte> byteList = new ArrayList<>();
        /*
         * 此处i借助count这个增量来移动
         * 若在每一轮循环时i++会导致漏扫描字符
         */
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            Byte b;
            while (true) {
                String str = stringBuilder.substring(i, i + count);
                b = map.get(str);
                if (b != null) {
                    byteList.add(b);
                    i += count;
                    break;
                }
                count++;
            }
        }
        // 将list赋值给array
        byte[] bytes = new byte[byteList.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = byteList.get(i);
        }
        return bytes;
    }
}
