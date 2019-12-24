package com.example.qingjiaxu.rotatetest;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class Calc {
    //设定最大的数组长度
    private static final int MAXLEN = 10 ;

    /**
     * 计算表达式的字符串形式
     * 从左到右扫描，数字先入number栈，运算符入operator栈
     * +-基本优先级为1 *÷的优先级为2，log ln sin cos tan n!的优先级为3，开方，乘方的优先级为4
     * 括号内存运算法的优先级高于4
     * 当前运算符优先级高于栈顶压栈，低于栈顶弹出一个运算符与两个数字进行运算
     * 重复直到运算符大于栈顶
     * 扫描完之后对剩下的运算符与数字依次进行计算
     * @param str
     */
    public static String process(String str , boolean drag_flag){
        int weightPlus = 0;  //记录同一个()的基本优先级
        int arrayTop = 0; //数组计数器
        int numTop = 0;
        int flag = 1;   //判断正负 1为正数 -1为负数
        int weightTemp = 0; //记录临时优先级的变化
        int weight[] = new int[MAXLEN];
        double[] number = new double[MAXLEN];
        number[0] = 0;
        char ch, getNextElement, operator[] = new char[MAXLEN];
        String num; // 记录数字 str将以+-×÷()sctgl!√ ^分割，字符串之间即为数字
        String expression = str;
        StringTokenizer expToken = new StringTokenizer(expression, "+-×÷()sctgl!m√ ^");
        int i = 0;
        while(i < expression.length()){
            ch = expression.charAt(i);

            //判断正负数
            if(i == 0){
                if(ch == '-')
                    flag = -1;
            }
            else if (expression.charAt(i - 1) == '(' && ch == '-')
                flag = -1;

            //取得数字，将正负号交给该数字
            if(ch >= '0' &&  ch <= '9' || ch == '.'|| ch == 'E'){
                num = expToken.nextToken();
                getNextElement = ch;
                while(i < expression.length() && (getNextElement >= '0' && getNextElement <= '9' || getNextElement == '.' || getNextElement == 'E')){
                    getNextElement = expression.charAt(i++);
                    //System.out.println("i的值为：" + i + " , ch_get的值为：" + getNextElement);
                }

                if(i >= expression.length() && getNextElement != '!')
                    i -= 1 ;
                else i -= 2 ;

                if(num.compareTo(".") == 0)
                    number[numTop++] = 0;
                else {
                    number[numTop++] = Double.parseDouble(num) * flag;
                    flag = 1;
                }
            }

            //计算运算符的优先级
            if(ch == '(') weightPlus += 4;
            if(ch == ')') weightPlus -= 4;
            if(ch == '-' && flag == 1 || ch == '+' || ch == '-' || ch == '×' || ch == '÷' ||
                    ch == 's' || ch == 'c' || ch == 't' || ch == 'l' || ch == 'g' || ch == '!'
                    || ch == 'm' || ch == '√' || ch == '^'){
                switch(ch){
                    //+-的优先级最低
                    case '+':
                    case '-':
                        weightTemp = weightPlus + 1 ;
                        break;
                    //×÷的优先级为2
                    case '÷':
                    case '×':
                        weightTemp = weightPlus + 2 ;
                        break;
                    case 's':
                    case 'c':
                    case 't':
                    case 'l':
                    case 'g':
                    case '!':
                        weightTemp = weightPlus + 3 ;
                        break;
                    case 'm':
                    case '√':
                    case '^':
                    default:
                        weightTemp = weightPlus + 4 ;
                        break;
                }

                //如果当前优先级大于栈顶的元素，则直接入栈
                if(arrayTop == 0 || weight[arrayTop - 1] < weightTemp){
                    weight[arrayTop] = weightTemp;
                    operator[arrayTop] = ch;
                    //System.out.println("============"+ch+"========");
                    arrayTop++;
                }else{ //否则将堆栈中的运算符逐个取出，直到当前栈顶部运算符的优先级小于当前运算符
                    while(arrayTop > 0 && weight[arrayTop - 1] > weightTemp){
                        switch(operator[arrayTop - 1]){
                            case '+':
                                number[numTop - 2] += number[numTop - 1];
                                break;
                            case '-':
                                number[numTop - 2] -= number[numTop - 1];
                                break;
                            case '×':
                                number[numTop - 2] *= number[numTop - 1];
                                break;
                            case '÷':
                                if(number[numTop - 1] == 0){
                                    //System.out.println("0不能为除数");
                                    return "0不能为除数";
                                }
                                number[numTop - 2] /= number[numTop - 1];
                                break;
                            case 'm':
                                if(number[numTop - 1] <= 0) {
                                    //System.out.println("0和负数不能用作模");
                                    return "0和负数不能用作模";
                                }
                                number[numTop - 2] %= number[numTop - 1];
                                break;
                            case '√':
                                //负值不能开偶次方
                                if(number[numTop - 1] == 0 || (number[numTop - 2] < 0 && number[numTop - 1] % 2 == 0)){
                                    //System.out.println("平方数不存在或者负数不能开偶次方！");
                                    return "平方数不存在或者负数不能开偶次方！";
                                }
                                number[numTop - 2] = Math.pow(number[numTop - 1], 1 / number[numTop - 2]);
                                break;
                            case '^':
                                number[numTop - 2] = Math.pow(number[numTop - 2], number[numTop - 1]);
                                break;
                            case 's':
                                //角度
                                if(drag_flag){
                                    number[numTop - 1] = Math.sin(number[numTop - 1] * Math.PI / 180);
                                }else{
                                    //此时为弧度
                                    number[numTop - 1] = Math.sin(number[numTop - 1]);
                                }
                                numTop++ ;
                                break;
                            case 'c':
                                if(drag_flag){
                                    number[numTop - 1] = Math.cos(number[numTop - 1] * Math.PI / 180);
                                }else{
                                    number[numTop - 1] = Math.cos(number[numTop - 1]);
                                }
                                numTop++;
                                break;
                            case 't':
                                if(drag_flag){
                                    if(Math.abs(number[numTop - 1] % 90) == 1){
                                        //System.out.println("tan取值有错误");
                                        return "tan取值有错误";
                                    }
                                    number[numTop - 1] = Math.tan(number[numTop - 1] * Math.PI / 180);
                                }else{
                                    if(Math.abs(number[numTop - 1] % Math.PI * 2) == 1){
                                        //System.out.println("tan取值有错误");
                                        return "tan取值有错误";
                                    }
                                    number[numTop - 1] = Math.tan(number[numTop - 1]);
                                }
                                numTop++;
                                break;
                            //ln
                            case 'l':
                                if(number[numTop - 1]<=0){
                                    //System.out.println("对数ln值有误");
                                    return "对数ln值有误";
                                }
                                number[numTop - 1] = Math.log(number[numTop - 1]);
                                numTop++;
                                break;
                            //log
                            case 'g':
                                if(number[numTop - 1] <= 0){
                                    //System.out.println("对数log值有误");
                                    return "对数log值有误";
                                }
                                number[numTop - 1] = Math.log10(number[numTop - 1]);
                                numTop++;
                                break;
                            case '!':
                                if(number[numTop - 1] < 0){
                                    //System.out.println("阶乘数有误");
                                    return "阶乘数有误";
                                }
                                number[numTop - 1] = getFactorial(number[numTop - 1]);
                                numTop++;
                                break;
                        }
                        //继续堆栈下一个元素判断
                        numTop--;
                        arrayTop--;
                    }
                    //将运算符压入栈中
                    weight[arrayTop] = weightTemp;
                    operator[arrayTop] = ch;
                    arrayTop++;
                }
            }
            i++;
        }

        //依次取出堆运算中的运算符进行运算
        while(arrayTop > 0 && (numTop >= 2 || operator[arrayTop - 1] == 's'
                || operator[arrayTop - 1] == 'c' || operator[arrayTop - 1] == 't'
                || operator[arrayTop - 1] == 'l' || operator[arrayTop - 1] == 'g'
                || operator[arrayTop - 1] == '!' )){
            //直接将数组的后两位取出运算
            switch(operator[arrayTop - 1]){
                case '+':
                    number[numTop - 2] += number[numTop - 1];
                    break;
                case '-':
                    number[numTop - 2] -= number[numTop - 1];
                    break;
                case '×':
                    number[numTop - 2] *= number[numTop - 1];
                    break;
                case '÷':
                    if(number[numTop - 1] == 0){
                        //System.out.println("0不能为除数");
                        return "0不能为除数";
                    }
                    number[numTop - 2] /= number[numTop - 1];
                    break;
                case 'm':
                    if(number[numTop - 1] <= 0) {
                        //System.out.println("0和负数不能用作模");
                        return "0和负数不能用作模";
                    }
                    number[numTop - 2] %= number[numTop - 1];
                    break;
                case '√':
                    //负值不能开偶次方
                    if(number[numTop - 1] == 0 || (number[numTop - 2] < 0 && number[numTop - 1] % 2 == 0)){
                        //System.out.println("平方数不存在或者负数不能开偶次方！");
                        return "平方数不存在或者负数不能开偶次方！";
                    }
                    number[numTop - 2] = Math.pow(number[numTop - 1], 1 / number[numTop - 2]);
                    break;
                case '^':
                    number[numTop - 2] = Math.pow(number[numTop - 2], number[numTop - 1]);
                    break;
                case 's':
                    //角度
                    if(drag_flag){
                        number[numTop - 1] = Math.sin(number[numTop - 1] * Math.PI / 180);
                    }else{
                        //此时为弧度
                        number[numTop - 1] = Math.sin(number[numTop - 1]);
                    }
                    numTop++ ;
                    break;
                case 'c':
                    if(drag_flag){
                        number[numTop - 1] = Math.cos(number[numTop - 1] * Math.PI / 180);
                    }else{
                        number[numTop - 1] = Math.cos(number[numTop - 1]);
                    }
                    numTop++;
                    break;
                case 't':
                    if(drag_flag){
                        if(Math.abs(number[numTop - 1] % 90) == 1){
                            //System.out.println("tan取值有错误");
                            return "tan取值有错误";
                        }
                        number[numTop - 1] = Math.tan(number[numTop - 1] * Math.PI / 180);
                    }else{
                        if(Math.abs(number[numTop - 1] % Math.PI * 2) == 1){
                            //System.out.println("tan取值有错误");
                            return "tan取值有错误";
                        }
                        number[numTop - 1] = Math.tan(number[numTop - 1]);
                    }
                    numTop++;
                    break;
                //ln
                case 'l':
                    if(number[numTop - 1] <= 0){
                        //System.out.println("对数ln值有误");
                        return "对数ln值有误";
                    }
                    number[numTop - 1] = Math.log(number[numTop - 1]);
                    numTop++;
                    break;
                //log
                case 'g':
                    if(number[numTop - 1] < 0){
                        //System.out.println("对数log值有误");
                        return "对数log值有误";
                    }
                    number[numTop - 1] = Math.log10(number[numTop - 1]);
                    numTop++;
                    break;
                case '!':
                    if(number[numTop - 1] < 0){
                        //System.out.println("阶乘数有误");
                        return "阶乘数有误";
                    }
                    number[numTop - 1] = getFactorial(number[numTop - 1]);
                    numTop++;
                    break;
            }
            //取下一个元素计算
            numTop--;
            arrayTop--;
        }

        if(number[0] > 7.3E306){
            //System.out.println("计算数组过大");
            return "数据溢出";
        }

        //数据显示:
        DecimalFormat format = new DecimalFormat("0.############");
        return format.format(number[0]);
        //System.out.println("最终计算答案为："+ dataShow(number[0]));
    }

    private static double getFactorial(double n){
        if(n == 0)
            return 1;
        double sum = 1.0;
        for(int s = 1; s <= n; s++){
            sum *= s;
        }
        return sum;
    }

//    private static String dataShow(double n){
//        DecimalFormat format = new DecimalFormat("0.############");
//        return format.format(n).toString();
//    }
}
