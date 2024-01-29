package behavior;

import java.util.Stack;

/**
 * 命令模式
 * @author guoming
 */
public class CommandPatten {

    public static void main(String[] args) {

        Editor editor= new Editor();
        editor.execute(new InsertCommand(0,"Hello"));
        editor.execute(new InsertCommand(5," Word"));
        editor.execute(new DeleteCommand(5,10));

        editor.undo();
        editor.undo();
        editor.undo();
        editor.redo();
        editor.redo();
        editor.redo();


    }

    public interface ICommand
    {

        /**
         * 执行
         */
        void execute(StringBuilder stringBuilder);

        /**
         * 撤销
         */
        void undo(StringBuilder stringBuilder);

    }

    public static  class Command implements ICommand
    {
        @Override
        public String toString() {
            return super.toString();
        }

        @Override
        public void execute(StringBuilder stringBuilder) {

        }

        @Override
        public void undo(StringBuilder stringBuilder) {

        }
    }

    public static class InsertCommand
            extends  Command
            implements ICommand
    {
        private Integer offset;
        private String message;

        public InsertCommand(Integer offset, String message) {
            this.offset= offset;
            this.message=message;
        }

        @Override
        public void execute(StringBuilder stringBuilder ) {

            stringBuilder.insert(offset, message);
        }

        @Override
        public void undo(StringBuilder stringBuilder) {
            stringBuilder.delete(offset,offset+message.length());
        }
    }

    public static class DeleteCommand
        extends  Command
            implements ICommand
    {

        private Integer start;

        private Integer end;

        private String message;

        public DeleteCommand(Integer start, Integer end) {
            this.start=start;
            this.end=end;
        }

        @Override
        public void execute(StringBuilder stringBuilder) {

            this.message = stringBuilder.substring(start,end);
            stringBuilder.delete(start, end);

        }

        @Override
        public void undo(StringBuilder stringBuilder) {
            stringBuilder.insert(start, message);
        }
    }

    public static class Editor {


        private final Stack<ICommand> executeStack=new Stack<>();

        private final Stack<ICommand> undoStack=new Stack<>();

        private final  StringBuilder stringBuilder=new StringBuilder(0);

        public void execute(ICommand command)
        {
            executeStack.push(command);
            command.execute(stringBuilder);

            System.out.println("execute:"+command.toString());
            System.out.println(stringBuilder);
        }

        public void undo()
        {
            if(executeStack.size()<=0)
            {
                return;
            }

            ICommand command = executeStack.pop();

            if (command!=null) {
                undoStack.push(command);

                command.undo(stringBuilder);

                System.out.println("undo:" + command.toString());
                System.out.println(stringBuilder);
            }
        }

        public void redo() {

            if (undoStack.size() <= 0) {
                return;
            }

            ICommand command = undoStack.pop();

            if (command != null) {
                executeStack.push(command);

                command.execute(stringBuilder);

                System.out.println("redo:" + command.toString());
                System.out.println(stringBuilder);
            }


        }

    }

}
