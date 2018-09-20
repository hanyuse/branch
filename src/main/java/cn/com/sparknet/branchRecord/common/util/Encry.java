package cn.com.sparknet.branchRecord.common.util;

/**
 * 加密
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: sparknet</p>
 * @author cuijie
 * @version 1.0
 */
public class Encry {

  private int[] BitIP = {
      57, 49, 41, 33, 25, 17, 9, 1,
      59, 51, 43, 35, 27, 19, 11, 3,
      61, 53, 45, 37, 29, 21, 13, 5,
      63, 55, 47, 39, 31, 23, 15, 7,
      56, 48, 40, 32, 24, 16, 8, 0,
      58, 50, 42, 34, 26, 18, 10, 2,
      60, 52, 44, 36, 28, 20, 12, 4,
      62, 54, 46, 38, 30, 22, 14, 6};
  private int[] BitCP = {
      39, 7, 47, 15, 55, 23, 63, 31,
      38, 6, 46, 14, 54, 22, 62, 30,
      37, 5, 45, 13, 53, 21, 61, 29,
      36, 4, 44, 12, 52, 20, 60, 28,
      35, 3, 43, 11, 51, 19, 59, 27,
      34, 2, 42, 10, 50, 18, 58, 26,
      33, 1, 41, 9, 49, 17, 57, 25,
      32, 0, 40, 8, 48, 16, 56, 24};

  private int[] BitExp = {
      31, 0, 1, 2, 3, 4, 3, 4, 5, 6, 7, 8, 7, 8, 9, 10,
      11, 12, 11, 12, 13, 14, 15, 16, 15, 16, 17, 18, 19, 20, 19, 20,
      21, 22, 23, 24, 23, 24, 25, 26, 27, 28, 27, 28, 29, 30, 31, 0};

  private int[] BitPM = {
      15, 6, 19, 20, 28, 11, 27, 16, 0, 14, 22, 25, 4, 17, 30, 9,
      1, 7, 23, 13, 31, 26, 2, 8, 18, 12, 29, 5, 21, 10, 3, 24};

