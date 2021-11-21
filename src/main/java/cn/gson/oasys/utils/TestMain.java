package cn.gson.oasys.utils;

import org.junit.Test;

public class TestMain {
    @Test
    public void test1() throws Exception {
        String text = "毛毛杜";
//        （"d:/1234.jpg二维码logo图片地址）     （d:/二维码生成后图片地址）
        QRCodeUtil.encode(text, "d:\\QRCode\\123.jpg", "d:\\QRCode\\picture", true);
    }
}
