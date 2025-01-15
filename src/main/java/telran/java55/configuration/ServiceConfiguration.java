package telran.java55.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

	@Bean // метод выполняется в момент компиляции
	ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper(); // инициализируем
		modelMapper.getConfiguration()
									.setFieldMatchingEnabled(true) //все поля что можно обратиться 
									.setFieldAccessLevel(AccessLevel.PRIVATE) // ходи по приватным полям
									.setMatchingStrategy(MatchingStrategies.STRICT); // по какому кретирию может матчить , смотрит чтоб соответствовали правильности слова, ищет ошибки в словах 
		return modelMapper;
	}
}
