package behavior;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 解释器模式
 * @author guoming
 */
public class InterpreterPatten {

    public static void main(String[] args) {

        IExpression number1=new NumberExpression(10);
        IExpression number2=new NumberExpression(20);
        PlusExpression plusExpression = new PlusExpression(number1, number2);
        System.out.println(plusExpression.interpreter());
    }


    /**
     * @param <T>
     */
    public interface  IExpression<T> {

        /**
         * @return
         */
       T interpreter();

    }


    public static class NumberExpression implements IExpression<Integer>
    {

        private final Integer number;

        public NumberExpression(Integer number)
        {
            this.number=number;
        }

        @Override
        public Integer interpreter() {

            return number;
        }
    }

    public static class VariableExpression implements IExpression<Integer>
    {

        private final Map<String,Integer> variables;
        private final String variableName;

        public VariableExpression(HashMap<String,Integer> variables, String variableName)
        {
            this.variables=variables;
            this.variableName = variableName;
        }
        @Override
        public Integer interpreter() {
            return variables.getOrDefault(variableName, null);
        }
    }

    public static class PlusExpression implements IExpression<Integer>
    {

        private final IExpression<Integer> left;
        private final IExpression<Integer> right;

        public PlusExpression(IExpression<Integer> left, IExpression<Integer> right)
        {
            this.left = left;
            this.right = right;
        }

        @Override
        public Integer interpreter() {
            return left.interpreter()+ right.interpreter();
        }
    }

}
