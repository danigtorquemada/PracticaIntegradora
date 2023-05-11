package com.dgomezt.practicaintegradora.utilities;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Component
@ConfigurationProperties(prefix = "mysql")
public class MysqlProperties {
    LocalDate maxDate;
    LocalDate minDate;
    BigDecimal minDecimal;
    BigDecimal maxDecimal;
}
