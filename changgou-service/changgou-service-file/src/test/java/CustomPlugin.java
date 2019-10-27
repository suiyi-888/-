

 
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import java.sql.Statement;
import java.util.Properties;
 
/**
 * Created by dong on 2019/2/22.
 */
@Intercepts({//注意看这个大花括号，也就这说这里可以定义多个@Signature对多个地方拦截，都用这个拦截器
        @Signature(
                type =ResultSetHandler.class,//这是指拦截哪个接口
                method = "handleResultSets", //这个接口内的哪个方法名，不要拼错了
                args = {Statement.class})//这是拦截的方法的入参，按顺序写到这，不要多也不要少，如果方法重载，可是要通过方法名和入参来确定唯一的
        ,
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class CustomPlugin implements Interceptor {
    // 这里是每次执行操作的时候，都会进行这个拦截器的方法内
    public Object intercept(Invocation invocation) throws Throwable {
        //TODO:自已的业务处理
        return invocation.proceed();
    }
    @Override
    // 主要是为了把这个拦截器生成一个代理放到拦截器链中
    public Object plugin(Object target) {
        //官方推荐写法
        return Plugin.wrap(target, this);
    }
 
    // 插件初始化的时候调用，也只调用一次，插件配置的属性从这里设置进来
    public void setProperties(Properties properties) {
 
    }
}
