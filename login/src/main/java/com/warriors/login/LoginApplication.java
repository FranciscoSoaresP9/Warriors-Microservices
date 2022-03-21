package com.warriors.login;

import com.warriors.login.model.account.Account;
import com.warriors.login.repository.AccountRepository;
import com.warriors.login.restservice.LoginImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@EnableEurekaClient
@SpringBootApplication
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
		LoginImpl login= new LoginImpl(new AccountRepository() {
			@Override
			public Account getAccountByUsername(String username) {
				return null;
			}

			@Override
			public List<Account> findAll() {
				return null;
			}

			@Override
			public List<Account> findAll(Sort sort) {
				return null;
			}

			@Override
			public List<Account> findAllById(Iterable<Integer> integers) {
				return null;
			}

			@Override
			public <S extends Account> List<S> saveAll(Iterable<S> entities) {
				return null;
			}

			@Override
			public void flush() {

			}

			@Override
			public <S extends Account> S saveAndFlush(S entity) {
				return null;
			}

			@Override
			public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
				return null;
			}

			@Override
			public void deleteAllInBatch(Iterable<Account> entities) {

			}

			@Override
			public void deleteAllByIdInBatch(Iterable<Integer> integers) {

			}

			@Override
			public void deleteAllInBatch() {

			}

			@Override
			public Account getOne(Integer integer) {
				return null;
			}

			@Override
			public Account getById(Integer integer) {
				return null;
			}

			@Override
			public <S extends Account> List<S> findAll(Example<S> example) {
				return null;
			}

			@Override
			public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
				return null;
			}

			@Override
			public Page<Account> findAll(Pageable pageable) {
				return null;
			}

			@Override
			public <S extends Account> S save(S entity) {
				return null;
			}

			@Override
			public Optional<Account> findById(Integer integer) {
				return Optional.empty();
			}

			@Override
			public boolean existsById(Integer integer) {
				return false;
			}

			@Override
			public long count() {
				return 0;
			}

			@Override
			public void deleteById(Integer integer) {

			}

			@Override
			public void delete(Account entity) {

			}

			@Override
			public void deleteAllById(Iterable<? extends Integer> integers) {

			}

			@Override
			public void deleteAll(Iterable<? extends Account> entities) {

			}

			@Override
			public void deleteAll() {

			}

			@Override
			public <S extends Account> Optional<S> findOne(Example<S> example) {
				return Optional.empty();
			}

			@Override
			public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
				return null;
			}

			@Override
			public <S extends Account> long count(Example<S> example) {
				return 0;
			}

			@Override
			public <S extends Account> boolean exists(Example<S> example) {
				return false;
			}

			@Override
			public <S extends Account, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
				return null;
			}
		})
	}
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return  new RestTemplate();
	}

}
