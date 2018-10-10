package com.time.web.servlet;

import com.time.dto.Exposer;
import com.time.dto.SeckillExecution;
import com.time.dto.SeckillResult;
import com.time.enums.SeckillStatEnum;
import com.time.exception.RepeatKillException;
import com.time.exception.SeckillCloseException;
import com.time.pojo.Seckill;
import com.time.pojo.User;
import com.time.service.SeckillService;
import com.time.service.impl.SeckillServiceImpl;
import com.time.utils.Constants;
import com.time.web.base.BaseServlet;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet("/seckill")
public class SeckillServlet extends BaseServlet {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取秒杀的列表页
     */
    public String list(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        // 获取列表页
        SeckillService seckillService = new SeckillServiceImpl();
        List<Seckill> list = seckillService.getSeckillList();
        request.setAttribute("list", list);
        return "slist";
    }

    /**
     * 商品详情页
     */
    public String detail(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        if (user == null) {

            return "login";
        }
        SeckillService seckillService = new SeckillServiceImpl();
        Long seckillId = Long.parseLong(request.getParameter("seckillId"));

        if (seckillId == null) {
            list(request, resp);
            return "slist";
        }

        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "slist";
        }
        request.setAttribute("seckill", seckill);
        return "detail";
    }

    /**
     * 暴露秒杀接口
     * @param request
     * @param resp
     * @throws Exception
     */
    public void exposer(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        SeckillService seckillService = new SeckillServiceImpl();
        Long seckillId = Long.parseLong(request.getParameter("seckillId"));
        SeckillResult<Exposer> result;
        resp.setContentType("application/json;charset=utf-8");
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<>(true, exposer);
            resp.getWriter().write(JSONObject.fromObject(result).toString());

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
            resp.getWriter().write(JSONObject.fromObject(result).toString());
        }
    }

    /**
     * 执行秒杀
     * @param request
     * @param resp
     * @return json SeckillResult<SeckillException>
     * @throws IOException
     */
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        SeckillService seckillService = new SeckillServiceImpl();
        Long seckillId = Long.parseLong(request.getParameter("seckillId"));
        String md5 = request.getParameter("md5");
        Integer userId = user.getUserId();
        JSONObject result = null;
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, userId, md5);
            result  = JSONObject.fromObject(new SeckillResult<SeckillExecution>(true, execution));
            writer.write(result.toString());
            return null;
        } catch (RepeatKillException e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            result  = JSONObject.fromObject(new SeckillResult<SeckillExecution>(true, execution));
            writer.write(result.toString());
            return null;
        } catch (SeckillCloseException e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            result  = JSONObject.fromObject(new SeckillResult<SeckillExecution>(true, execution));
            writer.write(result.toString());
            return null;
        } catch (Exception e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            result  = JSONObject.fromObject(new SeckillResult<SeckillExecution>(true, execution));
            writer.write(result.toString());
            return null;
        }

    }

    /**
     * 获取系统时间
     * @param request
     * @param resp
     * @return
     * @throws IOException
     */
    public String nowTime(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        Date now = new Date();
        SeckillResult result = new SeckillResult(true,now.getTime());
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(JSONObject.fromObject(result).toString());
        return null;
    }

}
