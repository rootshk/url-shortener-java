package top.roothk.service.urlshortener.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

/**
 * 短地址
 */
@Controller("tController")
@RequestMapping("")
public class TController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取短地址
     * @param context
     */
    @GetMapping("/{context}")
    public String redirect(@PathVariable(value = "context") String context) {
        if (stringRedisTemplate.opsForHash().hasKey("t", context)) {
            String url = Objects.requireNonNull(stringRedisTemplate.opsForHash().get("t", context)).toString();
            return "redirect:" + url;
        }
        return "redirect:https://roothk.top";
    }

    @GetMapping("")
    public String index() {
        return "Hello";
    }

}
