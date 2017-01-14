package redis.dao;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import redis.entity.Address;



@Repository
public class AddressDao {

	@Autowired
	protected RedisTemplate<Serializable, Serializable> redisTemplate;

	public void saveAddress(final Address address) {
		redisTemplate.execute(new RedisCallback<Object>() {

			public Object doInRedis(RedisConnection connection) throws DataAccessException {

				connection.set(redisTemplate.getStringSerializer().serialize(address.getId() + ""),
						redisTemplate.getStringSerializer().serialize(address.getName()));

				return null;
			}

		});
	}

	public Address getAddress(final long id) {
		return redisTemplate.execute(new RedisCallback<Address>() {

			public Address doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(id + "");
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					String name = redisTemplate.getStringSerializer().deserialize(value);
					Address address = new Address();
					address.setName(name);
					address.setId(id);
					return address;
				}
				return null;
			}

		});
	}

}
