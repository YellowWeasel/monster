package com.erayic.agr.serverproduct;

/**
 * Created by wxk  on 2017/5/5.
 */

public class Constants {
        public static  final double[] RAINLVL={0,10,25,50,100,250,255};
        public static  final double[] RAINLVL10MIN={0,0.5,1,8,10,50};
        public static  final String[] RAINLVLTITLE={"晴","阵雨","小雨","中雨","大雨","暴雨"};
        public static  final double[] WINDLVL={0,0.3,1.6,3.4,5.5,8.0,10.8,13.9,17.2,20.8,24.5,28.5,32.6,37.0,41.4,46.2,50.9,56.0,61.2,100};
        public static  String getRainLvlTitle(double value){
                for (int i=0;i<RAINLVL10MIN.length;i++){
                        if (value<=RAINLVL10MIN[i]){
                                return RAINLVLTITLE[i];
                        }
                }
                return "—";
        }
        public static  String  getWindLvlTitle(double value){
                for (int i=0;i<WINDLVL.length;i++){
                        if (value<=WINDLVL[i]){
                                return String.valueOf(i)+"级";
                        }
                }
                return "—";
        }
}
