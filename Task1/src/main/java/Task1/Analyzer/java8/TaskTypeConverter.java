package Task1.Analyzer.java8;

import Task1.Analyzer.Implementations.*;
import Task1.Analyzer.Implementations.EnumTaskType;
import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
public class TaskTypeConverter implements IStringConverter<Task1.Analyzer.Implementations.EnumTaskType> {

    @Override
    public Task1.Analyzer.Implementations.EnumTaskType convert(String value) {

        Task1.Analyzer.Implementations.EnumTaskType convertedValue = EnumTaskType.fromString(value);

        if(convertedValue == null) {
            throw new ParameterException("Value " + value + "can not be converted to EnumTaskType. " +
                    "Available values are: frequency, length, duplicates.");
        }
        return convertedValue;

    }
}
