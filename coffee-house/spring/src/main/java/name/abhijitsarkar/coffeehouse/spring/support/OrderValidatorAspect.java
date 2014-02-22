/*
 * Copyright (c) 2014, the original author or authors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * A copy of the GNU General Public License accompanies this software,
 * and is also available at http://www.gnu.org/licenses.
 */

package name.abhijitsarkar.coffeehouse.spring.support;

import name.abhijitsarkar.coffeehouse.Coffee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author Abhijit Sarkar
 */

@Component
@Aspect
public class OrderValidatorAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderValidatorAspect.class);

    /* Define a pointcut, for the execution of all methods that return type 'Coffee',
     * are defined in the 'Barista' interface, named 'serve' and takes at least one String argument. */
    @Pointcut(
            "execution(name.abhijitsarkar.coffeehouse.Coffee name.abhijitsarkar.coffeehouse.Barista.serve(String, ..))"
    )
    public void order() {
        /* This method only exists to serve as a pointcut definition; it doesn't need a body. */
    }

    /* Could also be written using an in-place pointcut expression:
     * @Before("execution(* name.abhijitsarkar.coffeehouse.Coffee name.abhijitsarkar.coffeehouse.Barista serve(String, ..))").
     *
     * Arguments are more commonly retrieved using the 'binding' form args(parameterName,..)
     * in the pointcut expression. That provides for both stronger match and strong typing.
     *
     * So, in this case, pointcut would become:
     * @Pointcut("execution(* name.abhijitsarkar.coffeehouse.Coffee name.abhijitsarkar.coffeehouse.Barista serve(String, ..))
     *      && args(String blend, ..)")
     * public void order(String blend)
     * and advice would become:
     * @Before("name.abhijitsarkar.coffeehouse.spring.support.OrderValidatorAspect.order(blend)")
     *
     * Since the number of arguments are variable, we resort to the JoinPoint instead of the 'binding' args.
     */
    @Before("name.abhijitsarkar.coffeehouse.spring.support.OrderValidatorAspect.order()")
    public void validateOrder(final JoinPoint joinPoint) {
        LOGGER.debug("Intercepted {}.", joinPoint.getSignature());

        final Object[] args = joinPoint.getArgs();

        final String orderedBlend = args[0].toString();

        Coffee.Blend.valueOf(orderedBlend.toUpperCase(Locale.getDefault()));

        if (args.length == 2) {
            final String orderedFlavor = args[1].toString();
            Coffee.Flavor.valueOf(orderedFlavor.toUpperCase(Locale.getDefault()));
        }
    }
}