package imgb.rulesystem.rulereader.regulation.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Daniel on 2014/10/10.标识方法是否能够产生规则所需要迭代的token对象。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IterableToken {
    public String tokenName();
    public String tagName() default ""; //标识是否在当前的Token外套一层默认的session
    public boolean subSession() default false; //标示当前返回的对象是否是一个默认的子对象
}
