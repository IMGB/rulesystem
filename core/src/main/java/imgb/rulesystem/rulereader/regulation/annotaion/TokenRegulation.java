package imgb.rulesystem.rulereader.regulation.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Daniel on 2014/9/7. 标识方法是否能够产生规则所需要的token对象。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenRegulation {
    public String tokenName();
    public boolean subSession() default false;
}
