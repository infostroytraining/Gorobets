package Task1.Analyzer.Implementations;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
public class TaskTypeConverter implements IStringConverter<EnumTaskType> {

    @Override
    public EnumTaskType convert(String value) {

        EnumTaskType convertedValue = EnumTaskType.fromString(value);

        if(convertedValue == null) {
            throw new ParameterException("Value " + value + "can not be converted to EnumTaskType. " +
                    "Available values are: frequency, length, duplicates.");
        }
        return convertedValue;

    }
}
