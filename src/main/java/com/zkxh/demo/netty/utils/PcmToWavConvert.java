package com.zkxh.demo.netty.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName PcmToWavConvert
 * @Description 音频文件 PCM 格式转换为 WAV 格式
 * @Auther lifeng
 * @DATE 2018/8/16 18:09
 * @Vserion v0.0.1
 */

public class PcmToWavConvert {

    public static void main(String[] args) throws IOException {
        PcmToWavConvert.convert("D:\\resources\\file\\voice\\65539\\20180925100431655398.pcm", "C:\\Users\\wr\\Desktop\\65539.wav");
    }

    public static void convert(String source, String target) throws IOException {

        System.out.println(source);
        System.out.println(target);
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(target);

        //计算长度
        byte[] buf = new byte[1024 * 4];
        int size = fis.read(buf);
        int PCMSize = 0;
        while (size != -1) {
            PCMSize += size;
            size = fis.read(buf);
        }
        fis.close();


        //填入参数，比特率等等。这里用的是16位单声道 8000 hz
        WaveHeader header = new WaveHeader();
        //长度字段 = 内容的大小（PCMSize) + 头部字段的大小(不包括前面4字节的标识符RIFF以及fileLength本身的4字节)
        header.fileLength = PCMSize + (44 - 8);
        header.FmtHdrLeth = 16;
        header.BitsPerSample = 16;
        header.Channels = 1;
        header.FormatTag = 0x0001;
        header.SamplesPerSec = 8000;
        header.BlockAlign = (short) (header.Channels * header.BitsPerSample / 8);
        //  header.AvgBytesPerSec = 512000;
        header.AvgBytesPerSec = header.BlockAlign * header.SamplesPerSec;
        header.DataHdrLeth = PCMSize;

        byte[] h = header.getHeader();

        System.out.println(header.toString());
        assert h.length == 44; //WAV标准，头部应该是44字节
        //write header
        fos.write(h, 0, h.length);
        //write data stream
        fis = new FileInputStream(source);
        size = fis.read(buf);
        while (size != -1) {
            fos.write(buf, 0, size);
            size = fis.read(buf);
        }
        fis.close();
        fos.close();
        System.out.println("Convert OK!");
    }

}
