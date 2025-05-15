//package com.health.WE_CARE.utility;
//
//public class CoachIdGenerator {
//}
 package com.health.WE_CARE.utility;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class CoachIdGenerator implements IdentifierGenerator {
    private static final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        int count = counter.incrementAndGet();
        return "COACH" + timestamp + count;
    }


}
