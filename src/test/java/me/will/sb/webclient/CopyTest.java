package me.will.sb.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

/**
 * me.will.sb.webclient.CopyTest
 *
 * @author Zhuang Jiabin
 * @version V1.0.0
 * @since 2020/8/19 16:25
 */
public class CopyTest {

    @Test
    public void copy(){
        var a = new A(1,"a",9L);
        var b = new B();
        BeanUtils.copyProperties(a,b);
        System.out.println(b);
    }

    class B{
        private int i;
        private String str;
        private long l;
        private int j;

        public int getJ() {
            return j;
        }

        public void setJ(int j) {
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        public long getL() {
            return l;
        }

        public void setL(long l) {
            this.l = l;
        }

        @Override
        public String toString() {
            return "B{" +
                    "i=" + i +
                    ", str='" + str + '\'' +
                    ", l=" + l +
                    '}';
        }
    }

    class A{
        private int i;
        private String str;
        private long l;
        private int k;

        public int getK() {
            return k;
        }

        public void setK(int k) {
            this.k = k;
        }

        public A(int i, String str, long l) {
            this.i = i;
            this.str = str;
            this.l = l;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        public long getL() {
            return l;
        }

        public void setL(long l) {
            this.l = l;
        }
    }
}
