package com.yat.wechatorderingsystem.Util;


import com.yat.wechatorderingsystem.VO.ResultVO;

public class ResultVOUtil {

    // 封装返回成功获取对象的VO
    public static ResultVO success(Object object){
        // setter
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code, String msg){
        // setter
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
