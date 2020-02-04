package com.lile.controller;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;


public class Shuapiao {

    public  String login = "http://taikang2019.i-mice.cn/taikang/user/login";
    public  String getToken = "http://taikang2019.i-mice.cn/taikang/user/token";
    public  String vote = "http://taikang2019.i-mice.cn/taikang/video/vote";
    public  String[] ipArrays = {
            "66.102.251.", "112.211.0.", "141.8.225.","159.106.121.",
            "216.58.221.", "61.244.148.", "59.125.39.", "58.30.15.", "114.80.166.",
            "202.96.134.", "58.19.24.", "119.39.23.", "58.195.128.", "124.236.223.",
            "183.221.217.", "222.182.90.", "58.194.96.", "211.138.161.",
            "112.112.13.", "219.159.82.", "202.98.226.", " 61.128.101.",
            "130.039.000.", "130.039.255.", "131.230.000.","131.230.255.",
            "144.092.000.", "144.092.255.", "151.000.000.", "152.255.255.",
            "161.058.000.", "161.058.255.", "169.208.000.", "169.223.255.",
            "171.208.000.", "171.220.255.", "195.010.040.", "195.010.040.",
            "195.010.062.", "195.010.063.", "195.010.194.", "195.010.194.",
            "195.063.159.", "195.063.159.", "195.090.044.", "195.090.046.",
            "195.090.047.", "195.090.048.", "195.090.049.", "195.090.051.",
            "195.090.052.", "195.090.053.", "195.100.066.", "195.112.164.",
            "195.112.172.", "195.112.173."};

    public String getIp(){
        Random r = new Random();
        Integer counter = r.nextInt(255);

        int index = r.nextInt(34);
        String ip = ipArrays[index];
        return ip+counter;
    }

    public static void main(String[] args) {
        Mythread m1 = new Mythread();
        Mythread m2 = new Mythread();
        Mythread m3 = new Mythread();
        Mythread m4 = new Mythread();
        Mythread m5 = new Mythread();
        m1.start();
        m2.start();
        m3.start();
        m4.start();
        m5.start();

        Shuapiao shuapiao = new Shuapiao();
        String phone = "";
        System.out.println(shuapiao.getChineseName());
        String name ="";
        int num = 0;
        while(num++<70000){
            phone = shuapiao.getTel();
            name = shuapiao.getChineseName();
            String token =  shuapiao.getT(phone);
            System.out.println(token);
            String ut = shuapiao.doPostLogin(shuapiao.login,token,phone,name) ;
            //   登录失败继续执行
            if(ut.equals("{\"code\":101,\"msg\":\"手机号码与姓名不匹配\",\"data\":null}")){

                continue;
            }

            String a =  shuapiao.doVote(ut);
            JSONObject jsonObject = JSONObject.parseObject(a);
            String data = jsonObject.getString("data");
            System.out.println(name+","+phone+"-----剩余票："+data);
           if(data==null){
               phone = shuapiao.getTel();
               name = shuapiao.getChineseName();
           }else{
               JSONObject jsonOb = JSONObject.parseObject(data);
               String remain = jsonOb.getString("remain");
               if(remain.equals("0")){
                   // 新手机号
                   phone = shuapiao.getTel();
                   name = shuapiao.getChineseName();
               }
           }

        }



    }



    // inittoken接口
    /*
     * @Author leli
            * @Description //获取token * @Date 17:52 2020/1/14
     * @Param [phone]
            * @return java.lang.String
            **/
    public   String getT(String phone){
        String str = "";
        try {
            URL url = new URL(getToken);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            out = new PrintWriter(conn.getOutputStream());
            out.flush();//缓冲数据
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((str = br.readLine()) != null) {
                str=new String(str.getBytes(),"UTF-8");
                // 28
                JSONObject jsonObject = JSONObject.parseObject(str);
                System.out.println(str+"--"+jsonObject.getString("data"));
                str = jsonObject.getString("data");
                return str;
            }
            is.close();
            conn.disconnect();
            return str;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return str;
    }


    /*
     * @Author leli
            * @Description ///登录  * @Date 18:00 2020/1/14
     * @Param [phone, token]
            * @return java.lang.String
            **/
    public  String login(String phone,String token){

        String str = "";
        try {
            URL url = new URL(login);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            OutputStream op = null;
            PrintWriter out = null;
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");//GET和POST必须全大写
            op = conn.getOutputStream();
            String params = "usertype=\"其他\"&username=白秒妙&phone="+phone+"&token="+token;
            StringBuffer sb = new StringBuffer();
            sb.append("usertype").append("=").append("其他").append("&username")
            .append("=").append("白喵喵").append("&").append("phone=")
            .append(phone).append("&token").append("=").append(token);
            op.write(sb.toString().getBytes());
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((str = br.readLine()) != null) {
                str=new String(str.getBytes(),"UTF-8");
                JSONObject jsonObject = JSONObject.parseObject(str);
                System.out.println(str+"--"+jsonObject.getString("data"));

            }
            is.close();
            conn.disconnect();
            return str;

        } catch (Exception e) {
            e.printStackTrace();
        }




        return "";
    }

    //生成 电话号
    public  String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    public  int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
    public  String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }


