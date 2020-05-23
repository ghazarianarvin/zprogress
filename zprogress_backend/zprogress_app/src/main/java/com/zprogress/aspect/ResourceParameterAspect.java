package com.zprogress.aspect;

import com.zprogress.controller.ClientContext;
import com.zprogress.domain.Resource;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ResourceParameterAspect {

    private ClientContext clientContext;

    @Before("execution(* com.zprogress.reporsitory.AbstractRepository+.*(com.zprogress.domain.Resource+)) && args(resource)")
    public void beforeQuery(Resource resource) {
        resource.setUsername(this.clientContext.getUsername());
    }

    @Autowired
    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

}
