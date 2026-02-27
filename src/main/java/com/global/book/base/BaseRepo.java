package com.global.book.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@NoRepositoryBean
public interface BaseRepo <T extends BaseEntity<ID> ,ID extends Number> extends JpaRepository<T, ID> {
	
	@Modifying 
	@Transactional
	@Query("update #{#entityName} t Set t.statusCode =:statusCode WHERE t.id =:id")
	void updateEnitiy(@Param("id") ID id,@Param ("statusCode") String statusCode );
}
