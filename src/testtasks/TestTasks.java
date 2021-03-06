/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testtasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;
import static java.util.stream.Collectors.toMap;

/**
 *
 * @author Gelcen
 */
public class TestTasks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Вывод результата для первого задания
        //int a[] = {3, 0, 2, 11, 0, 4, 2, 0, 11, 2, 1, 9, 2};
        int a[] = {2, 2, 3, 3, 4, 4, 5, 5};
        Task1(a);
        
        System.out.println("***********************************************");
        
        // Вывод результата для второго задания        
        String b1 = "([][[]()])";
        String b2 = "([][]()])";
        System.out.println("Проверка "+b1+ " "+Task2(b1));
        System.out.println("Проверка "+b2+ " "+Task2(b2));
        System.out.println("Проверка "+"]"+ " "+Task2("]"));
        System.out.println("Проверка "+"(("+ " "+Task2("(("));
        
        System.out.println("***********************************************");
        // Вывод результата для третьего задания 
        
        Integer num = 205020109;
        
        System.out.println(num);                      
        num = Task3(num);
        System.out.println(num);                                      
        
        System.out.println("***********************************************");
        
        Task3_1("0100101");
    }
    
    //Решение первого задания
    private static void Task1(int a[])
    {
        //Список с числом и количеством его вхождений
        Map<Integer, Integer> numbers = new HashMap<>();
        
        for (int i=0; i < a.length; i++)
        {
            if (!numbers.containsKey(a[i]))
            {
                numbers.put(a[i], 0);
                for (int j = i; j < a.length; j++)
                {
                    if (a[i]==a[j])
                    {
                        numbers.replace(a[i], numbers.get(a[i])+1);
                    }
                }
            }
        }                        
        
        //Сортировка значений
        Map<Integer, Integer> sorted = numbers
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue())
                .collect(
                    toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, 
                            LinkedHashMap::new));
        
        sorted.forEach((k, v) -> System.out.println(k+":"+v));
    }
    
    //Решение второго задания
    private static boolean Task2(String a)
    {
        //Индексы закрывающей и открывающей части
        //у одинаковых скобок должны быть одинаковы
        //в списках.
        
        //Список для хранения открывающих скобок
        ArrayList<Character> open;
        open = new ArrayList();
        open.add('(');
        open.add('[');
        
        //Список для хранения закрывающих скобок
        ArrayList<Character> close;
        close = new ArrayList();
        close.add(')');
        close.add(']');
        
        Stack<Character> stack;
        stack = new Stack();
        

        char temp;
        for (int i=0; i < a.length(); i++)
        {
            temp = a.charAt(i);
            if (open.contains( temp ))
            {
                stack.push(temp);
            }
            else 
                if (!stack.empty())
                {
                    int openIndex = open.indexOf(stack.peek());
                    int closeIndex = close.indexOf(temp);
                    if (openIndex == closeIndex)
                    {
                        stack.pop();
                    }
                    else 
                    {
                        return false;
                    }
                } 
                else 
                    {
                        return false;
                    }
        }
        
        return stack.empty();
    }
    
    //Решение третьего задания
    private static Integer Task3(Integer n)
    {        
        String s = n.toString();
        
        if (!s.contains("0")) 
        {
            return n;
        }       
        
        int index = s.lastIndexOf('0');        
        
        char[] chars = s.toCharArray();
        chars[index] = '1';
        
        s = String.valueOf(chars);
        
        return Integer.parseInt(s);
    }
    
    //Решение третьего задания для двоичных чисел
    private static void Task3_1(String number)
    {
        //Цикл для валидации числа
        for (int i=0; i < number.length(); i++)
        {
            if (number.charAt(i) == '0' ||
                number.charAt(i) == '1')
            {
                continue;
            }
            else 
            {
                System.out.println("Неккоректная запись двоичного числа");
                return;
            }
        }
        
        int a = Integer.parseInt(number, 2);
        
        System.out.println(Integer.toBinaryString(a));
        
        int b = a + 1;
        
        int result = b | a;
        
        System.out.println(Integer.toBinaryString(result));
    }
    
    //Решение четвёртого задания
    private static void Task4()
    {
        //Запрос работает для БД Oracle
        /*
        
        select id+1,l-id-1
        from
        (select id,lead(id) over(order by id)l
        from testTask)
        where id<l-1;
        
        */

    }
}
