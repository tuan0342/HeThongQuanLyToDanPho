package controller;

import model.HoKhauStatic;
import model.LichSuStatic;
import model.NhanKhauStatic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SinhNgauNhien {
    public static String sinhIdHoKhau (String queQuan) {
        String[] array = queQuan.split(" ");
        String result = "";
        for (int i=0; i< array.length; i++) {
            if (40 < array[i].charAt(0) && array[i].charAt(0) < 91){
                result += array[i].charAt(0);
            }
        }
        Random generator = new Random();
        List ds = new ArrayList<>();
        int random;
        do {
            random = generator.nextInt(100000) + 10000;
            String str = result + random;
            ds = HoKhauStatic.getDsHoKhau().filtered(node -> node.timTheoHoKhau(str));
        } while (!ds.isEmpty());
        return result + random;
    }

    public static String sinhIdNhanKhau (String hoTen, String queQuan) {
        String tmp = hoTen + " " + queQuan;
        String[] array = tmp.split(" ");
        String result = "";
        for (int i=0; i< array.length; i++) {
            if (40 < array[i].charAt(0) && array[i].charAt(0) < 91){
                result += array[i].charAt(0);
            }
        }
        Random generator = new Random();
        List ds = new ArrayList<>();
        int random;
        do {
            random = generator.nextInt(100000) + 10000;
            String str = result + random;
            ds = NhanKhauStatic.getDsNhanKhau().filtered(node -> node.timTheoNhanKhau(str));
        } while (!ds.isEmpty());
        return result+random;
    }

    public static String sinhIdLichSu (String thaoTac) {
        String tmp = "Lịch Sử " + thaoTac;
        String[] array = tmp.split(" ");
        String result = "";
        for (int i=0; i< array.length; i++) {
            if (40 < array[i].charAt(0) && array[i].charAt(0) < 91){
                result += array[i].charAt(0);
            }
        }
        Random generator = new Random();
        List ds = new ArrayList<>();
        int random;
        do {
            random = generator.nextInt(100000) + 10000;
            String str = result + random;
            ds = LichSuStatic.getDsLichSu().filtered(node -> node.timTheoIdLichSu(str));
        } while (!ds.isEmpty());
        return result+random;
    }
}
