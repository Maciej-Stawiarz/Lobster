package ms.lobster.configurations.redis;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.DefaultTyping;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import tools.jackson.databind.jsontype.PolymorphicTypeValidator;

import java.time.Duration;

@EnableCaching
@Configuration
public class RedisCacheConfigs {
	
	@Bean
	public RedisCacheManager cacheConfiguration(RedisConnectionFactory connectionFactory) {
		
		PolymorphicTypeValidator polymorphicTypeValidator = BasicPolymorphicTypeValidator.builder()
				.allowIfBaseType(Object.class)
				.allowIfSubType("ms.lobster")
				.build();
		
		ObjectMapper objectMapper = JsonMapper.builder()
				.activateDefaultTyping(
						polymorphicTypeValidator,
						DefaultTyping.NON_FINAL,
						JsonTypeInfo.As.PROPERTY)
				.build();
		
		GenericJacksonJsonRedisSerializer jsonSerializer = new GenericJacksonJsonRedisSerializer(objectMapper);
		StringRedisSerializer stringSerializer = new StringRedisSerializer();
		
		
		RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration
				.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(60))
				.disableCachingNullValues()
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringSerializer))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer));
		
		return RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(cacheConfiguration)
				.build();
	}
}