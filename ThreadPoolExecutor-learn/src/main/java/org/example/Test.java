package org.example;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 伍六七
 * @date 2023/8/6 21:07
 */
public class Test {
    static Map<Operator, IComputer> computers;

    static {
        computers = new HashMap<Operator, IComputer>() {{

            put(Operator.ADD, new IComputer() {

                @Override
                public BigDecimal sum(BigDecimal num1, BigDecimal num2) {
                    return num1.add(num2);
                }
            });
            put(Operator.SUB, ((num1, num2) -> num1.subtract(num2)));
            put(Operator.MULTI, ((num1, num2) -> num1.multiply(num2)));
            put(Operator.DIVIDE, ((num1, num2) -> num1.divide(num2)));
        }};
    }

    public static void main(String[] args) {
        //测试验证1+2
        BigDecimal result=compute(Operator.DIVIDE,BigDecimal.valueOf(1),BigDecimal.valueOf(2));//输出结果3
        System.out.println(result);
    }

    public static BigDecimal compute(Operator operator, BigDecimal num1, BigDecimal num2) {
        BigDecimal result = computers.get(operator).sum(num1,num2);
        return result;
    }

    enum Operator {
        ADD, SUB, MULTI, DIVIDE;
    }

    interface IComputer {
        BigDecimal sum(BigDecimal num1,BigDecimal num2);
    }
}
