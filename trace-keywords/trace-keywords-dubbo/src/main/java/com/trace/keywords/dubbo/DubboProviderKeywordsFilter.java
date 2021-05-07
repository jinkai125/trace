package com.trace.keywords.dubbo;

import java.util.HashMap;
import java.util.Map;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import com.google.gson.Gson;
import com.trace.keywords.Keywords;
import com.trace.keywords.metadata.Metadata;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Activate(group = { CommonConstants.PROVIDER })
public class DubboProviderKeywordsFilter implements Filter {

    private static Gson gson = new Gson();
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Object[] args = invocation.getArguments();
        Map<String, String> keywords = new HashMap<>();
        for (Object arg : args) {
            Map<String, Keywords> keywordMap = Metadata.getInstance().getKeywords(arg.getClass());
            keywordMap.values().forEach(keyword->{
                try {
                    keywords.put(keyword.getAlias(), keyword.resolve(arg));
                } catch (Exception e) {
                }
            });
        }
        log.info(gson.toJson(keywords));
        return invoker.invoke(invocation);
    }
}
