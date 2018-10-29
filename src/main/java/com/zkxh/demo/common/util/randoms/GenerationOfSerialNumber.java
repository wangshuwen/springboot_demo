package com.zkxh.demo.common.util.randoms;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName GenerationOfSerialNumber
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/29 9:21
 * @Vserion v0.0.1
 */

public class GenerationOfSerialNumber {

    private static GenerationOfSerialNumber serialNumberTool = null;
    private static SimpleDateFormat yMd = new SimpleDateFormat("yyyyMMdd");
    //用于自增长的标识
    private int flag;
    //用于记录日期
    private Date date;


    private GenerationOfSerialNumber() {
        if (date == null || !(yMd.format(new Date()).equals(yMd.format(date)))) {
            date = new Date();
            flag = 0;
        }
    }

    /**
     * 判断是否改变
     */
    private void checkChangeDay() {
        if (date == null || !(yMd.format(new Date()).equals(yMd.format(date)))) {
            date = new Date();
            flag = 0;
        }
    }


    /**
     * 取得PrimaryGenerater的单例实现
     *
     * @return
     */
    public static GenerationOfSerialNumber getInstance() {
        if (serialNumberTool == null) {
            synchronized (GenerationOfSerialNumber.class) {
                if (serialNumberTool == null) {
                    serialNumberTool = new GenerationOfSerialNumber();
                }
            }
        }
        return serialNumberTool;
    }


    /**
     * 生成下一个编号,前缀自动补全 0
     * params:
     * 2.places 需要补全的总位数
     */
    public synchronized Integer generaterNextNumber() {
        checkChangeDay();
//        StringBuffer stringBuffer = new StringBuffer();
//        int numPlaces = String.valueOf(flag).length();
//        //数字位数小于需要补全的总位数，需要补全0
//        if(numPlaces < places ){
//            for (int i = 0; i < places - numPlaces; i++) {
//                stringBuffer.append("0");
//            }
//            stringBuffer.append(flag);
//        }
//        else stringBuffer.append(flag);
        flag++;
        return flag;
    }

    /**
     * 避免当断网后重连或者服务器重启时导致标识flag清0，可以手动设置flag的开始点
     *
     * @param args
     */
    public void operateFlag(int position) {
        this.flag = position;
    }

    public static void main(String[] args) {
//        for (int i = 0; i <10000 ; i++) {
        GenerationOfSerialNumber.getInstance().operateFlag(2);
        System.out.println(GenerationOfSerialNumber.getInstance().generaterNextNumber());
//        }
    }
}
