package com.shulv.study.jfinaldemo.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.shulv.study.jfinaldemo.exception.BusinessException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.executable.ExecutableValidator;
import java.util.Iterator;
import java.util.Set;

public class ValidatorInterceptor implements Interceptor {
    private final ExecutableValidator executableValidator = Validation.buildDefaultValidatorFactory().getValidator().forExecutables();

    @Override
    public void intercept(Invocation inv) {
        Set<ConstraintViolation<Object>> violationSet = executableValidator.validateParameters(
                inv.getTarget(),
                inv.getMethod(),
                inv.getArgs());
        if (violationSet != null && !violationSet.isEmpty()) {
            throw BusinessException.CUSTOM_ERROR(getThrowErrorStr(violationSet));
        }
        inv.invoke();
        Object returnValue = inv.getReturnValue();
        violationSet = executableValidator.validateReturnValue(inv.getTarget(), inv.getMethod(), returnValue);
        if (violationSet != null && !violationSet.isEmpty()) {
            throw BusinessException.CUSTOM_ERROR(getThrowErrorStr(violationSet));
        }
    }

    private String getThrowErrorStr(Set<ConstraintViolation<Object>> constraintViolations) {
        Iterator<ConstraintViolation<Object>> iter = constraintViolations.iterator();
        StringBuilder errorMsg = new StringBuilder();
        boolean isError = false;
        String op = ",";
        while (iter.hasNext()) {
            if (isError) {
                errorMsg.append(op);
            }
            String message = iter.next().getMessage();
            errorMsg.append(message);
            isError = true;
        }
        return errorMsg.toString();
    }
}
