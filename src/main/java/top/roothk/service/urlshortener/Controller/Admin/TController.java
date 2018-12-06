package top.roothk.service.urlshortener.Controller.Admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController("adminTController")
@RequestMapping("/admin")
public class TController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置
     * @param data
     * @return
     */
    @PostMapping("")
    public String set(@RequestBody JSONObject data) {
        String url = data.getString("url");
        String key = DigestUtils.md5DigestAsHex(url.getBytes()).substring(0, 10);
        if (!stringRedisTemplate.opsForHash().hasKey("t", key)) {
            stringRedisTemplate.opsForHash().put("t", key, url);
        }
        return key;
    }


    @GetMapping("")
    public String set() {
        return "Hello";
    }

}