    /**
     * @Author leli
            * @Description //获取姓名 * @Date 18:09 2020/1/15
     * @Param
            * @return
            **/
    public  String firstName="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
    public  String girl="秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    public  String boy="伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
    public  String name_sex = "";
    public  String getChineseName() {
        int index=getNum(0, firstName.length()-1);
        String first=firstName.substring(index, index+1);
        int sex=getNum(0,1);
        String str=boy;
        int length=boy.length();
        if(sex==0){
            str=girl;
            length=girl.length();
            name_sex = "女";
        }else {
            name_sex="男";
        }
        index=getNum(0,length-1);
        String second=str.substring(index, index+1);
        int hasThird=getNum(0,1);
        String third="";
        if(hasThird==1){
            index=getNum(0,length-1);
            third=str.substring(index, index+1);
        }
        return first+second+third;
    }



    /*
     * @Author leli
            * @Description //登录   * @Date 18:11 2020/1/14
     * @Param [url, token, phone]
            * @return java.lang.String
            **/
    public  String doPostLogin(String url, String token,String phone,String name){
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);

        postMethod.addRequestHeader("accept", "*/*");
        postMethod.addRequestHeader("connection", "Keep-Alive");
        //设置json格式传送
        postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        //必须设置下面这个Header
        postMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        // 设置随机ip
        postMethod.addRequestHeader("x-forwarded-for",getIp());
        //添加请求参数
        postMethod.addParameter("usertype","其他");
        postMethod.addParameter("username",name);
        postMethod.addParameter("phone",phone);
        postMethod.addParameter("token",token);

        String res = "";
        try {

                int code = httpClient.executeMethod(postMethod);
                res = postMethod.getResponseBodyAsString();

                while (res.equals("{\"code\":108,\"msg\":\"IP地址注册频次超限\",\"data\":null}")){
                    // ip超限
                    // 设置随机ip
                    postMethod.setRequestHeader("x-forwarded-for",getIp());
                    code = httpClient.executeMethod(postMethod);
                    res= postMethod.getResponseBodyAsString();
                    System.out.println("--ip超限++++++");
                }
                System.out.println("登录...."+phone+","+name+","+res);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /*
     * @Author leli
            * @Description //投票 * @Date 18:21 2020/1/14
     * @Param [ut]
            * @return java.lang.String
            **/
    public   String doVote(String ut){
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(vote);

        postMethod.addRequestHeader("accept", "*/*");
        postMethod.addRequestHeader("connection", "Keep-Alive");
        postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        postMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");

        JSONObject jsonObject = JSONObject.parseObject(ut);
        System.out.println("vote--"+jsonObject.getString("data"));
        String data = jsonObject.getString("data");

        JSONObject json = JSONObject.parseObject(data);
        String userCode = json.getString("usercode");
        String userToken = json.getString("token");

        String cookie = "Hm_lvt_a3f4a8bd95a4ecc878ec050c3d4cfa7d=1578988540; tk_user_code="+userCode+
                "; tk_access_token="+userToken+
                "; JSESSIONID=7EAE4037AD76D3CE8B12E07CD94F4439; Hm_lpvt_a3f4a8bd95a4ecc878ec050c3d4cfa7d=1578993722";
        postMethod.addRequestHeader("Cookie",cookie);

        //添加请求参数
        postMethod.addParameter("videocode","cI4Thfcw");


        String res = "";
        try {
            int code = httpClient.executeMethod(postMethod);

            res = postMethod.getResponseBodyAsString();
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
class Mythread extends  Thread{

    @Override
    public void run() {

        Shuapiao shuapiao = new Shuapiao();
        String phone = shuapiao.getTel();
        String name = shuapiao.getChineseName();
        int i =7000;

        while(i-->0){
            String token =  shuapiao.getT(phone);
            System.out.println(token);
            String ut = shuapiao.doPostLogin(shuapiao.login,token,phone,name) ;
            //   登录失败继续执行
            if(ut.equals("{\"code\":101,\"msg\":\"手机号码与姓名不匹配\",\"data\":null}")){
                continue;
            }

            String a =  shuapiao.doVote(ut);
            JSONObject jsonObject = JSONObject.parseObject(a);
            String data = jsonObject.getString("data");
            System.out.println(name+","+phone+"-----剩余票："+data);
            if(data==null){
                phone = shuapiao.getTel();
                name = shuapiao.getChineseName();
            }else{
                JSONObject jsonOb = JSONObject.parseObject(data);
                String remain = jsonOb.getString("remain");
                if(remain.equals("0")){
                    // 新手机号
                    phone = shuapiao.getTel();
                    name = shuapiao.getChineseName();
                }
            }
        }

    }
}