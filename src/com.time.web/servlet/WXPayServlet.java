package com.time.web.servlet;

import com.github.wxpay.sdk.WXPayUtil;
import com.time.pojo.Order;
import com.time.pojo.User;
import com.time.utils.Constants;
import com.time.utils.HttpClientUtils;
import com.time.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/wxPayServlet")
public class WXPayServlet extends BaseServlet {

    /**
     * 生成二维码
     * @param resp
     * @return
     * @throws Exception
     */
    public String generateQRCode(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        String out_trade_no = request.getParameter("out_trade_no");
        // 调用pay工程的支付接口

        // 获取到code_url,将其生成二维码

        return null;
    }

    /**
     * 去支付
     * @param request
     * @param resp
     * @return
     * @throws Exception
     */
    public String toReOrder(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        // 获取用户信息
        User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
        // 生成全局唯一充值订单号 = 时间戳 + redis全局唯一数字
        String rechargerNo = new Date().toString()+ "";
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setOrderPrice(120d);
        order.setOrderState(0);
        order.setUserId(user.getUserId());
        // 生成充值记录
        return "showCode";
    }

    public String wxpay(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        String body = request.getParameter("body");
        String out_trade_no = request.getParameter("out_trade_no");
        String total_fee = request.getParameter("total_fee");
        Map<String,String> requestDataMap = new HashMap<>();
        requestDataMap.put("appid", "wx8a3fcf509313fd74");
        requestDataMap.put("mch_id", "1361137902");
        // 生成随机字符串
        String nonceStr = WXPayUtil.generateNonceStr();
        requestDataMap.put("nonce_str", nonceStr);
        // 商品描述
        requestDataMap.put("body", body);
        // 商户订单号
        requestDataMap.put("out_trade_no", out_trade_no);
        // 订单金额，单位为分
        requestDataMap.put("total_fee", total_fee);
        InetAddress locaHost = InetAddress.getLocalHost();
        String hostAddress = locaHost.getHostAddress();
        requestDataMap.put("spbill_create_ip", hostAddress);
        requestDataMap.put("notify_url", "http://localhost:8080/wxPayNotifyServlet");
        // 交易类型
        requestDataMap.put("trade_type", "NATIVE");
        requestDataMap.put("product_id", out_trade_no);

        String signValue = WXPayUtil.generateSignature(requestDataMap, "367151c5fd0d50f1e34a68a802d6bbca");
        // 生成签名
        requestDataMap.put("sign", signValue);
        // 将类型为map的参数转化为xml格式
        String requestDataXml = WXPayUtil.mapToXml(requestDataMap);
        // 发送调用统一下单API接口，响应的是xml格式的结果
        String responseDataXml = HttpClientUtils.doPostByXml("https://api.mch.weixin.qq.com/pay/unifiedorder", requestDataXml);

        return responseDataXml;
    }
}
