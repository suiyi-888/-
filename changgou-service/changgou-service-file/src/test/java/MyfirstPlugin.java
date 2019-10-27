import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

@Intercepts({
    @Signature(type= StatementHandler.class,method="parameterize",args=java.sql.Statement.class)
})
public class MyfirstPlugin implements Interceptor {

    /**
     * 拦截目标对象的目标方法的执行；
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("要拦截的方法"+invocation+invocation.getMethod());
        Object prObject = invocation.proceed();
        return prObject;
    }

    /**
     * 包装目标对象的：包装：为目标对象创建一个代理对象
     */
    @Override
    public Object plugin(Object target) {
        // TODO Auto-generated method stub
        System.out.println("包装的对象"+target.toString());
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    /**
     * setProperties：
     *      将插件注册时 的property属性设置进来
     */
    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
        System.out.println(properties);
    }

}
