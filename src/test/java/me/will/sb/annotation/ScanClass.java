package me.will.sb.annotation;

import me.will.sb.mapper.SBMapper;
import me.will.sb.mapper.SBRepository;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.springframework.data.util.CastUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * 扫描 me.will.sb.service 包下 含有My注解的类并创建它们
 *
 * @author Zhuang Jiabin
 * @version V1.0.0
 * @since 2020/8/20 17:08
 */
public class ScanClass {

    @Test
    public void test(){
        Reflections refs = new Reflections("me.will.sb.service");
        for(Class<?> clazz : refs.getTypesAnnotatedWith(My.class)){
            try {
                Object resource = CastUtils.cast(clazz.getDeclaredConstructor(SBMapper.class, SBRepository.class).newInstance(null,null));
                System.out.println(resource.getClass().getName());
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
