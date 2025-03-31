import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class MissingExtensionException extends Exception {
    public MissingExtensionException(String message) {
        super(message);
    }
}

public class ExceptionDemo {
    private double divisor;
    private double dividend;
    private double result;

    public void divide() throws InputMismatchException, ArithmeticException {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入除数: ");
        divisor = input.nextDouble();
        System.out.print("请输入被除数: ");
        dividend = input.nextDouble();
        result = divisor / dividend;
        System.out.println("结果是: " + result);
    }

    public void goToDivideMethod() throws InputMismatchException, ArithmeticException {
        divide();
    }

    public void displayChoices() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("1. 除法运算");
            System.out.println("2. 从文件读取");
            System.out.println("3. 退出");
            System.out.print("请选择: ");
            try {
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        try {
                            goToDivideMethod();
                        } catch (InputMismatchException e) {
                            System.out.println(" 异常 " + e + " 发生。期望输入数字，但未提供");
                        } catch (ArithmeticException e) {
                            System.out.println(" 异常 " + e + " 发生。尝试进行除以零的操作");
                        } finally {
                            System.out.println("关闭所有资源");
                        }
                        break;
                    case 2:
                        try {
                            readAFile();
                        } catch (IOException e) {
                            System.out.println(" 异常 " + e + " 发生。读取文件时出错");
                        }
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("无效的选择，请重新输入。");
                }
            } catch (InputMismatchException e) {
                System.out.println(" 异常 " + e + " 发生。期望输入数字，但未提供");
                input.nextLine(); 
            }
        }
    }

    public void readAFile() throws IOException, MissingExtensionException {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入文件路径: ");
        String filePath = input.nextLine();
        if (!filePath.contains(".")) {
            throw new MissingExtensionException("文件路径缺少扩展名");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null) {
                System.out.println("读取的内容: " + line);
            }
        }
    }

    public static void main(String[] args) {
        ExceptionDemo demo = new ExceptionDemo();
        demo.displayChoices();
    }
}    