  private int[][] sBox = {
      {
      14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7,
      0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8,
      4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0,
      15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
      ,

      {
      15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10,
      3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5,
      0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15,
      13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
      ,

      {
      10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8,
      13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1,
      13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7,
      1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
      ,

      {
      7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15,
      13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9,
      10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4,
      3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
      ,

      {
      2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9,
      14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,
      4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14,
      11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
      ,

      {
      12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11,
      10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,
      9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6,
      4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
      ,

      {
      4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1,
      13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,
      1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2,
      6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
      ,

      {
      13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7,
      1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,
      7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8,
      2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
  };

  private int[] BitPMC1 = {
      56, 48, 40, 32, 24, 16, 8,
      0, 57, 49, 41, 33, 25, 17,
      9, 1, 58, 50, 42, 34, 26,
      18, 10, 2, 59, 51, 43, 35,
      62, 54, 46, 38, 30, 22, 14,
      6, 61, 53, 45, 37, 29, 21,
      13, 5, 60, 52, 44, 36, 28,
      20, 12, 4, 27, 19, 11, 3};

  private int[] BitPMC2 = {
      13, 16, 10, 23, 0, 4,
      2, 27, 14, 5, 20, 9,
      22, 18, 11, 3, 25, 7,
      15, 6, 26, 19, 12, 1,
      40, 51, 30, 36, 46, 54,
      29, 39, 50, 44, 32, 47,
      43, 48, 38, 55, 33, 52,
      45, 41, 49, 35, 28, 31};

  private int[][] subKey = new int[16][6];

  private int[] initPermutation(int[] inData) {
    int[] outData = new int[8];
    int[] newData = new int[8];
    for (byte i = 0; i <= 63; i++) {
      if ( (inData[BitIP[i] >> 3] & (1 << (7 - (BitIP[i] & 0x07)))) != 0)
        newData[i >> 3] = (byte) (newData[i >> 3] | (1 << (7 - (i & 0x07))));
    }
    for (byte i = 0; i <= 7; i++) {
      inData[i] = newData[i];
      if (inData[i] < 0)
        inData[i] = inData[i] + 256;
    }

    outData = inData;
    return outData;

  }

  private int[] expand(int[] inData) {
    byte i;
    int[] outData = new int[6];
    for (i = 0; i <= 47; i++) {
      if ( (inData[BitExp[i] >> 3] & (1 << (7 - (BitExp[i] & 0x07)))) != 0) {
        outData[i >> 3] = (byte) (outData[i >> 3] | (1 << (7 - (i & 0x07))));
      }
      if (outData[i >> 3] < 0)
        outData[i >> 3] = outData[i >> 3] + 256;
    }
    return outData;
  }

  private int si(int s, int inByte) {
    int c;
    int out;
    c = ( (inByte & 0x20) | ( (inByte & 0x1e) >> 1) |
         ( (inByte & 0x01) << 4));
    if (c < 0)
      c = c + 256;
    out = sBox[s][c] & 0x0f;
    if (out < 0)
      out = out + 256;
    return out;

  }

  private int[] permutation(int[] inData) {
    int[] newData = new int[4];
    byte i;
    for (i = 0; i <= 31; i++) {
      if ( (inData[BitPM[i] >> 3] & (1 << (7 - (BitPM[i] & 0x07)))) != 0) {
        newData[i >> 3] = (byte) (newData[i >> 3] | (1 << (7 - (i & 0x07))));
      }
      if (newData[i >> 3] < 0)
        newData[i >> 3] = newData[i >> 3] + 256;
    }
    for (i = 0; i <= 3; i++)
      inData[i] = newData[i];
    return inData;
  }

  private int[] encry(int[] inData, int[] subKey, int[] outData) {
    int[] outBuf = new int[6];
    int[] buf = new int[8];
    byte i;
    outBuf = expand(inData);
    for (i = 0; i <= 5; i++) {
      outBuf[i] = (byte) (outBuf[i] ^ subKey[i]);
      if (outBuf[i] < 0)
        outBuf[i] = outBuf[i] + 256;
    }
    buf[0] = (byte) (outBuf[0] >> 2); //xxxxxx -> 2
    buf[1] = (byte) ( ( (outBuf[0] & 0x03) << 4) | (outBuf[1] >> 4)); // 4 <- xx xxxx -> 4
    buf[2] = (byte) ( ( (outBuf[1] & 0x0f) << 2) | (outBuf[2] >> 6)); //        2 <- xxxx xx -> 6
    buf[3] = (byte) (outBuf[2] & 0x3f); //                    xxxxxx
    buf[4] = (byte) (outBuf[3] >> 2); //                           xxxxxx
    buf[5] = (byte) ( ( (outBuf[3] & 0x03) << 4) | (outBuf[4] >> 4)); //                                 xx xxxx
    buf[6] = (byte) ( ( (outBuf[4] & 0x0f) << 2) | (outBuf[5] >> 6)); //                                        xxxx xx
    buf[7] = (byte) (outBuf[5] & 0x3f);
    for (i = 0; i <= 7; i++) {
      if (buf[i] < 0)
        buf[i] = buf[i] + 256;
    }
    for (i = 0; i <= 7; i++)
      buf[i] = si(i, buf[i]);
    for (i = 0; i <= 3; i++) {
      outBuf[i] = (byte) ( (buf[i * 2] << 4) | buf[i * 2 + 1]);
      if (outBuf[i] < 0)
        outBuf[i] = outBuf[i] + 256;
    }
    permutation(outBuf);
    for (i = 0; i <= 3; i++)
      outData[i] = outBuf[i];
    return outData;
  }

  private int[] conversePermutation(int[] inData) {

    int[] newData = new int[8];
    int i;
    for (i = 0; i <= 63; i++) {
      if ( (inData[BitCP[i] >> 3] & (1 << (7 - (BitCP[i] & 0x07)))) != 0)
        newData[i >> 3] = (byte) (newData[i >> 3] | (1 << (7 - (i & 0x07))));
      if (newData[i >> 3] < 0) {
        newData[i >> 3] = newData[i >> 3] + 256;
      }
    }
    for (i = 0; i <= 7; i++)
      inData[i] = newData[i];
    return inData;
  }

  private int[] desData(int[] inData) {
    int[] outData = new int[8];
    int[] temp = new int[4];
    int[] buf = new int[4];
    byte i, j;
    outData = initPermutation(inData);
    for (i = 0; i <= 15; i++) {
      for (j = 0; j <= 3; j++)
        temp[j] = outData[j]; //temp = Ln
      for (j = 0; j <= 3; j++)
        outData[j] = outData[j + 4]; //Ln+1 = Rn
      buf = encry(outData, subKey[i], buf); //Rn ==Kn==> buf
      for (j = 0; j <= 3; j++) {
        outData[j + 4] = (byte) (temp[j] ^ buf[j]); //Rn+1 = Ln^buf
        if (outData[j + 4] < 0)
          outData[j + 4] = outData[j + 4] + 256;
      }
    }

    for (j = 0; j <= 3; j++)
      temp[j] = outData[j + 4];
    for (j = 0; j <= 3; j++)
      outData[j + 4] = outData[j];
    for (j = 0; j <= 3; j++)
      outData[j] = temp[j];

    return conversePermutation(outData);
  }

  private int[] permutationChoose1(int[] inData, int[] outData) {
    byte i;
    for (i = 0; i <= 55; i++) {
      if ( (inData[BitPMC1[i] >> 3] & (1 << (7 - (BitPMC1[i] & 0x07)))) != 0) {
        outData[i >> 3] = (byte) (outData[i >> 3] | (1 << (7 - (i & 0x07))));
      }
      if (outData[i >> 3] < 0)
        outData[i >> 3] = (outData[i >> 3] + 256);
    }
    return outData;
  }

  private int[] permutationChoose2(int[] inData, int[] outData) {
    byte i;
    for (i = 0; i <= 47; i++) {
      if ( (inData[BitPMC2[i] >> 3] & (1 << (7 - (BitPMC2[i] & 0x07)))) != 0) {
        outData[i >> 3] = (byte) (outData[i >> 3] | (1 << (7 - (i & 0x07))));
      }
      if (outData[i >> 3] < 0)
        outData[i >> 3] = (outData[i >> 3] + 256);
    }
    return outData;
  }

  private int[][] makeKey(int[] inKey, int[][] outKey) {
    int bitDisplace[] = {
        1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
    int outData56[] = new int[7];
    int key28l[] = new int[4];
    int key28r[] = new int[4];
    int key56o[] = new int[7];
    byte i;
    outData56 = permutationChoose1(inKey, outData56);
    key28l[0] = (byte) (outData56[0] >> 4);
    key28l[1] = (byte) (outData56[0] << 4 | (outData56[1] >> 4));
    key28l[2] = (byte) (outData56[1] << 4 | (outData56[2] >> 4));
    key28l[3] = (byte) (outData56[2] << 4 | (outData56[3] >> 4));
    key28r[0] = (byte) (outData56[3] & 0x0f);
    key28r[1] = outData56[4];
    key28r[2] = outData56[5];
    key28r[3] = outData56[6];
    for (i = 0; i <= 3; i++) {
      if (key28l[i] < 0)
        key28l[i] = key28l[i] + 256;
      if (key28r[i] < 0)
        key28r[i] = key28r[i] + 256;
    }

    for (i = 0; i <= 15; i++) {
      key28l = cycleMove(key28l, bitDisplace[i]);
      key28r = cycleMove(key28r, bitDisplace[i]);
      key56o[0] = (byte) ( (key28l[0] << 4) | (key28l[1] >> 4));
      if (key56o[0] < 0)
        key56o[0] = key56o[0] + 256;
      key56o[1] = (byte) ( (key28l[1] << 4) | (key28l[2] >> 4));
      if (key56o[1] < 0)
        key56o[1] = key56o[1] + 256;
      key56o[2] = (byte) ( (key28l[2] << 4) | (key28l[3] >> 4));
      if (key56o[2] < 0)
        key56o[2] = key56o[2] + 256;
      key56o[3] = (byte) ( (key28l[3] << 4) | (key28r[0]));
      if (key56o[3] < 0)
        key56o[3] = key56o[3] + 256;
      key56o[4] = key28r[1];
      if (key56o[4] < 0)
        key56o[4] = key56o[4] + 256;
      key56o[5] = key28r[2];
      if (key56o[5] < 0)
        key56o[5] = key56o[5] + 256;
      key56o[6] = key28r[3];
      if (key56o[6] < 0)
        key56o[6] = key56o[6] + 256;
      outKey[i] = permutationChoose2(key56o, outKey[i]);
    }
    return outKey;
  }

  private int[] cycleMove(int[] inData, int bitMove) {
    byte i;
    for (i = 0; i <= bitMove - 1; i++) {
      inData[0] = (byte) ( (inData[0] << 1) | (inData[1] >> 7));
      if (inData[0] < 0)
        inData[0] = inData[0] + 256;
      inData[1] = (byte) ( (inData[1] << 1) | (inData[2] >> 7));
      if (inData[1] < 0)
        inData[1] = inData[1] + 256;
      inData[2] = (byte) ( (inData[2] << 1) | (inData[3] >> 7));
      if (inData[2] < 0)
        inData[2] = inData[2] + 256;
      inData[3] = (byte) ( (inData[3] << 1) | ( (inData[0] & 0x10) >> 4));
      if (inData[3] < 0)
        inData[3] = inData[3] + 256;
      inData[0] = (byte) ( (inData[0] & 0x0f));
      if (inData[0] < 0)
        inData[0] = inData[0] + 256;
    }

    return inData;
  }

  private String EncryStr(String Str, String Key) {
    int[] StrByte = new int[8];
    int[] OutByte = new int[8];
    int[] KeyByte = new int[8];
    String StrResult = "";
    byte i, j;
    if (Key.length() < 8) {
      while (Key.length() < 8) {
        Key = Key + (char) 0;
      }
    }
    while (Str.length() % 8 != 0) {
      Str = Str + (char) 0;
    }
    for (j = 0; j <= 7; j++) {
      KeyByte[j] = (byte) (Key.charAt(j));
    }
    subKey = makeKey(KeyByte, subKey);
    for (i = 0; i <= Str.length() / 8 - 1; i++) {
      for (j = 0; j <= 7; j++) {
        StrByte[j] = (byte) Str.charAt(i * 8 + j);
      }
      OutByte = desData(StrByte);
      for (j = 0; j <= 7; j++) {
        StrResult = StrResult + (char) (OutByte[j]);
      }
    }

    return StrResult;
  }

  /**
   * ���ܺ���
   * @param sPassword - ԭ����
   * @param Key - ���_
   * @return
   */
  public String EncryStrHex(String sPassword, String Key) {
    String StrResult = "";
    String TempResult, Temp;
    byte i;
    TempResult = EncryStr(sPassword, Key);
    StrResult = "";
    for (i = 0; i <= TempResult.length() - 1; i++) {
      Temp = Integer.toHexString(TempResult.charAt(i));
      if (Temp.length() == 1)
        Temp = "0" + Temp;
      StrResult = StrResult + Temp;
    }
    return StrResult.toUpperCase();
  }

  /************************���ڽ���KEY********************************************/
  public static String UncrypKey(String Src) {
    int idx;
    int KeyLen;
    int KeyPos;
    int offset;
    String dest = "";
    int SrcPos;
    int SrcAsc;
    int TmpSrcAsc;
    int Range;

    Src = Src.trim();
    String Key = "jiangsugongshangju-write-by-cn.com.sparknet";
    KeyLen = Key.length();
    KeyPos = -1;
    SrcPos = 0;
    SrcAsc = 0;
    Range = 230;
    String tmpstr = Src.substring(0, 2);
    int i = Src.length();
    offset = hexToint(tmpstr);
    SrcPos = 2;
    while (SrcPos < Src.length()) {
      try {
        tmpstr = Src.substring(SrcPos, SrcPos + 2);
        SrcAsc = hexToint(tmpstr);
        if (KeyPos < KeyLen) {
          KeyPos = KeyPos + 1;
        }
        else {
          KeyPos = 0;
        }
        TmpSrcAsc = SrcAsc ^ Key.charAt(KeyPos);
        if (TmpSrcAsc <= offset) {
          TmpSrcAsc = 255 + TmpSrcAsc - offset;
        }
        else {
          TmpSrcAsc = TmpSrcAsc - offset;
        }
        char tmpchar = (char) (TmpSrcAsc);
        dest = dest + (char) (TmpSrcAsc);
      }
      catch (Exception e) {
      }
      finally {
        offset = SrcAsc;
        SrcPos = SrcPos + 2;
      }
    }
    return dest;
  }

  public static int hexToint(String tmpstr) {
    int rtn1 = 0;
    int rtn2 = 0;
    char char1 = tmpstr.charAt(0);
    char char2 = tmpstr.charAt(1);
    if (char1 >= 'A') {
      rtn1 = (char1 - 'A') + 10;
    }
    else {
      rtn1 = (char1 - '0');
    }
    if (char2 >= 'A') {
      rtn2 = (char2 - 'A') + 10;
    }
    else {
      rtn2 = (char2 - '0');
    }
    return rtn1 * 16 + rtn2;
  }

  public static void main(final String[] args) throws Exception{
      String password = "123456";
      password=new Encry().EncryStrHex(password,"cn.com.sparkent.fll");
      System.out.println(password);
  }
}