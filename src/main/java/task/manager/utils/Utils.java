package task.manager.utils;

import task.manager.repository.TasksRepository;
import task.manager.repository.TasksRepositoryJson;
import task.manager.service.Instruction;
import task.manager.service.InstructionType;
import task.manager.service.impl.Help;
import task.manager.service.impl.add.Add;
import task.manager.service.impl.delete.Delete;
import task.manager.service.impl.read.List;
import task.manager.service.impl.update.MarkDone;
import task.manager.service.impl.update.MarkInProgress;
import task.manager.service.impl.update.Update;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Utils {
    public static final String BAD_INPUT = "bad input of instruction";
    private final static TasksRepository tasksRepository = new TasksRepositoryJson();
    private static final Map<InstructionType, Instruction> instructionTypeInstructionMap = new HashMap<>(
            Map.of(
                    InstructionType.ADD, new Add(tasksRepository),
                    InstructionType.UPDATE, new Update(tasksRepository),
                    InstructionType.DELETE, new Delete(tasksRepository),
                    InstructionType.MARK_IN_PROGRESS, new MarkInProgress(tasksRepository),
                    InstructionType.MARK_DONE, new MarkDone(tasksRepository),
                    InstructionType.LIST, new List(tasksRepository),
                    InstructionType.HELP, new Help(),
                    InstructionType.ONE_DASH_HELP, new Help(),
                    InstructionType.DASHED_HELP, new Help()
            )
    );

    private static Instruction getInstructionFromArgs(final String[] args) {
        try {
            return instructionTypeInstructionMap.get(
                    Arrays.stream(InstructionType.values()).filter(type -> type.equals(args[0])).findFirst()
                            .orElseThrow());
        } catch (final NoSuchElementException ignored) {
            throw new RuntimeException(ignored);
        }
    }

    public static void validateCorrectNumOfArgs(final int numArgs, final String[] args) {
        if (args.length != numArgs)
            throw new RuntimeException("bad num of arguments, should be one");
    }

    public static Instruction initializeInstructionWithArgs(final String[] args) {
        return getInstructionFromArgs(args).buildWithArgsFactory(Arrays.copyOfRange(args, 1, args.length));
    }
}
