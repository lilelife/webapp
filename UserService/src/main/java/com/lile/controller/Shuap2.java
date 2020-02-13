package com.lile.controller;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName Shuap2
 * @Description TODO
 * @Author lile
 * @Date 2020/1/18 13:05
 * @Version 1.0
 */
public class Shuap2 extends  Thread{


    @Override
    public void run() {
        Shuapiao shuapiao = new Shuapiao();
        String phone = shuapiao.getTel();
        String name = shuapiao.getChineseName();
        int i =5000;

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


