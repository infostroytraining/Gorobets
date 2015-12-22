package filter;

import java.io.File;
/*3.Реализовать программу, выполняющую поиск файлов по заданному набору параметров в указанном каталоге. Для формирования фильтра поиска использовать шаблон «Цепочка обязанностей».

В качестве параметров поиска использовать имя файла, расширение, диапазон размеров файла, диапазон дат изменения файла.

Типы параметров поиска и ограничения ввести с консоли.

Выглядеть это должно так:

> искать по имени файла ? (0\1)

0

> искать по расширению файла ? (0\1)

1

> Введите расширение

gif

…… и так далее …….*/
public abstract class Filter {

	private Filter nextFilter;

	protected Filter(Filter nextFilter){
		this.nextFilter = nextFilter;
	}

	public boolean accept(File file) {
		boolean result = currentAccept(file);
		if (nextFilter != null && result) {
			return nextFilter.accept(file);
		}
		return result;
	}

	public abstract boolean currentAccept(File file);

}
