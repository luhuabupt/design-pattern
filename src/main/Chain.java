package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 责任链
 * <p>
 * 使每个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。将这些对象练成一条链，并沿着这条链传递该请求，直到有一个对象处理它为止
 * 好处是添加新的处理器或者重新排列处理器非常容易
 * 经常用在拦截，预处理请求等
 */
public class Chain {
    public interface Handler {
        Boolean process(Request request);
    }

    public static class HandlerChain {
        private List<Handler> handlerList = new ArrayList<>();

        public void addHandler(Handler handler) {
            this.handlerList.add(handler);
        }

        public boolean process(Request request) {
            for (Handler handler : handlerList) {
                Boolean res = handler.process(request);
                if (res != null) {
                    return res;
                }
            }
            throw new RuntimeException("Could not handle request: " + request);
        }
    }

    public static class ManagerHandler implements Handler {
        public Boolean process(Request request) {
            // 如果超过1000元，处理不了，交下一个处理:
            if (request.getAmount().compareTo(BigDecimal.valueOf(1000)) > 0) {
                return null;
            }
            return true;
        }
    }

    public static class DirectorHandler implements Handler {
        public Boolean process(Request request) {
            // 如果超过10000元，处理不了，交下一个处理:
            if (request.getAmount().compareTo(BigDecimal.valueOf(10000)) > 0) {
                return null;
            }
            return true;
        }
    }

    public static class CeoHandler implements Handler {
        public Boolean process(Request request) {
            return true;
        }
    }


    public static class Request {
        private String name;
        private BigDecimal amount;

        public Request(String name, BigDecimal amount) {
            this.name = name;
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public BigDecimal getAmount() {
            return amount;
        }
    }
}
